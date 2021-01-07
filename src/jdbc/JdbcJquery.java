package jdbc;

import jdbc.pool.ConnectionPool;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

 class JdbcJquery extends JdbcAll{

    public JdbcJquery(String classname, String url, String username, String password, ConnectionPool pool) {
        super(classname, url, username, password,pool);
    }

    @Override
    protected List<Map<String,Object>> four() throws SQLException {
        ResultSet rs= pstat.executeQuery();
        List<Map<String,Object>> rows=new ArrayList<>();
        while(rs.next()){
            Map<String,Object> row=new HashMap<>();
            for(int i=1;i<=rs.getMetaData().getColumnCount();i++){
                String key=rs.getMetaData().getColumnName(i);
                Object value=rs.getObject(i);
                row.put(key.toLowerCase(),value);
            }
            rows.add(row);
        }
        return rows;
    }
}
