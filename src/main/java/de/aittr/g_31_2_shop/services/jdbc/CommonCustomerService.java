package de.aittr.g_31_2_shop.services.jdbc;

import de.aittr.g_31_2_shop.domain.dto.CustomerDto;
import de.aittr.g_31_2_shop.domain.interfaces.Customer;
import de.aittr.g_31_2_shop.repositories.interfaces.CustomerRepository;
import de.aittr.g_31_2_shop.services.interfaces.CustomerService;
import de.aittr.g_31_2_shop.services.mapping.CustomerMappingService;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public class CommonCustomerService implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMappingService customerMappingService;

    public CommonCustomerService(CustomerRepository customerRepository, CustomerMappingService customerMappingService) {
        this.customerRepository = customerRepository;
        this.customerMappingService = customerMappingService;
    }

    @Override
    public CustomerDto save(CustomerDto customerDto) {
        Customer customer = customerMappingService.mapDtoToCommonCustomer(customerDto);
        Customer saved = customerRepository.save(customer);
        return customerMappingService.mapCustomerEntityToDto(saved);
    }

    @Override
    public List<CustomerDto> getAllActiveCustomers() {
        return customerRepository.getAll()
                .stream()
                .map(customerMappingService::mapCustomerEntityToDto)
                .toList();
    }

    @Override
    public CustomerDto getActiveCustomerById(int id) {
        return null;
    }

    @Override
    public void update(CustomerDto customer) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteByName(int id) {

    }

    @Override
    public void restoreById(int id) {

    }

    @Override
    public int getActiveCustomerCount() {
        return 0;
    }

    @Override
    public double getCartTotalPriceById(int customerId) {
        return 0;
    }

    @Override
    public double getAverageProductPriceById(int customerId) {
        return 0;
    }

    @Override
    public void addProductToCart(int customerId, int productId) {

    }

    @Override
    public void deleteProductFromCart(int customerId, int productId) {

    }

    @Override
    public void clearCartById(int customerId) {

    }
}
