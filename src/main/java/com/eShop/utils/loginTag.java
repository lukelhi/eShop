package com.eShop.utils;

import com.eShop.commons.bean.User;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class loginTag extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException, IOException {
        //获取context
        PageContext context = (PageContext) this.getJspContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        User login_user = (User) context.getSession().getAttribute("login_user");
        if(login_user == null){
             response.sendRedirect(request.getContextPath()+"/client/error/privilege.jsp");//重定向
        }
    }
}
