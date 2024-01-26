package de.aittr.g_31_2_shop.services.interfaces;

import de.aittr.g_31_2_shop.domain.dto.ProductDto;
import de.aittr.g_31_2_shop.domain.interfaces.Product;

import java.util.List;

public interface ProductService {
    ProductDto save(ProductDto productDto);

    List<ProductDto> getAllActiveProducts();

    ProductDto getActiveProductById(int id);

    void update(ProductDto productDto);

    void deleteById(int id);

    void deleteByName(String name);

    void restoreById(int id);

    int getActiveProductCount();

    double getActiveProductsTotalPrice();

    double getActiveProductAveragePrice();
}
