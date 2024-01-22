package de.aittr.g_31_2_shop.repositories;

import de.aittr.g_31_2_shop.domain.CommonCart;
import de.aittr.g_31_2_shop.domain.CommonCustomer;
import de.aittr.g_31_2_shop.domain.interfaces.Customer;
import de.aittr.g_31_2_shop.repositories.interfaces.CustomerRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Locale;

import static de.aittr.g_31_2_shop.repositories.DBConnector.getConnection;

@Repository
public class CommonCustomerRepository implements CustomerRepository {
    private final String ID = "id";
    private final String PRICE = "price";
    private final String NAME = "name";

    @Override
    public Customer save(Customer customer) {
        try (Connection connection = getConnection()) {
            String query = String.format("INSERT INTO `31_2_shop`.`customer` (`name`, `is_active`) VALUES ('%s', '1');",
                    customer.getName());
            Statement statement = connection.createStatement();
            statement.execute(query);

            query = "select id from customer order by id desc limit 1;";
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            int id = resultSet.getInt(ID);

            query = String.format("INSERT INTO `31_2_shop`.`cart` (`customer_id`) VALUES ('%d');", id);
            statement.execute(query);
            customer.setId(id);
            query = "select id from cart order by id desc limit 1;";
            resultSet = statement.executeQuery(query);
            resultSet.next();
            id = resultSet.getInt(ID);
            customer.setCart(new CommonCart(id));

            return customer;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Customer> getAll() {
        return null;
    }

    @Override
    public Customer getById(int id) {
        return null;
    }

    @Override
    public void update(Customer customer) {

    }

    @Override
    public void deleteById(int id) {

    }
}
