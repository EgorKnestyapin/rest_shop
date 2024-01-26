package de.aittr.g_31_2_shop.services.jpa;

import de.aittr.g_31_2_shop.domain.dto.ProductDto;
import de.aittr.g_31_2_shop.domain.interfaces.Product;
import de.aittr.g_31_2_shop.domain.jpa.JpaProduct;
import de.aittr.g_31_2_shop.repositories.jpa.JpaProductRepository;
import de.aittr.g_31_2_shop.services.interfaces.ProductService;
import de.aittr.g_31_2_shop.services.mapping.ProductMappingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaProductService implements ProductService {
    private final JpaProductRepository repository;
    private final ProductMappingService mappingService;

    public JpaProductService(JpaProductRepository repository, ProductMappingService mappingService) {
        this.repository = repository;
        this.mappingService = mappingService;
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        JpaProduct entity = mappingService.mapDtoToJpaProduct(productDto);
        entity.setId(0);
        entity = repository.save(entity);
        return mappingService.mapProductToDto(entity);
    }

    @Override
    public List<ProductDto> getAllActiveProducts() {
        return repository.findAll()
                .stream()
                .filter(JpaProduct::isActive)
                .map(mappingService::mapProductToDto)
                .toList();
    }

    @Override
    public ProductDto getActiveProductById(int id) {
        JpaProduct jpaProduct = repository.findById(id).orElse(null);
        return jpaProduct != null && jpaProduct.isActive() ? mappingService.mapProductToDto(jpaProduct) : null;
    }

    @Override
    public void update(ProductDto productDto) {
        JpaProduct jpaProduct = mappingService.mapDtoToJpaProduct(productDto);
        repository.save(jpaProduct);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteByName(String name) {
        JpaProduct jpaProduct = repository.findByName(name);
        repository.deleteById(jpaProduct.getId());
    }

    @Override
    public void restoreById(int id) {
        repository.restoreById(id);
    }

    @Override
    public int getActiveProductCount() {
        List<ProductDto> activeProducts = getAllActiveProducts();
        return activeProducts.size();
    }

    @Override
    public double getActiveProductsTotalPrice() {
        List<ProductDto> activeProducts = getAllActiveProducts();
        return activeProducts
                .stream()
                .mapToDouble(ProductDto::getPrice)
                .sum();
    }

    @Override
    public double getActiveProductAveragePrice() {
        List<ProductDto> activeProducts = getAllActiveProducts();
        double totalPrice = activeProducts
                .stream()
                .mapToDouble(ProductDto::getPrice)
                .sum();
        return totalPrice / activeProducts.size();
    }
}
