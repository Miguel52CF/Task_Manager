document.addEventListener("DOMContentLoaded", function () {
  initEmojiPicker();
  loadTaskTypes();

  document
    .getElementById("crearTaskTypeForm")
    .addEventListener("submit", submitTaskTypeForm);

  document
    .getElementById("crearCategoriaModal")
    .addEventListener("hidden.bs.modal", function () {
      document.getElementById("crearTaskTypeForm").reset();
      document.getElementById("selectedEmojiPreview").textContent = "😊";
    });
});

// Load emojis from JSON file
async function loadEmojis() {
  try {
    const response = await fetch("/json/emojis.json");
    if (!response.ok) throw new Error("Failed to load emojis");
    const data = await response.json();
    return data.categoryEmojis;
  } catch (error) {
    console.error("Error loading emojis:", error);
    return ["📁", "📂", "📝", "📌", "📍", "📊"];
  }
}

// Initialize emoji picker
async function initEmojiPicker() {
  const emojiPickerContainer = document.getElementById("emojiPickerContainer");
  const emojiInput = document.getElementById("iconoCategoria");
  const emojiPreview = document.getElementById("selectedEmojiPreview");
  const pickerButton = document.getElementById("emojiPickerButton");

  const emojis = await loadEmojis();
  emojiPickerContainer.innerHTML = "";

  emojis.forEach((emoji) => {
    const emojiButton = document.createElement("button");
    emojiButton.type = "button";
    emojiButton.className = "btn btn-emoji";
    emojiButton.style.cssText =
      "font-size:1.5rem;padding:5px;margin:2px;border:none;background:none;";
    emojiButton.textContent = emoji;
    emojiButton.addEventListener("click", () => {
      emojiInput.value = emoji;
      emojiPreview.textContent = emoji;
      emojiPickerContainer.style.display = "none";
    });
    emojiPickerContainer.appendChild(emojiButton);
  });

  pickerButton.addEventListener("click", () => {
    emojiPickerContainer.style.display =
      emojiPickerContainer.style.display === "none" ? "block" : "none";
  });

  document.addEventListener("click", (event) => {
    if (
      !pickerButton.contains(event.target) &&
      !emojiPickerContainer.contains(event.target)
    ) {
      emojiPickerContainer.style.display = "none";
    }
  });
}

// Submit form to create new task type
async function submitTaskTypeForm(event) {
  event.preventDefault();

  const nombre = document.getElementById("nombreCategoria").value.trim();
  const icono = document.getElementById("iconoCategoria").value.trim();

  if (!nombre) {
    Swal.fire("Campos incompletos", "Por favor ingresa un nombre", "warning");
    return;
  }

  const taskTypeDTO = { name: nombre, icon: icono };
  const submitBtn = event.target.querySelector('button[type="submit"]');

  try {
    submitBtn.disabled = true;
    submitBtn.innerHTML =
      '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Guardando...';

    const response = await fetch("/taskTypes/new", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(taskTypeDTO),
    });

    const message = await response.text();

    if (!response.ok) {
      throw new Error(message);
    }

    const modal = bootstrap.Modal.getInstance(
      document.getElementById("crearCategoriaModal")
    );
    modal.hide();

    Swal.fire({
      title: "¡Guardado!",
      text: "La categoría fue creada exitosamente.",
      icon: "success",
      confirmButtonText: "Aceptar",
    }).then(() => {
      // Esperar 3 segundos antes de recargar
      setTimeout(() => {
        window.location.reload();
      }, 3000);
    });
  } catch (error) {
    console.error("Error:", error);
    Swal.fire("Error", error.message, "error");
  } finally {
    submitBtn.disabled = false;
    submitBtn.textContent = "Guardar";
  }
}
// Load and render task types
async function loadTaskTypes() {
  try {
    const response = await fetch("/taskTypes/all");
    const data = await response.json();

    const tbody = document.getElementById("taskTypeList");
    tbody.innerHTML = "";

    console.log("debug", data);
    

    data.forEach((type) => {
      const tr = document.createElement("tr");
      tr.innerHTML = `
        <td class="name">${type.name}</td>
        <td class="icon">
          <span class="icon-preview">${type.icon}</span>
        </td>
        <td class="text-center action-buttons">
          <div class="btn-group btn-group-sm" role="group">
            <button class="btn btn-outline-primary" title="Editar">
              <i class="fas fa-edit"></i>
            </button>
            <button class="btn btn-outline-danger" onclick="deleteTaskType(${type.id})" title="Eliminar">
              <i class="fas fa-trash-alt"></i>
            </button>
          </div>
        </td>
      `;
      tbody.appendChild(tr);
    });

    if (window.taskList) window.taskList.clear();

    window.taskList = new List("taskListWrapper", {
      valueNames: ["name", "icon"],
      page: 5,
      pagination: {
        innerWindow: 1,
        left: 0,
        right: 0,
        paginationClass: "pagination",
      },
    });
  } catch (error) {
    console.error("Error loading task types:", error);
    Swal.fire("Error", "No se pudieron cargar los tipos de tarea", "error");
  }
}

async function deleteTaskType(id) {
  // Mostrar confirmación antes de eliminar
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
    // Mostrar indicador de carga
    Swal.fire({
      title: "Eliminando...",
      text: "Por favor espera",
      allowOutsideClick: false,
      didOpen: () => {
        Swal.showLoading();
      },
    });

    const response = await fetch(`/taskTypes/${id}`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
    });

    if (!response.ok) {
      throw new Error(await response.text());
    }

    const result = await response.text();

    // Mostrar mensaje de éxito y recargar después de 2 segundos
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
