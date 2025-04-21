package com.tareas.tareas.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tareas.tareas.base.BaseService;
import com.tareas.tareas.dto.TaskType.TaskTypeDTO;
import com.tareas.tareas.mappers.TaskTypeMapper;
import com.tareas.tareas.models.TaskType;
import com.tareas.tareas.repositories.TaskTypeRepository;

@Service
public class TaskTypeService extends BaseService<TaskType, TaskTypeDTO> {

  private final TaskTypeRepository taskTypeRepository;
  private final TaskTypeMapper mapper;

  public TaskTypeService(TaskTypeRepository taskTypeRepository, TaskTypeMapper mapper) {
    super(taskTypeRepository, TaskType.class);
    this.taskTypeRepository = taskTypeRepository;
    this.mapper = mapper;
  }

  @Override
  public List<?> findAll() {
    return taskTypeRepository.findAllByActiveIsTrue()
        .stream()
        .map(mapper::response)
        .toList();
  }

  @Override
  public TaskTypeDTO findDTOById(Long id) {
    return mapper.response(
        findById(id));
  }

  @Override
  public TaskTypeDTO update(Long id, TaskTypeDTO updatedObject) {
    Long repsonseId = updateElement(id, updatedObject);
    return findDTOById(repsonseId);
  }

}
