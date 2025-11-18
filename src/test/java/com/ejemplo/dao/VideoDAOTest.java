package com.ejemplo.dao;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ejemplo.model.Video;
import com.ejemplo.utils.Database;

public class VideoDAOTest {

    private VideoDAO dao;

    @BeforeEach
    void setUpDB() {
        // DB exclusiva para tests (archivo separado)
        Database.overrideUrl("jdbc:sqlite:test-db.sqlite");

        // Crear tabla para tests
        Database.crearTablaVideos();

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
        v.setNombre("LikeTest");
        v.setLink("https://a.com");
        dao.agregar(v);

        int id = dao.listar().get(0).getId();

        dao.toggleLike(id);

        assertTrue(dao.listar().get(0).isLiked());
    }

    @Test
    void testToggleFavorite() {
        Video v = new Video();
        v.setNombre("FavTest");
        v.setLink("https://a.com");
        dao.agregar(v);

        int id = dao.listar().get(0).getId();

        dao.toggleFavorite(id);

        assertTrue(dao.listar().get(0).isFavorite());
    }

    @Test
    void testEliminar() {
        Video v = new Video();
        v.setNombre("EliminarTest");
        v.setLink("https://a.com");
        dao.agregar(v);

        int id = dao.listar().get(0).getId();
        dao.eliminar(id);

        assertTrue(dao.listar().isEmpty());
    }
}
