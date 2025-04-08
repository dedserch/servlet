<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Profile</title>
    <link rel="stylesheet" href="../css/styles.css">
</head>
<body>
<div class="container">
    <h2>Edit Your Profile</h2>
    <div class="avatar-preview">
        <img src="${not empty user.avatarUrl ? user.avatarUrl : '/images/default-avatar.png'}"
             alt="Current Avatar"
             class="avatar">
    </div>

    <form action="${pageContext.request.contextPath}/profile/edit" method="POST" enctype="multipart/form-data">
        <div class="form-group">
            <label for="newUsername">Username:</label>
            <input type="text" id="newUsername" name="newUsername" value="${user.username}" required>
        </div>

        <div class="form-group">
            <label for="newAvatar">Avatar:</label>
            <input type="file" id="newAvatar" name="newAvatar" accept="image/*">
        </div>

        <button type="submit" class="btn-submit">Save Changes</button>
    </form>
</div>
</body>
</html>
