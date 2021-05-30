<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script
            src="https://code.jquery.com/jquery-3.5.1.js"
            integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
            crossorigin="anonymous"></script>
    <script>

        function sendReq() {

            let size = document.getElementById("size").value
            let pagesCount = document.getElementById("pagesCount").value
            console.log(size)

            let data = {
                "size": size,
                "pagesCount": pagesCount
            };

            $.ajax({
                type: "POST",
                url: "/departments/ajax",
                data: JSON.stringify(data),
                success: function (response) {
                    renderList(response)
                },
                dataType: "json",
                contentType: "application/json"
            });
        }

        function renderList(response) {
            console.log(response)
            let innerHtml = ''
            <#list departmentList as department>
            innerHtml +=
                '        <tr>' +
                '        <td>${department.id}</td>\n' +
                '        <td>${department.name}</td>\n' +
                '</tr>'
            </#list>
            document.getElementById('table').innerHTML += innerHtml
        }

    </script>
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

    <input id="size" required class="form-control" type="number" placeholder="Size">
    <input id="pagesCount" required class="form-control" type="number" placeholder="Page">
    <input type="submit" class="btn btn-lg btn-primary" onclick="sendReq()" value="OK">


<div>
    <table>
        <tr>
            <th>id</th>
            <th>email</th>
        </tr>
        <tr id="table">
            <#list departmentList as department>
        <tr>
            <td>${department.id}</td>
            <td>${department.name}</td>
        </tr>
        </#list>
        </tr>
    </table>
</div>
</body>
</html>