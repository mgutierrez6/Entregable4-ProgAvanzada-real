package com.ejemplo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    // Carga el driver SQLite
    static {
        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println(">>> DRIVER SQLITE CARGADO");
        } catch (ClassNotFoundException e) {
            System.out.println(">>> ERROR: Driver SQLite no encontrado");
            e.printStackTrace();
        }
    }

    // Nombre del archivo de DB
    private static final String DB_URL = "jdbc:sqlite:mgutierrezvlena.db";

    // Crea la tabla si no existe
    static {
        try (Connection conn = getConnection()) {
            String sql = """
                CREATE TABLE IF NOT EXISTS videos (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre TEXT NOT NULL,
                    link TEXT NOT NULL,
                    liked INTEGER DEFAULT 0,
                    favorite INTEGER DEFAULT 0
                );
            """;

            conn.createStatement().execute(sql);
            System.out.println(">>> TABLA 'videos' verificada/creada");
        } catch (Exception e) {
            System.out.println(">>> ERROR creando tabla");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
}
