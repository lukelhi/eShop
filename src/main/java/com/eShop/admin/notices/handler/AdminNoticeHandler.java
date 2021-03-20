package com.eShop.admin.notices.handler;

import com.eShop.admin.notices.service.AdminNoticeService;
import com.eShop.commons.bean.Notice;
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
    /**
     * 根据id查询notice
     * 编辑公告的回显
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/findNoticeById")
    public String findNoticeById(String id,Model model){
        Notice notice = adminNoticeService.selectNoticeById(id);
        model.addAttribute("n",notice);
        return "/admin/notices/edit.jsp";
    }

    /**
     * 修改公告
     * @param notice
     * @param model
     * @return
     */
    @RequestMapping("/editNotice")
    public String editProduct(Notice notice,Model model){
        adminNoticeService.editNotice(notice);
        return ListNotice(model);
    }

    /**
     * 添加公告
     * @param notice
     * @param model
     * @return
     */
    @RequestMapping("/addNotice")
    public String addProduct(Notice notice,Model model){
        adminNoticeService.addNotice(notice);
        return ListNotice(model);
    }
    /**
     * 删除公告
     */
    @RequestMapping("/removeNotice")
    public String removeProduct(Integer id,Model model){
        adminNoticeService.removeNotice(id);
        return ListNotice(model);
    }
}
