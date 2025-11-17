package com.ejemplo.model;

import java.io.Serializable;

public class Video implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String nombre;
    private String link;
    private boolean liked = false;
    private boolean favorite = false; 

    public Video() {}

    public Video(String nombre, String link) {
        this.nombre = nombre;
        this.link = link;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }

    // LIKE
    public boolean isLiked()  { return liked; }
    public void setLiked(boolean liked) { this.liked = liked; }
    public void toggleLiked() { this.liked = !this.liked; }

    // FAVORITO
    public boolean isFavorite() { return favorite; }
    public void setFavorite(boolean favorite) { this.favorite = favorite; }
    public void toggleFavorite() { this.favorite = !this.favorite; }
}
