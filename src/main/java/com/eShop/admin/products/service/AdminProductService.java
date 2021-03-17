package com.eShop.admin.products.service;

import com.eShop.commons.bean.Product;
import com.eShop.commons.bean.ProductList;

import java.util.List;

public interface AdminProductService {
    List<Product> findProduct();

    List<Product> findProductByManyCondition(Product product, Double maxPrice, Double minPrice);

    void addProduct(Product product);

    Product findProductById(String id);

    void editProduct(Product product);

    void removeProduct(String id);

    List<ProductList> findProductSalList(String year, String month);
}
