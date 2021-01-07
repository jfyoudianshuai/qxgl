package jdbc;

import java.util.List;

 class SqlAndParam {
    private String sql;
    private List<Object> list;

    public SqlAndParam() {
    }

    public SqlAndParam(String sql, List<Object> list) {
        this.sql = sql;
        this.list = list;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }
}
