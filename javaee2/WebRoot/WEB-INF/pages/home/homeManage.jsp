<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>后台模板</title>
<link rel="stylesheet" href="<%=path%>/assets/css/amazeui.css" />
<link rel="stylesheet" href="<%=path%>/assets/css/core.css" />
<link rel="stylesheet" href="<%=path%>/assets/css/menu.css" />
<link rel="stylesheet" href="<%=path%>/assets/css/index.css" />
<link rel="stylesheet" href="<%=path%>/assets/css/admin.css" />
<link rel="stylesheet" href="<%=path%>/assets/css/page/typography.css" />
<link rel="stylesheet" href="<%=path%>/assets/css/page/form.css" />
<link rel="stylesheet" href="<%=path%>/assets/css/component.css" />
<script type="text/javascript" src="<%=path%>/assets/js/edit/wangEditor.min.js"></script>
<script type="text/javascript" src="<%=path%>/assets/js/jquery-2.1.0.js"></script>
</head>
<body>
<script type="text/javascript">
/*转义字符解码  */
function HTMLDecode(text) { 
    var temp = document.createElement("div"); 
    temp.innerHTML = text; 
    var output = temp.innerText || temp.textContent; 
    temp = null; 
    return output; 
} 
/* 动态渲染出html标签 */
 $(function(){ 
 	var intorHtmlCode='<s:property value="#request.introContent.content"/>';
	var styleHtmlCode='<s:property value="#request.styleContent.content"/>';
	var noticeHtmlCode='<s:property value="#request.noticeContent.content"/>';
	var introHtmlDecode=HTMLDecode(intorHtmlCode);
	var styleHtmlDecode=HTMLDecode(styleHtmlCode);
	var noticeDecode=HTMLDecode(noticeHtmlCode);
	
 	var introHtml=IntroEditor.txt.html(introHtmlDecode);
 	var styleHtml=styleEditor.txt.html(styleHtmlDecode);
 	var noticeHtml=noticeEditor.txt.html(noticeDecode);
 });
 
 function submitContent(){
	 var introHtml=IntroEditor.txt.html();
	 var styleHtml=styleEditor.txt.html();
	 var noticeHtml=noticeEditor.txt.html();
	 var url = "<%=path%>/home_updateContent";
	 var args = {"intro":introHtml,"style":styleHtml,"notice":noticeHtml};
	 $.post(url,args,function(data){
		alert(data);
	 },"JSON");
}
			 
</script>
	<header class="am-topbar am-topbar-fixed-top">
		<div class="contain" style="background-color: #f8f8f8">
			<div class="am-g doc-am-g">
				<div class="am-u-sm-4 am-u-md-4 am-u-lg-4">&nbsp;</div>
				<div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
					<ul class="am-nav am-navbar-nav ">
						<li><h4 class="page-title">fjut英语培训机构，欢迎您的到来!</h4></li>
					</ul>
				</div>
				<div class="am-u-sm-4 am-u-md-4 am-u-lg-4">&nbsp;</div>
			</div>
		</div>
	</header>

	<div class="admin">
		<jsp:include page="../common/fun.jsp"></jsp:include>
		<div class="content-page">			
			<div id="menu" class="toolbar">			
			</div>
			<div style="padding: 5px 0; color: #ccc"><s:property value="#request.introContent.title"/></div>
			<div id="jj" class="text" style="wi"></div>
			<div style="padding: 5px 0; color: #ccc"><s:property value="#request.styleContent.title"/></div>
			<div id="fm" class="text" style="wi"></div>
			<div style="padding: 5px 0; color: #ccc"><s:property value="#request.noticeContent.title"/></div>
			<div id="tz" class="text" style="wi"></div>
			<input type="button" id="my-popover" onclick="submitContent();" class="am-btn am-btn-primary" value="提交" />
			<!-- 引用js -->
			<script type="text/javascript" src="/wangEditor.min.js"></script>
			<script type="text/javascript">
			    var E = window.wangEditor;
			    var IntroEditor = new E('#menu', '#jj');
			    IntroEditor.create();
			    var styleEditor =  new E('#fm');
			    styleEditor.create();
			    var noticeEditor =  new E('#tz');
			    noticeEditor.create();
			</script>
		</div>
	</div>


	<!-- navbar -->
	<a href="admin-offcanvas"
		class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu"
		data-am-offcanvas="{target: '#admin-offcanvas'}"> 
	</a>


	<script type="text/javascript" src="<%=path%>/assets/js/amazeui.min.js"></script>
	<script type="text/javascript" src="<%=path%>/assets/js/app.js"></script>
	<script type="text/javascript" src="<%=path%>/assets/js/blockUI.js"></script>
	<script type="text/javascript"
		src="<%=path%>/assets/js/charts/echarts.min.js"></script>
	<script type="text/javascript"
		src="<%=path%>/assets/js/charts/indexChart.js"></script>
</body>

</html>

