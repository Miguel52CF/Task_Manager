<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!-- Modal para Crear Tarea -->
<div class="modal fade" id="crearTareaModal" tabindex="-1" aria-labelledby="crearTareaModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="crearTareaModalLabel">Crear Nueva Tarea</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <!-- Formulario para Crear Tarea -->
        <form action="/tasks/create" method="POST">
          <div class="mb-3">
            <label for="titulo" class="form-label">Título</label>
            <input type="text" class="form-control" id="titulo" name="titulo" required />
          </div>
          <div class="mb-3">
            <label for="descripcion" class="form-label">Descripción</label>
            <textarea class="form-control" id="descripcion" name="descripcion" rows="3" required></textarea>
          </div>
          <div class="mb-3">
            <label for="estado" class="form-label">Estado</label>
            <select class="form-select" id="estado" name="estado" required>
              <option value="TODO">Por hacer</option>
              <option value="IN_PROGRESS">En progreso</option>
              <option value="ON_HOLD">En espera</option>
              <option value="COMPLETED">Completada</option>
              <option value="CANCELLED">Cancelada</option>
            </select>
          </div>
          <div class="mb-3">
            <label for="tipoTarea" class="form-label">Tipo de Tarea</label>
            <select class="form-select" id="tipoTarea" name="tipoTarea" required>
              <option value="1">Urgente</option>
              <option value="2">Normal</option>
              <option value="3">Baja Prioridad</option>
            </select>
          </div>
          <div class="mb-3">
            <label for="tareaPadre" class="form-label">Tarea Principal (si es subtarea)</label>
            <select class="form-select" id="tareaPadre" name="parentTaskId">
              <option value="">Seleccionar tarea principal (opcional)</option>
              <c:forEach var="task" items="${tasks}">
                <option value="${task.id}">${task.title}</option>
              </c:forEach>
            </select>
          </div>
          <div class="d-flex justify-content-end">
            <button type="submit" class="btn btn-primary">Crear Tarea</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>