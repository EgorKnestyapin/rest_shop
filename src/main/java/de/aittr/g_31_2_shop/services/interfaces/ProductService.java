package de.aittr.g_31_2_shop.services.interfaces;

import de.aittr.g_31_2_shop.domain.interfaces.Product;

import java.util.List;

public interface ProductService {
    Product save(Product product);

    List<Product> getAllActiveProducts();

    Product getActiveProductById(int id);

    void update(Product product);

    void deleteById(int id);

    void deleteByName(int id);

    void restoreById(int id);

    int getActiveProductCount();

    double getActiveProductsTotalPrice();

    double getActiveProductAveragePrice();
}
