package ru.gb.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.spring.models.Product;
import ru.gb.spring.services.ProductService;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "all_products";
    }

    @GetMapping("/add")
    public String showProductAddForm() {
        return "product_add_form";
    }

    @PostMapping("/add")
    public String addNewProduct(@ModelAttribute Product newProduct) {
        productService.addProduct(newProduct);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String showProductEditForm(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "product_edit_form";
    }

    @PostMapping("/edit")
    public String editProduct(@ModelAttribute Product editedProduct) {
        // Todo edit logic
        productService.editProduct(editedProduct);
        return "redirect:/products";
    }

    @GetMapping("/json/{id}")
    @ResponseBody
    public Product showProductJson(@PathVariable int id) {
        return productService.getProductById(id);
    }
}
