package com.duyi.qxgl.service;

import com.duyi.qxgl.dao.RoleDao;
import com.duyi.qxgl.domain.Page;
import com.duyi.qxgl.domain.Role;
import com.duyi.qxgl.util.MySpring;

import java.util.List;

public class RoleService {
    private RoleDao dao= MySpring.getBean("com.duyi.qxgl.dao.RoleDao");
    public long totle(){
        return dao.findTotle();
    }
    public Page findList(Integer page, Integer rows){
        if(page<1){
            page=1;
        }
        long totle=totle();
        int max= (int) (totle%rows==0?totle/rows:totle/rows+1);
        if(page>max){
            page=max;
        }
        int start=rows*(page-1);
        List<Role> list=dao.findList(start,rows);
        return new Page(page,max,list);
    }

    public void delete(Integer rid) {
        dao.delete(rid);
    }

    public Role findOne(Integer rid) {
        return dao.finOne(rid);
    }

    public void update(Role role) {
        dao.update(role);
    }

    public List<Role> allList() {
        return dao.allList();
    }

    public List<Role> findRoleForUid(Integer uid) {
        return dao.finRoleForUid(uid);

    }

    public void addRoleForUser(Integer uid, String[] rids) {
        dao.deleteRole(uid);
        for(String rd:rids){
            Integer rid=Integer.parseInt(rd);
            dao.addRoleForUser(uid,rid);
        }
    }
}
