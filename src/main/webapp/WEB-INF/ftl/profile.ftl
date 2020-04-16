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
    <title>Profile</title>
    <script>
        function sendFile() {
            // данные для отправки
            let formData = new FormData();
            // забрал файл из input
            let files = ($('#file'))[0]['files'];
            // добавляю файл в formData
            [].forEach.call(files, function (file, i, files) {
                console.log(file)
                formData.append("file", file);
            });

            //иначе будет отправлять по два раза
            // $.ajax({
            //     type: "POST",
            //     url: "http://localhost:8080/WebApplicationForMetallists_war/",
            //     data: formData,
            //     processData: false,
            //     contentType: false
            // })
            //     .done(function (response) {
            //         alert(response)
            //     })
            //     // .fail(function () {
            //     //     alert('Error')
            //     // });
        }
    </script>
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
<#--        <form method="post">-->
<#--            <button>Invite</button>-->
<#--        </form>-->
<#--    </#if>-->

    <#--    <div>-->
    <form method="post" enctype="multipart/form-data">
        <input type="file" id="file" name="file" placeholder="Имя файла..."/>
        <button onclick="sendFile()">
            Загрузить файл
        </button>
        <input type="hidden" id="file_hidden">
        <div class="filename"></div>
    </form>
<#--    </div>-->


    <#list model.files as file>
        <li>${file.path}</li>
<#--        <img src="../../../../..${file.path}" alt="Music">-->
    </#list>

<#--    <#if (model.userBySession.id == model.user.id)>-->
        <form method="post">
            <a href="/logout">Logout</a>
        </form>
<#--    </#if>-->
</body>
</html>