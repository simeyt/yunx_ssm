package com.simeyt.yunx.controller;

import com.github.pagehelper.PageHelper;
import com.simeyt.yunx.pojo.*;
import com.simeyt.yunx.service.*;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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

    @RequestMapping("forecategory")
    public String category(int cid,Model model){
        Category c = categoryService.get(cid);
        productService.fill(c);
        productService.setSaleAndReviewNumber(c.getProducts());
        model.addAttribute("c",c);
        return "fore/category";
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

    @RequestMapping("foreloginAjax")
    @ResponseBody
    public String loginAjax(@RequestParam("name") String name, @RequestParam("password") String password, HttpSession session) {
        name = HtmlUtils.htmlEscape(name);
        User user = userService.get(name,password);

        if(null==user){
            return "fail";
        }
        session.setAttribute("user", user);
        return "success";
    }

    @RequestMapping("foreloginout")
    public String loginout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:forehome";
    }

    @RequestMapping("forecheckLogin")
    @ResponseBody
    public String checkLogin( HttpSession session) {
        User user =(User)  session.getAttribute("user");
        if(null!=user)
            return "success";
        return "fail";
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

    @RequestMapping("foresearch")
    public String search(String keyword, Model model){
        PageHelper.offsetPage(0,20);
        List<Product> ps =  productService.search(keyword);
        model.addAttribute("ps",ps);
        return "fore/searchResult";
    }

    @RequestMapping("forebuyone")
    public String buyone(int pid,int num,HttpSession session){
        Product p = productService.get(pid);
        int oiid = 0;
        User user = (User) session.getAttribute("user");
        boolean found = false;//
        List<OrderItem> ois = orderItemService.listByUser(user.getId());
        for (OrderItem oi : ois){
            // 如果已经存在这个产品对应的OrderItem，并且还没有生成订单，即还在购物车中。 那么就应该在对应的OrderItem基础上，调整数量
            if(oi.getProduct().getId().intValue()==p.getId().intValue()){
                oi.setNumber(oi.getNumber()+num);
                orderItemService.update(oi);// 调整数量
                found = true;
                oiid = oi.getId();// 获取这个订单项的 id
                break;
            }
        }
        if(!found){
            OrderItem oi = new OrderItem();
            oi.setPid(pid);
            // oid不需要插入，没有付款
            oi.setUid(user.getId());
            oi.setNumber(num);
            orderItemService.add(oi);// 插入到数据库
            oiid = oi.getId();
        }
        return "redirect:forebuy?oiid="+oiid;// 会跳转到下面@RequestMapping("forebuy")这个控制器
    }

    @RequestMapping("forebuy")
    public String buy( Model model,String[] oiid,HttpSession session){
        List<OrderItem> ois = new ArrayList<>();
        float total = 0;

        for (String strid : oiid) {
            int id = Integer.parseInt(strid);
            OrderItem oi= orderItemService.get(id);
            total +=oi.getProduct().getPromotePrice()*oi.getNumber();
            ois.add(oi);
        }

        session.setAttribute("ois", ois);
        model.addAttribute("total", total);
        return "fore/buy";
    }

}
