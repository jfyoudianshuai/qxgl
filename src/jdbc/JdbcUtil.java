package jdbc;


import jdbc.pool.ConnectionPool;

import java.lang.reflect.Method;
import java.util.*;

public class JdbcUtil {
    private String classname;
    private String url;
    private String username;
    private String password;
    protected ConnectionPool pool;

    public JdbcUtil(String classname, String url, String username, String password,ConnectionPool pool) {
        this.classname = classname;
        this.url = url;
        this.username = username;
        this.password = password;
        this.pool=pool;
    }

    public void setPool(ConnectionPool pool) {
        this.pool = pool;
    }

    private int UPDATE(String sql, Object...con){
        JdbcUpdate ju=new JdbcUpdate(classname,url,username,password,pool);
            int result=0;
        try {
            result=(int)ju.all(sql,con);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public int insert(String sql,Object...con){
        if(sql.trim().toLowerCase().startsWith("insert")){
            return this.UPDATE(sql,con);
        }else {
            throw new SqlFormatException("not a insert :"+sql);
        }
    }
    public int update(String sql,Object...con){
        if(sql.trim().toLowerCase().startsWith("update")){
            return this.UPDATE(sql,con);
        }else {
            throw new SqlFormatException("not a update"+sql);
        }
    }
    public int delete(String sql,Object...con){
        if(sql.trim().toLowerCase().startsWith("delete")){
            return this.UPDATE(sql,con);
        }else {
            throw new SqlFormatException("not a delete"+sql);
        }
    }
    public List<Map<String,Object>> selectListMap(String sql,Object...param){
        if(sql.trim().substring(0,6).equalsIgnoreCase("select")) {
            JdbcJquery jj = new JdbcJquery(classname, url, username, password,pool);
            List<Map<String, Object>> rows = (List<Map<String, Object>>) jj.all(sql, param);
            //把row组成用户想要的对象  这得传进来一个规
            return rows;
        }else{
            throw new SqlFormatException("not a select"+sql);
        }
    }
    public Map<String,Object> selectMap(String sql,Object...param){
        List<Map<String,Object>> list=this.selectListMap(sql,param);
        if(list==null || list.size()==0){
            return null;
        }else if(list.size()==1){
            return list.get(0);
        }
        return null;
    }
    public <T> List<T> selectList(String sql,RowMapping mapping, Object...param){
        if(sql.trim().substring(0,6).equalsIgnoreCase("select")) {
            JdbcJquery jj = new JdbcJquery(classname, url, username, password,pool);
            List<T> list = new ArrayList<>();
            List<Map<String, Object>> rows = (List<Map<String, Object>>) jj.all(sql, param);
            //把row组成用户想要的对象  这得传进来一个规则
            for (Map<String, Object> row : rows) {
                T t = mapping.mapping(row);
                list.add(t);
            }
            return list;
        }else{
            throw new SqlFormatException("not a select"+sql);
        }

    }
    public <T>T selectOne(String sql,RowMapping mapping,Object...param){
        List<T> list=this.selectList(sql,mapping,param);
        if(list!=null || list.size()!=0){
            return null;
        }else if(list.size()==1){
            return list.get(0);
        }
        return null;
    }
    public <T> List<T> selectList(String sql,Class<T> type,Object...param){
        List<Map<String,Object>> rows=this.selectListMap(sql,param);
        List<T> list=new ArrayList<>();

        try {
            for(Map<String,Object> row:rows) {
                Object obj = null;
                if(type==int.class || type==Integer.class){
                    Collection collection=  row.values();
                    for(Object o:collection){
                         obj= ((Number) o).intValue();
                    }
                }else if(type==long.class || type==Long.class){
                    Collection collection=  row.values();
                    for(Object o:collection){
                        obj= ((Number) o).longValue();
                    }
                }else if(type==double.class || type==Double.class){
                    Collection collection=  row.values();
                    for(Object o:collection){
                        obj= ((Number) o).intValue();
                    }
                }else if(type==String.class){
                    Collection collection=  row.values();
                    for(Object o:collection){
                        obj= (String)o;
                    }
                }else {
                    Method[] methods = type.getMethods();
                    obj= type.getConstructor().newInstance();
                    for (Method method : methods) {
                        String methodName = method.getName();
                        if (methodName.substring(0, 3).equals("set")) {
                            String fieldName = methodName.substring(3).toLowerCase();
                            Object value = row.get(fieldName);
                            if (value == null) {
                                continue;
                            } else {
                                Class paramType = method.getParameterTypes()[0];
                                if (paramType == int.class || paramType == Integer.class) {
                                    method.invoke(obj, ((Number)value).intValue());
                                } else if (paramType == long.class || paramType == Long.class) {
                                    method.invoke(obj, ((Number)value).longValue());
                                } else if (paramType == double.class || paramType == Double.class) {
                                    method.invoke(obj, ((Number)value).doubleValue());
                                } else if (paramType == String.class) {
                                    method.invoke(obj, (String)value);
                                }
                            }
                        }
                    }
                }
                list.add((T)obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public <T>T selectOne(String sql,Class<T> type,Object...param){
        List<T> list=this.selectList(sql,type,param);
        if(list==null || list.size()==0){
            return null;
        }else if(list.size()==1){
            return list.get(0);
        }
        return null;
    }
}
