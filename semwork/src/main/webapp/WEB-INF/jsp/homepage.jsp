<%--
  Created by IntelliJ IDEA.
  User: FakDL
  Date: 26.11.2020
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
    .img{
        padding: 5px; /* Поля вокруг текста */
        float: left; /* Обтекание по правому краю */
        width: 800px; /* Ширина слоя */
    }
    .text{
        font-size: 32px;
    }
</style>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <title>Главная</title>
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
<h1 class="text-center">Поликлиника №3</h1>
<p><img class="img" height="450" src="https://i1.photo.2gis.com/images/branch/29/4081387196325424_10c5.jpg" width="800"></p>
<p class="text">Наше многопрофильное лечебно-профилактическое учреждение одно из лучших в вашем городе.
    Лучшее оборудование.
    Мы предоставляем качественные услуги.</p>
</body>
</html>
