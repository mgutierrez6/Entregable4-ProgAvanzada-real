<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ejemplo.model.Video" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>🎬 Mi Playlist</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">

<h1 class="mb-4">🎬 Mi Playlist</h1> 

<div class="mb-3">
    <a href="videos?action=favoritos" class="btn btn-warning">
        ❤️ Mis Favoritos
    </a>
</div>


<form action="videos" method="post" class="mb-4">
    <div class="row g-2">
        <div class="col-md-4">
            <input type="text" name="nombre" class="form-control" placeholder="Nombre del video" required>
        </div>
        <div class="col-md-6">
            <input type="text" name="link" class="form-control"
                   placeholder="Link de YouTube (https://www.youtube.com/watch?v=...)" required>
        </div>
        <div class="col-md-2">
            <button type="submit" class="btn btn-primary w-100">Agregar</button>
        </div>
    </div>
</form>

<div class="row">
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

    <div class="col-md-4 mb-3">
        <div class="card h-100">

            <!-- Preview clickeable -->
            <a href="videos?action=ver&id=<%= v.getId() %>">
                <img src="<%= thumbnailUrl %>" class="card-img-top" alt="Preview de <%= v.getNombre() %>">
            </a>

            <div class="card-body text-center">
                <h5><%= v.getNombre() %></h5>

                <div class="d-flex justify-content-around mt-3">
                    <!-- LIKE (corazón) -->
                    <button type="button"
                            class="btn btn-sm <%= v.isLiked() ? "btn-danger" : "btn-outline-danger" %>"
                            onclick="toggleLike(<%= v.getId() %>, this)">
                        ❤️ <span><%= v.isLiked() ? "Liked" : "Like" %></span>
                    </button>

                    <!-- FAVORITOS (+ / ✓) -->
                    <button type="button"
                            class="btn btn-sm <%= v.isFavorite() ? "btn-success" : "btn-secondary" %>"
                            onclick="toggleFavorite(<%= v.getId() %>, this)">
                        Favoritos <span><%= v.isFavorite() ? "✓" : "+" %></span>
                    </button>

                    <!-- BORRAR -->
                    <a href="videos?action=delete&id=<%= v.getId() %>"
                       class="btn btn-sm btn-danger">
                        🗑️
                    </a>
                </div>
            </div>
        </div>
    </div>

    <%
            }
        } else {
    %>
    <p>No hay videos cargados todavía.</p>
    <%
        }
    %>
</div>

<script>
    async function toggleLike(id, btn) {
        try {
            const res = await fetch(`videos?action=like&id=${id}`, { method: 'POST' });
            if (!res.ok) return;

            const isLiked = !btn.classList.contains('btn-danger');

            if (isLiked) {
                btn.classList.remove('btn-outline-danger');
                btn.classList.add('btn-danger');
                btn.querySelector('span').textContent = 'Liked';
            } else {
                btn.classList.remove('btn-danger');
                btn.classList.add('btn-outline-danger');
                btn.querySelector('span').textContent = 'Like';
            }
        } catch (e) {
            console.error(e);
        }
    }

    async function toggleFavorite(id, btn) {
        try {
            const res = await fetch(`videos?action=favorite&id=${id}`, { method: 'POST' });
            if (!res.ok) return;

            const isFav = !btn.classList.contains('btn-success');

            if (isFav) {
                btn.classList.remove('btn-secondary');
                btn.classList.add('btn-success');
                btn.querySelector('span').textContent = '✓';
            } else {
                btn.classList.remove('btn-success');
                btn.classList.add('btn-secondary');
                btn.querySelector('span').textContent = '+';
            }
        } catch (e) {
            console.error(e);
        }
    }
</script>
<script src="js/videos.js"></script>
</body>
</html>
