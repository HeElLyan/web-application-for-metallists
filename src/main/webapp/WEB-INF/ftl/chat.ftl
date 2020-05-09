<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>
    <script src="/WebApplicationForMetallists_war/res/js/chat.js"></script>
</head>
<body onload="sendMessage('${band.id}', 'Login')">
<h1>Беседа группы ${band.name}</h1>
<div>
    <input id="message" placeholder="Ваше сообщение">
    <button onclick="sendMessage('${band.id}',
            $('#message').val())">Отправить</button>
</div>

<div>
    <ul id="messages">

    </ul>
</div>
<div>
    <#list messageList as message>
        <#if (message.text)??>
            <tr>
                <td>${message.text}</td>
            </tr>
        </#if>
    </#list>
</div>
</body>
</html>

<#--<!doctype html>-->
<#--<html lang="en">-->
<#--<head>-->
<#--    <meta charset="UTF-8">-->
<#--    <meta name="viewport"-->
<#--          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">-->
<#--    <meta http-equiv="X-UA-Compatible" content="ie=edge">-->
<#--    <title>Chat</title>-->
<#--    <script type="text/javascript">-->
<#--        function sendComment(id, bandId) {-->
<#--            let text = $('textarea#' + id).val();-->
<#--            /*alert(text);*/-->
<#--            $.band("/WebApplicationForMetallists_war/chat", {-->
<#--                text: text,-->
<#--                band_id: bandId-->
<#--            });-->
<#--        }-->
<#--    </script>-->

<#--    <script type="text/javascript">-->
<#--        function getComments(divId, bandId, wait) {-->
<#--            if (wait != null) {-->
<#--                $.get("/WebApplicationForMetallists_war/chat", {-->
<#--                    band_id: bandId,-->
<#--                    w: 1-->
<#--                }, function (data) {-->
<#--                    $("#" + divId).html(data);-->
<#--                    getComments(divId, bandId, "b")-->
<#--                })-->
<#--            } else {-->
<#--                $.get("/WebApplicationForMetallists_war/chat", {-->
<#--                    band_id: bandId-->
<#--                }, function (data) {-->
<#--                    $("#" + divId).html(data);-->
<#--                    getComments(divId, bandId, "b")-->
<#--                })-->
<#--            }-->
<#--        }-->
<#--    </script>-->

<#--</head>-->

<#--<body onload="sendComment($('#message').val(), '${band.id}')">-->
<#--    <h1>Беседа группы ${band.name}</h1>-->
<#--    <div>-->
<#--        <input id="message" placeholder="Ваше сообщение">-->
<#--        <button onclick="sendComment($('#message').val(), '${band.id}')">Отправить</button>-->
<#--    </div>-->

<#--    <div>-->
<#--        <ul id="messages">-->

<#--        </ul>-->
<#--    </div>-->
<#--    <div>-->
<#--        <#list messageList as message>-->
<#--            <#if (message.text)??>-->
<#--                <tr>-->
<#--                    <td>${message.text}</td>-->
<#--                </tr>-->
<#--            </#if>-->
<#--        </#list>-->
<#--    </div>-->

<#--</body>-->
<#--</html>-->

