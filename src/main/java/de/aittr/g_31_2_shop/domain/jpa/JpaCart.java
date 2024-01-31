package de.aittr.g_31_2_shop.domain.jpa;

import de.aittr.g_31_2_shop.domain.interfaces.Cart;
import de.aittr.g_31_2_shop.domain.interfaces.Customer;
import de.aittr.g_31_2_shop.domain.interfaces.Product;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cart")
public class JpaCart implements Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @ManyToMany
    @JoinTable(
            name = "cart_product",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<JpaProduct> productList = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "customer_id")
    private JpaCustomer customer;

    public JpaCart() {
    }

    public JpaCart(int id, List<JpaProduct> productList) {
        this.id = id;
        this.productList = productList;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public List<Product> getProducts() {
        // TODO посмотреть, как будет на практике, потом переделать
        return new ArrayList<>(productList);
    }

    @Override
    public void addProduct(Product product) {
        try {
            productList.add((JpaProduct) product);
        } catch (Exception e) {
            throw new IllegalArgumentException("В корзину JpaCart помещён несовместимый тип продукта!");
        }
    }

    @Override
    public void deleteProductById(int productId) {
        // TODO проверить работу на практике и при необходимости переделать
        productList.removeIf(jpaProduct -> jpaProduct.getId() == productId);
    }

    @Override
    public void clear() {
        productList.clear();
    }

    @Override
    public double getTotalPrice() {
        return productList
                .stream()
                .filter(JpaProduct::isActive)
                .mapToDouble(JpaProduct::getPrice)
                .sum();
    }

    @Override
    public double getAveragePrice() {
        return productList
                .stream()
                .filter(JpaProduct::isActive)
                .mapToDouble(JpaProduct::getPrice)
                .average()
                .orElse(0);
    }

    public List<Product> getProductList() {
        return new ArrayList<>(productList);
    }

    public void setProductList(List<JpaProduct> productList) {
        this.productList = productList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(JpaCustomer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JpaCart jpaCart = (JpaCart) o;
        return id == jpaCart.id && Objects.equals(productList, jpaCart.productList) && Objects.equals(customer, jpaCart.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productList, customer);
    }

    @Override
    public String toString() {
        return "JpaCart{" +
                "id=" + id +
                ", productList=" + productList +
                ", customer=" + customer +
                '}';
    }
}
