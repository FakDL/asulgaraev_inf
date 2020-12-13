<%--
  Created by IntelliJ IDEA.
  User: FakDL
  Date: 29.11.2020
  Time: 21:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Контакты</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
</head>
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

<h1>Как с нами связаться?</h1>

<h2>Наша почта: poliklinika3kzn@mail.ru</h2>
<h2>Наш телефон: 88005553535</h2>
<h2>Наш адрес: Фатыха Амирхана 12б</h2>

</body>
</html>
