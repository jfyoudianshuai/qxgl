package jdbc.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
    private List<ConnectionProxy> pool;
    public ConnectionPool(String driver,String url,String username,String password) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        pool=new ArrayList(10);
        for(int i=0;i<10;i++){
            Connection conn= DriverManager.getConnection(url,username,password);
            ConnectionProxy proxy=new ConnectionProxy(conn);
            pool.add(proxy);
        }
    }
    public Connection getConnection() throws InterruptedException {
        int wait_time=0;
        head:while(true) {
            for (ConnectionProxy proxy : pool) {
                if (proxy.useFalg == false) {
                    synchronized (proxy) {
                        if(proxy.useFalg ==false) {
                            proxy.useFalg = true;
                            return proxy;
                        }else{
                            continue;
                        }
                    }
                }
            }
            Thread.sleep(100);
            wait_time+=100;
            if(wait_time==2000){
                throw new ConnectionMoreException("conn is busy");
            }
        }
    }
}
