package com.enigma.jdbc;

import com.enigma.jdbc.config.DBConnector;
import com.enigma.jdbc.crud.crud;

import java.sql.*;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // membuat tabel baru "m_user_credential", kita bisa lho membuat tabel di kodingan
        // user credential itu yang, kalo gak username, email, ya id (nomer ktp kek, atau nim kek, gitu)..

        //INSERT DATA CUSTOMER
//        saveDataMember();
        //DELETE DATA CUSTOMER
//        deleteDataMember();
        //UPDATE DATA CUSTOMER
        //updateDataMember(19,"Al Amin","081234556768",true);

        //VIEW DATA CUSTOMER
        viewAll();


        System.out.println("DADI !");
        crud.viewAll();
        //viewedByUsername();
        //batchProccess();
    }
    private static void deleteDataMember() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Chose id that you want to delete : ");
        int inputId = scanner.nextInt();
        try (Connection connection = DBConnector.connection()) {
            String query = "delete from customer_table where id_customer=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, inputId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }
    private static void updateDataMember(int id, String name, String phone, boolean isMember){
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Masukan id   :");
//        int id = Integer.parseInt(scanner.nextLine());
//        System.out.println("Masukan nama :");
//        String name = scanner.nextLine();
//        System.out.println("Masukan tele :");
//        String phone = scanner.nextLine();
//        System.out.println("Member status:");
//        boolean isMember = Boolean.parseBoolean(scanner.nextLine());
//        scanner.close();
        try(Connection connection = DBConnector.connection()) {
            String query = "update customer_table set customer_name=? ,customer_phone=? ,member_status=? where id_customer=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(4, id);
            statement.setString(1, name);
            statement.setString(2, phone);
            statement.setBoolean(3, isMember);
            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }
    private static void viewAll(){

        try(Connection connection = DBConnector.connection()) {
            String query = "select * from customer_table";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                System.out.println("\n"+"=".repeat(30));
                System.out.println("ID: " + resultSet.getInt("id_customer"));
                System.out.println("Name: " + resultSet.getString("customer_name"));
                System.out.println("Phone: " + resultSet.getString("customer_phone"));
                System.out.println("Status: " + resultSet.getString("member_status"));

            }


        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }
    private static void saveDataMember(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Masukan id   :");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Masukan nama :");
        String name = scanner.nextLine();
        System.out.println("Masukan tele :");
        String phone = scanner.nextLine();
        System.out.println("Member status:");
        boolean isMember = Boolean.parseBoolean(scanner.nextLine());
        scanner.close();
        try(Connection connection = DBConnector.connection()) {
            String query = "insert into customer_table(id_customer,customer_name ,customer_phone ,member_status  ) values(?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, phone);
            preparedStatement.setBoolean(4, isMember);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    private static void viewedByUsername() {
        try(Connection connection = DBConnector.connection()) {
            String query = "select id, username, password from m_user_credential where username=? and password=?";
            PreparedStatement statement = connection.prepareStatement(query);
            Scanner scanner = new Scanner(System.in);

            System.out.println("Please enter your username: ");
            String username = scanner.nextLine();
           statement.setString(1, username);
            System.out.println("Please enter your password: ");
            String password = scanner.nextLine();
           statement.setString(2, password);
           ResultSet resultSet = statement.executeQuery();
           if(resultSet.next()) {
               int id = resultSet.getInt("id");
               System.out.println(resultSet.getString("username") + " uccessfully logged in");
               System.out.println(query.toUpperCase(Locale.ROOT));
           }

            //Statement statement = connection.createStatement();
            /*statement.execute("CREATE TABLE user_credential (\n" +
                    "    id SERIAL PRIMARY KEY,\n" +
                    "    username VARCHAR(50) UNIQUE,\n" +
                    "    password VARCHAR(255) \n)" );*/
       /*     Scanner scanner = new Scanner(System.in);
            System.out.println("Masukan Username :\n");
            String username = scanner.nextLine();
            System.out.println("Masukan Password :\n");
            String password = scanner.nextLine();
            String query = "SELECT * FROM m_user_credential WHERE username = '" + username + "' AND password = '" + password + "'";
            ResultSet resultSet = statement.executeQuery(query);
            System.out.println(query);
            if (resultSet.next()) {
                System.out.println(resultSet.getString("username") +" Successfully logged in");

            }*/
            statement.close();

            // APA KEKURANGAN STATEMENT? ==> ya banyak, dan kekurangan nya ini berakibat fatal, sangat tidak disarnkan, apalagi create statement
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    // SQL INJECTION ==> salah satu teknik kejahatan pembobolan data dengan memanipulasi logika pada query berikat adalah salah satu bentuk query
    // SELECT * FROM m_user_credential WHERE username = 'budiman' AND password = ''or'1'='1', dimana passwoer disi dengan ('or'1'='1 yang tertulis di query )..

    private static void batchProccess() {
        try(Connection connection = DBConnector.connection()){
            Statement statement = connection.createStatement();
            int id = 31;
            for(int i = 1; i <= 20; i++){
                String name = "usman" + id;
                String phone = "0812379973" + id;
                Boolean isMember = Boolean.FALSE;

                String query = "insert into customer_table values ('"+id+"','"+name+"','"+phone+"','"+isMember+"')";
                statement.addBatch(query);
                id++;
                System.out.println("dadi");

            }
            statement.executeBatch();
            statement.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
