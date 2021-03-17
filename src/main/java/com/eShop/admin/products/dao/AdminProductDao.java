package com.eShop.admin.products.dao;

import com.eShop.commons.bean.Product;
import com.eShop.commons.bean.ProductList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Repository
public interface AdminProductDao {
    List<Product> selectProduct();

    List<Product> selectProductByManyCondition(Map map);

    void insertProduct(Product product);

    Product selectProductById(String id);

    void updateProduct(Product product);

    void deleteProduct(String id);

    List<ProductList> selectProductSalList(@Param("year") String year, @Param("month") String month);
}
