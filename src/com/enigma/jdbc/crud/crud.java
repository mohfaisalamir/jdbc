package com.enigma.jdbc.crud;

import com.enigma.jdbc.config.DBConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class crud {
    public static void viewAll() {
        try(Connection connection = DBConnector.connection()) {
            ResultSet resultSet = connection.createStatement().executeQuery("select * from customer_table");
            while(resultSet.next()) {
                System.out.println("\n"+"=".repeat(20));
                System.out.println("Id      : "+resultSet.getInt(1));
                System.out.println("Name    : "+resultSet.getString(2));
                System.out.println("Phone   : "+resultSet.getString(3));
                System.out.println("IsMember: "+resultSet.getBoolean(4));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void insert() {
        try(Connection connection = DBConnector.connection()) {
            ResultSet resultSet = connection.createStatement().executeQuery("select * from customer_table");
            while(resultSet.next()) {
                System.out.println("\n"+"=".repeat(20));
                System.out.println("Id      : "+resultSet.getInt(1));
                System.out.println("Name    : "+resultSet.getString(2));
                System.out.println("Phone   : "+resultSet.getString(3));
                System.out.println("IsMember: "+resultSet.getBoolean(4));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void update() {
        try(Connection connection = DBConnector.connection()){
            try(Statement statement = connection.createStatement()) {
                int resultSet = statement.executeUpdate(  "update customer_table set customer_name='Panthera',member_status=false where id_customer=11");
                System.out.println("Update Customer Table : Berhasil");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void delete() {
        try(Connection connection = DBConnector.connection()){
            try(Statement statement = connection.createStatement()) {
                int resultSet = statement.executeUpdate("delete from customer_table where id_customer=12");
                System.out.println("delete Customer Table : Berhasil");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
/*
* Makasih @Rizdalah matrabaknya, semoga Allah membantumu berhenti dari kecanduan coli
* */



/*        try (Connection connection = DBConnector.connection()){
            try (Statement statement = connection.createStatement()){
                ResultSet resultSet = statement.executeQuery("select * from customer_table where id_customer in (30)");
                if (resultSet.next()){
                    System.out.println("\n"+"=".repeat(20));
                    System.out.println("Id      : "+resultSet.getInt(1));
                    System.out.println("Name    : "+resultSet.getString(2));
                    System.out.println("Phone   : "+resultSet.getString(3));
                    System.out.println("IsMember: "+resultSet.getBoolean(4));
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
// BERIKUT ADALAH CRUD MENGGUNAKAN DATA YANG FLEKSIBEL (BISA DIINPUT)

