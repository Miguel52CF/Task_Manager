document.addEventListener("DOMContentLoaded", function () {
  loadTasks();
  loadModalSelects();

  document
    .getElementById("createTaskForm")
    .addEventListener("submit", submitTaskForm);

  document
    .getElementById("crearTareaModal")
    .addEventListener("hidden.bs.modal", function () {
      resetTaskForm();
    });
});

function resetTaskForm() {
  document.getElementById("createTaskForm").reset();
  document.getElementById("taskId").value = "";
  document.getElementById("tipoTarea").value = "";
  document.getElementById("tareaPadre").value = "";
  document.getElementById("crearTareaModalLabel").textContent =
    "Crear Nueva Tarea";
}

// Load and render tasks
async function loadTasks() {
  try {
    const response = await fetch("/tasks/all");
    const data = await response.json();

    const tbody = document.getElementById("tasksTableList");
    tbody.innerHTML = "";

    data.forEach((task) => {
      const tr = document.createElement("tr");

      let subTasksHtml = "<em class='text-muted'>Sin subtareas</em>";
      if (task.subTasks && task.subTasks.length > 0) {
        subTasksHtml = "<ul class='mb-0'>";
        task.subTasks.forEach((sub) => {
          subTasksHtml += `<li>${sub.title}</li>`;
        });
        subTasksHtml += "</ul>";
      }

      tr.innerHTML = `
        <td class="name">${task.title}</td>
        <td class="description">${task.task}</td>
        <td class="status">
          <span class="badge" style="background-color: ${task.status.color}">
            ${task.status}
          </span>
        </td>
        <td class="type">${task.taskType.name}</td>
        <td class="subtasks">${subTasksHtml}</td>
        <td class="text-center action-buttons">
          <div class="btn-group btn-group-sm" role="group">
            <button class="btn btn-outline-primary" onclick="openEditModal(${task.id})" title="Editar">
              <i class="fas fa-edit"></i>
            </button>
            <button class="btn btn-outline-danger" onclick="deleteTask(${task.id})" title="Eliminar">
              <i class="fas fa-trash-alt"></i>
            </button>
          </div>
        </td>
      `;

      tbody.appendChild(tr);
    });

    if (window.taskList) window.taskList.clear();

    window.taskList = new List("taskListWrapper", {
      valueNames: ["name", "description", "status", "type", "subtasks"],
      page: 5,
      pagination: {
        innerWindow: 1,
        left: 0,
        right: 0,
        paginationClass: "pagination",
      },
    });
  } catch (error) {
    console.error("Error loading tasks:", error);
    Swal.fire("Error", "No se pudieron cargar las tareas", "error");
  }
}

let allTasks = [];
let allTaskTypes = [];

async function loadModalSelects() {
  try {
    const [responseTask, responseTypeTask] = await Promise.all([
      fetch("/tasks/all"),
      fetch("/taskTypes/all"),
    ]);

    allTasks = await responseTask.json();
    allTaskTypes = await responseTypeTask.json();

    // Inicializa tipo de tarea
    new TomSelect("#tipoTarea", {
      maxOptions: 5,
      searchField: "text",
      valueField: "id",
      labelField: "text",
      options: allTaskTypes.slice(0, 5).map((type) => ({
        id: type.id,
        text: `${type.icon} ${type.name}`,
      })),
      load: function (search, callback) {
        const filtered = search
          ? allTaskTypes.filter((type) =>
              type.name.toLowerCase().includes(search.toLowerCase())
            )
          : allTaskTypes.slice(0, 5);

        callback(
          filtered.slice(0, 5).map((type) => ({
            id: type.id,
            text: `${type.icon} ${type.name}`,
          }))
        );
      },
    });

    new TomSelect("#tareaPadre", {
      maxOptions: 5,
      searchField: "text",
      valueField: "id",
      labelField: "text",
      options: allTasks.slice(0, 5).map((task) => ({
        id: task.id,
        text: task.title,
      })),
      load: function (search, callback) {
        const filtered = search
          ? allTasks.filter((task) =>
              task.title.toLowerCase().includes(search.toLowerCase())
            )
          : allTasks.slice(0, 5);

        callback(
          filtered.slice(0, 5).map((task) => ({
            id: task.id,
            text: task.title,
          }))
        );
      },
    });
  } catch (error) {
    console.error("Error al cargar opciones:", error);
    Swal.fire("Error", "No se pudieron cargar las opciones", "error");
  }
}

