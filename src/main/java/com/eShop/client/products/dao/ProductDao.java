package com.eShop.client.products.dao;

import com.eShop.commons.bean.Notice;
import com.eShop.commons.bean.Product;
import com.eShop.utils.PageModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao {
    List<Product> selectProductByCategory(@Param("category") String category, @Param("pageModel") PageModel pageModel);

    int selectProductCountByCategory(String category);

    List<Product> selectProductByName(@Param("name") String name, @Param("pageModel") PageModel pageModel);

    int selectProductByCount(String name);

    Product selectProductById(String id);

    Notice selectNoticeRecent();

    List<Product> selectWeekHotProduct();
}
