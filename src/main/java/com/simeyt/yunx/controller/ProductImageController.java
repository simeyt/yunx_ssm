package com.simeyt.yunx.controller;

import com.simeyt.yunx.pojo.Product;
import com.simeyt.yunx.pojo.ProductImage;
import com.simeyt.yunx.service.ProductImageService;
import com.simeyt.yunx.service.ProductService;
import com.simeyt.yunx.util.ImageUtil;
import com.simeyt.yunx.util.UploadedImageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("")
public class ProductImageController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductImageService productImageService;
    @RequestMapping("admin_productImage_list")
    public String list(Model model,int pid){
        Product p = productService.get(pid);
        List<ProductImage> pisSingle = productImageService.list(pid,ProductImageService.type_single);
        List<ProductImage> pisDetail = productImageService.list(pid,ProductImageService.typt_detail);

        model.addAttribute("p",p);
        model.addAttribute("pisSingle",pisSingle);
        model.addAttribute("pisDetail",pisDetail);

        return "admin/listProductImage";
    }

    @RequestMapping("admin_productImage_add")
    public String add(ProductImage pi, HttpSession session, UploadedImageFile uploadedImageFile){
        productImageService.add(pi);
        String fileName = pi.getId()+".jpg";
        String imageFolder;
        String imageFolder_small=null;
        String imageFolder_middle=null;
        if(ProductImageService.type_single.equals(pi.getType())){
            imageFolder = session.getServletContext().getRealPath("img/productSingle");//通过getRealPath定位存放分类图片的路径
            imageFolder_small = session.getServletContext().getRealPath("img/productSingle_small");
            imageFolder_middle = session.getServletContext().getRealPath("img/productSingle_middle");
        }else {
            imageFolder = session.getServletContext().getRealPath("img/productDetail");//也是放详细图
        }
        File f =new File(imageFolder,fileName);
        f.getParentFile().mkdir();

        try {
            uploadedImageFile.getImage().transferTo(f);//通过UploadedImageFile把浏览器传递过来的图片保存在上述指定的位置
            BufferedImage img = ImageUtil.change2jpg(f);//确保图片格式一定是jpg，而不仅仅是后缀名是jpg.
            ImageIO.write(img,"jpg",f);
            if (ProductImageService.type_single.equals(pi.getType())){//生成小图跟中图
                File f_small = new File(imageFolder_small,fileName);
                File f_middle = new File(imageFolder_middle,fileName);
                ImageUtil.resizeImage(f,56,56,f_small);//把f生成小图的同时会创建目录
                ImageUtil.resizeImage(f,217,190,f_middle);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:admin_productImage_list?pid="+pi.getPid();
    }

    @RequestMapping("admin_productImage_delete")
    public String delete(int id,HttpSession session){
        ProductImage pi = productImageService.get(id);

        String fileName = pi.getId()+".jpg";
        String imageFolder;
        String imageFolder_small;
        String imageFolder_middle;

        if(ProductImageService.type_single.equals(pi.getType())){
            imageFolder = session.getServletContext().getRealPath("img/productSingle");
            imageFolder_small = session.getServletContext().getRealPath("img/productSingle_small");
            imageFolder_middle =session.getServletContext().getRealPath("img/productSingle_middle");
            System.out.println(imageFolder);
            File imageFile = new File(imageFolder,fileName);
            File f_small = new File(imageFolder_small,fileName);
            File f_middle = new File(imageFolder_middle,fileName);

            imageFile.delete();
            f_small.delete();
            f_middle.delete();//把小图中图单图都删掉
        }else{
            imageFolder = session.getServletContext().getRealPath("img/productDetail");
            File imageFile = new File(imageFolder,fileName);
            imageFile.delete();//把详细图删掉
        }
        productImageService.delete(id);
        return "redirect:admin_productImage_list?pid="+pi.getPid();
    }
}
