package com.tareas.tareas.dto.TaskType;

import lombok.Getter;

@Getter
public class TaskTypeResponseDTO extends TaskTypeDTO {
  private Long id;

  public TaskTypeResponseDTO(
      Long id,
      String name,
      String icon) {
    super(name, icon);
    this.id = id;
  }
}
