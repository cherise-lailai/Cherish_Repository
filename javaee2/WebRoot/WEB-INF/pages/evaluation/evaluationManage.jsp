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
</head>
<body>
<script type="text/javascript">
/* 日期组件使用 */
var endTime=null;
var beginTime=null;
$(function() {
 	 $('#beginTime').datepicker().on('changeDate.datepicker.amui', function(event) {
     	beginTime=event.date.valueOf();
     }); 
     $('#endTime').datepicker().on('changeDate.datepicker.amui', function(event) {
     	endTime=event.date.valueOf();
     });	
}); 

//开始时间必须在截止时间前
function checkTime(){
/* 	alert(beginTime);
	alert(endTime); */
	if(beginTime==null||endTime==null){
		alert("请选择日期！");
		return false;
	}
	if(beginTime>endTime){
		alert("请核对日期是否输入错误！");
		return false;
	}
	return true;
}
 
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
			<form class="am-form am-form-horizontal" action="<%=path%>/eva_getList" method="post" onsubmit="return checkTime();">
				<div class="am-u-sm-3">
					<h2>开始时间</h2>
					<p><input type="text"  id="beginTime" name="beginTime" class="am-form-field" placeholder="日历组件" data-am-datepicker="{theme: 'success'}" readonly/></p>
					<span class="am-text-success">你选的开始时间：<s:property value="#request.beginTime" /></span>
				</div>
				<div class="am-u-sm-3">
					<h2>截止时间</h2>
					<p><input type="text"  id="endTime" name="endTime" class="am-form-field" placeholder="日历组件" data-am-datepicker="{theme: 'success'}" readonly/></p>
					<span class="am-text-success">你选的结束时间：<s:property value="#request.endTime" /></span>
				</div>
				<s:if test="#session.permission=='teacher'">
					<div class="am-u-sm-3">
						<h2>输入学生姓名名：</h2>
						<input type="text" name="userName" placeholder="用户名" value="${userName}">
					</div>
				</s:if>
				<div class="am-u-sm-3">
					<h1>&nbsp;</h1>
					<button type="submit" class="am-btn am-btn-primary">查询</button>	
				</div>
			</form>
			<table class="am-table am-table-striped am-table-hover table-main">
					<thead>
				        <tr>
				            <td class="">序号</td>
				            <td class="">学生</td>
				            <td class="">课程</td>
				            <td class="">老师</td>
				            <td class="">老师反馈</td>
				            <td class="">上课时间</td>
				        </tr>
			   		</thead>
			    	<tbody>
						<s:iterator status="statu" value="#request.evaLsit"
							id="eva">
							<tr>
							 	<td class=""><s:property value="#statu.index+1" /></td>
							 	<td class=""><s:property value="#eva.userName"/></td>
							 	<td class=""><s:property value="#eva.courseName" /></td>
					            <td class=""><s:property value="#eva.teacher" /></td>
					            <td class=""><s:property value="#eva.feedback"/></td>
					            <td class=""><s:property value="#eva.date"/></td>
				        	</tr>
						</s:iterator>
					</tbody>
			</table>	
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

