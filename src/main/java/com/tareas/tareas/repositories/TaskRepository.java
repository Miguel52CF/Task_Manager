package com.tareas.tareas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tareas.tareas.base.BaseRepository;
import com.tareas.tareas.dto.task.TaskDTO;
import com.tareas.tareas.models.Task;

public interface TaskRepository extends BaseRepository<Task, TaskDTO> {

  @Query("""
        SELECT new com.tareas.tareas.dto.task.TaskDTO(
          t.id, t.title, t.task, t.status
        )
        FROM Task t
        WHERE t.parentTask.id = :parentId AND t.active = true
      """)
  List<TaskDTO> findAllSubTasksByParentId(@Param("parentId") Long parentId);

}
