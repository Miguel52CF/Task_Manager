package com.tareas.tareas.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tareas.tareas.enums.TaskStatus;
import com.tareas.tareas.models.Task;
import com.tareas.tareas.services.TaskService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/tasks")
public class TaskController {

  private final TaskService taskService;

  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @GetMapping("")
  public String index() {
    return "Task/index.jsp";
  }

  @GetMapping("/all")
  public ResponseEntity<List<Task>> getAllTasks() {
    List<Task> tasks = taskService.findAll();
    return ResponseEntity.ok(tasks);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getTaskById(@PathVariable Long id) {
    Task task = taskService.findById(id);
    if (task == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarea no encontrada.");
    }
    return ResponseEntity.ok(task);
  }

  @PostMapping("/new")
  public ResponseEntity<?> createTask(@Valid @RequestBody Task task) {
    try {
      if (task.getStatus() == null) {
        task.setStatus(TaskStatus.TODO);
      }

      // Manejar parentTask
      if (task.getParentTaskId() != null) {
        Task parent = taskService.findById(task.getParentTaskId());
        task.setParentTask(parent);
      }

      taskService.save(task);

      return ResponseEntity.status(HttpStatus.CREATED)
          .body("Tarea creada exitosamente.");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Error al crear la tarea: " + e.getMessage());
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateTask(@PathVariable Long id, @Valid @RequestBody Task updatedTask) {
    try {
      Task existingTask = taskService.findById(id);
      if (existingTask == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarea no encontrada.");
      }

      existingTask.setTitle(updatedTask.getTitle().trim());
      existingTask.setTask(updatedTask.getTask().trim());
      existingTask.setTaskType(updatedTask.getTaskType());
      existingTask.setStatus(updatedTask.getStatus() != null ? updatedTask.getStatus() : TaskStatus.TODO);

      // Manejar parentTask
      if (updatedTask.getParentTaskId() != null) {
        Task parent = taskService.findById(updatedTask.getParentTaskId());
        existingTask.setParentTask(parent);
      } else {
        existingTask.setParentTask(null);
      }

      taskService.save(existingTask);

      return ResponseEntity.ok("Tarea actualizada exitosamente.");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Error al actualizar la tarea: " + e.getMessage());
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteTask(@PathVariable Long id) {
    try {
      taskService.delete(id);
      return ResponseEntity.ok("Tarea eliminada exitosamente.");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Error al eliminar la tarea: " + e.getMessage());
    }
  }
}
