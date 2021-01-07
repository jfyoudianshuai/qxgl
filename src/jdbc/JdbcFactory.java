package jdbc;

import jdbc.pool.ConnectionPool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcFactory {
    private String className;
    private String url;
    private String username;
    private String password;
    private ConnectionPool pool;
    public JdbcFactory(){
        this("db.properties");
    }
    public JdbcFactory(String fileName){
        String path=Thread.currentThread().getContextClassLoader()
                .getResource(fileName).getPath();
        File file=new File(path);
        this.readFile(file);
    }

    public JdbcFactory(File file) {
        this.readFile(file);
    }
    private void readFile(File file){
        try {
            Properties properties = new Properties();
            InputStream is = new FileInputStream(file);
            properties.load(is);
            className= (String) properties.get("driver");
            url= (String) properties.get("url");
            username= (String) properties.get("username");
            password= (String) properties.get("password");
            this.createPool();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    private void createPool(){
        try {
            pool=new ConnectionPool(className,url,username,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  JdbcUtil getJdbcUtil(){
        return  new JdbcUtil(className,url,username,password,pool);

    }
    public JdbcSession getJdbcSession(){
        JdbcSession session= new JdbcSession(className,url,username,password,pool);
        return session;
    }
}
