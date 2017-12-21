<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>--%>
<table border="1" cellspacing="0">
    <thead>
        <th>Name</th>
        <th>Surname</th>
        <th>Balance</th>
    </thead>
    <tbody>
        <tr>
            <td>${richest.name}</td>
            <td>${richest.surname}</td>
            <td>${richest.account.balance}</td>
        </tr>
    </tbody>
</table>
<%--</body>
</html>--%>
