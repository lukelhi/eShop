package com.eShop.admin.orders.handler;

import com.eShop.admin.orders.service.AdminOrdersService;
import com.eShop.commons.bean.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/order")
public class AdminOrdersHandler {

    @Autowired
    AdminOrdersService adminOrdersService;

    @RequestMapping("/findOrders")
    public String findOrders(Model model){
        List orders = adminOrdersService.findOrders();
        model.addAttribute("orders",orders);
        return "/admin/orders/list.jsp";
    }

    @RequestMapping("/findOrderById")
    public String findOrderById(String id, Model model){
        Order order = adminOrdersService.findOrderById(id);
        System.err.println(order);
        model.addAttribute("order",order);
        return "/admin/orders/view.jsp";
    }
}
