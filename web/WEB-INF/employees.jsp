<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Никита
  Date: 16.05.2016
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список сотрудников</title>
</head>
<body>
<table border="1">
    <form>
        <tr>
            <td bgcolor="#adff2f">Имя</td>
            <td bgcolor="#adff2f">Фамилия</td>
            <td bgcolor="#adff2f">Должность</td>
            <td bgcolor="#adff2f">Отдел</td>
            <td bgcolor="#adff2f"></td>
        </tr>
    </form>
    <c:forEach items="${requestScope.employees}" var="employee">
        <tr bgcolor="#c0c0c0">
            <td><c:out value="${employee.name}"/></td>
            <td><c:out value="${employee.surname}"/></td>
            <td><c:out value="${employee.position}"/></td>
            <td><c:out value="${employee.department}"/></td>
            <td>
                <a href="/delete?id=${employee.id}">
                    <img src="delete.png"/>
                </a>
            </td>
        </tr>
    </c:forEach>
    <form action="/add" method="post">
        <tr>
            <td><input name="name" type="text"></td>
            <td><input name="surname" type="text"></td>
            <td><input name="position" type="text"></td>
            <td><input name="department" type="text"></td>
            <td><input type="submit" value="Добавить сотрудника"></td>
        </tr>
    </form>
    <form action="/filter" method="post">
        <tr>
            <td><input name="name" type="text"></td>
            <td><input name="surname" type="text"></td>
            <td colspan="3"><input type="submit" value="Включить/отключить фильтр"></td>
        </tr>
    </form>
</table>
</body>
</html>
