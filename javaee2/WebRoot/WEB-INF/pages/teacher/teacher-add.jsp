<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags" %>    
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

<script type="text/javascript"
		src="<%=path%>/assets/js/jquery-2.1.0.js"></script>
<style>

 </style>
</head>
<body>
<script>
function previewFile() {
var preview = document.querySelector('#showHead');
var file    = document.querySelector('input[type=file]').files[0];
var reader  = new FileReader();

reader.onloadend = function () {
preview.src = reader.result;
}

if (file) {
reader.readAsDataURL(file);
} else {
preview.src = "";
}
}
//回显老师的技能
$("#checkbox").ready(function(){
	var url = "<%=path%>/teacher_getGoodAtByid";
	var tid='<s:property value="#session.teacher.id"/>';
	var args = {"tid":tid}
	$.post(url,args,function(data){
		for(var i=0;i<data.length;i++){
			var g=data[i];
			if(g=="阅读理解"){
				$("#read").uCheck('check');
			}else if(g=="听力"){
				$("#listen").uCheck('check');
			}
			else if(g=="词汇"){
				$("#word").uCheck('check');
			}
			else if(g=="写作"){
				$("#write").uCheck('check');
			}
			else if(g=="口语"){
				$("#speak").uCheck('check');
			}
		} 
	},"JSON");
});
</script>
	<!-- Begin page -->
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
	<!-- end page -->
	<div class="admin">
		<jsp:include page="../common/fun.jsp"></jsp:include>
		<div class="content-page" >
			<div>
				<div class="am-u-sm-4">&nbsp;</div>
				<div class="am-u-sm-4">
			<%-- 	<s:debug></s:debug> --%>
				<%-- 	<s:actionerror/> --%>
					<s:fielderror></s:fielderror>	
					<form action="<%=path%>/teacher_add" class="am-form" method="post" enctype="multipart/form-data">
						<fieldset>
							<input type="hidden" name="tid" value="<s:property value="#session.teacher.id"/>"/>
							<!--用户名-->
							<div class="am-form-group">
								<label for="username">老师名：</label> <input type="text"
									name="teaName"  minlength="3" placeholder="输入用户名（至少 3 个字符）"
									required  value="<s:property value="#session.teacher.name"/>"/>
							</div>
							<!--密码-->
							<div class="am-form-group">
								<label for="password">密码：</label> <input type="password"
									name="password" minlength="3" placeholder="输入密码（至少 3 个字符）"
									required value="<s:property value="#session.teacher.password"/>"/>
							</div>
							
							<!--文件上传-->
							<div>
								<div class="am-form-group am-form-file">
									<label for="doc-ipt-file-2">选择图片</label>
									<div>
										<button type="button" class="am-btn am-btn-default am-btn-sm">
											<i class="am-icon-cloud-upload"></i> 选择要上传的文件
										</button>
									</div>
									<input type="file" accept="image/x-png,image/bmp,image/gif,image/jpeg,image/jpg" id="doc-ipt-file-2" id="headImage" name="headImage" onchange="previewFile()"><br>
									<img id="showHead" src="" height="200" alt="">
								</div>
							</div>
							<div class="am-form-group">
								<label for="doc-vld-email-2">邮箱：</label> <input type="email"
									id="doc-vld-email-2" name="email" placeholder="输入邮箱" required value="<s:property value="#session.teacher.email"/>" />
							</div>
							<!--擅长课程选择-->
							<div>
								<div class="am-g">
								  <div class="am-u-sm-6" id="checkbox">
							   			<h3>请选择</h3>
									    <label class="am-checkbox">
									      	<input type="checkbox" id="read" name="goodAt" value="阅读理解" data-am-ucheck > 阅读理解
									    </label>
									    <label class="am-checkbox">
									      	<input type="checkbox" id="listen" name="goodAt"  value="听力" data-am-ucheck > 听力
									    </label>
									    <label class="am-checkbox">
									      <input type="checkbox" id="word" name="goodAt" value="词汇" data-am-ucheck >词汇
									    </label>
									    <label class="am-checkbox">
									      <input type="checkbox" id="write" name="goodAt" value="写作" data-am-ucheck  >写作
									    </label>
									    <label class="am-checkbox">
									      <input type="checkbox" id="speak" name="goodAt"  value="口语" data-am-ucheck  >口语
									    </label>
								  </div>
							</div>
							<div>
								<div class="am-u-sm-4">&nbsp;</div>
								<div class="am-u-sm-4">
									<button type="submit" class="am-btn am-btn-default">提交</button>
								</div>
								<s:property value="teacherAddInfo"/>
								<div class="am-u-sm-4">&nbsp;</div>
							</div>
						</fieldset>
					</form>
				</div>
				<div class="am-u-sm-4">&nbsp;</div>
			</div>
		</div>
	</div>


	<!-- navbar -->
	<a href="admin-offcanvas"
		class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu"
		data-am-offcanvas="{target: '#admin-offcanvas'}"> <!--<i class="fa fa-bars" aria-hidden="true"></i>-->
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

