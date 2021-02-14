<!doctype html>
<html>
<head>
    <title>Регистрация</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<div id="login">
    <h3 class="text-center text-white pt-5">Login form</h3>
    <div class="container">
        <div id="login-row" class="row justify-content-center align-items-center">
            <div id="login-column" class="col-md-6">
                <div id="login-box" class="col-md-12">
                    <form id="login-form" class="form" action="/signUp" method="post">
                        <h3 class="text-center text-info">Регистрация</h3>
                        <div class="form-group">
                            <label for="firstName" class="text-info">Имя:</label><br>
                            <input type="text" name="firstName" id="firstName" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="lastName" class="text-info">Фамилия:</label><br>
                            <input type="text" name="lastName" id="lastName" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="email" class="text-info">Почта:</label><br>
                            <input type="email" name="email" id="email" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="password" class="text-info">Пароль:</label><br>
                            <input type="password" name="password" id="password" class="form-control">
                        </div>
                        <div class="form-group">
                            <input type="submit" name="submit" class="btn btn-info btn-md" value="Зарегистрироваться">
                            <div id="register-link" class="text-right">
                                <a href="/signIn" class="text-info">Войти</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>