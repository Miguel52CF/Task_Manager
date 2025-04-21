package com.tareas.tareas.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.tareas.tareas.dto.TaskType.TaskTypeResponseDTO;
import com.tareas.tareas.dto.task.TaskDTO;
import com.tareas.tareas.dto.task.TaskResponseDTO;
import com.tareas.tareas.models.Task;
import com.tareas.tareas.repositories.TaskRepository;

@Component
public class TaskMapper {

  private final TaskRepository taskRepository;
  private final TaskTypeMapper taskTypeMapper;

  public TaskMapper(TaskRepository taskRepository, TaskTypeMapper taskTypeMapper) {
    this.taskRepository = taskRepository;
    this.taskTypeMapper = taskTypeMapper;
  }

  public TaskResponseDTO response(Task task) {

    TaskDTO parentTask = null;

    if (task.getParentTask() != null) {
      parentTask = taskRepository
          .findByIdAndActiveIsTrue(task.getParentTask().getId())
          .map(this::toDTO)
          .orElse(null);
    }

    List<TaskDTO> subTasks = taskRepository.findAllSubTasksByParentId(task.getId());

    TaskTypeResponseDTO tasktype = taskTypeMapper.response(task.getTaskType());

    return new TaskResponseDTO(
        task.getId(),
        task.getTitle(),
        task.getTask(),
        task.getStatus(),
        parentTask,
        subTasks,
        tasktype);
  }

  private TaskDTO toDTO(Task task) {
    return new TaskDTO(
        task.getId(),
        task.getTitle(),
        task.getTask(),
        task.getStatus());
  }

}
