package com.tareas.tareas.enums;

public enum TaskStatus {
  TODO("Por hacer"),
  IN_PROGRESS("En progreso"),
  ON_HOLD("En espera"),
  COMPLETED("Completada"),
  CANCELLED("Cancelada");

  private final String description;

  TaskStatus(String description) {
    this.description = description;
  }

  public String description() {
    return description;
  }
}