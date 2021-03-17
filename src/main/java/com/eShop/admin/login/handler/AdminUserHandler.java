package com.eShop.admin.login.handler;

import com.eShop.admin.login.service.IAdminUserService;
import com.eShop.commons.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin/login")
public class AdminUserHandler {
    @Autowired
    IAdminUserService adminUserService;
    @RequestMapping("/login")
    public String login(User user, HttpSession session,Model model){
        //System.err.println("登录用户"+user);
        User login_user = adminUserService.findUserByLogin(user);
        if(login_user != null){
            if("超级管理员".equals(login_user.getRole())){
                session.setAttribute("login_user",login_user);
                return "/admin/login/home.jsp";
            }else{
                //普通用户，权限不足
                return "redirect:/admin/error/privilege.jsp";
            }
        }else{
            model.addAttribute("fail","用户名或密码错误！");
            return "/admin/login/login.jsp";
        }
    }
    @RequestMapping("/test")
    public String test(){
        return "/admin/login/home.jsp";
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session,Model model){
        session.removeAttribute("login_user");
        model.addAttribute("fail","用户退出成功！");
        return "/admin/login/login.jsp";
    }
}
