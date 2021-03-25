package com.eShop.client.cart.handler;

import com.eShop.client.products.service.ProductService;
import com.eShop.commons.bean.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/client/cart")
public class CartHandler {
    @Autowired
    ProductService productService;
    @RequestMapping("/addCart")
    public String addCart(String id, HttpSession session){
        //使用session存储购物车的内容，存储类型为map
        //map中键为商品名称，值为购买数量
        /**
         * 查询商品信息
         *判断购物车，如果有购物车（map）不需要定义，如果没有购物车定义一个购物车
         * 向购物车放入商品
         * 将购物车存到session
         */
        Product product = productService.findProductById(id);
        Map<Product,Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
        if(cart == null){
            cart = new HashMap<Product, Integer>();
        }
        //关于map的key重复：可以相同的key值，但是添加的value值会覆盖前面的，返回值是前一个，如果没有就返回null
        //接受map的返回值，如果不为空,count为原来的值；
        Integer count = cart.put(product,1);
        if(count != null){
            cart.put(product,count+1);
            //增加之后超过库存，将其还原
            if((count+1) > product.getPnum()){
                cart.put(product,count);
            }
        }
        //需要重写equals方法，当product的id相同的时候也判定为同一个对象。
        session.setAttribute("cart",cart);
        return "redirect:/client/cart.jsp";
    }
    @RequestMapping("/changeCart")
    public String changeCart(String id,Integer count,HttpSession session){
        Product product = productService.findProductById(id);
        //获取购物车
        Map<Product,Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
        if(count == 0){
            cart.remove(product);
        }else{
            cart.put(product,count);
        }
        return "redirect:/client/cart.jsp";
    }
}
