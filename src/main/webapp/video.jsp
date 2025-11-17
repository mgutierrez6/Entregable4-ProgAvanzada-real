<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ejemplo.model.Video" %>

<%
    Video v = (Video) request.getAttribute("video");
%>

<html>
<head>
    <title><%= v.getNombre() %> </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="container mt-4">

    <h1 class="mb-4">🎬 <%= v.getNombre() %></h1>
    <a href="videos" class="btn btn-secondary mb-3">Volver</a>

    <!-- VIDEO EN GRANDE -->
    <div class="ratio ratio-16x9 mb-4">
        <iframe src="<%= v.getLink() %>" 
                frameborder="0" 
                allowfullscreen>
        </iframe>
    </div>

    <!-- BOTONES ABAJO A LA DERECHA -->
    <div class="d-flex justify-content-end gap-2">

        <!-- LIKE -->
        <button class="btn btn-sm <%= v.isLiked() ? "btn-danger" : "btn-outline-danger" %>"
                onclick="toggleLike(<%= v.getId() %>, this)">
            ❤️ <span><%= v.isLiked() ? "Liked" : "Like" %></span>
        </button>

        <!-- FAVORITOS -->
        <button class="btn btn-sm <%= v.isFavorite() ? "btn-success" : "btn-secondary" %>"
                onclick="toggleFavorite(<%= v.getId() %>, this)">
            Favoritos <span><%= v.isFavorite() ? "✓" : "+" %></span>
        </button>

        <!-- BORRAR -->
        <a href="videos?action=delete&id=<%= v.getId() %>" 
           class="btn btn-sm btn-danger">
            🗑️
        </a>
    </div>

<script src="js/videos.js?v=3"></script>

</body>
</html>
