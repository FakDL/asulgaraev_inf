<%--
  Created by IntelliJ IDEA.
  User: FakDL
  Date: 11.11.2020
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Услуги</title>
</head>
<style>
    .dep{
        font-size: 32px;
    }
</style>
<script>
    function checkService(servName, servPrice, servId, servDepId, depId) {
        if (servDepId === depId) {
            document.getElementById(depId).innerHTML += '<li>' + servName + ' ' + servPrice + ' руб' + '</li>'

        }
    }

</script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<body>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
    <a href="/homepage" class="navbar-brand">Поликлиника</a>
    <button class="navbar-toggler" data-toggle="collapse" data-target="#navbarMenu">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarMenu">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/departments" class="nav-link">Доктора</a>
            </li>
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/appointment" class="nav-link">Запись</a>
            </li>
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/services" class="nav-link">Услуги</a>
            </li>
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/profile" class="nav-link">Профиль</a>
            </li>
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/about" class="nav-link">Контакты</a>
            </li>
        </ul>
    </div>
</nav>
<h1 class="text-center">Услуги поликлиники</h1>

    <ul>
        <c:forEach items="${departments}" var="dep">
            <li class="dep"> ${dep.name}
                <ul id="${dep.id}">
                    <c:forEach items="${services}" var="serv">
                        <script>
                            checkService('${serv.name}', ${serv.price}, ${serv.id}, ${serv.department_id}, ${dep.id})
                        </script>
                    </c:forEach>
                </ul>
            </li>
        </c:forEach>
    </ul>

</body>
</html>
