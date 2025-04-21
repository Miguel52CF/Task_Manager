package com.tareas.tareas.dto.task;

import com.tareas.tareas.enums.TaskStatus;
import com.tareas.tareas.models.TaskType;

import lombok.Getter;

@Getter
public class TaskRequestDTO extends TaskDTO {
  private Long parentTaskId;
  private TaskType taskType;

  public TaskRequestDTO(
      Long id,
      String title,
      String task,
      TaskStatus status,
      Long parentTaskId,
      TaskType taskType) {
    super(
        id,
        title,
        task,
        status);
    this.parentTaskId = parentTaskId;
    this.taskType = taskType;
  }
}
