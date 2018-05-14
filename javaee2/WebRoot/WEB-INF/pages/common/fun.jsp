<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
<script type="text/javascript">
function exitLogin(){
	var url = "<%=path%>/login_exitLogin";
	var args =null;
	$.post(url,args,function(data){	
		if(data=="true"){
			window.location.href="<%=path%>/index.jsp";
		}
	},"JSON");
}	
</script>
	<div class="admin-sidebar am-offcanvas  am-padding-0"
		id="admin-offcanvas">
		<div class="am-offcanvas-bar admin-offcanvas-bar">
			<!-- User -->
			<div class="user-box am-hide-sm-only">
				<div class="user-img">
					<s:if test="#session.permission=='merchant'">
						<a href="javascript:void(0)">
							<img alt="当前用户头像" src="<%=path%>/assets/img/monkey.jpg" class="img-circle img-thumbnail img-responsive"/>
						</a>
						<a href="javascript:void(0)">
							<span><s:property value="#session.currentPeople.name"/></span>
						</a>
					</s:if>
					<s:if test="#session.permission!='merchant'">
						<a href="javascript:void(0)">
							<img alt="当前用户头像" src="<%=path%><s:property value="#session.currentPeople.imgPath"/>" class="img-circle img-thumbnail img-responsive"/>
						</a>
						<a href="javascript:void(0)">
							<span><s:property value="#session.currentPeople.name"/></span>
						</a>
					</s:if>							
				</div>
				<h5>
					
				</h5>
				<button class="btn-primary" onclick="exitLogin()">退出</button>
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
				<s:if test="#session.permission=='merchant'">
					<!--商家具有权限-->
					<li class="admin-parent"><a class="am-cf"
						data-am-collapse="{target: '#collapse-nav10'}"><span
							class="am-icon-table"></span> 班级 <span
							class="am-icon-angle-right am-fr am-margin-right"></span></a>
						<ul class="am-list am-collapse admin-sidebar-sub am-in"
							id="collapse-nav10">
							<li><a href="<%=path%>/cla_all" class="am-cf"> 班级管理</span></a></li>
							<li><a href="<%=path%>/page_courseTimeAdd">开班选课</a></li>
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
				</s:if>
				<s:if test="#session.permission=='teacher'||#session.permission=='merchant'">
			<%-- 	<s:if test="#session.permission=='teacher'">  --%>
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
				</s:if>
				<s:if test="#session.permission=='teacher'">
					<li class="admin-parent"><a class="am-cf"
						data-am-collapse="{target: '#collapse-nav2'}"><i
							class="am-icon-line-chart" aria-hidden="true"></i> 考勤 <span
							class="am-icon-angle-right am-fr am-margin-right"></span></a>
						<ul class="am-list am-collapse admin-sidebar-sub am-in"
							id="collapse-nav2">
							<li><a href="<%=path%>/page_checkManage" class="am-cf"> 考勤管理</span></a></li>
							<li><a href="<%=path%>/page_checkAdd" class="am-cf"> 考勤</span></a></li>
						</ul>
					</li>
				</s:if>
				
				<s:if test="#session.permission!='merchant'">
					<li class="admin-parent"><a class="am-cf"
						data-am-collapse="{target: '#collapse-nav11'}"><i
							class="am-icon-line-chart" aria-hidden="true"></i> 反馈 <span
							class="am-icon-angle-right am-fr am-margin-right"></span></a>
						<ul class="am-list am-collapse admin-sidebar-sub am-in"
							id="collapse-nav11">
							<li><a href="<%=path%>/eva_getList" class="am-cf"> 查看反馈</span></a></li>
						</ul>
					</li>
				</s:if>
				
				<s:if test="#session.permission=='merchant'||#session.permission=='teacher'">
					<li class="admin-parent"><a class="am-cf"
						data-am-collapse="{target: '#collapse-nav12'}"><i
							class="am-icon-line-chart" aria-hidden="true"></i> 补课 <span
							class="am-icon-angle-right am-fr am-margin-right"></span></a>
						<s:if test="#session.permission=='teacher'">
							<ul class="am-list am-collapse admin-sidebar-sub am-in"
								id="collapse-nav12">
								<li><a href="<%=path%>/reStudy_halfMonthReStudy" class="am-cf">安排补课时间</span></a></li>
							</ul>
						</s:if>
						<s:if test="#session.permission=='merchant'">
							<ul class="am-list am-collapse admin-sidebar-sub am-in"
								id="collapse-nav12">
								<li><a href="<%=path%>/reStudy_openReStudy" class="am-cf">管理补课</span></a></li>
							</ul>
						</s:if>
					</li>
				</s:if>		
				<s:if test="#session.permission!='merchant'">
					<li class="admin-parent"><a class="am-cf"
						data-am-collapse="{target: '#collapse-nav3'}"><span
							class="am-icon-file"></span> 课程表管理 <span
							class="am-icon-angle-right am-fr am-margin-right"></span></a>
						<ul class="am-list am-collapse admin-sidebar-sub am-in"
							id="collapse-nav3">
							<li><a href="<%=path%>/ct_getCT" class="am-cf"> 查看课程表</a></li>
						</ul>
					</li>
				</s:if>
				<li class="admin-parent"><a class="am-cf"
					data-am-collapse="{target: '#collapse-nav4'}"><span
						class="am-icon-file"></span> 账户管理 <span
						class="am-icon-angle-right am-fr am-margin-right"></span></a>
					<ul class="am-list am-collapse admin-sidebar-sub am-in"
						id="collapse-nav4">
						<li><a href="<%=path%>/page_changePWD" class="am-cf"> 修改密码</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</body>
</html>
