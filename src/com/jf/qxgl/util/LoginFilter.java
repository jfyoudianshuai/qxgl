package com.duyi.qxgl.util;

import com.duyi.qxgl.domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter({"*.do","*.jsp"})
public class LoginFilter implements Filter{

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        String url=request.getRequestURI();
        String method=request.getParameter("method");
        if(
                "/qxgl/".equals(url) ||
                        url.indexOf("index.jsp") != -1 ||  //url中包含index.jsp，请求登录页面
                        url.indexOf("UserController.do") != -1 && "login".equals(method) ||//请求注销
                        url.indexOf("UserController.do")!=-1 && "exit".equals(method) ||
                        url.indexOf("timeout.jsp")!=-1
                ){
            //此次请求不需要登录认证，继续完成后续操作

            filterChain.doFilter(request,response);//继续调用目标方法
            return ;
        }
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("loginuser");
        if(user==null){
            response.sendRedirect("timeout.jsp");
        }else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }
}
