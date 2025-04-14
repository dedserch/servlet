<!-- login.jsp -->
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<div class="container">
    <h1>Login</h1>
    <p>Enter your details to log in</p>
    <% if (request.getAttribute("error") != null) { %>
    <div id="error" style="color:red;"><%= request.getAttribute("error") %></div>
    <% } %>
    <form action="/login" method="POST">
        <input type="text" name="username" placeholder="Username" required>
        <input type="password" name="password" placeholder="Password" required>
        <button type="submit">Log in</button>
    </form>
    <p class="no-account">Don't have an account? <a href="./register.jsp">Register</a></p>
</div>
</body>
</html>
