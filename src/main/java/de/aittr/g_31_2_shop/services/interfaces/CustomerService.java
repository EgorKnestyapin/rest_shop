package de.aittr.g_31_2_shop.services.interfaces;

import de.aittr.g_31_2_shop.domain.interfaces.Customer;
import de.aittr.g_31_2_shop.domain.interfaces.Product;

import java.util.List;

public interface CustomerService {
    Customer save(Customer customer);

    List<Customer> getAllActiveCustomers();

    Customer getActiveCustomerById(int id);

    void update(Customer customer);

    void deleteById(int id);

}
