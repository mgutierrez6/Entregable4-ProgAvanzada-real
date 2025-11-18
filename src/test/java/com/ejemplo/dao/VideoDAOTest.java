package com.ejemplo.dao;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;  // <-- ESTE FALTABA
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ejemplo.model.Video;
import com.ejemplo.utils.Database;

public class VideoDAOTest {

    private static VideoDAO dao;

    @BeforeEach
    void setUpDB() throws Exception {
        // Cambiar la DB a memoria
        Field dbUrlField = Database.class.getDeclaredField("DB_URL");
        dbUrlField.setAccessible(true);
        dbUrlField.set(null, "jdbc:sqlite:file:memdb1?mode=memory&cache=shared");

        // Crear la tabla en memoria
        Database.crearTablaParaTests();

        // Instanciar DAO
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
