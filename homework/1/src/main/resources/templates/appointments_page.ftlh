<html>
<head>
    <style>
        .btn {
            margin-top: 4px;
        }

        .card {
            /* Add shadows to create the "card" effect */
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
            transition: 0.3s;
            border-radius: 5px;
            padding: 12px;
        }

        .card:hover {
            box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
        }

        .container {
            padding: 2px 16px;
        }
    </style>
    <title>Запись</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script>
        function setDateLimit(id) {
            let today = new Date();
            let nMonth;
            let dd = today.getDate();
            let mm = today.getMonth() + 1; //January is 0
            let nm, ny;
            const yyyy = today.getFullYear();

            if (mm === 12) {
                nm = '01'
                ny = yyyy + 1
            } else {
                nm = mm + 1
                ny = yyyy
            }
            if (nm < 10 && nm !== '01') {
                nm = '0' + nm
            }

            if (dd < 10) {
                dd = '0' + dd
            }
            if (mm < 10) {
                mm = '0' + mm
            }


            today = yyyy + '-' + mm + '-' + dd;
            nMonth = ny + '-' + nm + '-' + dd;
            document.getElementById(id).setAttribute("min", today);
            document.getElementById(id).setAttribute("max", nMonth);
        }

        function clearHours(docId) {
            document.getElementById('hours' + docId).innerHTML = ''
            console.log('cleared')
        }

        function checkService(servName, servDepId, docDepID, servId, docId) {
            if (servDepId === docDepID) {
                document.getElementById('serv' + docId).innerHTML += '<option value="' + servId + '">' + servName + '</option>'
            }
        }

        function renderList(blockedHours, listId) {

            for (let i = 8; i < 17; i++) {
                if (blockedHours.includes(i)) {
                    document.getElementById(listId).innerHTML +=
                        '<option disabled>' + i + ':00' + '</option>'
                    console.log(i)
                } else document.getElementById(listId).innerHTML +=
                    '<option value="' + i + '">' + i + ':00' + '</option>'
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
                url: "/appointments/ajax",
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
    <a href="/about" class="navbar-brand">Поликлиника</a>
    <button class="navbar-toggler" data-toggle="collapse" data-target="#navbarMenu">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarMenu">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a href="/departments" class="nav-link">Отделения</a>
            </li>
            <li class="nav-item">
                <a href="/appointments" class="nav-link">Запись</a>
            </li>
            <li class="nav-item">
                <a href="/profile" class="nav-link">Профиль</a>
            </li>
            <li class="nav-item">
                <a href="/about" class="nav-link">Контакты</a>
            </li>
        </ul>
    </div>
</nav>
<h2 class="text-center">Записаться к врачу</h2>
<div class="container">
    <#list doctors as doc>
        <script>console.log(${doc.id})</script>
        <form action="/appointments/create" method="post">
            <p class="card">
                Врач: ${doc.lastName} ${doc.firstName} <br>
                Выберите услугу:
                <select name="service" id="serv${doc.id}">
                    <#list services as serv>
                        <script>checkService('${serv.name}',
                                ${serv.department.id}, ${doc.department.id},
                                ${serv.id}, ${doc.id})</script>
                    </#list>
                </select>
                Выберите дату:
                <input id="calen${doc.id}" oninput="sendReq(${doc.id}, this.value, 'hours${doc.id}')" type="date"
                       name="calendar">
                <script>setDateLimit('calen${doc.id}')</script>
                Выберите время:
                <select id="hours${doc.id}" name="hour">
                </select>
                <input type="hidden" name="docid" value="${doc.id}">
                <input type="submit" class="btn btn-info btn-md" value="Записаться">
            </p>
        </form>
    </#list>
</div>
