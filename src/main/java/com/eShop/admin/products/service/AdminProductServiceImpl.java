package com.eShop.admin.products.service;

import com.eShop.admin.products.dao.AdminProductDao;
import com.eShop.commons.bean.Product;
import com.eShop.commons.bean.ProductList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminProductServiceImpl implements AdminProductService {
    @Resource
    AdminProductDao adminProductDao;
    @Override
    public List<Product> findProduct() {
        return adminProductDao.selectProduct();
    }

    @Override
    public List<Product> findProductByManyCondition(Product product, Double maxPrice, Double minPrice) {
        Map map = new HashMap();
        map.put("product",product);
        map.put("maxPrice",maxPrice);
        map.put("minPrice",minPrice);
        return adminProductDao.selectProductByManyCondition(map);
    }

    @Override
    public void addProduct(Product product) {
        adminProductDao.insertProduct(product);
    }

    @Override
    public Product findProductById(String id) {
        return adminProductDao.selectProductById(id);
    }

    @Override
    public void editProduct(Product product) {
        adminProductDao.updateProduct(product);
    }

    @Override
    public void removeProduct(String id) {
        adminProductDao.deleteProduct(id);
    }

    @Override
    public List<ProductList> findProductSalList(String year, String month) {
        return adminProductDao.selectProductSalList(year,month);
    }
}
