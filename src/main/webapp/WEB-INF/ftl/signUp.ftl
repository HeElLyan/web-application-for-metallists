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

<#--<form method="post">-->
<#--&lt;#&ndash;    <input type="text" name="email">&ndash;&gt;-->
<#--&lt;#&ndash;    <br>&ndash;&gt;-->
<#--&lt;#&ndash;    <input type="password" name="password">&ndash;&gt;-->
<#--&lt;#&ndash;    <br>&ndash;&gt;-->
<#--&lt;#&ndash;    <input type="submit" value="SignUp">&ndash;&gt;-->
<#--    <label for="username">Username-->
<#--        <input class="input-field" placeholder="!!!!" type="text" id="username" name="username" required>-->
<#--    </label>-->
<#--    <br>-->
<#--    <label for="password">Password-->
<#--        <input class="input-field" placeholder="!!!!" type="password" id="password" name="password" required>-->
<#--    </label>-->
<#--    <br>-->
<#--    <label for="name">First Name-->
<#--        <input class="input-field" placeholder="!!!!" type="text" id="firstName" name="firstName" required>-->
<#--    </label>-->
<#--    <br>-->
<#--    <label for="name">Last Name-->
<#--        <input class="input-field" placeholder="!!!!" type="text" id="lastName" name="lastName" required>-->
<#--    </label>-->
<#--    <br>-->
<#--    <label for="email">Email-->
<#--        <input class="input-field" placeholder="!!!!" type="email" id="email" name="email" required>-->
<#--    </label>-->
<#--    <br>-->
<#--    <label for="city">City-->
<#--        <input class="input-field" placeholder="!!!!" type="text" id="country" name="country">-->
<#--    </label>-->
<#--&lt;#&ndash;    <br>&ndash;&gt;-->
<#--&lt;#&ndash;    <label for="city">City&ndash;&gt;-->
<#--&lt;#&ndash;        <input class="input-field" placeholder="!!!!" type="text" id="city" name="city">&ndash;&gt;-->
<#--&lt;#&ndash;    </label>&ndash;&gt;-->
<#--&lt;#&ndash;    <br>&ndash;&gt;-->
<#--&lt;#&ndash;    <label for="city">About&ndash;&gt;-->
<#--&lt;#&ndash;        <input class="input-field" placeholder="!!!!" type="text" id="about" name="about">&ndash;&gt;-->
<#--&lt;#&ndash;    </label>&ndash;&gt;-->
<#--    <br>-->
<#--    <label for="metalGenre">Choose the metal genre-->
<#--        <select name="metalGenre">-->
<#--        <#list model.enumForMetalGenres as metalGenre>-->
<#--            <option value=${metalGenre.value}>${metalGenre.value}</option>-->
<#--        </#list>-->
<#--        </select>-->
<#--    </label>-->
<#--    <br>-->
<#--    <label for="instrumentType">Choose the metal instrument-->
<#--        <select name="instrumentType">-->
<#--            <#list model.enumForInstruments as instrument>-->
<#--                <option value=${instrument.value}>${instrument.value}</option>-->
<#--&lt;#&ndash;                <#if ${instrument.value}=="Progressivemetal"></#if>&ndash;&gt;-->
<#--            </#list>-->
<#--        </select>-->
<#--    </label>-->

<#--    <input class="color-input-field" type="submit" value="Sign Up">-->
<#--</form>-->

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
<#--            <label for="country">Country</label>-->
<#--            Country:-->
<#--            <@spring.formInput "regDto.country"/>-->
<#--            <@spring.showErrors "<br>","error"/>-->
<#--            <br>-->
            <label for="city">City</label>
            City:
            <@spring.formInput "regDto.city"/>
            <@spring.showErrors "<br>","error"/>

        <#--        <br><br>-->
<#--        Age: <br>-->
<#--        <@spring.formInput "profileForm.age"/>-->
<#--        <@spring.showErrors "<br>","error"/>-->
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
            <#--                <#if ${instrument.value}=="Progressivemetal"></#if>-->
                        </#list>
                    </select>
                </label>
            <br>
            <input type="submit" value="Submit">
        </form>
</div>
</body>
</html>