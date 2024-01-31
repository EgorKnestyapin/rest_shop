package de.aittr.g_31_2_shop.controllers;

import de.aittr.g_31_2_shop.domain.dto.ProductDto;
import de.aittr.g_31_2_shop.exception_handling.Response;
import de.aittr.g_31_2_shop.exception_handling.exceptions.FirstTestException;
import de.aittr.g_31_2_shop.services.interfaces.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    public ProductDto save(@Valid @RequestBody ProductDto productDto) {
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

    @DeleteMapping("/del_by_name/{name}")
    public void deleteByName(@PathVariable String name) {
        productService.deleteByName(name);
    }

    @PutMapping("/{id}")
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

    // 1 способ - создание метода-обработчика в контроллере, где мы ожидаем ошибки
    // Минус - если в разных контроллерах требуется обрабатывать ошибки одинаково,
    // то нам придется написать один и тот же обработчик в разных контроллерах.
    // Плюс - если в разных контроллерах требуется обработать ошибки по-разному,
    // то мы можем это сделать
    @ExceptionHandler(FirstTestException.class)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public Response handleException(FirstTestException e) {
        return new Response(e.getMessage());
    }
}
