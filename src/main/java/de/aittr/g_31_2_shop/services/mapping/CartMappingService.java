package de.aittr.g_31_2_shop.services.mapping;

import de.aittr.g_31_2_shop.domain.dto.CartDto;
import de.aittr.g_31_2_shop.domain.dto.ProductDto;
import de.aittr.g_31_2_shop.domain.interfaces.Cart;
import de.aittr.g_31_2_shop.domain.interfaces.Product;
import de.aittr.g_31_2_shop.domain.jdbc.CommonCart;
import de.aittr.g_31_2_shop.domain.jdbc.CommonProduct;
import de.aittr.g_31_2_shop.domain.jpa.JpaCart;
import de.aittr.g_31_2_shop.domain.jpa.JpaProduct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartMappingService {
    private final ProductMappingService productMappingService;

    public CartMappingService(ProductMappingService productMappingService) {
        this.productMappingService = productMappingService;
    }

    public CartDto mapCartEntityToDto(Cart cart) {
        int id = cart.getId();
        List<ProductDto> productDtos = cart.getProducts()
                .stream()
                .map(productMappingService::mapProductToDto)
                .toList();
        return new CartDto(id, productDtos);
    }

    public CommonCart mapDtoToCommonCart(CartDto cartDto) {
        int id = cartDto.getId();
        List<Product> carts = new ArrayList<>(cartDto.getProductList()
                .stream()
                .map(productMappingService::mapDtoToCommonProduct)
                .toList());
        return new CommonCart(id, carts);
    }

    public JpaCart mapDtoToJpaCart(CartDto cartDto) {
        int id = cartDto.getId();
        List<JpaProduct> carts = cartDto.getProductList()
                .stream()
                .map(productMappingService::mapDtoToJpaProduct)
                .toList();
        return new JpaCart(id, carts);
    }
}
