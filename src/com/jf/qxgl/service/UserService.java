package com.duyi.qxgl.service;

import com.duyi.qxgl.dao.UserDao;
import com.duyi.qxgl.domain.Fn;
import com.duyi.qxgl.domain.Page;
import com.duyi.qxgl.domain.User;
import com.duyi.qxgl.util.MySpring;

import java.util.List;

public class UserService {
    private UserDao dao= MySpring.getBean("com.duyi.qxgl.dao.UserDao");
    public User login(String uname, String upass){
        return dao.selectOneUser(uname,upass);
    }

    /**
     *
     * @param page 查看第几页
     * @param rows 查看多少条
     * @return
     */
    public Page selectUserList(Integer page,Integer rows){
       if(page==null || page <0){
           page=1;
       }
       long totle=dao.selectCount();
       int max=(int)Math.ceil(1.0*totle/10);
       if(page>max){
           page=max;
       }
       int start=(page-1)*rows;
       List<User> list=dao.selectUserList(start,rows);
       return new Page(page,max,list);
    }
    public void insertUser(User user){
        dao.insertUser(user);
    }
    public void deleteUser(Integer uid){
        dao.deleteUser(uid);
    }
    public User selectOneUser(Integer uid){
        return dao.selectUser(uid);
    }
    public void updateUser(User user){
        dao.updateUser(user);
    }

}
