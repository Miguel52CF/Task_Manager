<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es" data-bs-theme="light">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Gestor de Tareas</title>
    <style>

      .section-card {
        transition: transform 0.2s ease;
      }

      .section-card:hover {
        transform: scale(1.02);
      }

    </style>
  </head>
  <body>
    <!-- Navbar -->
    <%@ include file="./Components/header.jsp" %>

    <!-- Contenido principal -->
    <div class="container main-content text-center">
      <h1 class="mb-4">Bienvenido al Gestor de Tareas</h1>
      <p class="lead mb-5">
        Organiza tus tareas, clasifícalas por categorías y visualízalas en un
        tablero práctico y ordenado.
      </p>

      <div class="row g-4">
        <div class="col-md-4">
          <div class="card section-card h-100 shadow-sm">
            <div class="card-body">
              <h5 class="card-title">📋 Ver Tareas</h5>
              <p class="card-text">
                Explora todas tus tareas registradas, su estado, subtareas y
                detalles organizados.
              </p>
              <a href="#" class="btn btn-outline-primary">Ir a Tareas</a>
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <div class="card section-card h-100 shadow-sm">
            <div class="card-body">
              <h5 class="card-title">🏷️ Crear Categorías</h5>
              <p class="card-text">
                Agrega o modifica los tipos de tareas para clasificar mejor tu
                flujo de trabajo.
              </p>
              <a href="/taskTypes" class="btn btn-outline-primary">Crear Categoría</a>
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <div class="card section-card h-100 shadow-sm">
            <div class="card-body">
              <h5 class="card-title">🧩 Ver Board</h5>
              <p class="card-text">
                Visualiza tus tareas en formato de tablero Kanban, ideal para
                organizar por estado.
              </p>
              <a href="#" class="btn btn-outline-primary">Ver Board</a>
            </div>
          </div>
        </div>
      </div>
    </div>

    <%@ include file="./Components/footer.jsp" %>
  </body>
</html>
