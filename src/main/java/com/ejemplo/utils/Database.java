package com.ejemplo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    // URL por defecto (DB real de la aplicación)
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
     * Permite que los tests cambien la DB sin afectar la DB real.
     * Por ejemplo: Database.overrideUrl("jdbc:sqlite:test-db.sqlite");
     */
    public static void overrideUrl(String newUrl) {
        DB_URL = newUrl;
        System.out.println(">>> DB_URL OVERRIDDEN: " + newUrl);
    }

    /**
     * Crea la tabla videos en cualquier DB (real o test)
     */
    public static void crearTablaVideos() {
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
            System.out.println(">>> TABLA 'videos' creada/verificada");
        } catch (Exception e) {
            throw new RuntimeException("Error creando tabla", e);
        }
    }

    public static void limpiarTablaVideos() {
        try (Connection conn = getConnection()) {
            String sql = "DELETE FROM videos;";
            conn.createStatement().execute(sql);
            System.out.println(">>> TABLA 'videos' limpiada");
        } catch (Exception e) {
            throw new RuntimeException("Error limpiando tabla", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

}
