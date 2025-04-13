<%@ page language="java" contentType="text/html; charset=UTF-8" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es" data-bs-theme="light">
  <head>
    <meta charset="UTF-8" />
    <title>Listado de Tareas</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <style>
      .action-buttons .btn {
        width: 40px;
        padding: 0.375rem;
      }
      .table th,
      .table td {
        vertical-align: middle;
      }
      .pagination li {
        margin: 0 2px;
      }
      .pagination li a {
        border-radius: 6px !important;
        padding: 4px 10px !important;
        color: #0d6efd;
      }
      .pagination li.active a {
        background-color: #0d6efd;
        color: white !important;
      }
    </style>
  </head>
  <body>
    <%@ include file="../Components/header.jsp" %>

    <div class="container main-content mt-4">
      <div class="card shadow-sm">
        <div class="card-header bg-primary text-white">
          <h2 class="mb-0">Tareas</h2>
        </div>
        <div class="card-body">
          <div id="taskListWrapper">
            <div class="d-flex justify-content-between mb-3">
              <input
                class="search form-control"
                placeholder="Buscar..."
                style="max-width: 300px"
              />
              <button
                class="btn btn-primary"
                data-bs-toggle="modal"
                data-bs-target="#crearTareaModal"
              >
                <i class="fas fa-plus-circle me-2"></i>Nueva Tarea
              </button>
            </div>

            <div class="table-responsive">
              <table class="table table-hover table-bordered align-middle">
                <thead class="table-light">
                  <tr>
                    <th><span class="name">Título</span></th>
                    <th><span class="description">Descripción</span></th>
                    <th><span class="status">Estado</span></th>
                    <th><span class="type">Tipo</span></th>
                    <th><span class="subtasks">Subtareas</span></th>
                    <th class="text-center" style="width: 120px">Acciones</th>
                  </tr>
                </thead>
                <tbody class="list" id="tasksTableList">
                  <!-- Will be filled dynamically -->
                </tbody>
              </table>
            </div>

            <ul class="pagination"></ul>
          </div>
        </div>
      </div>
    </div>

    <%@ include file="./modalCreate.jsp" %> <%@ include
    file="../Components/footer.jsp" %>
    <!-- Script personalizado -->
    <script src="${pageContext.request.contextPath}/js/Task.js"></script>
  </body>
</html>
