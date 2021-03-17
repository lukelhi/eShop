package com.eShop.client.user.service;

import com.eShop.client.order.dao.OrderDao;
import com.eShop.client.user.dao.IUserDao;
import com.eShop.commons.bean.OrderItem;
import com.eShop.commons.bean.Product;
import com.eShop.commons.bean.User;
import com.eShop.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    IUserDao userDao;
    @Autowired
    OrderDao orderDao;
    @Override
    public int addUser(User user, HttpServletRequest request) {//加入request参数
        String emailMessage = "请点击链接激活用户，请点击：<a href='http://localhost:8080/"+request.getContextPath()+"/client/user/activeUser?activeCode="+user.getActiveCode()+"'>激活</a>后使用";
        try {
            MailUtils.sendMail(user.getEmail(),emailMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return userDao.insertUser(user);
    }

    @Override
    public int activeUser(String activeCode) {
        return userDao.activeUser(activeCode);
    }

    @Override
    public User findEmail(String email) {
        return userDao.selectEmail(email);
    }

    @Override
    public User findUsername(String username) {
        return userDao.selectUsername(username);
    }

    @Override
    public User findUserByLogin(User user) {
        return userDao.selectUserByLogin(user);
    }

    @Override
    public int modifyUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public void removeOrderById(String id, String flag) {
            //加回库存
            if(flag == null) {
                //查找到所有的订单条目，将orderItem中buynum加回库存
                List<OrderItem> items = orderDao.findOrderItemById(id);//item中包含product关联对象
                for(OrderItem item:items){
                    userDao.updatePnum(item);
                }
            }
            //删除订单和订单项
            userDao.deleteOrder(id);
            userDao.deleteOrderItem(id);
    }
}
