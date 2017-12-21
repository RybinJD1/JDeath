<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
        <meta http-equiv="Content-Type" content="text/html; Charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    </head>
    <body>
        <table border="1" cellspacing="0">
            <caption>List accounts</caption>
            <thead>
            <th>Name</th>
            <th>Surname</th>
            <th>Balance</th>
            </thead>
            <tbody>
            <c:forEach items="${userList}" var="users">
                <tr>
                    <td><c:out value="${users.name}"/></td>
                    <td><c:out value="${users.surname}"/></td>
                    <td><c:out value="${users.account.balance}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <p></p>
        <%--<a href="MainServlet?action=richest">--%>
            <%--<button>displaying richest user</button>--%>
        <%--</a>--%>
        <%--<p></p>--%>
        <%--<a href="MainServlet?action=sum">--%>
            <%--<button>displaying sum of all accounts</button>--%>
        <%--</a>--%>
        <%--<p></p>--%>
        <button id="btn1" type="button" onclick="">Richest user</button>
        <%--<p></p>--%>
        <button id="btn2" type="button" onclick="">Sum of all accounts</button>
        <p></p>
        <div id="content"></div>
        <script>
            $(document).ready(function() {
                $('#btn1').click(function () {
                    $.ajax({
                        url: "/MainServlet?action=richest",
                        cache: false,
                        success: function (richest) {
                            $("#content").html(richest);
                        }
                    });
                });
                $('#btn2').click(function () {
                    $.ajax({
                        url: "/MainServlet?action=sum",
                        cache: false,
                        success: function (sum) {
                            $("#content").html(sum);
                        }
                    });
                });
            })
        </script>
    </body>
</html>