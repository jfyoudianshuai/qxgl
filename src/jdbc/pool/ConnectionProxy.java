package jdbc.pool;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionProxy extends ConnectionAbstract{
     boolean useFalg=false;
     boolean closeFalg=false;//false 不关闭 释放  true关闭
    public ConnectionProxy(Connection conn){
        super.conn=conn;
    }
    public void close() throws SQLException {
        if(closeFalg){
            conn.close();
        }else{
            useFalg=false;
        }
    }
}
