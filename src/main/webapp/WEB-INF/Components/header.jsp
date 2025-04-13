<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<link
  href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
  rel="stylesheet"
/>
<!-- Use a newer version (6.4.2 is latest as of Oct 2023) -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">

<!-- Tom Select CSS y JS -->
<link href="https://cdn.jsdelivr.net/npm/tom-select@2.2.2/dist/css/tom-select.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/tom-select@2.2.2/dist/js/tom-select.complete.min.js"></script>

<!-- SweetAlert2 -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
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

  /* Tom Select en tema oscuro */
  [data-bs-theme="dark"] .ts-control {
    background: #333;
    border-color: #444;
    color: #fff;
  }

  [data-bs-theme="dark"] .ts-dropdown {
    background: #333;
    border-color: #444;
  }

  [data-bs-theme="dark"] .ts-dropdown .option {
    color: #fff;
  }

  [data-bs-theme="dark"] .ts-dropdown .active {
    background-color: #555;
  }
  /* Estilo del control y opciones seleccionadas en tema oscuro */
  [data-bs-theme="dark"] .ts-control {
    background: #333 !important;
    color: #fff !important;
  }

  [data-bs-theme="dark"] .ts-dropdown .selected .ts-control input{
    background-color: #555 !important;
    color: #fff !important;
  }

  [data-bs-theme="dark"] .ts-control input {
      color: #fff !important;
  }
</style>
<nav class="navbar navbar-expand-lg bg-body-tertiary border-bottom shadow-sm">
  <div class="container">
    <a class="navbar-brand fw-bold" href="/">Gestor de Tareas</a>
    <div class="d-flex align-items-center ms-auto">
      <ul class="navbar-nav me-3">
        <li class="nav-item">
          <a class="nav-link" href="/tasks">Ver Tareas</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/taskTypes">Crear Categorías</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/Board">Ver Board</a>
        </li>
      </ul>
      <button
        id="themeToggle"
        class="theme-toggle-btn"
        aria-label="Cambiar modo">
        🌞
      </button>
    </div>
  </div>
</nav>
