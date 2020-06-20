<!DOCTYPE html>
<#import "spring.ftl" as spring />
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Test</title>
</head>
<style>
    .error {
        color: #ff0000;
    }
</style>
<body>

<h4>Sign up Page</h4>

<div>
    <@spring.bind "regDto"/>
        <form action="/WebApplicationForMetallists_war/signUp" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <label for="username">Username</label>
            <@spring.formInput "regDto.username"/>
            <@spring.showErrors "<br>","error"/>
            <br>
            <label for="password">Password</label>
            <@spring.formInput "regDto.password"/>
            <@spring.showErrors "<br>","error"/>
            <br>
            <label for="email">Email</label>
            <@spring.formInput "regDto.email"/>
            <@spring.showErrors "<br>"/>
            <br>
            <label for="firstName">First Name</label>
            <@spring.formInput "regDto.firstName"/>
            <@spring.showErrors "<br>","error"/>
            <br>
            <label for="lastName">Last Name</label>
            <@spring.formInput "regDto.lastName"/>
            <@spring.showErrors "<br>","error"/>
            <br>
            <label for="city">City</label>
            <@spring.formInput "regDto.city"/>
            <@spring.showErrors "<br>","error"/>
            <br>
                <label for="metalGenre">Choose the metal genre
                    <select name="metalGenre">
                    <#list enumForMetalGenres as metalGenre>
                        <option value=${metalGenre.value}>${metalGenre.value}</option>
                    </#list>
                    </select>
                </label>
                <br>
                <label for="instrumentType">Choose the metal instrument
                    <select name="instrumentType">
                        <#list enumForInstruments as instrument>
                            <option value=${instrument.value}>${instrument.value}</option>
                        </#list>
                    </select>
                </label>
            <br>
            <input type="submit" value="Submit">
        </form>
</div>
</body>
</html>
