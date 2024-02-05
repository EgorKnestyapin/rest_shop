package de.aittr.g_31_2_shop.controllers;

import de.aittr.g_31_2_shop.domain.dto.CustomerDto;
import de.aittr.g_31_2_shop.services.interfaces.CustomerService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public CustomerDto save(@Valid @RequestBody CustomerDto customer) {
        logger.info("По эндпоинту /customers POST запросом вызван метод контроллера save с параметром {}", customer);
        return customerService.save(customer);
    }

    @GetMapping
    public List<CustomerDto> getAll() {
        logger.info("По эндпоинту /customers GET запросом вызван метод контроллера getAll");
        return customerService.getAllActiveCustomers();
    }

    @GetMapping("/{id}")
    public CustomerDto getById(@PathVariable int id) {
        logger.info("По эндпоинту /customers/{id} GET запросом вызван метод контроллера getById с параметром {}", id);
        return customerService.getActiveCustomerById(id);
    }
}
