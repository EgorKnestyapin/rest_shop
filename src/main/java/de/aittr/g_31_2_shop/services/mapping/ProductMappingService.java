package de.aittr.g_31_2_shop.services.mapping;

import de.aittr.g_31_2_shop.domain.jdbc.CommonProduct;
import de.aittr.g_31_2_shop.domain.dto.ProductDto;
import de.aittr.g_31_2_shop.domain.interfaces.Product;
import de.aittr.g_31_2_shop.domain.jpa.JpaProduct;
import org.springframework.stereotype.Service;

@Service
public class ProductMappingService {
    public ProductDto mapProductToDto(Product product) {
        int id = product.getId();
        String name = product.getName();
        double price = product.getPrice();
        return new ProductDto(id, name, price);
    }

    public CommonProduct mapDtoToCommonProduct(ProductDto productDto) {
        int id = productDto.getId();
        String name = productDto.getName();
        double price = productDto.getPrice();
        return new CommonProduct(id, true, name, price);
    }

    public JpaProduct mapDtoToJpaProduct(ProductDto productDto) {
        int id = productDto.getId();
        String name = productDto.getName();
        double price = productDto.getPrice();
        return new JpaProduct(id, name, price, true);
    }
}
