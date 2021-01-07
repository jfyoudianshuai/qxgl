package com.duyi.qxgl.controller;

import com.duyi.qxgl.domain.Fn;
import com.duyi.qxgl.domain.Page;
import com.duyi.qxgl.domain.User;
import com.duyi.qxgl.service.FnService;
import com.duyi.qxgl.service.UserService;
import com.duyi.qxgl.util.MySpring;
import mymvc.ModelAndView;
import mymvc.RequestParam;
import mymvc.ResponseBody;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserController {
    private UserService service= MySpring.getBean("com.duyi.qxgl.service.UserService");
    private FnService fservice= MySpring.getBean("com.duyi.qxgl.service.FnService");
    private final static  Integer USER_ROWS=10;
    public String login(HttpServletRequest request,@RequestParam("uname") String uname, @RequestParam("upass")String upass){
        User user=service.login(uname,upass);
        if(user==null){
            //没找到 说明密码错误 返回登录页面
            return "index.jsp?status=9";
        }
        HttpSession session=request.getSession();
        session.setAttribute("loginuser",user);
        List<Fn> menus=fservice.findMenuByUser(user.getUid());
        List<Fn> listbtn=fservice.findBtnByUser(user.getUid());
        Map buttons=new HashMap<String,Fn>();
        for(Fn fn:listbtn){
            buttons.put(fn.getFname(),fn);
        }
        session.setAttribute("menus",menus);
        session.setAttribute("buttons",buttons);
        return "main.jsp";
    }
    public ModelAndView list(@RequestParam("page") Integer page){
        ModelAndView mv=new ModelAndView();
        Page userPage=service.selectUserList(page,USER_ROWS);
        mv.addAttribute("page",userPage);
        mv.setViewName("user.jsp");
        return mv;
    }
    public String exit(HttpServletRequest request){
        HttpSession session=request.getSession();
        session.invalidate();//清空里面的
        return "index.jsp";
    }
    public ModelAndView add(User user){
        service.insertUser(user);
        return list(1);
    }
    public ModelAndView delete(@RequestParam("uid") Integer uid){
        service.deleteUser(uid);
        return list(1);
    }
    public ModelAndView edit(@RequestParam("uid") Integer uid){
        User user=service.selectOneUser(uid);
        ModelAndView mv=new ModelAndView();
        mv.addAttribute("user",user);
        mv.setViewName("edituser.jsp");
        return mv;
    }
    public ModelAndView update(User user){
        service.updateUser(user);
        return list(1);
    }
    @ResponseBody
    public String saves(HttpServletRequest request) throws FileUploadException, IOException {
        DiskFileItemFactory factory=new DiskFileItemFactory();
        ServletFileUpload fileUpload=new ServletFileUpload(factory);
        List<FileItem> fileItems=fileUpload.parseRequest(request);
        //==================
        FileItem fi=fileItems.get(0);
        InputStream is=fi.getInputStream();

        //workbook就相当与excel文件
        Workbook workbook= WorkbookFactory.create(is);
        //获取第一个表
        Sheet sheet=workbook.getSheetAt(0);
        for(int i=1;i<=sheet.getLastRowNum();i++){
            //获取每一行
            Row row=sheet.getRow(i);
            //获取每一行中的前五个单元格
            Cell cel1=row.getCell(0);
            Cell cel2=row.getCell(1);
            Cell cel3=row.getCell(2);
            Cell cel4=row.getCell(3);
            Cell cel5=row.getCell(4);

            //获取单元格中的内容
            //如果单元格中的内容是数字形式的 获得的字符串是浮点型的
            String uname=cel1.toString();
            String upass=cel2.toString().replace(".0","");//123.0
            String truename=cel3.toString();
            String sex=cel4.toString();
            int age=Integer.parseInt(cel5.toString().replace(".0",""));//20.0
            User user=new User();
            user.setUname(uname);
            user.setUpass(upass);
            user.setTruename(truename);
            user.setSex(sex);
            user.setAge(age);
            service.insertUser(user);
        }
        return "导入成功";
    }
    public  void down(HttpServletResponse response) throws IOException {
        InputStream is=Thread.currentThread().getContextClassLoader().getResourceAsStream("users.xlsx");
        //读取内容 放到bs数组中
        byte[] bs=IOUtils.toByteArray(is);

        //
        response.setHeader("content-disposition","attachment;filename=users.xlsx");
        response.getOutputStream().write(bs);
    }
}
