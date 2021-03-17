package com.eShop.admin.notices.handler;

import com.eShop.admin.notices.service.AdminNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/notice")
public class AdminNoticeHandler {
    @Autowired
    AdminNoticeService adminNoticeService;

    @RequestMapping("/ListNotice")
    public String ListNotice(Model model){

        List notices = adminNoticeService.listNotice();
        model.addAttribute("notices",notices);
        return "/admin/notices/list.jsp";
    }
}
