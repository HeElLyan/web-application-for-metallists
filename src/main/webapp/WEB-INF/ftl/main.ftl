<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Main</title>
</head>
<body>

<table>
    <tr>
<#--        <th>First Name</th>-->
<#--        <th>Last Name</th>-->
        <th>Username</th>
    </tr>
    <#list users as user>
        <tr>
<#--            <td>${user.firstName}</td>-->
<#--            <td>${user.lastName}</td>-->
            <td>${user.username}</td>
        </tr>
    </#list>
</table>

</body>
</html>