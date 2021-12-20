package ru.gb.spring.repositories;

import org.springframework.stereotype.Component;
import ru.gb.spring.models.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> products;

    @PostConstruct
    public void init() {
        this.products = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public Product getProductById(int id) {
        for (Product p: products) {
            if(p.getId() == id) {
                return p;
            }
        }
        throw new RuntimeException("Product not found");
    }

    public boolean addProduct(Product product) {
        return products.add(product);
    }
}
