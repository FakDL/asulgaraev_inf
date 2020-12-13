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

    <style>
        .doclist {
            font-size: 26px;
        }
    </style>
    <title>Отделения</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">

    <script
            src="https://code.jquery.com/jquery-3.5.1.js"
            integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
            crossorigin="anonymous"></script>

    <script>

    function renderList(doctors, list) {
            let innerHtml = '';
            for(let i = 0; i < doctors.length; i++) {
                innerHtml += doctors[i]['firstName'] + " " + doctors[i]['lastName'] + '<br>';
            }
            list.html(innerHtml);
        }


        function sendReq(depId) {

            let data = {
                "id": depId
            };

            $.ajax({
                type: "POST",
                url: "/departments",
                data: JSON.stringify(data),
                success: function (response) {
                    renderList(response, $('#list'))
                },
                dataType: "json",
                contentType: "application/json"
            });
        }
    </script>
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
<h1 class="text-center">Доктора отделений</h1>

<select onchange="sendReq(this.value)" class="custom-select" name="type" id="typeId">
    <option selected>Выберите отделение:</option>
    <c:forEach items="${departments}" var="dep">
        <option  value="${dep.id}">${dep.name}</option>
    </c:forEach>
</select>

<ul id="list" class="text-center doclist" >
</ul>
</body>
</html>
