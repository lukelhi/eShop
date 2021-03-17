package com.eShop.client.products.handler;

import com.eShop.client.products.service.ProductService;
import com.eShop.commons.bean.Notice;
import com.eShop.commons.bean.Product;
import com.eShop.utils.PageModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/client/product")
public class ProductHandler {
    @Autowired
    ProductService productService;
    @RequestMapping("/findProductByCategory")
    public String findProductByCategory(@RequestParam(defaultValue = "1")int pageIndex, String category, Model model){
        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(pageIndex);//分页查询需要的当前页的起始索引以及页面大小；
        List<Product> products = productService.findProductByCategory(category,pageModel);
//        for(Product d:products){
//            System.err.println(d);
//        }
        int recordCount = productService.findProductCountByCategory(category);
        pageModel.setRecordCount(recordCount);
        model.addAttribute("products",products);
        model.addAttribute("category",category);
        model.addAttribute("pageModel",pageModel);

        return "/client/product_list.jsp";
    }
    @RequestMapping("/findProductByName")
    public String findProductByName(@RequestParam(defaultValue = "1") int pageIndex,String name,Model model){
        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(pageIndex);
        List<Product> products = productService.findProductByName(name,pageModel);
        int recordCount = productService.findProductByCount(name);
        System.err.println(recordCount);
        pageModel.setRecordCount(recordCount);
//        for(Product p:products)
//        System.err.println(p);
        model.addAttribute("products",products);
        model.addAttribute("pageModel",pageModel);//分页
        model.addAttribute("name",name);//回显
        return "/client/product_search_list.jsp";
    }
    @RequestMapping("/findProductById")
    public String findProductById(String id,Model model){
        Product p = productService.findProductById(id);
        model.addAttribute("p",p);
        return "/client/info.jsp";
    }
    @RequestMapping("/showIndex")
    public String showIndex(Model model){
//        System.err.println("首页信息！");
        Notice notice = productService.findNoticeRecent();
        model.addAttribute("n",notice);
        List<Product> products = productService.findWeekHotProduct();
        model.addAttribute("pList",products);
        return "/client/index.jsp";
    }
}