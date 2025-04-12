package com.tareas.tareas.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tareas.tareas.enums.TaskStatus;
import com.tareas.tareas.models.Task;
import com.tareas.tareas.models.TaskType;

@Controller
@RequestMapping("/tasks")
public class TaskController {

  @GetMapping("")
  public String listarTareas(Model model) {
    TaskType urgente = new TaskType(1L, "Urgente", "bi-exclamation-circle");
    TaskType normal = new TaskType(2L, "Normal", "bi-list-task");
    TaskType baja = new TaskType(3L, "Baja Prioridad", "bi-hourglass-split");

    Task tarea1 = new Task();
    tarea1.setId(1L);
    tarea1.setTitle("Preparar informe");
    tarea1.setTask("Redactar y revisar el informe de ventas mensual.");
    tarea1.setStatus(TaskStatus.TODO);
    tarea1.setTaskType(urgente);

    Task tarea2 = new Task();
    tarea2.setId(2L);
    tarea2.setTitle("Reunión de equipo");
    tarea2.setTask("Discutir los avances del sprint actual.");
    tarea2.setStatus(TaskStatus.IN_PROGRESS);
    tarea2.setTaskType(normal);

    Task tarea3 = new Task();
    tarea3.setId(3L);
    tarea3.setTitle("Enviar reportes");
    tarea3.setTask("Enviar reportes financieros a la gerencia.");
    tarea3.setStatus(TaskStatus.COMPLETED);
    tarea3.setTaskType(urgente);
    tarea3.setParentTask(tarea1); // subtarea de tarea1

    Task tarea4 = new Task();
    tarea4.setId(4L);
    tarea4.setTitle("Revisión de presupuesto");
    tarea4.setTask("Verificar partidas del presupuesto 2025.");
    tarea4.setStatus(TaskStatus.ON_HOLD);
    tarea4.setTaskType(baja);

    Task tarea5 = new Task();
    tarea5.setId(5L);
    tarea5.setTitle("Planificación del evento anual");
    tarea5.setTask("Coordinar actividades, invitados y locación para el evento.");
    tarea5.setStatus(TaskStatus.TODO);
    tarea5.setTaskType(normal);

    // Subtareas para tarea5
    Task st1 = new Task();
    st1.setId(6L);
    st1.setTitle("Contactar proveedores");
    st1.setTask("Enviar cotizaciones a posibles proveedores.");
    st1.setStatus(TaskStatus.IN_PROGRESS);
    st1.setTaskType(normal);
    st1.setParentTask(tarea5);

    Task st2 = new Task();
    st2.setId(7L);
    st2.setTitle("Diseñar material publicitario");
    st2.setTask("Crear afiches, banners y publicaciones para redes.");
    st2.setStatus(TaskStatus.TODO);
    st2.setTaskType(baja);
    st2.setParentTask(tarea5);

    Task st3 = new Task();
    st3.setId(8L);
    st3.setTitle("Definir agenda");
    st3.setTask("Establecer el cronograma del evento.");
    st3.setStatus(TaskStatus.TODO);
    st3.setTaskType(normal);
    st3.setParentTask(tarea5);

    Task st4 = new Task();
    st4.setId(9L);
    st4.setTitle("Invitar ponentes");
    st4.setTask("Enviar invitaciones y confirmar participación.");
    st4.setStatus(TaskStatus.ON_HOLD);
    st4.setTaskType(normal);
    st4.setParentTask(tarea5);

    Task st5 = new Task();
    st5.setId(10L);
    st5.setTitle("Confirmar locación");
    st5.setTask("Reservar el lugar del evento.");
    st5.setStatus(TaskStatus.CANCELLED);
    st5.setTaskType(urgente);
    st5.setParentTask(tarea5);

    tarea1.setSubTasks(Arrays.asList(tarea3));
    tarea5.setSubTasks(Arrays.asList(st1, st2, st3, st4, st5));

    List<Task> tareas = Arrays.asList(tarea1, tarea2, tarea3, tarea4, tarea5);
    model.addAttribute("tasks", tareas);

    return "Task/index.jsp";
  }
}
