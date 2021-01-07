package jdbc;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class JdbcSessionHander {
    public static SqlAndParam handle(String sql,Object param){
        try{
            List<String> paramList=new ArrayList<>();
            List<Object> paramarray=new ArrayList<>();
            while(true){
                int i1=sql.indexOf("#{");
                int i2=sql.indexOf("}");
                if(i1==-1 || i2== -1 || i1>i2){
                    break;
                }
                String fieldName=sql.substring(i1+2,i2).toLowerCase();
                paramList.add(fieldName);
                if(i2==sql.length()-1){
                    sql=sql.substring(0,i1)+"?";
                    break;
                }
                sql=sql.substring(0,i1)+"?"+sql.substring(i2+1);
            }
            if(param==null){
                SqlAndParam sap=new SqlAndParam(sql,paramarray);
                return sap;

            }
            Class clazz=param.getClass();
            if(clazz==int.class || clazz==Integer.class || clazz==double.class ||
                    clazz==Double.class || clazz== Float.class || clazz==float.class
                    || clazz==String.class){
                paramarray.add(param);
            }else if (clazz == Map.class || clazz == HashMap.class) {
                for (String key : paramList) {
                    //key = cno .    map.get("cno");
                    Map map = (Map) param;
                    Object value = map.get(key);
                    paramarray.add(value);
                }
            }
            else {
                for(String fieldName: paramList){
                    //cname
                    String methodName="get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
                    Method method=clazz.getMethod(methodName);
                    Object fieldValue=method.invoke(param);
                    paramarray.add(fieldValue);
                }
            }
            SqlAndParam sap=new SqlAndParam(sql,paramarray);
            return sap;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
