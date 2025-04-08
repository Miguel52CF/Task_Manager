package com.tareas.tareas.base;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;

@Service
public abstract class BaseService<T> {

  private final BaseRepository<T> baseRepository;
  @Getter
  private final Class<T> modelType;

  public BaseService(BaseRepository<T> baseRepository, Class<T> modelType) {
    this.baseRepository = baseRepository;
    this.modelType = modelType;
  }

  public List<T> findAll() {
    return baseRepository.findAllByActiveIsTrue();
  }

  public T findById(Long id) {
    return baseRepository.findById(id)
        .orElseThrow(
            () -> new EntityNotFoundException("Object with id " + id + " not found."));
  }

  public T save(T object) {
    return baseRepository.save(object);
  }

  public T update(Long id, T updatedObject) {
    T existingObject = findById(id);
    BeanUtils.copyProperties(updatedObject, existingObject, "id", "active");
    save(existingObject);
    return findById(id);
  }

  public String delete(Long id) {
    T object = findById(id);
    BeanUtils.copyProperties(false, object, "active");
    save(object);
    return String.format("%s with id %d deleted successfully.", modelType.getSimpleName(), id);
  }

}
