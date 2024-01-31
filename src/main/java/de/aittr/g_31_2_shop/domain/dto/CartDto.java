package de.aittr.g_31_2_shop.domain.dto;

import de.aittr.g_31_2_shop.domain.jpa.JpaCustomer;
import de.aittr.g_31_2_shop.domain.jpa.JpaProduct;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CartDto {
    private int id;

    private final List<ProductDto> productList;

    public CartDto(int id, List<ProductDto> productList) {
        this.id = id;
        this.productList = productList;
    }

    public int getId() {
        return id;
    }

    public List<ProductDto> getProductList() {
        return productList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartDto cartDto = (CartDto) o;
        return id == cartDto.id && Objects.equals(productList, cartDto.productList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productList);
    }

    @Override
    public String toString() {
        return "CartDto{" +
                "id=" + id +
                ", productList=" + productList +
                '}';
    }
}
