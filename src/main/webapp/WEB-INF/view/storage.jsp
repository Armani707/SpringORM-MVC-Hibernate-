<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: lukas
  Date: 20.05.2023
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h2 align="center" style="color: brown">INFORMATION</h2>
<form:form action="savePerson" modelAttribute="student" method="get">
    <p align="center">
            <form:hidden path="id"/>
        <br>

        <b>
            Name: <form:input path="name"/>
            <br>
        </b>
        <b>
            Age: <form:input path="age"/>
        </b>
        <br>
                <b>
                    Surname: <form:input path="surname"/>
                    <br>
                </b>

    <p align="center">
        <input type="submit" value="ok">
    </p>

</form:form>
</body>
</html>
