package com.duyi.qxgl.controller;

import com.alibaba.fastjson.JSONObject;
import com.duyi.qxgl.domain.Page;
import com.duyi.qxgl.domain.Role;
import com.duyi.qxgl.service.RoleService;
import com.duyi.qxgl.util.MySpring;
import mymvc.ModelAndView;
import mymvc.RequestParam;
import mymvc.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class RoleController {
    private RoleService service= MySpring.getBean("com.duyi.qxgl.service.RoleService");
    private final static Integer ROLE_NUM=2;
    @ResponseBody
    public Page list(@RequestParam("page")Integer page){
        Page p=service.findList(page,ROLE_NUM);
        return p;
    }
    public void delete(@RequestParam("rid") Integer rid){
        service.delete(rid);
    }
    public ModelAndView edit(@RequestParam("rid")Integer rid){
        Role role=service.findOne(rid);
        ModelAndView mv=new ModelAndView();
        mv.addAttribute("role",role);
        mv.setViewName("editrole.jsp");
        return mv;
    }
    public String update(Role role){
        service.update(role);
        return "role.jsp";
    }
    public ModelAndView setRole(@RequestParam("uid") Integer uid,@RequestParam("truename")String truename){
        List<Role> list=service.allList();
        List<Role> ulist=service.findRoleForUid(uid);
        ModelAndView mv=new ModelAndView();
        mv.addAttribute("ulist",ulist);
        mv.addAttribute("list",list);
        mv.addAttribute("uid",uid);
        mv.addAttribute("truename",truename);
        mv.setViewName("setrole.jsp");
        return mv;
    }
    @ResponseBody
    public String addRoleForUser(HttpServletRequest request,@RequestParam("uid") Integer uid){
        String[] rids=request.getParameterValues("role");
        service.addRoleForUser(uid,rids);
        return "保存成功";
    }
}
