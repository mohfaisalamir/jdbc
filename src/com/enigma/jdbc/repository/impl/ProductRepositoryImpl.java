package com.enigma.jdbc.repository.impl;

import com.enigma.jdbc.config.DBConnector;
import com.enigma.jdbc.entity.Product;
import com.enigma.jdbc.repository.ProductRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {
    @Override
    public Product saveProduct(Product product) {
        try(Connection connection = DBConnector.connection()) {
            String query = "INSERT INTO m_product (name,price) VALUES (?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(
                    query,
                    Statement.RETURN_GENERATED_KEYS
            );
           // preparedStatement.setInt(1,product.getId()); jangan pakek ini, nanti idnya fleksibel gak urut.
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()) {
                System.out.println("Inserted product id: " + generatedKeys.getInt(1));
                product.setPrice(generatedKeys.getInt(1));
            }

            System.out.println("Product saved");
            preparedStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    @Override
    public Product findProductById(Integer Id) {
        Product product = new Product();
        try (Connection connection = DBConnector.connection()){
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM m_product WHERE id = ?"
            );
            preparedStatement.setInt(1, Id);
            ResultSet resultSet = preparedStatement.executeQuery();

            int id =0, price = 0;
            String name = null;
            if(resultSet.next()) {
                id = resultSet.getInt(1);
                name = resultSet.getString(2);
                price = resultSet.getInt(3);
                System.out.println();
            }
            product = new Product(id, name,price);
            resultSet.close();
            preparedStatement.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    @Override
    public List<Product> findAllProducts() {
        List<Product> products = new ArrayList<>();
        try(Connection connection = DBConnector.connection()){
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM m_product"
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int price = resultSet.getInt(3);

                Product product = new Product(id,name,price);
                products.add(product);

            }
            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public Product updateProduct(Product product) {
        try(Connection connection = DBConnector.connection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE m_product SET name = ?, price = ? WHERE id = ?"
                    , Statement.RETURN_GENERATED_KEYS
            );
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getId());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()) {
                product.setId(generatedKeys.getInt(1));
            }
            System.out.println("Product updated");
            preparedStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void deleteProduct(Integer Id) {
        try(Connection connection = DBConnector.connection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM m_product WHERE id = ?",
                    Statement.RETURN_GENERATED_KEYS
            );
            preparedStatement.setInt(1, Id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Product deleted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
