package de.aittr.g_31_2_shop.domain.jpa;

import de.aittr.g_31_2_shop.domain.interfaces.Cart;
import de.aittr.g_31_2_shop.domain.interfaces.Customer;
import de.aittr.g_31_2_shop.domain.jdbc.CommonCustomer;

import java.util.Objects;

public class JpaCustomer implements Customer {
    private int id;
    private boolean isActive;
    private String name;

    private Cart cart;

    public JpaCustomer() {
        this.isActive = true;
    }

    public JpaCustomer(int id, boolean isActive, String name, Cart cart) {
        this.id = id;
        this.isActive = isActive;
        this.name = name;
        this.cart = cart;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public Cart getCart() {
        return cart;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JpaCustomer that = (JpaCustomer) o;
        return id == that.id && isActive == that.isActive && Objects.equals(name, that.name) && Objects.equals(cart, that.cart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isActive, name, cart);
    }

    @Override
    public String toString() {
        return "JpaCustomer{" +
                "id=" + id +
                ", isActive=" + isActive +
                ", name='" + name + '\'' +
                ", cart=" + cart +
                '}';
    }
}
