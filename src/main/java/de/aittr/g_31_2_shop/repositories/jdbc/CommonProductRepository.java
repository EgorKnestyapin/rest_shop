package de.aittr.g_31_2_shop.repositories.jdbc;

import de.aittr.g_31_2_shop.domain.jdbc.CommonProduct;
import de.aittr.g_31_2_shop.domain.interfaces.Product;
import de.aittr.g_31_2_shop.repositories.interfaces.ProductRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static de.aittr.g_31_2_shop.repositories.jdbc.DBConnector.getConnection;

@Repository
public class CommonProductRepository implements ProductRepository {
    private final String ID = "id";
    private final String PRICE = "price";
    private final String NAME = "name";

    @Override
    public Product save(Product product) {
        try (Connection connection = getConnection()) {
            String query = String.format(Locale.US, "INSERT INTO `product` (`name`, `price`, `is_active`) " +
                    "VALUES ('%s', '%.2f', '1');", product.getName(), product.getPrice());
            connection.createStatement().execute(query);

            query = "select id from product order by id desc limit 1;";
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            resultSet.next();
            int id = resultSet.getInt(ID);

            product.setId(id);
            return product;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getAll() {
        try (Connection connection = getConnection()) {
            String query = String.format("select id, name, price from product where is_active = 1;");
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            List<Product> products = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt(ID);
                String name = resultSet.getString(NAME);
                double price = resultSet.getDouble(PRICE);
                Product product = new CommonProduct(id, true, name, price);
                products.add(product);
            }
            return products;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product getById(int id) {
        try (Connection connection = getConnection()) {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void update(Product product) {
        try (Connection connection = getConnection()) {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(int id) {
        try (Connection connection = getConnection()) {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
