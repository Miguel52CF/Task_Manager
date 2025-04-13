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

import com.tareas.tareas.models.TaskType;
import com.tareas.tareas.services.TaskTypeService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/taskTypes")
public class TaskTypeController {

  private final TaskTypeService taskTypeService;

  public TaskTypeController(TaskTypeService taskTypeService) {
    this.taskTypeService = taskTypeService;
  }

  @GetMapping("")
  public String index() {
    return "TaskType/index.jsp";
  }

  @GetMapping("/all")
  public ResponseEntity<Object> getALl() {
    List<TaskType> categories = taskTypeService.findAll();
    return ResponseEntity.ok(categories);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getById(@PathVariable Long id) {
    TaskType categorie = taskTypeService.findById(id);
    return ResponseEntity.ok(categorie);
  }

  @PostMapping("/new")
  public ResponseEntity<?> createTaskType(@Valid @RequestBody TaskType taskType) {
    try {
      TaskType newTaskType = new TaskType();
      newTaskType.setName(taskType.getName().trim());
      newTaskType.setIcon(taskType.getIcon().trim());

      taskTypeService.save(newTaskType);

      return ResponseEntity.status(HttpStatus.CREATED)
          .body("Tipo de tarea creado exitosamente.");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Error al guardar el tipo de tarea: " + e.getMessage());
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> delete(@PathVariable Long id) {
    String response = taskTypeService.delete(id);
    return ResponseEntity.ok(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateTaskType(
      @PathVariable Long id,
      @RequestBody TaskType taskType) {

    TaskType categorie = taskTypeService.update(id, taskType);
    return ResponseEntity.ok(categorie);
  }

}
