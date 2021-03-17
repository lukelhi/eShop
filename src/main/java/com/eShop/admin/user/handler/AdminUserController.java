package com.eShop.admin.user.handler;

import com.eShop.admin.user.service.AdminUserService;
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
}
