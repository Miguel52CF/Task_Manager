package com.tareas.tareas.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tareas.tareas.models.TaskType;

@Controller
@RequestMapping("/taskTypes")
public class TaskTypeController {

  @GetMapping("")
  public String listarTipos(Model model) {
    // Lista simulada de tipos de tarea
    List<TaskType> tipos = Arrays.asList(
        new TaskType(1L, "Urgente", "bi-exclamation-circle"),
        new TaskType(2L, "Normal", "bi-list-task"),
        new TaskType(3L, "Baja Prioridad", "bi-hourglass-split"),
        new TaskType(4L, "Reunión", "bi-people"),
        new TaskType(5L, "Entrega", "bi-box-arrow-down"));

    model.addAttribute("taskTypes", tipos);
    return "TaskType/index.jsp";
  }

}
