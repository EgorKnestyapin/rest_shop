package de.aittr.g_31_2_shop.services.jdbc;

import de.aittr.g_31_2_shop.domain.jdbc.CommonProduct;
import de.aittr.g_31_2_shop.domain.dto.ProductDto;
import de.aittr.g_31_2_shop.domain.interfaces.Product;
import de.aittr.g_31_2_shop.repositories.interfaces.ProductRepository;
import de.aittr.g_31_2_shop.services.interfaces.ProductService;
import de.aittr.g_31_2_shop.services.mapping.ProductMappingService;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public class CommonProductService implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMappingService productMappingService;

    public CommonProductService(ProductRepository productRepository, ProductMappingService productMappingService) {
        this.productRepository = productRepository;
        this.productMappingService = productMappingService;
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        CommonProduct commonProduct = productMappingService.mapDtoToCommonProduct(productDto);
        Product savedProduct = productRepository.save(commonProduct);
        return productMappingService.mapProductToDto(savedProduct);
    }

    @Override
    public List<ProductDto> getAllActiveProducts() {
        return productRepository.getAll()
                .stream()
                .map(productMappingService::mapProductToDto)
                .toList();
    }

    @Override
    public ProductDto getActiveProductById(int id) {
        return null;
    }

    @Override
    public void update(ProductDto productDto) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteByName(String name) {

    }

    @Override
    public void restoreById(int id) {

    }

    @Override
    public int getActiveProductCount() {
        return 0;
    }

    @Override
    public double getActiveProductsTotalPrice() {
        return 0;
    }

    @Override
    public double getActiveProductAveragePrice() {
        return 0;
    }
}
