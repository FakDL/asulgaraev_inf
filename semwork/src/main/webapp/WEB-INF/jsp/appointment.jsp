<%--
  Created by IntelliJ IDEA.
  User: FakDL
  Date: 19.11.2020
  Time: 12:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <style>
        .btn{
            margin-top: 4px;
        }
        .card {
            /* Add shadows to create the "card" effect */
            box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
            transition: 0.3s;
            border-radius: 5px;
            padding: 12px;
        }
        .card:hover {
            box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
        }
        .container {
            padding: 2px 16px;
        }
    </style>
    <title>Запись</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script>
        function setDateLimit(id){
            var today = new Date()
            var nMonth
            var dd = today.getDate()
            var mm = today.getMonth()+1 //January is 0
            var nm, ny
            var yyyy = today.getFullYear()

            if(mm===12) {
                nm = '01'
                ny = yyyy + 1
            }else {
                nm = mm+1
                ny = yyyy
            }
            if(nm<10 && nm!=='01'){
                nm='0'+nm
            }

            if(dd<10){
                dd='0'+dd
            }
            if(mm<10){
                mm='0'+mm
            }



            today = yyyy+'-'+mm+'-'+dd;
            nMonth = ny+'-'+nm+'-'+dd;
            document.getElementById(id).setAttribute("min", today);
            document.getElementById(id).setAttribute("max", nMonth);
        }
        function clearHours(docId){
            document.getElementById('hours'+docId).innerHTML = ''
            console.log('cleared')
        }
        function checkService(servName, servDepId, docDepID, servId,docId){
            if (servDepId === docDepID) {
                document.getElementById('serv'+docId).innerHTML += '<option value="'+servId+'">'+ servName +'</option>'
            }
        }
        function renderList(blockedHours, listId) {

            for(let i = 8; i < 17; i++) {
                    if (blockedHours.includes(i)) {
                        document.getElementById(listId).innerHTML +=
                        '<option disabled>' + i+':00' + '</option>'
                        console.log(i)
                    }
                    else document.getElementById(listId).innerHTML +=
                        '<option value="'+i+'">' + i+':00' + '</option>'
                }

        }
        function sendReq(docId, date, listId) {

            let data = {
                "docId": docId,
                "date": date
            };

            clearHours(docId)

            $.ajax({
                type: "POST",
                url: "/appointment?action=ajax",
                data: JSON.stringify(data),
                success: function (response) {
                    renderList(response, listId)
                },
                dataType: "json",
                contentType: "application/json"
            });
        }
    </script>
    <script
            src="https://code.jquery.com/jquery-3.5.1.js"
            integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
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
<h2 class="text-center">Записаться к врачу</h2>
<div class="container">
    <c:forEach items="${doctors}" var="doc">
    <form action="/appointment?action=form" method="post">
        <p class="card">
            Врач: ${doc.lastName} ${doc.firstName} <br>
            Выберите услугу:
            <select name="service" id="serv${doc.id}">
                <c:forEach items="${services}" var="serv">
                    <script>checkService('${serv.name}',
                        ${serv.department_id},${doc.department_id},
                        ${serv.id}, ${doc.id})</script>
                </c:forEach>
            </select>
            Выберите дату:
            <input id="calen${doc.id}" oninput="sendReq(${doc.id}, this.value, 'hours${doc.id}')"  type="date" name="calendar">
            <script>setDateLimit('calen${doc.id}')</script>
            Выберите время:
            <select id="hours${doc.id}" name="hour">
            </select>
            <input type="hidden" name="docid" value="${doc.id}">
            <input type="submit" class="btn btn-info btn-md" value="Записаться">
        </p>
</form>
</c:forEach>
</div>




</body>
</html>
