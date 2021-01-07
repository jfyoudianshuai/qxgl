package jdbc;

import jdbc.pool.ConnectionPool;

import java.sql.*;

 abstract class JdbcAll {
    private String classname;
    private String url;
    private String username;
    private String password;

    public JdbcAll(String classname, String url, String username, String password,ConnectionPool pool) {
        this.classname = classname;
        this.url = url;
        this.username = username;
        this.password = password;
        this.pool=pool;
    }

    private Connection conn;
    protected PreparedStatement pstat;
    private ResultSet rs;
    private ConnectionPool pool;
    public Object all(String sql,Object[] con){
        try {
            one();
            two();
            three(sql,con);
           Object result= four();
           return result;
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                five();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    private void one() throws ClassNotFoundException {
       // Class.forName(classname);
    }
    private void two() throws SQLException, InterruptedException {
        //conn= DriverManager.getConnection(url,username,password);
        conn=pool.getConnection();
    }
    private void three(String sql,Object[] con) throws SQLException {
        pstat=conn.prepareStatement(sql);
        if(con!=null) {
            for (int i = 0; i < con.length; i++) {
                pstat.setObject(i + 1, con[i]);
            }
        }
    }
    protected abstract Object four() throws SQLException;

    private void five() throws SQLException {
        if(rs!=null) {
            rs.close();
        }
        if(pstat!=null){
            pstat.close();
        }
        if(conn!=null){
            conn.close();
        }
    }

}
