package com.eShop.admin.interceptor;

import com.eShop.commons.bean.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        if(url.endsWith("login") || url.endsWith("logout")){//如果处理器方法是login放行
            return true;
        }
        //除了login之外的处理器方法，如果是管理员通过，不是管理员不通过
        User login_user = (User) request.getSession().getAttribute("login_user");
        if(login_user != null){
            if("超级管理员".equals(login_user.getRole())){
                return true;
            }else{
                response.sendRedirect(request.getContextPath()+"/admin/error/privilege.jsp");
                return false;
            }
        }else{
            response.sendRedirect(request.getContextPath()+"/admin/error/privilege.jsp");
            return false;
        }
    }
}
