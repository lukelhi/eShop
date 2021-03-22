package com.eShop.admin.user.handler;

import com.eShop.admin.login.service.IAdminUserService;
import com.eShop.admin.user.service.AdminUserService;
import com.eShop.client.user.service.IUserService;
import com.eShop.commons.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/user")
public class AdminUserController {//防止bean重复
    @Autowired
    AdminUserService adminUserService;

    @RequestMapping("/ListUser")
    public String ListUser(Model model){
        List<User> users = adminUserService.findUsers();
        model.addAttribute("users",users);
        return "/admin/user/list.jsp";
    }
    /**
     * 修改user时的回显
     */
    @RequestMapping("/findUserById")
    public String findUserById(Integer id,Model model){
        User userById = adminUserService.findUserById(id);
        model.addAttribute("user",userById);
        return "/admin/user/edit.jsp";
    }
    /**
     * 添加用户
     */
    @RequestMapping("/addUser")
    public String addUser(User user,Model model){
        //判断UserName是否重复
        User userById = adminUserService.findUserByUsername(user.getUsername());
        if(userById == null) {
            adminUserService.addUser(user);
            return ListUser(model);
        }
        else {
            model.addAttribute("error","用户名重复！");
            model.addAttribute("user",user);
            return "/admin/user/add.jsp";
        }
    }
    /**
     * 添加用户
     */
    @RequestMapping("/removeUserById")
    public String removeUserById(String id,Model model){
        adminUserService.removeUserById(id);
        return ListUser(model);
    }
}
