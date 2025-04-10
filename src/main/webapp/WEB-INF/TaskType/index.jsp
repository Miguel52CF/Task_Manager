<%@ page language="java" contentType="text/html; charset=UTF-8" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es" data-bs-theme="light">
  <head>
    <meta charset="UTF-8" />
    <title>Tipos de Tareas</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
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
          <div class="table-responsive">
            <div class="d-flex justify-content-between mb-3">
              <div class="search-box">
                <input
                  type="text"
                  id="searchInput"
                  class="form-control"
                  placeholder="Buscar..."
                  style="max-width: 300px"
                />
              </div>
              <button
                class="btn btn-primary"
                data-bs-toggle="modal"
                data-bs-target="#crearCategoriaModal"
              >
                <i class="fas fa-plus-circle me-2"></i>Nueva categoría
              </button>
            </div>
            <table
              id="taskTypesTable"
              class="table table-hover table-bordered align-middle"
            >
              <thead class="table-light">
                <tr>
                  <th>Nombre</th>
                  <th>Ícono</th>
                  <th class="text-center" style="width: 120px">Acciones</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="type" items="${taskTypes}" varStatus="status">
                  <tr>
                    <td>${type.name}</td>
                    <td>
                      <i class="${type.icon} icon-preview"></i>
                      <small class="text-muted">${type.icon}</small>
                    </td>
                    <td class="text-center action-buttons">
                      <div class="btn-group btn-group-sm" role="group">
                        <button
                          type="button"
                          class="btn btn-outline-primary"
                          title="Editar"
                        >
                          <i class="fas fa-edit"></i>
                        </button>
                        <button
                          type="button"
                          class="btn btn-outline-danger"
                          title="Eliminar"
                        >
                          <i class="fas fa-trash-alt"></i>
                        </button>
                      </div>
                    </td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>

    <%@ include file="../Components/footer.jsp" %> <%@ include
    file="./modalCreate.jsp" %>
    <script>
      $(document).ready(function () {
        $("#taskTypesTable").DataTable({
          pageLength: 10,
          language: {
            url: "//cdn.datatables.net/plug-ins/1.13.6/i18n/es-ES.json",
          },
          dom: '<"top"f>rt<"bottom"lip><"clear">',
          initComplete: function () {
            // Mover el buscador al input personalizado
            $(".dataTables_filter").hide();
            $("#searchInput").on("keyup", function () {
              $("#taskTypesTable").DataTable().search($(this).val()).draw();
            });
          },
        });
      });
    </script>
  </body>
</html>
