<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ page
language="java" contentType="text/html; charset=UTF-8" %>
<link
  href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
  rel="stylesheet"
/>
<link
  rel="stylesheet"
  href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
/>
<!-- CSS -->
<link
  rel="stylesheet"
  href="https://cdn.datatables.net/2.2.2/css/dataTables.dataTables.css"
/>

<style>
  body {
    min-height: 100vh;
    display: flex;
    flex-direction: column;
  }

  .main-content {
    flex: 1;
    padding: 2rem 1rem;
  }

  .theme-toggle-btn {
    border: none;
    background: none;
    font-size: 1.2rem;
    cursor: pointer;
  }
</style>
<nav class="navbar navbar-expand-lg bg-body-tertiary border-bottom shadow-sm">
  <div class="container">
    <a class="navbar-brand fw-bold" href="/">Gestor de Tareas</a>
    <div class="d-flex align-items-center ms-auto">
      <ul class="navbar-nav me-3">
        <li class="nav-item">
          <a class="nav-link" href="/tareas">Ver Tareas</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/taskTypes">Crear Categorías</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/board">Ver Board</a>
        </li>
      </ul>
      <button
        id="themeToggle"
        class="theme-toggle-btn"
        aria-label="Cambiar modo"
      >
        🌞
      </button>
    </div>
  </div>
</nav>
