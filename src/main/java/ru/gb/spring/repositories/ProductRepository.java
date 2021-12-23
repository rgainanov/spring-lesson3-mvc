package ru.gb.spring.repositories;

import org.springframework.stereotype.Component;
import ru.gb.spring.models.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> products;

    @PostConstruct
    public void init() {
        this.products = new ArrayList<>();
        this.products.add(new Product(1, "Milk", 0.6F));
        this.products.add(new Product(2, "Bread", 0.8F));
        this.products.add(new Product(3, "Meat", 1.6F));
    }

    public List<Product> getProducts() {
        Collections.sort(products, ProductRepository.sortById);
        return Collections.unmodifiableList(products);
    }

    public Product getProductById(int id) {
        for (Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        throw new RuntimeException("Product not found");
    }

    public boolean addProduct(Product product) {
        return products.add(product);
    }

    public boolean editProduct(Product editedProduct) {
        Product oldProduct = getProductById(editedProduct.getId());
        products.remove(oldProduct);
        return products.add(editedProduct);
    }

    public static Comparator<Product> sortById = (o1, o2) -> o1.getId() - o2.getId();
}
