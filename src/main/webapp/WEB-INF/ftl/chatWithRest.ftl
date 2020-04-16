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
    <script src="/WebApplicationForMetallists_war/res/js/chatWithRest.js"></script>
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
    <tr>
        <td>${message.text}</td>
    </tr>
    </#list>
</div>
</body>
</html>