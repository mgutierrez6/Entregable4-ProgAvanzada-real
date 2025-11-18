<%@ page import="com.ejemplo.model.Video" %>
<%@ page import="java.util.List" %>

<html>
<head>
    <title>❤️ Favoritos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">

<h1 class="mb-4"> Mis Videos Favoritos</h1>

<a href="videos" class="btn btn-secondary mb-3">Volver</a>

<div class="row">
<%
    List<Video> favs = (List<Video>) request.getAttribute("favoritos");
    if (favs != null && !favs.isEmpty()) {
        for (Video v : favs) {
%>

<div class="col-md-4 mb-3">
    <div class="card h-100">
        <%
            String link = v.getLink();
            String videoId = "";
            
            // Extraer ID de diferentes formatos de URL de YouTube
            if (link.contains("youtube.com/watch?v=")) {
                videoId = link.split("v=")[1].split("&")[0];
            } else if (link.contains("youtu.be/")) {
                videoId = link.split("youtu.be/")[1].split("\\?")[0];
            } else {
                // Si ya es solo el ID
                videoId = link;
            }
        %>
        <a href="videos?action=ver&id=<%= v.getId() %>">
            <img src="https://img.youtube.com/vi/<%= videoId %>/hqdefault.jpg" class="card-img-top">
        </a>
        <div class="card-body text-center">
            <h5><%= v.getNombre() %></h5>
        </div>
    </div>
</div>

<%
        }
    } else {
%>
    <p>No tienes ningun favorito aun.</p>
<%
    }
%>
</div>

</body>
</html>
