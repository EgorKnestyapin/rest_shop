package de.aittr.g_31_2_shop.domain.interfaces;

import java.util.List;

public interface Cart {
    int getId();
    List<Product> getProducts();
    void addProduct(Product product);
    void deleteProductById(int id);
    void clear();
    double getTotalPrice();
    double getAveragePrice();
}
