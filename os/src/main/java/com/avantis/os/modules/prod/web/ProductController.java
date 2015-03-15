package com.avantis.os.modules.prod.web;

import com.avantis.os.common.web.BaseController;
import com.avantis.os.modules.cms.service.ArticleService;
import com.avantis.os.modules.prod.entity.Product;
import com.avantis.os.modules.prod.service.ProductService;
import com.avantis.os.modules.sys.entity.User;
import com.avantis.os.modules.sys.security.FormAuthenticationFilter;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by admin on 2015/3/12.
 */
@Controller
@RequestMapping(value = "${adminPath}/prod")
public class ProductController extends BaseController{
    @Autowired
    private ProductService productService;

    @RequiresUser
    @RequestMapping(value = "list")
    public String list(HttpServletRequest request, HttpServletResponse response, Model model, Product product){
        model.addAttribute("productList", productService.findList());
        model.addAttribute("product", product);
        return "modules/prod/index";
    }

    @RequiresUser
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Product product, HttpServletRequest request, Model model){
        productService.addNewProduct(product);
        return "redirect:" + adminPath + "/prod/list";
    }

    @RequiresUser
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String delete(Product product, HttpServletRequest request, Model model){
        productService.delete(product);
        return "redirect:" + adminPath + "/prod/list";
    }

    @RequiresUser
    @RequestMapping(value = "update" ,method = RequestMethod.POST)
    public String update(Product product, Model model){
        productService.update(product);
        return "redirect:" + adminPath + "/prod/list";
    }
}
