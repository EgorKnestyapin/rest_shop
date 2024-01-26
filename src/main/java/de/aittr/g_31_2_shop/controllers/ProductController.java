package de.aittr.g_31_2_shop.controllers;

import de.aittr.g_31_2_shop.domain.dto.ProductDto;
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
    public ProductDto save(@RequestBody ProductDto productDto) {
        return productService.save(productDto);
    }

    @GetMapping
    public List<ProductDto> getAll() {
        return productService.getAllActiveProducts();
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable int id) {
        return productService.getActiveProductById(id);
    }

    @PutMapping
    public void update(@RequestBody ProductDto productDto) {
        productService.update(productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        productService.deleteById(id);
    }

    @DeleteMapping("/delete/{name}")
    public void deleteByName(@PathVariable String name) {
        productService.deleteByName(name);
    }

    @PutMapping("/restore/{id}")
    public void restoreById(@PathVariable int id) {
        productService.restoreById(id);
    }

    @GetMapping("/count")
    public int getCount() {
        return productService.getActiveProductCount();
    }

    @GetMapping("/totalPrice")
    public double getTotalPrice() {
        return productService.getActiveProductsTotalPrice();
    }

    @GetMapping("/averagePrice")
    public double getAveragePrice() {
        return productService.getActiveProductAveragePrice();
    }
}
