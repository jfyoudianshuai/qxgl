package jdbc;

import jdbc.annotion.Delete;
import jdbc.annotion.Insert;
import jdbc.annotion.Select;
import jdbc.annotion.Update;
import jdbc.pool.ConnectionPool;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.List;
import java.util.Map;

public class JdbcSession {
    private String className;
    private String url;
    private String username;
    private String password;
    private JdbcUtil jdbcUtil;
    private ConnectionPool pool;

    public JdbcSession(String className, String url, String username, String password,ConnectionPool pool) {
        jdbcUtil=new JdbcUtil(className,url,username,password,pool);
    }
    public void setPool(ConnectionPool pool){
        this.pool=pool;
    }

    /**
     * insert into t_car values(#{cname},#{cprice});
     * select * from t_car where cno=#{cno}
     * @param sql
     * @return
     */
    public int insert(String sql,Object param){
        SqlAndParam sap=JdbcSessionHander.handle(sql,param);
        return jdbcUtil.insert(sap.getSql(),sap.getList().toArray());
    }
    public int insert(String sql){
        return jdbcUtil.insert(sql);
    }
    public int update(String sql,Object param){
        SqlAndParam sap=JdbcSessionHander.handle(sql,param);
        return jdbcUtil.update(sap.getSql(),sap.getList().toArray());
    }
    public int update(String sql){
        return jdbcUtil.update(sql);
    }
    public int delete(String sql,Object param){
        SqlAndParam sap=JdbcSessionHander.handle(sql,param);
        return jdbcUtil.delete(sap.getSql(),sap.getList().toArray());
    }
    public int delete(String sql){
        return jdbcUtil.delete(sql);
    }
    public List<Map<String,Object>> selectListMap(String sql, Object param){
        SqlAndParam sap=JdbcSessionHander.handle(sql,param);
        return jdbcUtil.selectListMap(sap.getSql(),sap.getList().toArray());
    }
    public List<Map<String,Object>> selectListMap(String sql){
       return jdbcUtil.selectListMap(sql);
    }
    public Map<String,Object> selectMap(String sql,Object param){
        SqlAndParam sap=JdbcSessionHander.handle(sql,param);
        return jdbcUtil.selectMap(sap.getSql(),sap.getList().toArray());
    }
    public Map<String,Object> selectMap(String sql){
        return jdbcUtil.selectMap(sql);
    }
    public <T>List<T> selectList(String sql,Object param,Class<T> type){
        SqlAndParam sap=JdbcSessionHander.handle(sql,param);
        return jdbcUtil.selectList(sap.getSql(),type,sap.getList().toArray());
    }
    public <T>List<T> selectList(String sql,Class<T> type) {
        return jdbcUtil.selectList(sql,type);
    }
    public <T> T selectOne(String sql,Object param,Class<T> type){
        SqlAndParam sap=JdbcSessionHander.handle(sql,param);
        return jdbcUtil.selectOne(sap.getSql(),type,sap.getList().toArray());
    }
    public <T> T selectOne(String sql,Class<T> type) {
        return jdbcUtil.selectOne(sql,type);
    }

    public <T> T createinter(Class<T> interclass){
        return (T)Proxy.newProxyInstance(
                interclass.getClassLoader(),
                new Class[]{interclass},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Annotation ann=method.getAnnotations()[0];
                        Class aclass=ann.getClass();
                        String sql= (String) aclass.getMethod("value").invoke(ann);
                        Object param=null;
                        Object result=null;
                        if(args!=null && args.length!=0){
                            param=args[0];
                        }
                        if(ann.annotationType()== Insert.class){
                            result=insert(sql,param);
                        }else if(ann.annotationType()== Update.class){
                            result=update(sql,param);
                        }else if(ann.annotationType()== Delete.class){
                            result=delete(sql,param);
                        }else if(ann.annotationType()== Select.class){
                            Class rt=method.getReturnType();
                            if(rt ==List.class){
                                Type returnvalue=method.getGenericReturnType();
                                ParameterizedType pt= (ParameterizedType) returnvalue;
                                Class fx= (Class) pt.getActualTypeArguments()[0];
                                if(fx==Map.class){
                                    result=selectListMap(sql,param);
                                }else {
                                    result=selectList(sql,param,fx);
                                }
                            }else {
                                if(rt==Map.class){
                                    result=selectMap(sql,param);
                                }else {
                                    result=selectOne(sql,param,rt);
                                }
                            }
                        }
                        return result;
                    }
                }
        );
    }


    }
