<%--
  Created by IntelliJ IDEA.
  User: aitke
  Date: 02.02.2024
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <th>Set 1</th>
        <th>Set 2</th>
    </tr>
    <tr>
        <th>${player1Name}</th>
        <th>0</th>
        <th>0</th>
    </tr>
    <tr>
        <th>${player2Name}</th>
        <th>0</th>
        <th>0</th>
    </tr>
</table>
<form method="post" action="match-score">
    <input type="submit" value="Player 1 won a point">
    <input type="submit" value="Player 2 won a point">
</form>
</body>
</html>
