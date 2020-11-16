<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Marsel
  Date: 23.09.2020
  Time: 12:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/signUp" method="post">
    <input type="email" name="email" placeholder="Email">
    <input type="text" name="firstName" placeholder="First Name">
    <input type="text" name="lastName" placeholder="Last Name">
    <input type="password" name="password" placeholder="Password">
    <input type="submit" value="Sign Up">
</form>
</body>
</html>
