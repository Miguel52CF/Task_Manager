# 🗂️ Task Manager

A personal task manager built with Java and Spring Boot. This project is a personal exercise to deepen knowledge in Spring Boot development, database management, and JSP. The goal is to create a simple task management app, similar to sticky notes or post-its.

---

## ✨ Description

This project aims to serve as a personal task manager. It allows the creation of different types of tasks and their visual organization. It is intended more as a learning project than a finished product, although anyone who wants to use it personally is welcome to do so.

---

## 🚀 Technologies Used

- **Backend:**
  - Java 17+
  - Spring Boot
  - Spring Web
  - Spring Data JPA
  - Apache Commons
  - MySQL

- **Frontend:**
  - JSP (Java Server Pages)
  - HTML5
  - Bootstrap
  - Vanilla JavaScript

---

## 🧱 Project Structure

```
Task_Manager/
│
├── src/
│   └── main/
│       ├── java/
│       │   └── [Main classes and project logic]
│       ├── resources/
│       │   ├── static/
│       │   │   ├── css/
│       │   │   └── js/
│       │   └── application.properties
│       └── webapp/
│           └── WEB-INF/
│               └── [JSP views]
│
├── pom.xml
└── README.md
```

> Main classes are located under `src/main/java`, and JSP views under `src/main/webapp/WEB-INF`. Static resources (CSS, JS) are located in `resources/static`.

---

## ✅ Features

### Currently available:
- Create basic tasks
- Save tasks to a local database

### Future ideas (not confirmed):
- Task types
- Filters by date and priority
- Task hierarchy with subtasks
- Post-it style visual layout

---

## ⚙️ How to Run the Project

1. Clone the repository:
   ```bash
   git clone https://github.com/Miguel52CF/Task_Manager.git
   cd Task_Manager
   ```

2. Configure your MySQL database in `application.properties`.

3. Run with Maven:
   ```bash
   ./mvnw spring-boot:run
   ```

> No `Dockerfile` or deployment configuration is provided. The project is intended for local use and practice purposes.

---

## 👤 Author

Created by **📛化け猫**  
[GitHub](https://github.com/Miguel52CF)

---

## 📄 License

This project is **open source** and can be freely used for personal or educational purposes. If you use or modify it, I’d appreciate it if you keep credit to the original author.  
Thanks for respecting the shared effort and learning! 🙌
