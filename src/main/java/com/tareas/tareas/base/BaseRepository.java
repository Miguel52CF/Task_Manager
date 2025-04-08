package com.tareas.tareas.base;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, Long> {

  List<T> findAllByActiveIsTrue();

  Optional<T> findByIdAndActiveIsTrue(Long id);
}
