package com.tareas.tareas.mappers;

import org.springframework.stereotype.Component;

import com.tareas.tareas.dto.TaskType.TaskTypeResponseDTO;
import com.tareas.tareas.models.TaskType;

@Component
public class TaskTypeMapper {

  public TaskTypeResponseDTO response(TaskType taskType) {
    return new TaskTypeResponseDTO(
        taskType.getId(),
        taskType.getName(),
        taskType.getIcon());
  }

}
