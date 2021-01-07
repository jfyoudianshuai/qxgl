package com.duyi.qxgl.util;

import com.duyi.qxgl.domain.Fn;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Map;

public class ATag extends TagSupport {
    /**<jf:a href='url' label='新建用户' authorty='用户-新建'
     * <a href='url'>新建用户</a>
     * authorty来实现权限查找的
     */
    private String href;
    private String label;
    private String authorty;
    private String id;
    public void setHref(String href) {
        this.href = href;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setAuthorty(String authorty) {
        this.authorty = authorty;
    }

    @Override
    public int doStartTag() throws JspException {
        Map<String,Fn> buttons= (Map<String, Fn>) super.pageContext.getSession().getAttribute("buttons");
        Fn fn=buttons.get(authorty);
        if(fn!=null){
            //有这个按钮权限
            //显示这个按钮
            JspWriter out=super.pageContext.getOut();
            try {
                out.write("<a href='"+href+"'>"+label+"</a>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.doStartTag();
    }
}
