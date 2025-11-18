package com.ejemplo.dao;

import com.ejemplo.model.Video;
import com.ejemplo.utils.Database;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VideoDAOTest {

    private static VideoDAO dao;

    @BeforeAll
    static void setupDatabase() throws Exception {
        // Cambiamos la DB a una temporal en memoria
        Database.overrideUrl("jdbc:sqlite::memory:");

        // Creamos la tabla manualmente en la DB de tests
        try (Connection conn = Database.getConnection();
             Statement st = conn.createStatement()) {

            st.execute("""
                CREATE TABLE videos (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre TEXT NOT NULL,
                    link TEXT NOT NULL,
                    liked INTEGER DEFAULT 0,
                    favorite INTEGER DEFAULT 0
                );
            """);
        }

        dao = new VideoDAO();
    }

    @Test
    void testAgregarYListar() {
        Video v = new Video();
        v.setNombre("Video Test");
        v.setLink("https://test.com");

        dao.agregar(v);

        List<Video> lista = dao.listar();

        assertEquals(1, lista.size());
        assertEquals("Video Test", lista.get(0).getNombre());
    }

    @Test
    void testToggleLike() {
        Video v = new Video();
        v.setNombre("Test Like");
        v.setLink("https://example.com");

        dao.agregar(v);

        List<Video> lista1 = dao.listar();
        int id = lista1.get(0).getId();

        dao.toggleLike(id);

        List<Video> lista2 = dao.listar();
        assertTrue(lista2.get(0).isLiked());
    }

    @Test
    void testToggleFavorite() {
        Video v = new Video();
        v.setNombre("Test Fav");
        v.setLink("https://example.com");

        dao.agregar(v);
        int id = dao.listar().get(0).getId();

        dao.toggleFavorite(id);

        assertTrue(dao.listar().get(0).isFavorite());
    }

    @Test
    void testEliminar() {
        Video v = new Video();
        v.setNombre("Eliminar Test");
        v.setLink("link");

        dao.agregar(v);

        int id = dao.listar().get(0).getId();
        dao.eliminar(id);

        assertTrue(dao.listar().isEmpty());
    }
}
