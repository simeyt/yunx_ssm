package com.simeyt.yunx.controller;

import com.simeyt.yunx.pojo.*;
import com.simeyt.yunx.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("")
public class ForeController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    PropertyValueService propertyValueService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    ReviewService reviewService;


    @RequestMapping("forehome")
    public String home(Model model) {
        List<Category> cs= categoryService.list();
        productService.fill(cs);
        productService.fillByRow(cs);
         model.addAttribute("cs", cs);
        return "fore/home";
    }

    @RequestMapping("foreregister")
    public String register(Model model,User user){
        String name = user.getName();
        name = HtmlUtils.htmlEscape(name);
        user.setName(name);
        boolean exist = userService.isExist(name);

        if(exist){
            String m = "用户名已被使用，不能使用";
            model.addAttribute("msg",m);
            model.addAttribute("user",null);
            return "fore/register";
        }

        userService.add(user);
        return "redirect:registerSuccessPage";
    }

    @RequestMapping("forelogin")
    public String login(User user, Model model, HttpSession session){
        User user1 = userService.get(user.getName(),user.getPassword());

        if(user1==null){
            model.addAttribute("msg","账号或密码错误");
            return "fore/login";
        }
        session.setAttribute("user",user);
        return "redirect:forehome";
    }

    @RequestMapping("foreloginout")
    public String loginout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:forehome";
    }

    @RequestMapping("foreproduct")
    public String product(int pid,Model model){
        Product product = productService.get(pid);// 根据pid获取Product 对象p

        List<ProductImage> productSingleImages = productImageService.list(product.getId(),ProductImageService.type_single);
        List<ProductImage> productDetailImages = productImageService.list(product.getId(),ProductImageService.typt_detail);
        product.setProductSingleImages(productSingleImages);// 根据对象p，获取这个产品对应的单个图片集合
        product.setProductDetailImages(productDetailImages);// 根据对象p，获取这个产品对应的详情图片集合

        List<PropertyValue> pvs = propertyValueService.list(product.getId());// 获取产品的所有属性值
        List<Review> rs = reviewService.list(product.getId());// 获取产品对应的所有的评价
        productService.setSaleAndReviewNumber(product);// 设置产品的销量和评价数量
        model.addAttribute("pvs",pvs);
        model.addAttribute("reviews",rs);
        model.addAttribute("p",product);
        return "fore/product";
    }
}
