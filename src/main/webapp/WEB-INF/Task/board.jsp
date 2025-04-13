<%@ page language="java" contentType="text/html; charset=UTF-8" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es" data-bs-theme="light">
  <head>
    <meta charset="UTF-8" />
    <title>Board de tareas</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <style>
      .action-buttons .btn {
        width: 40px;
        padding: 0.375rem;
      }
      .table th,
      .table td {
        vertical-align: middle;
      }
    </style>
  </head>
  <body>
    <%@ include file="../Components/header.jsp" %>

    <div class="container main-content mt-4">
      <h1>Ist building please wait</h1>
    </div>

    <%@ include file="../Components/footer.jsp" %>
  </body>
</html>
