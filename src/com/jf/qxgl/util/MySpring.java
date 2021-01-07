package com.duyi.qxgl.util;

import java.util.HashMap;

public class MySpring {
    private static HashMap<String,Object> beanMap=new HashMap<>();
    public static <T>T getBean(String classname){
        T obj= (T) beanMap.get(classname);
        if(obj==null){
            synchronized ("jf"){
                if(obj==null){
                    try {
                        obj=(T)Class.forName(classname).getConstructor().newInstance();
                        beanMap.put(classname,obj);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return obj;
    }
}
