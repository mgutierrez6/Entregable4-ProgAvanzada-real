// --- LIKE ---
function toggleLike(id, btn) {

    fetch("videos", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: new URLSearchParams({
            action: "like",
            id: id
        })
    })
    .then(r => r.json())
    .then(res => {
        console.log("LIKE response:", res);

        // Cambiar la UI sin recargar
        btn.classList.toggle("btn-danger");
        btn.classList.toggle("btn-outline-danger");

        const span = btn.querySelector("span");
        span.textContent = span.textContent === "Like" ? "Liked" : "Like";
    })
    .catch(err => console.error("Error:", err));
}

// --- FAVORITE ---
// --- FAVORITE ---
function toggleFavorite(id, btn) {

    fetch("videos", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: new URLSearchParams({
            action: "favorite",
            id: id
        })
    })
    .then(r => r.json())
    .then(res => {
        console.log("FAV response:", res);

        const isFav = !btn.classList.contains("btn-success");

        if (isFav) {
            btn.classList.remove("btn-secondary");
            btn.classList.add("btn-success");
            btn.querySelector("span").textContent = "✓";
        } else {
            btn.classList.remove("btn-success");
            btn.classList.add("btn-secondary");
            btn.querySelector("span").textContent = "+";
        }
    })
    .catch(err => console.error("Error:", err));
}

