package com.example.ml_d9zm3p_java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHandler {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/szokereso_db"; // Az adatbázis neve
    private static final String DB_USER = "root"; // Alapértelmezett felhasználónév
    private static final String DB_PASSWORD = ""; // Alapértelmezett jelszó (ha nincs beállítva)

    private Connection connection;

    // Kapcsolódás az adatbázishoz
    public void connect() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lezárás
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Felhasználónév és jelszó ellenőrzése
    public boolean checkUserCredentials(String username, String password) {
        String query = "SELECT * FROM felhasznalok WHERE felhasznalonev = ? AND jelszo = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Ha találunk egyezést, visszaadjuk a true értéket
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
