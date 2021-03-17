package com.eShop.client.products.service;

import com.eShop.commons.bean.Notice;
import com.eShop.commons.bean.Product;
import com.eShop.utils.PageModel;

import java.util.List;

public interface ProductService {
    List<Product> findProductByCategory(String category, PageModel pageModel);

    int findProductCountByCategory(String category);

    List<Product> findProductByName(String name, PageModel pageModel);

    int findProductByCount(String name);

    Product findProductById(String id);

    Notice findNoticeRecent();

    List<Product> findWeekHotProduct();
}
