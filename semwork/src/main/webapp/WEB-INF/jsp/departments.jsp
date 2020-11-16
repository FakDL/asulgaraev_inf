<%--
  Created by IntelliJ IDEA.
  User: FakDL
  Date: 05.11.2020
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Отделения</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
</head>
<body>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
    <a href="#" class="navbar-brand">Поликлиника</a>
    <button class="navbar-toggler" data-toggle="collapse" data-target="#navbarMenu">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarMenu">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a href="#" class="nav-link">Отделение</a>
            </li>
            <li class="nav-item">
                <a href="#" class="nav-link">Products</a>
            </li>
        </ul>
    </div>
</nav>

<ul>
    <c:forEach items="${departments}" var="dep">
        <li href="/doctors?depId=${dep.id}"><c:out value="${dep.name}"/></li>
    </c:forEach>
</ul>


</body>
</html>
