package com.tareas.tareas.enums;

public enum TaskStatus {
  TODO("Por hacer", "#007bff"),
  IN_PROGRESS("En progreso", "#ffc107"),
  ON_HOLD("En espera", "#6c757d"),
  COMPLETED("Completada", "#28a745"),
  CANCELLED("Cancelada", "#dc3545");

  private final String description;
  private final String color;

  TaskStatus(String description, String color) {
    this.description = description;
    this.color = color;
  }

  public String description() {
    return description;
  }

  public String color() {
    return color;
  }
}