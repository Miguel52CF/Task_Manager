package com.tareas.tareas.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tareas.tareas.base.BaseModel;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "task_types")
@Getter
@Setter
@NoArgsConstructor
public class TaskType extends BaseModel {

  @NotNull
  @NotEmpty
  private String name;

  private String icon;

  @JsonIgnore
  @OneToMany(mappedBy = "taskType")
  private List<Task> tasks;

  public TaskType(Long id, String name, String icon) {
    this.setId(id);
    this.name = name;
    this.icon = icon;
  }

}
