<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<link href="css/style.css" rel="stylesheet">
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Welcome to GeeGee Simulator!" %>
</h1>
<br/>
<a href="add-gee-gee">Add GeeGee to the database</a>
<br><br>
<a href="GoToHomePage">Go to your homepage</a>
<br><br>
Sign in
<form action="login"
      method="POST">
    username: <label>
    <input type="text" name="username">
</label> <br>
    password: <label>
    <input type="password" name="password">
</label><br> <input
        type="submit" value="Login">
</form>
<br><br>
New user? Sign up
<form action=""
      method="POST">
    <label> username:
        <input type="text" name="username">
    </label> <br>
    <label> email:
        <input type="text" name="email">
    </label> <br>
    <label> password:
        <input type="password" name="password">
    </label><br>
    <input
        type="submit" value="Sign up">
</form>
</body>
</html>