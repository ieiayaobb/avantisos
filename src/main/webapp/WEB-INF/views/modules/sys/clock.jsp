<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title></title>
    <link href="${ctxStatic}/backbone/plugin/css/clock.css" rel="stylesheet" type="text/css">
    <link href="${ctxStatic}/webos/css/core.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="${ctxStatic}/underscore/underscore.js" ></script>
    <script type="text/javascript" src="${ctxStatic}/backbone/backbone.js" ></script>
    <script type="text/javascript" src="${ctxStatic}/backbone/plugin/js/clock.js" ></script>

        <script type="text/template" id="clock-template">
        <div class="up-background up-back">
            <div class="up-left"></div>
            <div class="up-right"></div>
        </div>
        <div class="up-background up-front">
            <div class="up-left"></div>
            <div class="up-right"></div>
        </div>
        <div class="down-background down-front">
            <div class="down-left"></div>
            <div class="down-right"></div>
        </div>
        <div class="down-background down-back">
            <div class="down-left"></div>
            <div class="down-right"></div>
        </div>
    </script>
</head>
<body>
    <div id="clock"></div>
</body>
</html>