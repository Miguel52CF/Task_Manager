package com.tareas.tareas.models;

import java.util.List;

import com.tareas.tareas.base.BaseModel;
import com.tareas.tareas.enums.TaskStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
public class Task extends BaseModel {

  @NotNull
  @NotEmpty
  private String title;

  @NotNull
  @NotEmpty
  private String task;

  @NotNull
  private TaskStatus status;

  @ManyToOne
  @JoinColumn(name = "task_type_id", nullable = false)
  private TaskType taskType;

  @ManyToOne
  @JoinColumn(name = "parent_task_id")
  private Task parentTask;

  @OneToMany(mappedBy = "parentTask")
  private List<Task> subTasks;

}
