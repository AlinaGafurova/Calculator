<%--
  Created by IntelliJ IDEA.
  User: Алина
  Date: 01.02.2018
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HomePage</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="CSS/HomStyle.css" type="text/css"/>
    <%--<script src="JS/Arithmetic.js"></script>--%>
    <script src="JS/WebSocket.js"></script>
</head>
<body onload="connect();" onunload="disconnect();">
    <h1> Chat Room </h1>
    <textarea id="messages" class="panel message-area" readonly ></textarea>
    <div class="panel input-area">
        <input id="userName" class="text-field" type="text" placeholder="Name"/>
        <input id="messageInput" class="text-field" type="text" placeholder="Message"
           onkeydown="if (event.keyCode == 13) sendMessage();" />
        <input class="button" type="submit" value="Send" onclick="sendMessage();" />
    </div>
</body>
</html>
