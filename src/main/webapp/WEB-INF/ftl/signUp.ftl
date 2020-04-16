<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Test</title>
</head>
<body>

<h4>Sign up Page</h4>

<form method="post">
<#--    <input type="text" name="email">-->
<#--    <br>-->
<#--    <input type="password" name="password">-->
<#--    <br>-->
<#--    <input type="submit" value="SignUp">-->
    <label for="username">Username
        <input class="input-field" placeholder="!!!!" type="text" id="username" name="username" required>
    </label>
    <br>
    <label for="password">Password
        <input class="input-field" placeholder="!!!!" type="password" id="password" name="password" required>
    </label>
    <br>
    <label for="name">First Name
        <input class="input-field" placeholder="!!!!" type="text" id="firstName" name="firstName" required>
    </label>
    <br>
    <label for="name">Last Name
        <input class="input-field" placeholder="!!!!" type="text" id="lastName" name="lastName" required>
    </label>
    <br>
    <label for="email">Email
        <input class="input-field" placeholder="!!!!" type="email" id="email" name="email" required>
    </label>
    <br>
    <label for="city">Country
        <input class="input-field" placeholder="!!!!" type="text" id="country" name="country">
    </label>
<#--    <br>-->
<#--    <label for="city">City-->
<#--        <input class="input-field" placeholder="!!!!" type="text" id="city" name="city">-->
<#--    </label>-->
<#--    <br>-->
<#--    <label for="city">About-->
<#--        <input class="input-field" placeholder="!!!!" type="text" id="about" name="about">-->
<#--    </label>-->
    <br>
    <label for="metalGenre">Choose the metal genre
        <select name="metalGenre">
        <#list model.enumForMetalGenres as metalGenre>
            <option value=${metalGenre.value}>${metalGenre.value}</option>
        </#list>
        </select>
    </label>
    <br>
    <label for="instrumentType">Choose the metal instrument
        <select name="instrumentType">
            <#list model.enumForInstruments as instrument>
                <option value=${instrument.value}>${instrument.value}</option>
<#--                <#if ${instrument.value}=="Progressivemetal"></#if>-->
            </#list>
        </select>
    </label>

    <input class="color-input-field" type="submit" value="Sign Up">
</form>
</body>
</html>