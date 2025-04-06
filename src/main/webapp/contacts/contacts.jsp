<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Your Contacts</title>
  <link rel="stylesheet" href="../css/contact.css">
</head>
<body class="contact-page">
<div class="contact-wrapper">
  <h2>My Contacts</h2>

  <form action="${pageContext.request.contextPath}/contacts/add" method="post">
    <div class="contact-form-group">
      <input type="text" name="name" placeholder="Name" required>
      <input type="text" name="phone" placeholder="Phone" required>
      <input type="email" name="email" placeholder="Email" required>
      <button type="submit" class="btn-contact-add">Add Contact</button>
    </div>
  </form>

  <c:if test="${not empty contacts}">
    <table class="contact-table">
      <thead>
      <tr>
        <th>Name</th>
        <th>Phone</th>
        <th>Email</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="contact" items="${contacts}">
        <tr>
          <td>${contact.name}</td>
          <td>${contact.phone}</td>
          <td>${contact.email}</td>
          <td>
            <form action="${pageContext.request.contextPath}/contacts/delete" method="post" style="display:inline;">
              <input type="hidden" name="contactId" value="${contact.id}">
              <button type="submit" class="btn-contact-delete">Delete</button>
            </form>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </c:if>
</div>
</body>
</html>