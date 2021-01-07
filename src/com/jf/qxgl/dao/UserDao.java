package com.duyi.qxgl.dao;
import com.duyi.qxgl.domain.User;
import com.duyi.qxgl.util.MysqlFactory;
import jdbc.JdbcSession;

import java.util.HashMap;
import java.util.List;

public class UserDao {
    private JdbcSession session= MysqlFactory.getSession();
    public User selectOneUser(String uname,String upass){
        String sql="select * from t_user where del=1 and uname=#{uname} and upass=#{upass}";
        HashMap<String,String> map=new HashMap<>();
        map.put("uname",uname);
        map.put("upass",upass);
        return session.selectOne(sql,map,User.class);
    }

    public List<User> selectUserList(int start,int rows) {
        String sql="select * from t_user where del=1 limit #{start},#{rows}";
        HashMap<String,Integer> map=new HashMap<>();
        map.put("start",start);
        map.put("rows",rows);
        List<User> userList=session.selectList(sql,map,User.class);
        return userList;
    }
    public Long selectCount(){
        String sql="select count(*) from t_user where del=1 ";
        return session.selectOne(sql,Long.class);
    }
    public void insertUser(User user){
        String sql="insert into t_user values(null,#{uname},#{upass},#{truename},#{sex},#{age},1,now(),#{yl1},#{yl2})";
        session.insert(sql,user);
    }
    public void deleteUser(Integer uid){
        String sql="update t_user set del=2 where uid=#{uid}";
        session.update(sql,uid);
    }

    public User selectUser(Integer uid) {
        String sql="select * from t_user where uid=#{uid}";
        return session.selectOne(sql,uid,User.class);
    }

    public void updateUser(User user) {
        String sql="update t_user set uname=#{uname}, truename=#{truename}, age=#{age}, sex=#{sex},yl1=#{yl1},yl2=#{yl2} where uid=#{uid} ";
        session.update(sql,user);
    }
}
