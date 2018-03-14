package com.simeyt.yunx.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simeyt.yunx.pojo.Category;
import com.simeyt.yunx.util.ImageUtil;
import com.simeyt.yunx.util.Page;
import com.simeyt.yunx.util.UploadedImageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.simeyt.yunx.service.CategoryService;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping({"admin","admin_category_list"})
    public String list(Model model, Page page){
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Category> cs= categoryService.list();
        int total = (int) new PageInfo<>(cs).getTotal();
        page.setTotal(total);
        model.addAttribute("cs", cs);
        model.addAttribute("page",page);
        return "admin/listCategory";
    }

    @RequestMapping("admin_category_add")
    public String add(Category c, HttpSession session, UploadedImageFile uploadedImageFile) throws IOException {
        System.out.println(c.getId());
        categoryService.add(c);
        System.out.println(c.getId());
        File imageFolder = new File(session.getServletContext().getRealPath("img/category"));//通过getRealPath定位存放分类图片的路径。
        System.out.println(imageFolder.getPath());
        File file = new File(imageFolder,c.getId()+".jpg");
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdir();//如果/img/category目录不存在，则创建该目录
        }
        System.out.println(uploadedImageFile);
        System.out.println(uploadedImageFile.getImage());
        System.out.println(file);
        uploadedImageFile.getImage().transferTo(file);//通过UploadedImageFile 把浏览器传递过来的图片保存在上述指定的位置
        BufferedImage img = ImageUtil.change2jpg(file);//通过ImageUtil.change2jpg(file); 确保图片格式一定是jpg，而不仅仅是后缀名是jpg.
        ImageIO.write(img,"jpg",file);
        return "redirect:/admin_category_list";
    }

    @RequestMapping("admin_category_delete")
    public String delete(int id,HttpSession session){
        categoryService.delete(id);
        File imageFolder = new File(session.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder,id+".jpg");
        file.delete();
        return "redirect:/admin_category_list";
    }

    @RequestMapping("admin_category_edit")
    public String edit(int id,Model model){
        Category c = categoryService.get(id);
        model.addAttribute("c",c);
        return "admin/editCategory";
    }

    @RequestMapping("admin_category_update")
    public String update(Category c,HttpSession session,UploadedImageFile uploadedImageFile) throws IOException {
        categoryService.update(c);
        MultipartFile image = uploadedImageFile.getImage();//UploadedImageFile 用于接受上传的图片
        if(image!=null && !image.isEmpty()){// 首先判断是否有上传图片
            File imageFolder = new File(session.getServletContext().getRealPath("img/category"));//通过getRealPath定位存放分类图片的路径。
            File file = new File(imageFolder,c.getId()+".jpg");// 根据分类id创建文件名
//            image.transferTo(file);//把浏览器传递过来的图片保存在上述指定的位置
            uploadedImageFile.getImage().transferTo(file);//通过UploadedImageFile 把浏览器传递过来的图片保存在上述指定的位置
            BufferedImage img = ImageUtil.change2jpg(file);//确保图片格式一定是jpg，而不仅仅是后缀名是jpg.
            ImageIO.write(img,"jpg",file);
        }
        return "redirect:/admin_category_list";
    }
}