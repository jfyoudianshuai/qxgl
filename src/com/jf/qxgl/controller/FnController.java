package com.duyi.qxgl.controller;

import com.duyi.qxgl.domain.Fn;
import com.duyi.qxgl.service.FnService;
import com.duyi.qxgl.util.MySpring;
import mymvc.RequestParam;
import mymvc.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FnController {
    private FnService service= MySpring.getBean("com.duyi.qxgl.service.FnService");
    @ResponseBody
    public List<Fn> list(){
        List<Fn> list=service.findList();
        return list;
    }
    public void insert(Fn fn){
        service.insert(fn);
    }
    @ResponseBody
    public String setFns(@RequestParam("rid") Integer rid, HttpServletRequest request){
        String[] fidArray=request.getParameterValues("fid");
        service.setFns(rid,fidArray);
        return "保存成功";
    }
    @ResponseBody
    public List<Integer> findFidsRole(@RequestParam("rid") Integer rid){
        List<Integer> fids=service.findList(rid);
        return fids;
    }
}
