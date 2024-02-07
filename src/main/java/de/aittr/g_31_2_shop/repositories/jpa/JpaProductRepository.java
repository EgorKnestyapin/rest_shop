package de.aittr.g_31_2_shop.repositories.jpa;

import de.aittr.g_31_2_shop.domain.jpa.JpaProduct;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaProductRepository extends JpaRepository<JpaProduct, Integer> {

    @Modifying
    @Query(value = "UPDATE `31_2_shop`.`product` SET `is_active` = '0' WHERE id = :id", nativeQuery = true)
    void deleteById(@Param("id") Integer id);

    @Modifying
    @Query(value = "UPDATE `31_2_shop`.`product` SET `is_active` = '1' WHERE id = :id", nativeQuery = true)
    void restoreById(@Param("id") Integer id);

    JpaProduct findByName(String name);

    @Query(value = "SELECT * FROM product ORDER BY id DESC LIMIT 1", nativeQuery = true)
    JpaProduct findLastAddedProduct();
}
