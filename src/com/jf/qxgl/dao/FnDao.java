package com.duyi.qxgl.dao;

import com.duyi.qxgl.domain.Fn;
import jdbc.annotion.Delete;
import jdbc.annotion.Insert;
import jdbc.annotion.Select;

import java.util.List;

public interface FnDao {
    @Select("select * from t_fn where del=1")
    public List<Fn> findList();
    @Insert("insert into t_fn values(null,#{fname},#{fhref},#{frag},#{pid},1,now(),#{yl1},#{yl2})")
    void insert(Fn fn);
    @Delete("delete from t_role_fn where rid=#{rid}")
    void deleteRelation(Integer rid);
    @Select("select fid from t_role_fn where rid=#{rid} ")
    List<Integer> findList(Integer rid);
    //1. (select rid from t_user_role where uid=#{uid})
    //2. (select fid from t_role_fn where rid in () )
    //3. (select * from fn where frag=1 and fid in(select fid from t_role_fn where rid in (select rid from t_user_role where uid=#{uid}))
    @Select("select * from t_fn where frag=1 and fid in(select fid from t_role_fn where rid in (select rid from t_user_role where uid=#{uid}))")
    List<Fn> finMenuByUser(Integer uid);

    @Select("select * from t_fn where frag=2 and fid in(select fid from t_role_fn where rid in (select rid from t_user_role where uid=#{uid}))")
    List<Fn> finBtnByUser(Integer uid);
}
