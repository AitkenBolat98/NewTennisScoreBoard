<%@ page contentType="text/html;charset=UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>New Match</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<h1>Player Registration</h1>
<form method="post" action="${pageContext.request.contextPath}/new-match">
    <label for="player1-name">Enter name of Player 1
        <input id="player1-name" type="text"  name="player1Name" required>
    </label>
    <label for="player2-name">Enter name of Player 2
        <input id="player2-name" type="text"  name="player2Name" required>
    </label>
    <input type="submit" value="Submit">
</form>
</body>
</html>
