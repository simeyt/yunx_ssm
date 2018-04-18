package com.simeyt.yunx.service.impl;

import com.simeyt.yunx.mapper.CategoryMapper;
import com.simeyt.yunx.mapper.ProductMapper;
import com.simeyt.yunx.pojo.Category;
import com.simeyt.yunx.pojo.Product;
import com.simeyt.yunx.pojo.ProductExample;
import com.simeyt.yunx.pojo.ProductImage;
import com.simeyt.yunx.service.CategoryService;
import com.simeyt.yunx.service.ProductImageService;
import com.simeyt.yunx.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductImageService productImageService;
    @Override
    public void add(Product product) {
        productMapper.insert(product);
    }

    @Override
    public void delete(int id) {
        productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Product product) {
        productMapper.updateByPrimaryKeySelective(product);
    }
// get和list方法都会把取出来的Product对象设置上对应的category
    public void setCategory(List<Product> ps){
        for (Product p : ps)
            setCategory(p);
    }

    public void setCategory(Product p){
        int cid = p.getCid();
        Category c= categoryService.get(cid);
        p.setCategory(c);
    }

    @Override
    public Product get(int id) {
        Product product = productMapper.selectByPrimaryKey(id);
        setCategory(product);
        setFirstProductImage(product);
        return product;
    }

    @Override
    public List list(int cid) {
        ProductExample example = new ProductExample();
        example.createCriteria().andCidEqualTo(cid);//表示查询cid字段
        example.setOrderByClause("id asc");
        List result = productMapper.selectByExample(example);
        setCategory(result);
        setFirstProductImage(result);//调用setFirstProductImage(List<Product> ps) 为多个产品设置图片
        return result;  //返回集合
    }

    @Override
    public void setFirstProductImage(Product p) {
        List<ProductImage> pis = productImageService.list(p.getId(),ProductImageService.type_single);
        if(!pis.isEmpty()){
            ProductImage pi =pis.get(0);//把第一图作为缩略图
            p.setFirstProductImage(pi);//把取出来的图片设置为product的缩略图属性
        }
    }

    public void setFirstProductImage(List<Product> ps){//给多个产品设置图片
        for(Product p : ps){
            setFirstProductImage(p);
        }
    }

    // 以下是前台
    @Override
    public void fill(List<Category> cs) {
        for (Category c : cs) {
            fill(c);
        }
    }
    @Override
    public void fill(Category c) {
        List<Product> ps = list(c.getId());
        c.setProducts(ps);
    }

    @Override
    public void fillByRow(List<Category> cs) {
        int productNumberEachRow = 8;
        for (Category c : cs) {
            List<Product> products =  c.getProducts();
            List<List<Product>> productsByRow =  new ArrayList<>();
            for (int i = 0; i < products.size(); i+=productNumberEachRow) {
                int size = i+productNumberEachRow;
                size= size>products.size()?products.size():size;
                List<Product> productsOfEachRow =products.subList(i, size);
                productsByRow.add(productsOfEachRow);
            }
            c.setProductsByRow(productsByRow);
        }
    }
}
