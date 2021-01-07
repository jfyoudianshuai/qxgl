package com.duyi.qxgl.service;

import com.duyi.qxgl.dao.FnDao;
import com.duyi.qxgl.dao.RoleDao;
import com.duyi.qxgl.domain.Fn;
import com.duyi.qxgl.util.MySpring;
import com.duyi.qxgl.util.MysqlFactory;

import java.util.ArrayList;
import java.util.List;

public class FnService {
    private  FnDao dao= MysqlFactory.getSession().createinter(FnDao.class);
    private RoleDao rdao= MySpring.getBean("com.duyi.qxgl.dao.RoleDao");
    public List<Fn> findList(){
        List<Fn> fns=dao.findList();
        return sort(fns,-1);
    }
    //让查找的菜单功能自己排序
    private List<Fn> sort(List<Fn> fnList,int pid){
        List<Fn> fns=new ArrayList<>();
        for(Fn fn:fnList){
            if(fn.getPid()==pid){
                List<Fn> list=sort(fnList,fn.getFid());
                fn.setList(list);
                fns.add(fn);
            }
        }

        return fns;
    }

    public void insert(Fn fn) {
        dao.insert(fn);
    }

    public void setFns(Integer rid, String[] fidArray) {
        dao.deleteRelation(rid);
        for(String fids:fidArray){
            Integer fid=Integer.parseInt(fids);
            rdao.saveRelation(rid,fid);
        }
    }

    public List<Integer> findList(Integer rid) {
        return dao.findList(rid);
    }

    public List<Fn> findMenuByUser(Integer uid) {
        List<Fn> menus=dao.finMenuByUser(uid);
        return sort(menus,-1);
    }
    public List<Fn> findBtnByUser(Integer uid){
        List<Fn> btns=dao.finBtnByUser(uid);
        return btns;
    }
}
