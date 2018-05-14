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
<script type="text/javascript" src="<%=path%>/assets/js/jquery-2.1.0.js"></script>

<%@ include file="/com/userQueryCondition.jsp"%>
</head>
<body>
<script type="text/javascript">
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
		<div class="content-page">
			<div class="content-page">
				<!-- Start content -->
				<div class="content">
					<div class="card-box">
						<div class="am-g am-panel" style="background-color: gainsboro">
							<div class="am-u-sm-2">
								<h2 style="float: left; display: block">--------</h2>
								&nbsp;&nbsp;&nbsp;
								<h2 class="text-success" style="float: left"></h2>
							</div>
							<div class="am-u-sm-2">
								<h2 style="float: left; display: block">--------</h2>
								&nbsp;&nbsp;&nbsp;
								<h2 class="text-success" style="float: left"></h2>
							</div>
						</div>
						<!-- Row start -->
						<div class="am-g">
							<div class="am-u-sm-2 am-u-md-2">
								<a href="<%=path%>/teacher_exportTeacherInfo?teaName=<s:property value="#teaNameConditon"/>&goodAt=<s:property value="#goodAtConditon"/>"><button class="am-btn am-btn-default" type="button">导出信息</button></a>	
							</div>
							<div class="am-u-sm-8 am-u-md-8"></div>
							<div class="am-u-sm-2 am-u-md-2">
								<div class="am-input-group am-input-group-sm">
									<form action="<%=path%>/teacher_all" method="post">
										<input type="text" name="goodAt" class="am-form-field" value="<s:property value="#goodAtConditon"/>" placeholder="老师技能">
										<input type="text" name="teaName" class="am-form-field" value="<s:property value="#teaNameConditon"/>" placeholder="老师名">
										<span class="am-input-group-btn">
											<button class="am-btn am-btn-default" type="submit">搜索</button>
										</span>
									</form>
								</div>
							</div>
						</div>
					</div>
					<table class="am-table am-table-striped am-table-hover table-main">
						<thead>
							<tr align="center">
								<td class="text-success" style="font-size: larger">头像</td>
								<td class="text-success" style="font-size: larger">老师</td>
								<td class="text-success" style="font-size: larger">邮箱</td>
								<td class="text-success" style="font-size: larger">技能</td>
								<td class="text-success" style="font-size: larger">操作</td>
							</tr>
						</thead>
						<tbody>
							<s:iterator id="teacher" value="#request.teacherList">
								<tr align="center">
									<td class=""><a href="<%=path%>/teacher_downImg?tid=<s:property value="#teacher.id"/>"><img style="width: 70px;height: 70px" src="<%=path%>/<s:property value="#teacher.imgPath"/>"
										alt="未知"></img></a></td>
									<td><span class="text-success"><s:property value="#teacher.name"/></span></td>
									<td><span class="text-success"><s:property value="#teacher.email"/></span></td>
									<td><span class="text-success"><s:property value="#teacher.goodAt"/></span></td>
									<td><a href="<%=path%>/teacher_update?tid=<s:property value="#teacher.id"/>"><button >修改</button></a>
									<a href="<%=path%>/teacher_delete?tid=<s:property value="#teacher.id"/>"><button >删除</button></a></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
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

