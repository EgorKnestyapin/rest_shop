package de.aittr.g_31_2_shop.domain.interfaces;

public interface Customer {
    int getId();

    void setId(int id);

    boolean isActive();

    void setActive(boolean isActive);

    String getName();

    void setName(String name);

    Cart getCart();

    void setCart(Cart cart);

    int getAge();

    String getEmail();
}
