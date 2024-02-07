package de.aittr.g_31_2_shop.services.jpa;

import de.aittr.g_31_2_shop.domain.dto.ProductDto;
import de.aittr.g_31_2_shop.domain.interfaces.Product;
import de.aittr.g_31_2_shop.domain.jpa.JpaProduct;
import de.aittr.g_31_2_shop.domain.jpa.Task;
import de.aittr.g_31_2_shop.exception_handling.exceptions.*;
import de.aittr.g_31_2_shop.repositories.jpa.JpaProductRepository;
import de.aittr.g_31_2_shop.scheduling.ScheduleExecutor;
import de.aittr.g_31_2_shop.services.interfaces.ProductService;
import de.aittr.g_31_2_shop.services.mapping.ProductMappingService;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaProductService implements ProductService {
    private final JpaProductRepository repository;
    private final ProductMappingService mappingService;
//    private final Logger logger = LogManager.getLogger(JpaProductService.class);
    private final Logger logger = LoggerFactory.getLogger(JpaProductService.class);

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
            throw new ProductValidationException("Incorrect values of product fields", e);
        }
    }

    @Override
    public List<ProductDto> getAllActiveProducts() {
        Task task = new Task("Method getAllActiveProducts called");
        ScheduleExecutor.scheduleAndExecuteTask(task);
        // Здесь будет JoinPoint, сюда будет внедряться вспомогательный код
        return repository.findAll()
                .stream()
                .filter(JpaProduct::isActive)
                .map(mappingService::mapProductToDto)
                .toList();
    }

    @Override
    public ProductDto getActiveProductById(int id) {

//        logger.log(Level.INFO, String.format("Запрошен продукт с идентификатором %d.", id));
//        logger.log(Level.WARN, String.format("Запрошен продукт с идентификатором %d.", id));
//        logger.log(Level.ERROR, String.format("Запрошен продукт с идентификатором %d.", id));

//        logger.info(String.format("Запрошен продукт с идентификатором %d.", id));
//        logger.warn(String.format("Запрошен продукт с идентификатором %d.", id));
//        logger.error(String.format("Запрошен продукт с идентификатором %d.", id));

        Product product = repository.findById(id).orElse(null);
        if (product != null && product.isActive()) {
            return mappingService.mapProductToDto(product);
        }
        throw new ThirdTestException("Продукт с указанным идентификатором отсутствует в базе данных");
    }

    @Override
    public void update(ProductDto productDto) {
        try {
            JpaProduct jpaProduct = mappingService.mapDtoToJpaProduct(productDto);
            repository.save(jpaProduct);
        } catch (Exception e) {
            throw new UpdateProductException(e.getMessage());
        }

    }

    @Override
    @Transactional
    public void deleteById(int id) {
//        repository.deleteById(id); альтернативное решение

        JpaProduct jpaProduct = repository.findById(id).orElse(null);

        if (jpaProduct != null && jpaProduct.isActive()) {
            jpaProduct.setActive(false);
            return;
        }
        throw new DeleteProductException("Удаление невозможно, так как продукт с таким ID отсутствует в базе данных");

    }

    @Override
    @Transactional
    public void deleteByName(String name) {
//        JpaProduct jpaProduct = repository.findByName(name); альтернативное решение
//        repository.deleteById(jpaProduct.getId());

        JpaProduct jpaProduct = repository.findByName(name);

        if (jpaProduct != null && jpaProduct.isActive()) {
            jpaProduct.setActive(false);
            return;
        }
        throw new DeleteProductException("Удаление невозможно, так как продукт с таким названием отсутствует " +
                "в базе данных");
    }

    @Override
    @Transactional
    public void restoreById(int id) {
//        repository.restoreById(id); альтернативное решение

        JpaProduct jpaProduct = repository.findById(id).orElse(null);

        if (jpaProduct != null && !jpaProduct.isActive()) {
            jpaProduct.setActive(true);
            return;
        }
        throw new RestoreProductException("Восстановление невозможно, так как продукт с таким ID отсутствует " +
                "в базе данных, либо уже активен");
    }

    @Override
    public int getActiveProductCount() {
        return (int) repository.findAll()
                .stream()
                .filter(JpaProduct::isActive)
                .count();
    }

    @Override
    public double getActiveProductsTotalPrice() {
        return repository.findAll()
                .stream()
                .filter(JpaProduct::isActive)
                .mapToDouble(JpaProduct::getPrice)
                .sum();
    }

    @Override
    public double getActiveProductAveragePrice() {
        return repository.findAll()
                .stream()
                .filter(JpaProduct::isActive)
                .mapToDouble(JpaProduct::getPrice)
                .average()
                .orElse(0);
    }

    public JpaProduct getLastAddedProduct() {
        return repository.findLastAddedProduct();
    }
}
