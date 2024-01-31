package de.aittr.g_31_2_shop.controllers;

import de.aittr.g_31_2_shop.domain.dto.CustomerDto;
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
    public CustomerDto save(@RequestBody CustomerDto customer) {
        return customerService.save(customer);
    }

    @GetMapping
    public List<CustomerDto> getAll() {
        return customerService.getAllActiveCustomers();
    }

    @GetMapping("/{id}")
    public CustomerDto getById(@PathVariable int id) {
        return customerService.getActiveCustomerById(id);
    }
}
