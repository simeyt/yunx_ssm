package com.simeyt.yunx.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simeyt.yunx.pojo.Category;
import com.simeyt.yunx.pojo.Property;
import com.simeyt.yunx.service.CategoryService;
import com.simeyt.yunx.service.PropertyService;
import com.simeyt.yunx.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
    @Controller
    @RequestMapping("")
    public class PropertyController {
        @Autowired
        CategoryService categoryService;
        @Autowired
        PropertyService propertyService;
        @RequestMapping("admin_property_list")
        public String list(int cid,Model model,Page page){
            Category c = categoryService.get(cid);
            page.setCount(10);//每页显示10个属性
            PageHelper.offsetPage(page.getStart(),page.getCount());
            List<Property> ps = propertyService.list(cid);

            int total = (int) new PageInfo<>(ps).getTotal();
            page.setTotal(total);
            page.setParam("&cid="+c.getId());//属性分页都是基于当前分类下的分页，分页的时候需要传递这个cid

            model.addAttribute("ps",ps);
            model.addAttribute("c",c);
            model.addAttribute("page",page);
            return "admin/listProperty";
        }

        @RequestMapping("admin_property_add")
        public String add(Model model,Property property){
            propertyService.add(property);
            return "redirect:admin_property_list?cid="+property.getCid();
        }

        @RequestMapping("admin_property_edit")
        public String edit(Model model,int id){
            Property p = propertyService.get(id);//通过属性ID获取属性对象
            Category c = categoryService.get(p.getCid());//通过属性对象的cid获取分类对象
            p.setCategory(c);//把Property对象放在request的 "p" 属性中;因为获得的属性不知道是哪个分类的
            model.addAttribute("p",p);
            return "admin/editProperty";
        }

        @RequestMapping("admin_property_update")
        public String update(Property p){
            propertyService.update(p);
            return "redirect:admin_property_list?cid="+p.getCid();
        }

        @RequestMapping("admin_property_delete")
        public String delete(int id){
            Property p = propertyService.get(id);
            propertyService.delete(id);
            return "redirect:admin_property_list?cid="+p.getCid();
        }
    }


