<%--
  Created by IntelliJ IDEA.
  User: aitke
  Date: 15.02.2024
  Time: 9:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Find Match by player name</h1>
<form method="post" action="player">
    <label for="player-name">Enter name of the player
        <input id="player-name" type="text" name="player-name" required>
    </label>
    <input type="submit" value="Submit">
</form>
</body>
</html>
