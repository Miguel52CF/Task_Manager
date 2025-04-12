<%@ page language="java" contentType="text/html; charset=UTF-8" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
          <div class="table-responsive">
            <div class="d-flex justify-content-between mb-3">
              <div class="search-box">
                <input
                  type="text"
                  id="searchInput"
                  class="form-control"
                  placeholder="Buscar tareas..."
                  style="max-width: 300px"
                />
              </div>
              <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#crearTareaModal">
                <i class="fas fa-plus-circle me-2"></i>Nueva Tarea
              </button>
            </div>
            <table
              id="tasksTable"
              class="table table-hover table-bordered align-middle"
            >
              <thead class="table-light">
                <tr>
                  <th>Título</th>
                  <th>Descripción</th>
                  <th>Estado</th>
                  <th>Tipo</th>
                  <th>Subtareas</th>
                  <th class="text-center" style="width: 120px">Acciones</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="task" items="${tasks}">
                  <tr>
                    <td>${task.title}</td>
                    <td>${task.task}</td>
                    <td>
                      <c:set var="statusColor" value="style ='background-color: ${task.status.color()}'" />
                      <span class="badge" ${statusColor}>
                        ${task.status.description()}
                      </span>
                    </td>                    
                    <td>${task.taskType.name}</td>
                    <td>
                      <c:if test="${not empty task.subTasks}">
                        <ul class="mb-0">
                          <c:forEach var="sub" items="${task.subTasks}">
                            <li>${sub.title}</li>
                          </c:forEach>
                        </ul>
                      </c:if>
                      <c:if test="${empty task.subTasks}">
                        <em class="text-muted">Sin subtareas</em>
                      </c:if>
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

    <%@ include file="./modalCreate.jsp" %> 
    <%@ include file="../Components/footer.jsp" %>
  </body>
</html>
