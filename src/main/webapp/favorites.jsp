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
            List<Video> videos = (List<Video>) request.getAttribute("videos");
            if (videos != null && !videos.isEmpty()) {
                for (Video v : videos) {

                    String link = v.getLink();
                    String videoId = link;

                    int idxV = link.indexOf("v=");
                    if (idxV != -1) {
                        videoId = link.substring(idxV + 2);
                        int amp = videoId.indexOf("&");
                        if (amp != -1) videoId = videoId.substring(0, amp);
                    } else if (link.contains("youtu.be/")) {
                        int slash = link.lastIndexOf("/");
                        videoId = link.substring(slash + 1);
                    } else if (link.contains("/embed/")) {
                        int embedIdx = link.indexOf("/embed/");
                        if (embedIdx != -1) {
                            videoId = link.substring(embedIdx + 7);
                        }
                    }

                    String thumbnailUrl = "https://img.youtube.com/vi/" + videoId + "/hqdefault.jpg";
                    String watchUrl = "https://www.youtube.com/watch?v=" + videoId;
        %>
        <a href="videos?action=ver&id=<%= v.getId() %>">
            <img src="thumbnailUrl" class="card-img-top">
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
