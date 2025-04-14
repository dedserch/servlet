<!-- register.jsp -->
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Register</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<div class="container">
  <h1>Register</h1>
  <p>Create a new account</p>
  <% if (request.getAttribute("error") != null) { %>
  <div id="error" style="color:red;"><%= request.getAttribute("error") %></div>
  <% } %>
  <form id="registerForm" action="/register" method="POST">
    <input type="text" name="username" placeholder="Username" required>
    <input type="email" name="email" placeholder="Email" required>
    <input type="password" name="password" placeholder="Password" required>
    <input type="password" name="repeatPassword" placeholder="Repeat Password" required>
    <button type="submit">Register</button>
  </form>
  <p class="already-account">Already have an account? <a href="./login.jsp">Log in</a></p>
</div>
<script>
  document.getElementById("registerForm").addEventListener("submit", function(e){
    var errorDiv = document.getElementById("error");
    if (!errorDiv) {
      errorDiv = document.createElement("div");
      errorDiv.id = "error";
      errorDiv.style.color = "red";
      this.prepend(errorDiv);
    }
    errorDiv.textContent = "";
    var password = this.password.value;
    var repeatPassword = this.repeatPassword.value;
    if(password !== repeatPassword){
      errorDiv.textContent = "The password must be the same!";
      e.preventDefault();
    }
  });
</script>
</body>
</html>
