package com.eShop.client.order.handler;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.eShop.client.order.service.OrderService;
import com.eShop.commons.bean.Order;
import com.eShop.commons.bean.OrderItem;
import com.eShop.commons.bean.Product;
import com.eShop.commons.bean.User;
import com.eShop.utils.AlipayConfig;
import com.eShop.utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/client/order")
public class OrderHandler {
    @Autowired
    OrderService orderService;
    @RequestMapping("/createOrder")
    public String createOrder(Order order, HttpSession session, Model model){
        //System.err.println(order);
        order.setId(IdUtils.getUUID());
        //此处应该加判断，如果用户没有登录不能购买
        User user = (User) session.getAttribute("login_user");
        order.setUser(user);
        //同时插入订单条目
        Map<Product,Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
        orderService.createOrder(order,cart);
        session.removeAttribute("cart");
        model.addAttribute("order",order);
        return "/client/confirm.jsp";
    }
    @RequestMapping("/pay")
    public void pay(HttpServletResponse response,HttpServletRequest request, String id, String money) throws AlipayApiException, IOException {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = id;
        //付款金额，必填
        String total_amount = money;
        //订单名称，必填
        String subject = id;
        //商品描述，可空
        String body = "";

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        response.setContentType("text/html");
        //输出
        response.getWriter().println(result);
    }
    @RequestMapping("/paysuccess")
    public String paysuccess(HttpServletRequest request) throws AlipayApiException, UnsupportedEncodingException {
        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名
        if(signVerified) {//商户订单号
            String orderId = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
            orderService.paySuccess(orderId);//修改订单的状态
            return "redirect:/client/paysuccess.jsp";
        }else {
            return "redirect:/client/fail.jsp";
        }
    }
    @RequestMapping("/findOrderByUser")
    public String findOrderByUser(HttpSession session,Model model){
        User user = (User) session.getAttribute("login_user");
        List<Order> orders = orderService.findOrderByUser(user);
        model.addAttribute("orders",orders);
        return "/client/orderlist.jsp";
    }
    @RequestMapping("/findOrderById")
    public String findOrderById(String id,Model model){
        List<OrderItem>  items = orderService.findOrderItemById(id);//orderItem的关联属性为order,product
        model.addAttribute("items",items);
        return "/client/orderInfo.jsp";
    }
}
