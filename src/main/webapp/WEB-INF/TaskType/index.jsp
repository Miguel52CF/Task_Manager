<%@ page language="java" contentType="text/html; charset=UTF-8" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es" data-bs-theme="light">
<head>
  <meta charset="UTF-8" />
  <title>Tipos de Tareas</title>
  <meta name="viewport" content="width=device-width, initial-scale=1" />

  <!-- SweetAlert -->
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

  <style>
    .action-buttons .btn {
      width: 40px;
      padding: 0.375rem;
    }
    .icon-preview {
      font-size: 1.5rem;
      margin-right: 10px;
      vertical-align: middle;
      color: #6c757d;
    }
    .table th,
    .table td {
      vertical-align: middle;
    }
    .btn-emoji:hover {
      background-color: #f0f0f0 !important;
      border-radius: 5px;
      cursor: pointer;
    }
    #emojiPickerContainer {
      border: 1px solid #dee2e6;
      border-radius: 5px;
      padding: 10px;
      margin-top: 5px;
      background-color: white;
      z-index: 1000;
    }
    #selectedEmojiPreview {
      font-size: 1.2rem;
      margin-right: 5px;
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
        <h2 class="mb-0">Tipos de Tarea</h2>
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
              data-bs-target="#crearCategoriaModal"
            >
              <i class="fas fa-plus-circle me-2"></i>Nueva categoría
            </button>
          </div>

          <div class="table-responsive">
            <table class="table table-hover table-bordered align-middle">
              <thead class="table-light">
                <tr>
                  <th><span class="sort" data-sort="name">Nombre</span></th>
                  <th><span class="sort" data-sort="icon">Ícono</span></th>
                  <th class="text-center" style="width: 120px">Acciones</th>
                </tr>
              </thead>
              <tbody class="list" id="taskTypeList">
                <!-- Will be filled dynamically -->
              </tbody>
            </table>
          </div>

          <ul class="pagination"></ul>
        </div>
      </div>
    </div>
  </div>

  <%@ include file="./modalCreate.jsp" %>
  <%@ include file="../Components/footer.jsp" %>

  <!-- Script personalizado -->
  <script src="${pageContext.request.contextPath}/js/TasType.js"></script>
</body>
</html>
