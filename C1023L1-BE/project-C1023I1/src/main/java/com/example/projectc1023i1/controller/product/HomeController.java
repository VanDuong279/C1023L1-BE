package com.example.projectc1023i1.controller.product;

import com.example.projectc1023i1.model.product.Product;
import com.example.projectc1023i1.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/home")
@CrossOrigin("*")
public class HomeController {
    @Autowired
    private IProductService productService;
    @GetMapping
    public ResponseEntity<?> findNewestProducts(
            @RequestParam(name = "option", defaultValue = "price",required = false) String option,
            @RequestParam(name = "sort", defaultValue = "ASC", required = false) String sort,
            @RequestParam(name = "name", defaultValue = "ASC", required = false) String name,
            @RequestParam(name = "page", defaultValue = "0", required = false) Integer page)
    {
        if (option == null || option.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Sort sortable = null;
        if (sort.equals("ASC")) {
            sortable = Sort.by(option).ascending();
        } else if (sort.equals("DESC")) {
            sortable = Sort.by(option).descending();
        } else {
            return ResponseEntity.badRequest().body("Sai tham số");
        }

        Pageable pageable = PageRequest.of(page, 20, sortable);
        List<Product> products = productService.searchByName(name);
//        List<IProductResponse> products = null;
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
