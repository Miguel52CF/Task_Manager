package com.tareas.tareas.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.tareas.tareas.base.BaseService;
import com.tareas.tareas.dto.task.TaskDTO;
import com.tareas.tareas.dto.task.TaskRequestDTO;
import com.tareas.tareas.mappers.TaskMapper;
import com.tareas.tareas.models.Task;
import com.tareas.tareas.repositories.TaskRepository;

@Service
public class TaskService extends BaseService<Task, TaskDTO> {

  private final TaskRepository taskRepository;
  private final TaskMapper mapper;

  public TaskService(TaskRepository taskRepository,
      TaskMapper mapper) {
    super(taskRepository, Task.class);
    this.taskRepository = taskRepository;
    this.mapper = mapper;
  }

  @Override
  public List<?> findAll() {
    return taskRepository.findAllByActiveIsTrue()
        .stream()
        .map(mapper::response)
        .toList();
  }

  @Override
  public TaskDTO findDTOById(Long id) {
    return mapper.response(
        findById(id));
  }

  @Override
  public TaskDTO update(Long id, TaskDTO updatedObject) {
    Long reponseId = updateElement(id, updatedObject);
    return findDTOById(reponseId);
  }

  public TaskDTO updateTask(Long id, TaskRequestDTO updatedTaskDTO) {
    Task task = findById(id);
    BeanUtils.copyProperties(updatedTaskDTO, task, "id", "active");

    if (updatedTaskDTO.getParentTaskId() != null) {
      Task newParent = findById(updatedTaskDTO.getParentTaskId());
      task.setParentTask(newParent);
    }

    save(task);
    return findDTOById(task.getId());
  }

  @Override
  public String delete(Long id) {
    Task task = findById(id);

    if (task.getParentTask() != null) {
      task.setParentTask(null);
    }

    String taskTile = task.getTitle();

    deactivateSubTasks(task);
    task.setActive(false);
    save(task);

    return String.format("Task con nombre " + taskTile + " eliminada exitosamente.");
  }

  private void deactivateSubTasks(Task task) {
    if (task.getSubTasks() != null && !task.getSubTasks().isEmpty()) {
      for (Task subTask : task.getSubTasks()) {
        if (subTask.isActive()) {
          subTask.setActive(false);
          subTask.setParentTask(null);
          save(subTask);
          deactivateSubTasks(subTask);
        }
      }
    }
  }

  @Override
  public Task save(Task task) {

    if (task.getParentTaskId() != null) {
      Task parent = findById(task.getParentTaskId());
      task.setParentTask(parent);
    }

    return super.save(task);
  }

}
