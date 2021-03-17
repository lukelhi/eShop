package com.eShop.client.products.service;

import com.eShop.client.products.dao.ProductDao;
import com.eShop.client.user.dao.IUserDao;
import com.eShop.commons.bean.Notice;
import com.eShop.commons.bean.Product;
import com.eShop.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDao productDao;
    IUserDao userDao;
    @Override
    public List<Product> findProductByCategory(String category, PageModel pageModel) {
        return productDao.selectProductByCategory(category,pageModel);
    }

    @Override
    public int findProductCountByCategory(String category) {
        return productDao.selectProductCountByCategory(category);
    }

    @Override
    public List<Product> findProductByName(String name, PageModel pageModel) {
        return productDao.selectProductByName(name,pageModel);
    }

    @Override
    public int findProductByCount(String name) {
        return productDao.selectProductByCount(name);
    }

    @Override
    public Product findProductById(String id) {
        return productDao.selectProductById(id);
    }

    @Override
    public Notice findNoticeRecent() {
        return productDao.selectNoticeRecent();
    }

    @Override
    public List<Product> findWeekHotProduct() {
        return productDao.selectWeekHotProduct();
    }

}
