package de.aittr.g_31_2_shop.controllers;

import de.aittr.g_31_2_shop.domain.CommonProduct;
import de.aittr.g_31_2_shop.domain.interfaces.Product;
import de.aittr.g_31_2_shop.services.interfaces.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product save(@RequestBody CommonProduct product) {
        return productService.save(product);
    }

    @GetMapping
    public List<Product> getAllActiveProducts() {
        return productService.getAllActiveProducts();
    }
}
