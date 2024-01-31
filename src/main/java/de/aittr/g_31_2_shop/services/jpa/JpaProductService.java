package de.aittr.g_31_2_shop.services.jpa;

import de.aittr.g_31_2_shop.domain.dto.ProductDto;
import de.aittr.g_31_2_shop.domain.interfaces.Product;
import de.aittr.g_31_2_shop.domain.jpa.JpaProduct;
import de.aittr.g_31_2_shop.exception_handling.exceptions.FirstTestException;
import de.aittr.g_31_2_shop.exception_handling.exceptions.FourthTestException;
import de.aittr.g_31_2_shop.exception_handling.exceptions.SecondTestException;
import de.aittr.g_31_2_shop.exception_handling.exceptions.ThirdTestException;
import de.aittr.g_31_2_shop.repositories.jpa.JpaProductRepository;
import de.aittr.g_31_2_shop.services.interfaces.ProductService;
import de.aittr.g_31_2_shop.services.mapping.ProductMappingService;
import jakarta.transaction.Transactional;
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
        try {
            JpaProduct entity = mappingService.mapDtoToJpaProduct(productDto);
            entity.setId(0);
            entity = repository.save(entity);
            return mappingService.mapProductToDto(entity);
        } catch (Exception e) {
            throw new FourthTestException(e.getMessage());
        }
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
        Product product = repository.findById(id).orElse(null);
        if (product != null && product.isActive()) {
            return mappingService.mapProductToDto(product);
        }
        throw new ThirdTestException("Продукт с указанным идентификатором отсутствует в базе данных");
    }

    @Override
    public void update(ProductDto productDto) {
        JpaProduct jpaProduct = mappingService.mapDtoToJpaProduct(productDto);
        repository.save(jpaProduct);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
//        repository.deleteById(id); альтернативное решение

        JpaProduct jpaProduct = repository.findById(id).orElse(null);

        if (jpaProduct != null && jpaProduct.isActive()) {
            jpaProduct.setActive(false);
        }
    }

    @Override
    @Transactional
    public void deleteByName(String name) {
//        JpaProduct jpaProduct = repository.findByName(name); альтернативное решение
//        repository.deleteById(jpaProduct.getId());

        JpaProduct jpaProduct = repository.findByName(name);

        if (jpaProduct != null && jpaProduct.isActive()) {
            jpaProduct.setActive(false);
        }
    }

    @Override
    @Transactional
    public void restoreById(int id) {
//        repository.restoreById(id); альтернативное решение

        JpaProduct jpaProduct = repository.findById(id).orElse(null);

        if (jpaProduct != null && !jpaProduct.isActive()) {
            jpaProduct.setActive(true);
        }
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
        return activeProducts
                .stream()
                .mapToDouble(ProductDto::getPrice)
                .average()
                .orElse(0);
    }
}
