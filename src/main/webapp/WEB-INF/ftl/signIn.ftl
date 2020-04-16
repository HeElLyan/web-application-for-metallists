<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Test</title>
</head>
<body>
<#if error??>
    <div class="alert alert-danger" role="alert">Логин или пароль введены неверно</div>
</#if>

<h4>Sign in Page</h4>

<form method="post">
    <label for="email">Email
        <input class="input-field" type="text" id="email" name="email">
    </label>
    <#--    <input type="text" name="email">-->
    <br>
    <label for="password">Password
        <input class="input-field" type="password" id="password" name="password">
    </label>
    <#--    <input type="password" name="password">-->
    <br>
    <label for="remember-me">
        <input type="checkbox" id="remember-me" name="remember-me">Remember me</label>
    <input type="submit" value="SignIn">
    <br>
</form>
</body>
</html>