<html>
<head>
    <title>Профиль</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <style>
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

        .li {
            font-size: 20px;
        }

        .container {
            padding: 2px 16px;
        }
    </style>
    <script
            src="https://code.jquery.com/jquery-3.5.1.js"
            integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
            crossorigin="anonymous"></script>
    <script>
        function renderList(userId) {
            let innerHtml = ''
            <#list appointments as appointment>
                if (userId === ${appointment.customer.id}) {
                    console.log('${appointment.doctor.department.name}')
                    innerHtml +=
                        '<li class="card" id="${appointment.id}"> ${appointment.date}:00 '
                    innerHtml += '${appointment.doctor.lastName} ${appointment.doctor.firstName} '
                    innerHtml += '${appointment.service.name} ${appointment.service.price} руб.'
                    innerHtml +=
                        ' <form onsubmit="sendReq(${appointment.id})">' +
                        '<input type="submit" class="btn btn-info btn-md" value="Отменить запись"></input></form></li>'
                    document.getElementById('list').innerHTML += innerHtml
                    innerHtml = ''
                }
            </#list>
        }
    </script>
    <script>
        function sendReq(appId) {

            $.ajax({
                type: "POST",
                url: "/profile/ajax",
                data: JSON.stringify(appId),
                success: function (response) {
                    renderList(response)
                },
                dataType: "json",
                contentType: "application/json"
            });
        }
    </script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
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
<h1 class="text-center">Личный кабинет</h1>

<div class="text-center">
    <div>
        <h3> Имя: ${user.firstName}</h3>
        <h3> Фамилия: ${user.lastName}</h3>
        <h3> Почта: ${user.email}</h3>
    </div>

    <form method="post" action="/logout">
        <input class="btn btn-info btn-md" type="submit" value="Выйти">
    </form>
</div>

<h2 class="text-center">Записи</h2>

<ul class="container" id="list">
</ul>
<script>renderList(${user.id})</script>
</body>
</html>

