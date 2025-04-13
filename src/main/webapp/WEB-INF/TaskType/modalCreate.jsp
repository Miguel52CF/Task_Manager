<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<div class="modal fade" id="crearCategoriaModal" tabindex="-1" aria-labelledby="crearCategoriaModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form id="crearTaskTypeForm">
                <input type="hidden" id="taskTypeId" name="id" value="">
                <div class="modal-header">
                    <h5 class="modal-title" id="crearCategoriaModalLabel">
                        Crear nueva categoría
                    </h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                </div>
                <div class="modal-body">
                    <div class="mb-3">
                        <label for="nombreCategoria" class="form-label">Nombre</label>
                        <input type="text" class="form-control" id="nombreCategoria" name="nombre" required />
                    </div>
                    <div class="mb-3">
                        <label for="iconoCategoria" class="form-label">Ícono (Emoji)</label>
                        <div class="input-group">
                            <input type="text" class="form-control" id="iconoCategoria" name="icono" readonly />
                            <button type="button" class="btn btn-outline-secondary" id="emojiPickerButton">
                                <span id="selectedEmojiPreview">😊</span> Seleccionar
                            </button>
                        </div>
                        <div id="emojiPickerContainer" style="display: none; max-height: 200px; overflow-y: auto;">
                            <!-- Emojis se cargarán aquí -->
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                        Cancelar
                    </button>
                    <button type="submit" class="btn btn-success">Guardar</button>
                </div>
            </form>
        </div>
    </div>
</div>