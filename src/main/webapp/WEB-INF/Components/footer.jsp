<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<footer class="text-center py-3 mt-auto text-muted">
  © 2025 - Gestor de Tareas
</footer>

<!-- jQuery (requerido) -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/list.js/2.3.1/list.min.js"></script>

<script>
  const themeToggle = document.getElementById("themeToggle");
  const root = document.documentElement;

  const currentTheme = localStorage.getItem("theme") || "light";
  root.setAttribute("data-bs-theme", currentTheme);
  themeToggle.textContent = currentTheme === "dark" ? "🌞" : "🌙";

  themeToggle.addEventListener("click", () => {
    const newTheme =
      root.getAttribute("data-bs-theme") === "dark" ? "light" : "dark";
    root.setAttribute("data-bs-theme", newTheme);
    themeToggle.textContent = newTheme === "dark" ? "🌞" : "🌙";
    localStorage.setItem("theme", newTheme);
  });
</script>
