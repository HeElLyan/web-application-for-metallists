<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script
            src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
            crossorigin="anonymous">

    </script>
    <title>${model.user.username}</title>
<#--    <title>User profile</title>-->
</head>

<body>
    <#if (model.user.email)??>
        <p1>Email: ${model.user.email}</p1>
    </#if>
    <br>
    <#if (model.user.username)??>
        <p1>Username: ${model.user.username}</p1>
    </#if>
    <br>
    <#if (model.user.firstName)??>
        <p1>First Name: ${model.user.firstName}</p1>
    </#if>
    <br>
    <#if (model.user.lastName)??>
        <p1>Last Name: ${model.user.lastName}</p1>
    </#if>
    <br>
    <#if (model.user.city)??>
        <p1>City: ${model.user.city}</p1>
    </#if>
    <br>

<#--    <#if (model.userBySession.id != model.user.id) && model.user.userInvitation.equals("NOT_INVITED")>-->
        <form method="post">
            <button>Invite</button>
        </form>
<#--    </#if>-->


    <#list model.files as file>
        <li>${file.path}</li>
<#--        <img src="../../../../..${file.path}" alt="Music">-->
    </#list>

</body>
</html>