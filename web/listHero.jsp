<%--
  Created by IntelliJ IDEA.
  User: Exception
  Date: 2019/11/30
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>用户信息</title>
</head>
<body>
    <table align="center" border="1" cellpadding="0">
        <tr>
            <td>id</td>
            <td>name</td>
            <td>hp</td>
            <td>damage</td>
            <td>edit</td>
            <td>delete</td>
        </tr>
        <c:forEach items="${heros}" var="hero" varStatus="st">
            <tr>
                <td>${hero.id}</td>
                <td>${hero.name}</td>
                <td>${hero.hp}</td>
                <td>${hero.damage}</td>
                <td><a href="editHero?id=${hero.id}">edit</a></td>
                <td><a href="deleteHero?id=${hero.id}">delete</a></td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="6" align="center">
                <a href="?start=0">首页</a>
                <a href="?start=${pre}">上一页</a>
                <a href="?start=${next}">下一页</a>
                <a href="?start=${last}">最后一页</a>
            </td>
            <td></td>
        </tr>
    </table>
</body>
</html>
