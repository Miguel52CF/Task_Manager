package com.tareas.tareas.services;

import org.springframework.stereotype.Service;

import com.tareas.tareas.base.BaseService;
import com.tareas.tareas.models.Task;
import com.tareas.tareas.repositories.TaskRepository;

@Service
public class TaskService extends BaseService<Task> {

  private final TaskRepository taskRepository;

  public TaskService(TaskRepository taskRepository) {
    super(taskRepository, Task.class);
    this.taskRepository = taskRepository;
  }

   public String delete(Long id) {
    Task task = findById(id);
    task.setActive(false);
    task = save(task);
    return "Delete successful";
  }

}
