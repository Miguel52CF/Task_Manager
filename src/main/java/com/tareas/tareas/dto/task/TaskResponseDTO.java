package com.tareas.tareas.dto.task;

import java.util.List;

import com.tareas.tareas.dto.TaskType.TaskTypeResponseDTO;
import com.tareas.tareas.enums.TaskStatus;

import lombok.Getter;

@Getter
public class TaskResponseDTO extends TaskDTO {
  private List<TaskDTO> subTasks;
  private TaskDTO parentTask;
  private TaskTypeResponseDTO taskType;

  public TaskResponseDTO(
      Long id,
      String title,
      String task,
      TaskStatus status,
      TaskDTO parentTask,
      List<TaskDTO> subTasks,
      TaskTypeResponseDTO taskType) {
    super(
        id,
        title,
        task,
        status);
    this.parentTask = parentTask;
    this.subTasks = subTasks;
    this.taskType = taskType;
  }

}
