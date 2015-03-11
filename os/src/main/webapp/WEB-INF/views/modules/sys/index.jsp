<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!doctype html>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=emulateIE7">
	<meta charset="utf-8">
	<!-- <meta name="decorator" content="cms_default_${site.theme}"/> -->
	<meta name="description" content="JeeSite ${site.description}" />
	<meta name="keywords" content="JeeSite ${site.keywords}" />

    <title>BiuOS</title>
    <link href="static/webos/css/core.css" rel="stylesheet" type="text/css">
    <link href="static/webos/css/index.css" rel="stylesheet" type="text/css">
    <link href="static/bootstrap/2.3.1/css_flat/bootstrap.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="${ctxStatic}/underscore/underscore.js" ></script>
    <script type="text/javascript" src="${ctxStatic}/backbone/backbone.js" ></script>
    <script type="text/javascript" src="${ctxStatic}/bootstrap/2.3.1/js/bootstrap.js" ></script>

    <!-- <script type="text/javascript" src="static/webos/js/global.js"></script> -->
    <script type="text/javascript">
        _.templateSettings = {
            interpolate : /\{\{(.+?)\}\}/g
        };
    </script>
    <script type="text/template" id="deskIcon-template">
        <span class="ue-state-default">
            <span class="icon iconClosetype1"></span>
        </span>
        <div class="desk-icon-hd"></div>
        <div class="desk-icon-bd">
            <img src="{{icon}}" width="64" height="64">
            <div class="text">{{name}}</div>
        </div>
        <div class="desk-icon-ft"></div>
    </script>

    <script type="text/template" id="window-template">
    <div class="frames miniFrame" moduleId="' + moduleId_ + '" frameId="' + frameId_ + '">
        <div class="contentHd">
            <div class="leftCorner"></div>
            <div class="midPart">
                <a class="title_icon home" href="javascript:;"></a>
                <a class="title_icon refresh" href="javascript:;"></a>
                <div class="title">{{title}}</div>
                <a class="title_icon min" href="javascript:;"></a>
                <a class="title_icon closeWindow" href="javascript:;"></a>
                </div>
            <div class="rightCorner"></div>
        </div>
        <div class="contentBd">
            <div class="leftCorner"></div>
            <div class="midPart">
                <iframe class="iframeClass" frameborder="0" width="100%" height="100%" src="{{href}}"></iframe>
            </div>
            <div class="rightCorner"></div>
        </div>
        <div class="contentFt">
            <div class="leftCorner"></div>
            <div class="midPart"></div>
            <div class="rightCorner"></div>
        </div>
    </div>
    </script>

    <script type="text/template" id="rightMenu-template">
        <div class="menuTop"></div>
        <div class="menuMid ue-clear">
            <ul>
                <li>
                    	<span class="rightPart">
                        	<span class="text">查看</span>
                            <span class="ico menuRight"></span>
                            <span class="ico menuRight2"></span>
                        </span>
                </li>
                <li>
                    	<span class="rightPart">
                        	<span class="text">排序方式(O)</span>
                        </span>
                </li>
                <li class="border"></li>
                <li>
                    	<span class="rightPart">
                        	<span class="text">刷新(E)</span>
                        </span>
                </li>
                <li class="disable">
                    	<span class="rightPart">
                        	<span class="text">删除(D)</span>
                        </span>
                </li>
                <li class="border"></li>
                <li class="disable">
                    	<span class="rightPart">
                        	<span class="text">更改快捷图标(H)</span>
                        </span>
                </li>
                <li>
                    <span class="rightMenuIcon theme"></span>
                    	<span class="rightPart">
                        	<span class="text">主题设置</span>
                        </span>
                </li>
                <li>
                    <span class="rightMenuIcon systemSet"></span>
                    	<span class="rightPart">
                            <span class="text">系统设置</span>
                        </span>
                </li>
                <li class="border"></li>
                <li>
                    <span class="rightMenuIcon iconSet"></span>
                    	<span class="rightPart">
                            <span class="text">图标设置</span>
                            <span class="ico menuRight"></span>
                            <span class="ico menuRight2"></span>
                        </span>
                </li>
                <li>
                    	<span class="rightPart">
                        	<span class="text">反馈</span>
                        </span>
                </li>
                <li>
                    	<span class="rightPart">
                        	<span class="text">注销</span>
                        </span>
                </li>
            </ul>
        </div>
        <div class="menuBottom"></div>
    </script>
    <script type="text/template" id="sign-template">
        <div class="contentHd">
            <span class="opGroup">
                    <a href="javascript:;" class="icon iconPlus addIcon"></a>
                    <a href="javascript:;" class="icon iconClosetype2 deleteIcon"></a>
                </span>
            </div>
        <div class="contentBd">
             <%--<button type="button" class="btn btn-primary btn-lg">签到</button>--%>
        </div>
        <div class="contentFt"></div>
    </script>

    <script type="text/javascript">
    window._contextPath = "${ctx}";
    </script>
    <script type="text/javascript" src="${ctxStatic}/webos/js/desk.js"></script>
<!--[if IE]> <script type="text/javascript" src="../js/html5.js"></script> <![endif]-->
</head>
<body id="transparent">
<div id="container">
    <div id="bd">
    	<div id="main">
            <div class="historyLayout"></div>
            <div class="historyCut miniHistoryCut">
                <div class="historyArrow"></div>
                <div class="contentHd">
                    <div class="title">访问记录</div>
                    <a class="title_icon closeWindow" href="javascript:;"></a>
                </div>
                <div class="contentBd">
                    <dl class="ue-clear">
                        <dt>
                            <span class="title">最近打开</span>
                        </dt>
                        <dd class="shortCutWrap">
                        	<ul class="shortcut ue-clear">
                            </ul>
                        </dd>
                    </dl>
                </div>
            </div><!--end of historyCut-->
        	<div class="window">
                <div class="pluginGroup">
                </div><!--end of fenceGroup-->
            	<div id="desk" class="shortcutTray miniShortcut  screen1">
					<div class="addItem" style="top: 16px; left: 26px; ">
                    	<div class="addIcon"></div>
                        <div class="text">添加</div>
                    </div>
                 </div><!--end of shortCutTray-->
                <div class="titleTip">
                	<span class="tipLeft"></span>
                    <span class="tipCenter">这是一个长长的标题</span>
                    <span class="tipRight"></span>
                </div>
            </div><!--END OF WINDOW-->
        	<div class="window"  style="visibility: hidden;">
            	<div class="shortcutTray  screen2"></div>
            </div><!--END OF WINDOW-->
            <div class="logo_bottom"></div>
        </div><!--END OF MAIN-->
    </div><!-- End of bd -->
</div><!-- End of container -->
</body>
</html>
