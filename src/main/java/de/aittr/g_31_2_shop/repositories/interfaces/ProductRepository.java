package de.aittr.g_31_2_shop.repositories.interfaces;

import de.aittr.g_31_2_shop.domain.interfaces.Product;

import java.util.List;

public interface ProductRepository {
    Product save(Product product);
    List<Product> getAll();
    Product getById(int id);
    void update(Product product);
    void deleteById(int id);
}
