package com.simeyt.yunx.controller;

import com.simeyt.yunx.pojo.Product;
import com.simeyt.yunx.pojo.PropertyValue;
import com.simeyt.yunx.service.ProductService;
import com.simeyt.yunx.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("")
public class PropertyValueController {
    @Autowired
    ProductService productService;
    @Autowired
    PropertyValueService propertyValueService;
    @RequestMapping("admin_propertyValue_edit")
    public String edit(Model model,int pid){
        Product p = productService.get(pid);
        propertyValueService.init(p);
        List<PropertyValue> pvs = propertyValueService.list(pid);

        model.addAttribute("p",p);
        model.addAttribute("pvs",pvs);

        return "admin/editPropertyValue";
    }
    @RequestMapping("admin_propertyValue_update")
    @ResponseBody
    public String update(PropertyValue propertyValue){
        propertyValueService.update(propertyValue);
        return "success";
    }
}
