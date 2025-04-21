package com.tareas.tareas.base;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;

@Service
public abstract class BaseService<T extends BaseModel, DTO> {

  private final BaseRepository<T, DTO> baseRepository;
  @Getter
  private final Class<T> modelType;

  public BaseService(BaseRepository<T, DTO> baseRepository, Class<T> modelType) {
    this.baseRepository = baseRepository;
    this.modelType = modelType;
  }

  public T findById(Long id) {
    return baseRepository.findByIdAndActiveIsTrue(id)
        .orElseThrow(
            () -> new EntityNotFoundException("Object with id " + id + " not found."));
  }

  public abstract DTO findDTOById(Long id);

  public abstract List<?> findAll();

  public T save(T object) {
    return baseRepository.save(object);
  }

  public void saveAll(List<T> objects) {
    baseRepository.saveAll(objects);
  }

  public Long updateElement(Long id, DTO updatedObject) {
    T existingObject = findById(id);
    BeanUtils.copyProperties(updatedObject, existingObject, "id", "active");
    save(existingObject);
    return id;
  }

  public abstract DTO update(Long id, DTO updatedObject);

  public String delete(Long id) {
    T object = findById(id);
    object.setActive(false);
    save(object);
    return String.format("%s with id %d deleted successfully.", modelType.getSimpleName(), id);
  }

}