async function submitTaskForm(event) {
  event.preventDefault();

  function esInvalido(valor) {
    return !valor || valor.trim() === "";
  }

  const id = document.getElementById("taskId").value;
  const titulo = document.getElementById("titulo").value;
  const descripcion = document.getElementById("descripcion").value;
  const tipoTarea = document.getElementById("tipoTarea").value;
  const tareaPadre = document.getElementById("tareaPadre").value; // opcional

  const camposFaltantes = [];

  if (esInvalido(titulo)) camposFaltantes.push("Titulo");
  if (esInvalido(descripcion)) camposFaltantes.push("Descripción");
  if (esInvalido(tipoTarea)) camposFaltantes.push("Tipo de Tarea");

  if (camposFaltantes.length > 0) {
    Swal.fire(
      "Campos incompletos",
      `Faltan por llenar: ${camposFaltantes.join(", ")}`,
      "warning"
    );
    return;
  }

  const taskDTO = {
    title: titulo,
    task: descripcion,
    status: "TODO",
    taskType: { id: tipoTarea },
    parentTaskId: tareaPadre || null,
  };

  const submitBtn = event.target.querySelector('button[type="submit"]');
  const url = id ? `/tasks/${id}` : "/tasks/new";
  const method = id ? "PUT" : "POST";

  try {
    submitBtn.disabled = true;
    submitBtn.innerHTML =
      '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Guardando...';

    const response = await fetch(url, {
      method,
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(taskDTO),
    });

    const message = await response.text();

    if (!response.ok) throw new Error(message);

    const modal = bootstrap.Modal.getInstance(
      document.getElementById("crearTareaModal")
    );
    modal.hide();

    Swal.fire({
      title: "¡Guardado!",
      text: id
        ? "La tarea fue actualizada exitosamente."
        : "La tarea fue creada exitosamente.",
      icon: "success",
      confirmButtonText: "Aceptar",
    }).then(() => window.location.reload());
  } catch (error) {
    console.error("Error:", error);
    Swal.fire("Error", error.message, "error");
  } finally {
    submitBtn.disabled = false;
    submitBtn.textContent = "Crear Tarea";
  }
}

async function deleteTask(id) {
  const confirmation = await Swal.fire({
    title: "¿Estás seguro?",
    text: "¡No podrás revertir esta acción!",
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#3085d6",
    cancelButtonColor: "#d33",
    confirmButtonText: "Sí, eliminar",
    cancelButtonText: "Cancelar",
  });

  if (!confirmation.isConfirmed) return;

  try {
    Swal.fire({
      title: "Eliminando...",
      text: "Por favor espera",
      allowOutsideClick: false,
      didOpen: () => {
        Swal.showLoading();
      },
    });

    const response = await fetch(`/tasks/${id}`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
    });

    if (!response.ok) {
      throw new Error(await response.text());
    }

    const result = await response.text();

    Swal.fire({
      title: "¡Eliminado!",
      text: result,
      icon: "success",
      timer: 2000,
      timerProgressBar: true,
      showConfirmButton: false,
    }).then(() => {
      window.location.reload();
    });
  } catch (error) {
    console.error("Error al eliminar:", error);
    Swal.fire(
      "Error",
      error.message || "Ocurrió un error al eliminar",
      "error"
    );
  }
}

async function openEditModal(id) {
  try {
    const response = await fetch(`/tasks/${id}`);
    if (!response.ok) throw new Error("Error al cargar la tarea");

    const task = await response.json();

    document.getElementById("taskId").value = task.id;
    document.getElementById("titulo").value = task.title || "";
    document.getElementById("descripcion").value = task.task || "";

    const tipoTareaTomSelect = document.getElementById("tipoTarea").tomselect;
    const tareaPadreTomSelect = document.getElementById("tareaPadre").tomselect;

    console.log("el Objecto", task);
    
    console.log(tareaPadreTomSelect.options);
    console.log("el id",task.parentTaskId);
    

    if (task.taskType?.id) {
      tipoTareaTomSelect.setValue(task.taskType.id);
    } else {
      tipoTareaTomSelect.clear();
    }

    if (task.parentTaskId) {
      
    } else {
      tareaPadreTomSelect.clear();
    }

    document.getElementById("crearTareaModalLabel").textContent = "Editar Tarea";
    document.querySelector("#createTaskForm button[type='submit']").textContent = "Actualizar Tarea";

    const modal = new bootstrap.Modal(document.getElementById("crearTareaModal"));
    modal.show();

  } catch (error) {
    console.error("Error:", error);
    Swal.fire("Error", error.message, "error");
  }
}