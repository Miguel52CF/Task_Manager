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

import com.tareas.tareas.dto.TaskType.TaskTypeDTO;
import com.tareas.tareas.dto.TaskType.TaskTypeRequestDTO;
import com.tareas.tareas.models.TaskType;
import com.tareas.tareas.services.TaskTypeService;
import com.tareas.tareas.utils.ValidationUtils;

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
    List<?> categories = taskTypeService.findAll();
    return ResponseEntity.ok(categories);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getById(@PathVariable Long id) {
    TaskTypeDTO categorie = taskTypeService.findDTOById(id);
    return ResponseEntity.ok(categorie);
  }

  @PostMapping("/new")
  public ResponseEntity<?> createTaskType(
      @Valid @RequestBody TaskType taskType,
      BindingResult result) {

    if (result.hasErrors()) {
      return ValidationUtils.createValidationErrorResponse(result);
    }

    taskType = taskTypeService.save(taskType);

    TaskTypeDTO taskTypeDTO = taskTypeService.findDTOById(taskType.getId());

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(taskTypeDTO);

  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateTaskType(
      @PathVariable Long id,
      @RequestBody TaskTypeRequestDTO taskType) {

    TaskTypeDTO categorie = taskTypeService.update(id, taskType);
    return ResponseEntity.ok(categorie);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> delete(@PathVariable Long id) {
    String response = taskTypeService.delete(id);
    return ResponseEntity.ok(response);
  }

}
