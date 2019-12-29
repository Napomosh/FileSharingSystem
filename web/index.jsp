<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Sharing system</title>
</head>
<body>
<FORM action = "login" method="post">
  Your login: <input type = "text" name = "login">
  <br />
  Your password: <input type = "password" name = "password">
  <input type = "submit" value = "Login" />
</FORM>
<button type = "button" onclick = "location.href = 'startPages/register.jsp'"> Register </button>
</body>
</html>