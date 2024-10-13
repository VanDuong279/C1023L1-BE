package codegym.vn.iposproduct.controller;

import codegym.vn.iposproduct.service.ProductService;
import codegym.vn.iposproduct.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class ProductController {
    @Autowired
    private ProductServiceImpl service;
    @GetMapping("/product")
    private void getList() {

    }
}
