package com.tareas.tareas.dto.task;

import com.tareas.tareas.enums.TaskStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TaskDTO {
  private Long id;
  private String title;
  private String task;
  private TaskStatus status;
}
