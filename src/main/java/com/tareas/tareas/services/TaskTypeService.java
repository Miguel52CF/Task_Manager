package com.tareas.tareas.services;

import org.springframework.stereotype.Service;

import com.tareas.tareas.base.BaseService;
import com.tareas.tareas.models.TaskType;
import com.tareas.tareas.repositories.TaskTypeRepository;

@Service
public class TaskTypeService extends BaseService<TaskType> {

  private final TaskTypeRepository taskTypeRepository;

  public TaskTypeService(TaskTypeRepository taskTypeRepository) {
    super(taskTypeRepository, TaskType.class);
    this.taskTypeRepository = taskTypeRepository;
  }

}
