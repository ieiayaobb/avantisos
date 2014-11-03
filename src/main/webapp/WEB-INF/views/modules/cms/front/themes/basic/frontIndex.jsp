<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
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
<script type="text/javascript" src="static/webos/js/jquery.js"></script>
<script type="text/javascript" src="static/webos/js/global.js"></script>
<script type="text/javascript" src="static/webos/js/window.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		//桌面图标序列
		var iconArr = [
			{"label" : "组织机构","icon" : "static/webos/img/bd/icon-1.png","src" : "${ctx}/list-2${urlSuffix}","moduleId" : "1","fenceId" : "0","fenceLabel":"测试Fence1"},
			{"label" : "质量监督","icon" : "static/webos/img/bd/icon-2.png","src" : "${ctx}/list-6${urlSuffix}","moduleId" : "2","fenceId" : "0","fenceLabel":"测试Fence1"},
			{"label" : "政策法规","icon" : "static/webos/img/bd/icon-3.png","src" : "${ctx}/list-10${urlSuffix}","moduleId" : "3","fenceId" : "0","fenceLabel":"测试Fence1"},
			{"label" : "桌面图标4","icon" : "static/webos/img/bd/icon-4.png","src" : "inner/message/message.html","moduleId" : "4","fenceId" : "0","fenceLabel":"测试Fence1"},
			{"label" : "桌面图标5","icon" : "static/webos/img/bd/icon-5.png","src" : "inner/message/message.html","moduleId" : "5","fenceId" : "0","fenceLabel":"测试Fence1"},
			{"label" : "桌面图标6","icon" : "static/webos/img/bd/icon-6.png","src" : "inner/message/message.html","moduleId" : "6","fenceId" : "0","fenceLabel":"测试Fence1"},
			{"label" : "桌面图标7","icon" : "static/webos/img/bd/icon-7.png","src" : "inner/message/message.html","moduleId" : "7","fenceId" : "0","fenceLabel":"测试Fence1"},
			{"label" : "fence图标1","icon" : "static/webos/img/bd/icon-1.png","src" : "inner/message/message.html","moduleId" : "8","fenceId" : "abc2","fenceSequence" : 1,"fenceLabel":"测试Fence2"},
			{"label" : "fence图标2","icon" : "static/webos/img/bd/icon-2.png","src" : "inner/message/message.html","moduleId" : "9","fenceId" : "abc3","fenceSequence" : 2,"fenceLabel":"测试Fence3"},
			{"label" : "fence图标3","icon" : "static/webos/img/bd/icon-3.png","src" : "inner/message/message.html","moduleId" : "10","fenceId" : "abc1","fenceSequence" : 0,"fenceLabel":"测试Fence1"},
			{"label" : "fence图标4","icon" : "static/webos/img/bd/icon-4.png","src" : "inner/message/message.html","moduleId" : "11","fenceId" : "abc1","fenceSequence" : 0,"fenceLabel":"测试Fence1"},
			{"label" : "fence图标5","icon" : "static/webos/img/bd/icon-5.png","src" : "inner/message/message.html","moduleId" : "12","fenceId" : "abc1","fenceSequence" : 0,"fenceLabel":"测试Fence1"},
			{"label" : "fence图标6","icon" : "static/webos/img/bd/icon-6.png","src" : "inner/message/message.html","moduleId" : "13","fenceId" : "abc1","fenceSequence" : 0,"fenceLabel":"测试Fence1"},
			{"label" : "fence图标7","icon" : "static/webos/img/bd/icon-7.png","src" : "inner/message/message.html","moduleId" : "14","fenceId" : "abc1","fenceSequence" : 0,"fenceLabel":"测试Fence1"},
			{"label" : "fence图标1","icon" : "static/webos/img/bd/icon-1.png","src" : "inner/message/message.html","moduleId" : "15","fenceId" : "abc2","fenceSequence" : 1,"fenceLabel":"测试Fence2"},
			{"label" : "fence图标2","icon" : "static/webos/img/bd/icon-2.png","src" : "inner/message/message.html","moduleId" : "16","fenceId" : "abc2","fenceSequence" : 1,"fenceLabel":"测试Fence2"},			
			{"label" : "fence图标3","icon" : "static/webos/img/bd/icon-3.png","src" : "inner/message/message.html","moduleId" : "17","fenceId" : "abc2","fenceSequence" : 1,"fenceLabel":"测试Fence2"},
			{"label" : "fence图标1","icon" : "static/webos/img/bd/icon-1.png","src" : "inner/message/message.html","moduleId" : "18","fenceId" : "abc3","fenceSequence" : 2,"fenceLabel":"测试Fence3"},
			{"label" : "fence图标2","icon" : "static/webos/img/bd/icon-2.png","src" : "inner/message/message.html","moduleId" : "19","fenceId" : "abc3","fenceSequence" : 2,"fenceLabel":"测试Fence3"},
			{"label" : "fence图标3","icon" : "static/webos/img/bd/icon-3.png","src" : "inner/message/message.html","moduleId" : "20","fenceId" : "abc3","fenceSequence" : 2,"fenceLabel":"测试Fence3"},
			{"label" : "fence图标4","icon" : "static/webos/img/bd/icon-4.png","src" : "inner/message/message.html","moduleId" : "21","fenceId" : "abc3","fenceSequence" : 2,"fenceLabel":"测试Fence3"},
			{"label" : "fence图标5","icon" : "static/webos/img/bd/icon-5.png","src" : "inner/message/message.html","moduleId" : "22","fenceId" : "abc3","fenceSequence" : 2,"fenceLabel":"测试Fence3"},
			{"label" : "fence图标1","icon" : "static/webos/img/bd/icon-1.png","src" : "inner/message/message.html","moduleId" : "23","fenceId" : "abc4","fenceSequence" : 3,"fenceLabel":"测试Fence4"},
			{"label" : "fence图标2","icon" : "static/webos/img/bd/icon-2.png","src" : "inner/message/message.html","moduleId" : "24","fenceId" : "abc4","fenceSequence" : 3,"fenceLabel":"测试Fence4"},
			{"label" : "fence图标3","icon" : "static/webos/img/bd/icon-3.png","src" : "inner/message/message.html","moduleId" : "25","fenceId" : "abc4","fenceSequence" : 3,"fenceLabel":"测试Fence4"},
			{"label" : "fence图标4","icon" : "static/webos/img/bd/icon-4.png","src" : "inner/message/message.html","moduleId" : "26","fenceId" : "abc4","fenceSequence" : 3,"fenceLabel":"测试Fence4"},
			{"label" : "fence图标5","icon" : "static/webos/img/bd/icon-5.png","src" : "inner/message/message.html","moduleId" : "27","fenceId" : "abc4","fenceSequence" : 3,"fenceLabel":"测试Fence4"}
		];
		/*var iconArr=[
			{
				"label": "用户管理",
				"icon": "../images/icons/icon-1.png",
				"moduleId": "1",
				"fenceId": "195.35714623401873",
				"fenceSequence": "3",
				"fenceLabel": "新栅栏",
				"src": "user/User!list.action"
			},
			{
				"label": "操作历史",
				"icon": "../images/icons/icon-2.png",
				"moduleId": "3",
				"fenceId": "2",
				"fenceSequence": "2",
				"fenceLabel": "操作历史",
				"src": "user/User!list.action"
			},
			{
				"label": "消息中心",
				"icon": "../images/icons/icon-2.png",
				"moduleId": "2",
				"fenceId": "0",
				"fenceSequence": "-1",
				"fenceLabel": "",
				"src": "user/User!list.action"
			},
			{
				"label": "智能搜",
				"icon": "../images/icons/icon-6.png",
				"moduleId": "4",
				"fenceId": "2",
				"fenceSequence": "2",
				"fenceLabel": "操作历史",
				"src": "user/User!list.action"
			}
		]*/
		/*var toolArr = [
			{"label":"添加","icon":"plus"},
			{"label":"皮肤","icon":"skin"},
			{"label":"聊天","icon":"chat"},
			{"label":"历史","icon":"history"},
			{"label":"焦点","icon":"focus"},
			{"label":"云","icon":"cloud"},
			{"label":"栅栏","icon":"fence"},
			{"label":"设置","icon":"set"}
		];*/
		//new出Desk对象
		var desk = new Desk(6);
		//加载完成事件
		$(desk).bind("onDeskLoad",function(){
			//console.log(desk.getIconArr());
			desk.addDeskIcons([{
				"label" : "测试添加1",
				"icon" : "static/webos/img/bd/icon-1.png",
				"src" : "inner/message/message.html",
				"moduleId" : "999"
			},{
				"label" : "测试添加2",
				"icon" : "static/webos/img/bd/icon-2.png",
				"src" : "",
				"moduleId" : "998"
			}]);
			desk.addDeskIcon({
				"label" : "测试添加3",
				"icon" : "static/webos/img/bd/icon-3.png",
				"src" : "inner/message/message.html",
				"moduleId" : "997"
			});
		});
		//初始化桌面
		desk.initDesk(iconArr);
		//desk.initTool(toolArr);
		// 删除整个fence块
		$(desk).bind("onFenceDelete",function(event,fenceid) {
			console.log("onFenceDelete=========");
			console.log("fenceid:" + fenceid);
		})
		// 将fence中的一个图标拖出
		$(desk).bind("onDropFromFence",function(event,iconid,fenceid) {
			console.log("onDropFromFence=========");
			console.log("iconid:" + iconid);
			console.log("fenceid:" + fenceid);
		});
		// 将桌面的一个图标拖入一个fence(包括从fence拖入到fence中)
		$(desk).bind("onDragToFence",function(event,iconid,fenceid,fencelabel,fenceSequence) {
			console.log("onDragToFence=========");
			console.log("iconid:" + iconid);
			console.log("fenceid:" + fenceid);
			console.log("fencelabel:" + fencelabel);
			console.log("fenceSequence:" + fenceSequence);
		});
		// 修改fence的标题名称
		$(desk).bind("onEditFenceLabel",function(event,fenceid,fencelabel) {
			console.log("onEditFenceLabel=========");
			console.log("fenceid:" + fenceid);
			console.log("fencelabel:" + fencelabel);
		});
		// 排列桌面图标的顺序
		$(desk).bind("onChangeShortcutSequence",function(event,deskIconArr) {
			console.log("onChangeShortcutSequence=========");
			console.log("deskIconArr:" + deskIconArr);
		});
		// 从桌面上删除一个图标
		$(desk).bind("onShortcutDelete",function(event,iconid) {
			console.log("onShortcutDelete=========");
			console.log("iconid:" + iconid);
		});
		// 从fence中删除一个图标
		$(desk).bind("onFenceIconDelete",function(event,iconid) {
			console.log("onFenceIconDelete=========");
			console.log("iconid:" + iconid);
		});
		/*setTimeout(function(){
			desk.openWindow("","测试打开页面");
		},2000);*/
	});
</script>
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
                <div class="fenceGroup">
                </div><!--end of fenceGroup-->
            	<div class="shortcutTray miniShortcut  screen1">
					<div class="addItem" style="top: 132px; left: 142px; ">
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
            <div class="rightMenu" style="display:none;">
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
            </div><!--鼠标右击菜单-->
        </div><!--END OF MAIN-->
    </div><!-- End of bd -->  
</div><!-- End of container -->
</body>
</html>
