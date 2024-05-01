package com.enigma.jdbc;

import com.enigma.jdbc.config.DBConnector;
import com.enigma.jdbc.crud.crud;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
//        crud.delete();
//    crud.viewAll();
    crud crud = new crud();
//        com.enigma.jdbc.crud.crud.insert();
    crud.update();
    }

}
