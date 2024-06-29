<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<c:set var="loginEmployee" value="lunia1109"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>WebSocket Practice</title>
    <link rel="stylesheet" href="${path}/css/hottalk.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500&display=swap" rel="stylesheet">
</head>
<body>
<section>
    <div class="menu">
        <button>메뉴1</button>
        <button>메뉴2</button>
        <button>메뉴3</button>
        <button>메뉴4</button>
        <button>메뉴5</button>
        <button>메뉴6</button>
        <img id="hotTalkImg" src="${path}/images/notaccess.jpg" alt="Hot Talk">
    </div>
    <div id="hotTalkMenu">
        <div class="hotTalkMenu">
            <button>HOT사원</button>
            <button onclick="initializeChatList();">핫톡목록</button>
            <button>환경설정</button>
        </div>
    </div>
    <div class="result">
        나는 채팅방 목록
    </div>
    <div class="specRoom">
        <div class="chattingContent">

        </div>
        <form class="messagefrm">
            <input type="text" placeholder="메시지를 입력하세요...">
            <div class="file-upload">
                <label for="file-input" class="file-upload-btn">
                    <span class="plus-icon">+</span>
                </label>
                <input id="file-input" type="file" style="display: none;">
                <span id="file-name"></span>
            </div>
            <button type="submit">전송</button>
        </form>
    </div>
</section>
<script type="text/javascript" src="${path}/js/hottalk.js"></script>
</body>
</html>