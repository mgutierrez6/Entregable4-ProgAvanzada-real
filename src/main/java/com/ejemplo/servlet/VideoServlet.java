package com.ejemplo.servlet;

import java.io.IOException;
import java.util.List;

import com.ejemplo.model.Video;
import com.ejemplo.service.VideoService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/videos")
public class VideoServlet extends HttpServlet {

    private final VideoService service = new VideoService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            service.eliminar(id);
            response.sendRedirect("videos");
            return;
        }

        if ("favoritos".equals(action)) {
            List<Video> favs = service.listarFavoritos();
            request.setAttribute("favoritos", favs);
            request.getRequestDispatcher("/favorites.jsp").forward(request, response);
            return;
        }

        if ("ver".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Video video = service.buscarPorId(id);

            request.setAttribute("video", video);
            request.getRequestDispatcher("/video.jsp").forward(request, response);
            return;
        }

        // Vista principal
        List<Video> videos = service.listar();
        request.setAttribute("videos", videos);
        request.getRequestDispatcher("/videos.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        System.out.println(">>> POST recibido");
        System.out.println("action = " + action);
        System.out.println("nombre = " + request.getParameter("nombre"));
        System.out.println("link   = " + request.getParameter("link"));
        System.out.println("id = " + request.getParameter("id"));


        // ----- LIKE -----
        if ("like".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            service.toggleLike(id);
            response.setContentType("application/json");
            response.getWriter().write("{\"status\":\"ok\"}");
            return;
        }

        // ----- FAVORITE -----
        if ("favorite".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            service.toggleFavorite(id);
            response.setContentType("application/json");
            response.getWriter().write("{\"status\":\"ok\"}");
            return;
        }

        // ----- AGREGAR VIDEO -----
        String nombre = request.getParameter("nombre");
        String rawLink = request.getParameter("link");

        // si nombre o link son NULL 
        if (nombre == null || rawLink == null || nombre.isEmpty() || rawLink.isEmpty()) {
            System.out.println(">>> NO es un submit del formulario. Redirigiendo.");
            response.sendRedirect("videos");
            return;
        }

        // convertir link a embed
        String link = rawLink;

        if (rawLink.contains("youtu.be/")) {
            link = "https://www.youtube.com/embed/" +
                rawLink.substring(rawLink.lastIndexOf("/") + 1).split("\\?")[0];
        } 
        else if (rawLink.contains("watch?v=")) {
            link = "https://www.youtube.com/embed/" +
                rawLink.split("v=")[1].split("&")[0];
        }

        System.out.println(">>> AGREGANDO VIDEO: " + nombre + " | " + link);

        service.agregar(nombre, link);

        response.sendRedirect("videos");
    }
}
