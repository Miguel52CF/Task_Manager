package com.tareas.tareas.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tareas.tareas.dto.task.TaskDTO;
import com.tareas.tareas.dto.task.TaskRequestDTO;
import com.tareas.tareas.models.Task;
import com.tareas.tareas.services.TaskService;
import com.tareas.tareas.utils.ValidationUtils;

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
  public ResponseEntity<?> getAllTasks() {
    List<?> tasks = taskService.findAll();
    return ResponseEntity.ok(tasks);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getTaskById(@PathVariable Long id) {
    TaskDTO task = taskService.findDTOById(id);
    return ResponseEntity.ok(task);
  }

  @PostMapping("/new")
  public ResponseEntity<?> createTask(
      @Valid @RequestBody Task task,
      BindingResult result) {

    if (result.hasErrors()) {
      return ValidationUtils.createValidationErrorResponse(result);
    }

    task = taskService.save(task);

    TaskDTO taskDTO = taskService.findDTOById(task.getId());
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(taskDTO);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateTask(
      @PathVariable Long id,
      @RequestBody TaskRequestDTO updatedTask) {

    TaskDTO task = taskService.updateTask(id, updatedTask);
    return ResponseEntity.ok(task);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteTask(@PathVariable Long id) {
    String response = taskService.delete(id);
    return ResponseEntity.ok(response);
  }
}
