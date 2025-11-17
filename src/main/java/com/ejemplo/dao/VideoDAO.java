package com.ejemplo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ejemplo.model.Video;
import com.ejemplo.utils.Database;

public class VideoDAO {

    // INSERT
    public void agregar(Video video) {
        // Si favorite tiene default 0 en la BD, podés dejar fuera la columna.
        String sql = "INSERT INTO videos(nombre, link, liked, favorite) VALUES(?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, video.getNombre());
            ps.setString(2, video.getLink());
            ps.setInt(3, video.isLiked() ? 1 : 0);
            ps.setInt(4, video.isFavorite() ? 1 : 0);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // LISTAR TODOS
    public List<Video> listar() {
        List<Video> lista = new ArrayList<>();
        String sql = "SELECT * FROM videos ORDER BY id DESC";

        try (Connection conn = Database.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Video v = new Video();
                v.setId(rs.getInt("id"));
                v.setNombre(rs.getString("nombre"));
                v.setLink(rs.getString("link"));
                v.setLiked(rs.getInt("liked") == 1);
                // leer también favorite
                v.setFavorite(rs.getInt("favorite") == 1);
                lista.add(v);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // VIDEOS LIKEADOS
    public List<Video> misVideosLikeados() {
        List<Video> lista = new ArrayList<>();
        String sql = "SELECT * FROM videos WHERE liked = 1";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Video v = new Video();
                v.setId(rs.getInt("id"));
                v.setNombre(rs.getString("nombre"));
                v.setLink(rs.getString("link"));
                v.setLiked(rs.getInt("liked") == 1);
                v.setFavorite(rs.getInt("favorite") == 1);
                lista.add(v);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // TOGGLE LIKE
    public void toggleLike(int id) {
        String sql = "UPDATE videos " +
                     "SET liked = CASE liked WHEN 1 THEN 0 ELSE 1 END " +
                     "WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ELIMINAR
    public void eliminar(int id) {
        String sql = "DELETE FROM videos WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // VIDEOS FAVORITOS
    public List<Video> misVideosFavoritos() {
        List<Video> lista = new ArrayList<>();
        String sql = "SELECT * FROM videos WHERE favorite = 1";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Video v = new Video();
                v.setId(rs.getInt("id"));
                v.setNombre(rs.getString("nombre"));
                v.setLink(rs.getString("link"));
                v.setLiked(rs.getInt("liked") == 1);
                v.setFavorite(rs.getInt("favorite") == 1);
                lista.add(v);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // TOGGLE FAVORITE
    public void toggleFavorite(int id) {
        String sql = "UPDATE videos " +
                     "SET favorite = CASE favorite WHEN 1 THEN 0 ELSE 1 END " +
                     "WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // BUSCAR
    public Video buscarPorId(int id) {
    String sql = "SELECT * FROM videos WHERE id=?";
    try (Connection conn = Database.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            Video v = new Video();
            v.setId(rs.getInt("id"));
            v.setNombre(rs.getString("nombre"));
            v.setLink(rs.getString("link"));
            v.setLiked(rs.getBoolean("liked"));
            v.setFavorite(rs.getBoolean("favorite"));
            return v;
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}

}
