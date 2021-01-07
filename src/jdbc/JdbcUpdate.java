package jdbc;

import jdbc.pool.ConnectionPool;

import java.sql.SQLException;

class JdbcUpdate extends JdbcAll{
    public JdbcUpdate(String classname, String url, String username, String password, ConnectionPool pool) {
        super(classname, url, username, password,pool);
    }

    @Override
    protected Object four() throws SQLException {
        return pstat.executeUpdate();
    }
}
