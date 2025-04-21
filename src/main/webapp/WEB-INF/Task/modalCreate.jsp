<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!-- Modal para Crear o Editar Tarea -->
<div class="modal fade" id="crearTareaModal" tabindex="-1" aria-labelledby="crearTareaModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form id="createTaskForm">
                <input type="hidden" id="taskId" name="id" value="">
                <div class="modal-header">
                    <h5 class="modal-title" id="crearTareaModalLabel">Crear Nueva Tarea</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                </div>

                <div class="modal-body">
                    <div class="mb-3">
                        <label for="titulo" class="form-label">Título</label>
                        <input type="text" class="form-control" id="titulo" name="titulo" required />
                    </div>

                    <div class="mb-3">
                        <label for="descripcion" class="form-label">Descripción</label>
                        <textarea class="form-control" id="descripcion" name="descripcion" rows="3" required></textarea>
                    </div>

                    <div class="mb-3">
                        <label for="tipoTarea" class="form-label">Tipo de Tarea</label>
                        <select class="form-select" id="tipoTarea" name="tipoTarea" required style="width: 100%"></select>
                    </div>

                    <div class="mb-3">
                        <label for="tareaPadre" class="form-label">Tarea Principal (si es subtarea)</label>
                        <select class="form-select" style="width: 100%" id="tareaPadre" name="parentTaskId">
                            <option value="">Seleccionar tarea principal (opcional)</option>
                        </select>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <button type="submit" class="btn btn-primary">Crear Tarea</button>
                </div>
            </form>
        </div>
    </div>
</div>
