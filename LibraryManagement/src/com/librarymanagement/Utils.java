package com.librarymanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Utils {
    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:library.db");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
