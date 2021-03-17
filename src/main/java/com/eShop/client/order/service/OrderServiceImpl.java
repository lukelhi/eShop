package com.eShop.client.order.service;

import com.eShop.client.order.dao.OrderDao;
import com.eShop.commons.bean.Order;
import com.eShop.commons.bean.OrderItem;
import com.eShop.commons.bean.Product;
import com.eShop.commons.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    OrderDao orderDao;

    @Override
    public void createOrder(Order order, Map<Product, Integer> cart) {
        //遍历购物车，并将其插入到订单条目
        for(Product p : cart.keySet()){
            OrderItem item = new OrderItem();
            item.setProduct(p);
            item.setOrder(order);
            item.setBuynum(cart.get(p));
            orderDao.insertItem(item);
            //修改库存
            orderDao.updateProductNum(item);
        }
        orderDao.insertOrder(order);
    }

    @Override
    public void paySuccess(String orderId) {
        orderDao.updatePayState(orderId);
    }

    @Override
    public List<Order> findOrderByUser(User user) {
        return orderDao.findOrderByUser(user);
    }

    @Override
    public List<OrderItem> findOrderItemById(String id) {
        return orderDao.findOrderItemById(id);
    }
}
