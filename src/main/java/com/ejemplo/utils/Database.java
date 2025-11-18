package com.ejemplo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    // URL por defecto (DB real)
    private static String DB_URL = "jdbc:sqlite:mgutierrezvlena.db";

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

    /**
     * Permite cambiar dinámicamente la URL de la base de datos.
     * JUnit la usa para setear ":memory:" sin afectar la DB real.
     */
    public static void overrideUrl(String newUrl) {
        DB_URL = newUrl;
        System.out.println(">>> DB_URL OVERWRITTEN TO: " + newUrl);
    }

    // Crea la tabla si no existe (solo la primera vez que se carga la clase)
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
