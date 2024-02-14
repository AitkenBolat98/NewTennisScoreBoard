<%--
  Created by IntelliJ IDEA.
  User: aitke
  Date: 02.02.2024
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Match Score</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<h1>Match-Score</h1>
<table>
    <tr>
        <th>Players</th>
        <th>Sets</th>
        <th>Games</th>
        <th>Points</th>
        <th>W/L</th>
    </tr>
    <tr>
        <th>${requestScope.player1Name}</th>
        <th>${requestScope.player1sets}</th>
        <th>${requestScope.player1games}</th>
        <th>${requestScope.player1points}</th>
        <th>${requestScope.player1WL}</th>
    </tr>
    <tr>
        <th>${requestScope.player2Name}</th>
        <th>${requestScope.player2sets}</th>
        <th>${requestScope.player2games}</th>
        <th>${requestScope.player2points}</th>
        <th>${requestScope.player2WL}</th>
    </tr>
</table>
<form method="post" action="match-score">
    <input type="hidden" name="id" value="${param.id}">
    <button name="pscored" value="1">${requestScope.player1Name} scored</button>
    <button name="pscored" value="2">${requestScope.player2Name} scored</button>
</form>
</body>
</html>
