<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Registration</title>
</head>
<body>
<p> This name is not available! Please, try again.</p>
<FORM action = "/register" method="post">
    Your login: <input type = "text" name = "login">
    <br />
    Your password: <input type = "password" name = "password">
    <input type = "submit" value = "Register" />
</FORM>
</body>
</html>
