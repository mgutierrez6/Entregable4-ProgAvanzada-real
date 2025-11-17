package com.ejemplo.service;

import com.ejemplo.dao.VideoDAO;
import com.ejemplo.model.Video;

import java.util.List;

public class VideoService {

    private final VideoDAO dao = new VideoDAO();

    public void agregar(String nombre, String link) {
        Video v = new Video();
        v.setNombre(nombre);
        v.setLink(link);
        v.setLiked(false);
        v.setFavorite(false);

        dao.agregar(v);
    }

    public List<Video> listar() {
        return dao.listar();
    }

    public void toggleLike(int id) {
        dao.toggleLike(id);
    }

    public void toggleFavorite(int id) {
        dao.toggleFavorite(id);
    }

    public void eliminar(int id) {
        dao.eliminar(id);
    }

    public List<Video> listarFavoritos() {
        return dao.misVideosFavoritos();
    }

    public List<Video> listarLikeados() {
        return dao.misVideosLikeados();
    }

    public Video buscarPorId(int id) {
        return dao.buscarPorId(id);
    }
}
