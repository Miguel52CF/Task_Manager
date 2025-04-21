package com.tareas.tareas.dto.TaskType;

import lombok.Getter;

@Getter
public class TaskTypeRequestDTO extends TaskTypeDTO {

  public TaskTypeRequestDTO(String name, String icon) {
    super(name, icon);
  }

}
