<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'MyJsp.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
-->

</head>

<body>
	<div class="admin-sidebar am-offcanvas  am-padding-0"
		id="admin-offcanvas">
		<div class="am-offcanvas-bar admin-offcanvas-bar">
			<!-- User -->
			<div class="user-box am-hide-sm-only">
				<div class="user-img">
					<img src="assets/img/avatar-1.jpg" alt="user-img" title="Mat Helme"
						class="img-circle img-thumbnail img-responsive">
					<div class="user-status offline">
						<i class="am-icon-dot-circle-o" aria-hidden="true"></i>
					</div>
				</div>
				<h5>
					<a href="#">cherish</a>
				</h5>
				<button class="btn-primary">退出</button>
				<ul class="list-inline">
					<li><a href="#"> <i class="fa fa-cog" aria-hidden="true"></i>
					</a></li>

					<li><a href="<%=path%>/login_Out" class="text-custom"> <i class="fa fa-cog"
							aria-hidden="true"></i>
					</a></li>
				</ul>
			</div>
			<!-- End User -->

			<ul class="am-list admin-sidebar-list">
				<li><a href="<%=path%>/page_index"><span class="am-icon-home"></span>
						首页</a></li>
				<!--商家具有权限-->
				<li class="admin-parent"><a class="am-cf"
					data-am-collapse="{target: '#collapse-nav1'}"><span
						class="am-icon-table"></span> 学生 <span
						class="am-icon-angle-right am-fr am-margin-right"></span></a>
					<ul class="am-list am-collapse admin-sidebar-sub am-in"
						id="collapse-nav1">
						<li><a href="<%=path%>/user_all" class="am-cf"> 学生管理</span></a></li>
						<li><a href="<%=path%>/page_userAdd">添加学生</a></li>
					</ul>
				</li>
				<li class="admin-parent"><a class="am-cf"
					data-am-collapse="{target: '#collapse-nav8'}"><span
						class="am-icon-table"></span> 老师 <span
						class="am-icon-angle-right am-fr am-margin-right"></span></a>
					<ul class="am-list am-collapse admin-sidebar-sub am-in"
						id="collapse-nav8">
						<li><a href="<%=path%>/teacher_all" class="am-cf"> 老师管理</span></a></li>
						<li><a href="<%=path%>/page_teacherAdd">添加老师</a></li>
					</ul>
				</li>
				
				<li class="admin-parent"><a class="am-cf"
					data-am-collapse="{target: '#collapse-nav9'}"><span
						class="am-icon-table"></span> 首页管理 <span
						class="am-icon-angle-right am-fr am-margin-right"></span></a>
					<ul class="am-list am-collapse admin-sidebar-sub am-in"
						id="collapse-nav9">
						<li><a href="<%=path%>/home_contentManage" class="am-cf"> 首页管理</span></a></li>
					</ul>
				</li>
				<li class="admin-parent"><a class="am-cf"
					data-am-collapse="{target: '#collapse-nav2'}"><i
						class="am-icon-line-chart" aria-hidden="true"></i> 考勤 <span
						class="am-icon-angle-right am-fr am-margin-right"></span></a>
					<ul class="am-list am-collapse admin-sidebar-sub am-in"
						id="collapse-nav2">
						<li><a href="<%=path%>/check_add" class="am-cf"> 考勤管理</span></a></li>
						<li><a href="<%=path%>/page_add" class="am-cf"> 考勤</span></a></li>
					</ul></li>
				<li class="admin-parent"><a class="am-cf"
					data-am-collapse="{target: '#collapse-nav3'}"><span
						class="am-icon-file"></span> 课程表管理 <span
						class="am-icon-angle-right am-fr am-margin-right"></span></a>
					<ul class="am-list am-collapse admin-sidebar-sub am-in"
						id="collapse-nav3">
						<li><a href="<%=path%>/ct_getCT" class="am-cf"> 查看课程表</a></li>
					</ul></li>
				<li class="admin-parent"><a class="am-cf"
					data-am-collapse="{target: '#collapse-nav4'}"><span
						class="am-icon-file"></span> 账户管理 <span
						class="am-icon-angle-right am-fr am-margin-right"></span></a>
					<ul class="am-list am-collapse admin-sidebar-sub am-in"
						id="collapse-nav4">
						<li><a href="<%=path%>/page_changePWD" class="am-cf"> 修改密码</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
</body>
</html>
