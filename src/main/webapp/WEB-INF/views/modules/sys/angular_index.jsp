<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html ng-app="menuApp">
<head>
    <title></title>
    <script src="${ctxStatic}/angularjs/angular.min.js"></script>
    <script src="${ctxStatic}/angularjs/angular-resource.min.js"></script>
    <script src="${ctxStatic}/angularjs/script/app.js"></script>
    <script src="${ctxStatic}/angularjs/script/services.js"></script>
    <script src="${ctxStatic}/angularjs/script/controller.js"></script>
    <script type="text/javascript">
        window._contextPath = "${ctx}";
    </script>
</head>
<body>
<ul ng-controller="menusListController">
    <li ng-repeat="menu in menus">{{menu.name}}</li>
</ul>
</body>
</html>
