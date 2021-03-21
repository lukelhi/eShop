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

    /**
     * 查看所有订单
     * @param model
     * @return
     */
    @RequestMapping("/findOrders")
    public String findOrders(Model model){
        List orders = adminOrdersService.findOrders();
        model.addAttribute("orders",orders);
        return "/admin/orders/list.jsp";
    }

    /**
     * 查看订单
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/findOrderById")
    public String findOrderById(String id, Model model){
        Order order = adminOrdersService.findOrderById(id);
        System.err.println(order);
        model.addAttribute("order",order);
        return "/admin/orders/view.jsp";
    }

    /**
     * 按条件查询
     * @param order
     * @param model
     * @return
     */
    @RequestMapping("/findOrderByExample")
    public String findOrderByExample(Order order,Model model){
        List<Order> orderByExample = adminOrdersService.findOrderByExample(order);
        model.addAttribute("orders",orderByExample);
        return "/admin/orders/list.jsp";
    }
    /**
     * 删除订单
     */
    @RequestMapping("/removeOrderById")
    public String removeOrder(String id,Model model){
        adminOrdersService.removeOrder(id);
        return findOrders(model);
    }
}
