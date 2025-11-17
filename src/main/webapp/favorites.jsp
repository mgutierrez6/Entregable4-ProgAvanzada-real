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
        <a href="https://www.youtube.com/watch?v=<%= v.getLink() %>" target="_blank">
            <img src="https://img.youtube.com/vi/<%= v.getLink() %>/hqdefault.jpg" class="card-img-top">
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
