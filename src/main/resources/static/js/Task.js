document.addEventListener("DOMContentLoaded", function () {
  loadTasks();
  loadModalSelects();

});

// Load and render tasks
async function loadTasks() {
  try {
    const response = await fetch("/tasks/all");
    const data = await response.json();

    const tbody = document.getElementById("tasksTableList");
    tbody.innerHTML = "";

    data.forEach((task) => {
      const tr = document.createElement("tr");

      // Subtareas
      let subTasksHtml = "<em class='text-muted'>Sin subtareas</em>";
      if (task.subTasks && task.subTasks.length > 0) {
        subTasksHtml = "<ul class='mb-0'>";
        task.subTasks.forEach((sub) => {
          subTasksHtml += `<li>${sub.title}</li>`;
        });
        subTasksHtml += "</ul>";
      }

      // Fila completa
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
      fetch("/taskTypes/all")
    ]);

    allTasks = await responseTask.json();
    allTaskTypes = await responseTypeTask.json();

    // Inicializa tipo de tarea
    new TomSelect('#tipoTarea', {
      maxOptions: 5,
      searchField: 'text',
      valueField: 'id',
      labelField: 'text',
      options: allTaskTypes.slice(0, 5).map(type => ({
        id: type.id,
        text: `${type.icon} ${type.name}`
      })),
      load: function(search, callback) {
        const filtered = search
          ? allTaskTypes.filter(type =>
              type.name.toLowerCase().includes(search.toLowerCase()))
          : allTaskTypes.slice(0, 5);

        callback(filtered.slice(0, 5).map(type => ({
          id: type.id,
          text: `${type.icon} ${type.name}`
        })));
      }
    });

    // Inicializa tarea padre
    new TomSelect('#tareaPadre', {
      maxOptions: 5,
      searchField: 'text',
      valueField: 'id',
      labelField: 'text',
      options: allTasks.slice(0, 5).map(task => ({
        id: task.id,
        text: task.title
      })),
      load: function(search, callback) {
        const filtered = search
          ? allTasks.filter(task =>
              task.title.toLowerCase().includes(search.toLowerCase()))
          : allTasks.slice(0, 5);

        callback(filtered.slice(0, 5).map(task => ({
          id: task.id,
          text: task.title
        })));
      }
    });

  } catch (error) {
    console.error("Error al cargar opciones:", error);
    Swal.fire("Error", "No se pudieron cargar las opciones", "error");
  }
}