package com.duyi.qxgl.dao;

import com.duyi.qxgl.domain.Role;
import com.duyi.qxgl.util.MysqlFactory;
import jdbc.JdbcSession;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoleDao {

    public List<Role> findList(Integer page, Integer rows){
        String sql="select * from t_role where del=1 limit #{page},#{rows}";
        Map<String,Integer> map=new HashMap<>();
        map.put("page",page);
        map.put("rows",rows);
        JdbcSession session= MysqlFactory.getSession();
        return session.selectList(sql,map,Role.class);
    }

    public long findTotle() {
        String sql="select count(*) from t_role where del=1";
        JdbcSession session= MysqlFactory.getSession();
        return session.selectOne(sql,Long.class);
    }

    public void delete(Integer rid) {
        String sql="update t_role set del=2 where rid=#{rid}";
        JdbcSession session=MysqlFactory.getSession();
        session.update(sql,rid);
    }

    public Role finOne(Integer rid) {
        String sql="select * from t_role where rid=#{rid}";
        return MysqlFactory.getSession().selectOne(sql,rid,Role.class);
    }

    public void update(Role role) {
        String sql="update t_role set rname=#{rname} where rid=#{rid}";
        MysqlFactory.getSession().update(sql,role);
    }

    public List<Role> allList() {
        String sql="select * from t_role where del=1";
        return MysqlFactory.getSession().selectList(sql,Role.class);
    }

    public List<Role> finRoleForUid(Integer uid) {
        String sql="select * from t_user_role where uid=#{uid}";
        return MysqlFactory.getSession().selectList(sql,uid,Role.class);
    }

    public void addRoleForUser(Integer uid, Integer rid) {
        Map map=new HashMap<Integer,Integer>();
        map.put("uid",uid);
        map.put("rid",rid);
        String sql="insert into t_user_role values(#{uid},#{rid})";
        MysqlFactory.getSession().insert(sql,map);
    }

    public void deleteRole(Integer uid) {
        String sql="delete from t_user_role where uid=#{uid}";
        MysqlFactory.getSession().delete(sql,uid);
    }

    public void saveRelation(Integer rid, Integer fid) {
        Map map=new HashMap<Integer,Integer>();
        map.put("rid",rid);
        map.put("fid",fid);
        String sql="insert into t_role_fn values(#{rid},#{fid})";
        MysqlFactory.getSession().insert(sql,map);
    }
}
