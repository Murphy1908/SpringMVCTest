package com.app03.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app03.domain.Product;
import com.app03.form.ProductForm;

@Controller
public class ProductController {

    private static final Log logger = LogFactory.getLog(ProductController.class);

    @RequestMapping(value="/product_input")
    public String inputProduct() {
        logger.info("inputProduct called");
        //修改返回值来实现不同页面的跳转
        return "ProductForm";
    }

    //返回值为ModelAndView
    @RequestMapping(value="/product_saveMV")
    public ModelAndView saveProductMV(ProductForm productForm) {
        logger.info("saveProduct called");

        Product product = new Product();
        product.setName(productForm.getName());
        product.setDescription(productForm.getDescription());
        try {
            product.setPrice(Float.parseFloat(productForm.getPrice()));
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ModelAndView mv = new ModelAndView();
        //返回页面
        mv.setViewName("ProductDetails");
        //传递product数据
        mv.addObject("product", product);
        return mv;
    }
    
    //返回值为String
    @RequestMapping(value="/product_saveStr")
    public String saveProductStr(ProductForm productForm, Model model) {
        logger.info("saveProduct called");

        Product product = new Product();
        product.setName(productForm.getName());
        product.setDescription(productForm.getDescription());
        try {
            product.setPrice(Float.parseFloat(productForm.getPrice()));
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        model.addAttribute(product);
        return "ProductDetails";
    }
    
    //返回值为String类型，重定向&转发
    @RequestMapping(value="/product_save")
    public String saveProduct(ProductForm productForm, RedirectAttributes redirectAttributes) {
        logger.info("saveProduct called");

        Product product = new Product();
        product.setName(productForm.getName());
        product.setDescription(productForm.getDescription());
        try {
            product.setPrice(Float.parseFloat(productForm.getPrice()));
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //由于重定向，model失效，redirectAttributes提供重定向传值的方法
        redirectAttributes.addFlashAttribute("message", "The product was successfully added.");
        return "redirect:/prodcut_view/"+product.getName();
        //转发，页面不跳转，依然还是/product_save
        //return "forward:/prodcut_view/"+product.getName();
    }
    
    @RequestMapping(value="/prodcut_view/{name}")
    public String viewProduct( @PathVariable String name, Model model ) {
    	Product product = new Product();
    	product.setName(name);
    	model.addAttribute("product",product);
    	return "ProductView";
    }
}
