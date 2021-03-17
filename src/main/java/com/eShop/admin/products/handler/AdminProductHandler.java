package com.eShop.admin.products.handler;

import com.eShop.admin.products.service.AdminProductService;
import com.eShop.commons.bean.Product;
import com.eShop.commons.bean.ProductList;
import com.eShop.utils.IdUtils;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellRange;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/product")
public class AdminProductHandler {
    @Autowired
    AdminProductService adminProductService;

    @RequestMapping("/listProduct")
    public String listProduct(Model model){
        List<Product> listProduct = adminProductService.findProduct();
        model.addAttribute("listProduct",listProduct);

        return "/admin/products/list.jsp";
    }

    @RequestMapping("/findProductByManyCondition")
    public String findProductByManyCondition(Product product,Double maxPrice,Double minPrice,Model model){//价格为Double类型
        List<Product> listProduct = adminProductService.findProductByManyCondition(product,maxPrice,minPrice);
        model.addAttribute("listProduct",listProduct);
        model.addAttribute("product",product);
        model.addAttribute("maxPrice",maxPrice);
        model.addAttribute("minPrice",minPrice);
        return "/admin/products/list.jsp";
    }
    @RequestMapping("/addProduct")
    public String addProduct(Product product, MultipartFile upload, HttpSession session) throws IOException {
        String path = session.getServletContext().getRealPath("/productImg");
        String path1 = "D:\\eShop\\src\\main\\webapp\\productImg";//在项目目录下面也保存一份文件
        System.err.println("路径"+path);

        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
        }

        String fileName = IdUtils.getUUID()+"-"+upload.getOriginalFilename();
        upload.transferTo(new File(path,fileName));//需要文件路径和文件名称
        FileUtils.copyFile(new File(path,fileName),new File(path1,fileName));//将文件拷贝一份到项目目录下面

        product.setId(IdUtils.getUUID());
        product.setImgurl("/productImg/"+fileName);
        adminProductService.addProduct(product);
        return "redirect:/admin/product/listProduct";
    }
    @RequestMapping("/findProductById")
    public String findProductById(String id,Model model){
        Product product = adminProductService.findProductById(id);
        model.addAttribute("p",product);
        return "/admin/products/edit.jsp";
    }
    @RequestMapping("/editProduct")
    public String editProduct(MultipartFile upload,Product product,HttpSession session) throws IOException {
        if(!upload.isEmpty()){
            Product target = adminProductService.findProductById(product.getId());
            File targetFile = new File(session.getServletContext().getRealPath("/")+product.getImgurl());//找到项目文件

            if(targetFile.exists()){
                targetFile.delete();
            }
            String path = session.getServletContext().getRealPath("/productImg");
            String fileName = IdUtils.getUUID()+"-"+upload.getOriginalFilename();
            upload.transferTo(new File(path,fileName));

            //上传新图片，赋值给product图片的属性
            product.setImgurl("/productImg/"+fileName);
        }

        adminProductService.editProduct(product);
        return "redirect:/admin/product/listProduct";
    }
    @RequestMapping("/removeProduct")
    public String removeProduct(String id,HttpSession session){
        Product target = adminProductService.findProductById(id);
        File targetFile = new File(session.getServletContext().getRealPath("/")+target.getImgurl());//找到目标文件

        if(targetFile.exists()){
            targetFile.delete();
        }
        adminProductService.removeProduct(id);
        return "redirect:/admin/product/listProduct";
    }
    @RequestMapping("/download")
    public void download(String year, String month, HttpServletRequest request,HttpServletResponse response) throws IOException {
        List<ProductList> pList = adminProductService.findProductSalList(year,month);
        /*for(ProductList p:pList){
            System.err.println(p);
        }*/
        String filename = year + "年"+month+"月销售榜单";
        String sheetName = month + "月销售榜单";
        String titleName = year + "年" + month +"月销售榜单";
        String [] columnName = {"商品名称","商品销量"};

        String[][] dataList = new String[pList.size()][2];//二维数组
        for(int i=0;i< pList.size();i++){
            dataList[i][0] = pList.get(i).getName();
            dataList[i][1] = pList.get(i).getSalNum();
        }

        HSSFWorkbook wb = new HSSFWorkbook();//创建excel文件
        HSSFSheet sheet = wb.createSheet();//创建excel中的表
        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cell = row1.createCell(0);//创建第一行的第一个单元格
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,1));
        cell.setCellValue(titleName);//给合并后的第一行的第一个表格赋值
        //创建第二行
        HSSFRow row = sheet.createRow(1);
        for(int i=0;i<2;i++){
            row.createCell(i).setCellValue(columnName[i]);
        }
        //创建数据行
        for(int i=0; i < dataList.length; i++){
            row = sheet.createRow(i+2);//从第三行开始创建数据
            for(int j=0;j<2;j++){
                row.createCell(j).setCellValue(dataList[i][j]);
            }
        }

        String fileName = filename + ".xls";
        response.setContentType("application/ms-excel;charset=UTF-8");
        response.setHeader("content-Disposition","attachment;filename="+processFileName(request,fileName));
        OutputStream out = response.getOutputStream();
        wb.write(out);
    }

    //处理文件名乱码问题
    public static String processFileName(HttpServletRequest request, String fileNames) {
        String codedfilename = null;
        try {
            String agent = request.getHeader("USER-AGENT");
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent && -1 != agent.indexOf("Trident")) {
                // IE
                codedfilename = java.net.URLEncoder.encode(fileNames, "UTF8");
            } else if (null != agent && -1 != agent.indexOf("Mozilla")) {
                // 火狐,CHROME等
                codedfilename = new String(fileNames.getBytes("UTF-8"), "iso-8859-1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return codedfilename;
    }
}
