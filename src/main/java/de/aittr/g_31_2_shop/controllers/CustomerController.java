package de.aittr.g_31_2_shop.controllers;

import de.aittr.g_31_2_shop.domain.jdbc.CommonCustomer;
import de.aittr.g_31_2_shop.domain.interfaces.Customer;
import de.aittr.g_31_2_shop.services.interfaces.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public Customer save(@RequestBody CommonCustomer customer) {
        return customerService.save(customer);
    }

    @GetMapping
    public List<Customer> getAll() {
        return customerService.getAllActiveCustomers();
    }
}
