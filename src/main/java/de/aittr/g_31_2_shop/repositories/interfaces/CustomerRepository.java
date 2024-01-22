package de.aittr.g_31_2_shop.repositories.interfaces;

import de.aittr.g_31_2_shop.domain.interfaces.Customer;
import de.aittr.g_31_2_shop.domain.interfaces.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CustomerRepository {
    Customer save(Customer customer);

    List<Customer> getAll();

    Customer getById(int id);

    void update(Customer customer);

    void deleteById(int id);
}
