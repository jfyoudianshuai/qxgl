package com.duyi.qxgl.util;

import jdbc.JdbcFactory;
import jdbc.JdbcSession;

public class MysqlFactory {
    private static JdbcFactory factory=new JdbcFactory("mysql.properties");
    private MysqlFactory(){

    }
    public static JdbcFactory getFactory(){
        return factory;
    }
    public static JdbcSession getSession(){
        return factory.getJdbcSession();
    }
}
