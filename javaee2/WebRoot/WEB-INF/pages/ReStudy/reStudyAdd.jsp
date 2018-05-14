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
		/* 页面年度值获取 */
		$(function() {
			var url = "<%=path%>/reStudy_findReStudyCourse";
			var args = null;
			$.post(url, args, function(data) {
				$("#course").append("<option value='' >" + "请选择" + "</option>");
				var courseArr = data.split(",");
				for (var i = 0; i < courseArr.length; i++)
					$("#course").append("<option value='" + courseArr[i] + "'>" + courseArr[i] + "</option>");
			}, "JSON");
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
		<div class="content-page">
			<form action="<%=path%>/reStudy_openReStudy" method="post">
				<div>
					<div class="am-g am-panel" style="background-color: gainsboro">
						<div class="am-u-sm-6">
							<h2 style="float: left; display: block">课程:</h2>
							<select data-am-selected id="course" style="width: 50px;"
								name="courseName"></select>
						</div>
						<div class="am-u-sm-6">
							<h2 style="float: left; display: block">老师:</h2>
							<select  data-am-selected id="teacher" style="width: 50px;"
								name="teacherName"></select>
						</div>

						<div class="am-u-sm-2">
							<button type="submit" class="am-btn am-btn-default">提交补课安排</button>
						</div>

					</div>
				</div>
			</form>
			
			<div data-am-sticky>
		     	<button class="am-btn am-btn-danger am-btn-block" >未正确安排补课时间的补课信息</button>
			</div>
			<table class="am-table am-table-striped am-table-hover table-main">
					<thead>
				        <tr>
				            <td class="">序号</td>
				            <td class="">课程</td>
				            <td class="">老师</td>
				            <td class="">期度</td>
				            <td class="">操作</td>
				        </tr>
			   		</thead>
			    	<tbody>
						<s:iterator status="statu" value="#request.adminOkList"
							id="reStudy">
							<tr>
							 	<td class="am-text-danger"><s:property value="#statu.index+1" /></td>
							 	<td class="am-text-danger"><s:property value="#reStudy.courseName"/></td>
							 	<td class="am-text-danger"><s:property value="#reStudy.teacher" /></td>
					            <td class="am-text-danger"><s:property value="#reStudy.period" /></td>
					            <td class="am-text-danger">
					            	<a type="" href="<%=path%>/reStudy_deleteByid?rscid=<s:property value="#reStudy.id" />">删除</a>
					            </td>
				        	</tr>
						</s:iterator>					
					</tbody>
			</table>
			
			<div data-am-sticky>
		     	<button class="am-btn am-btn-danger am-btn-block" >已经正确安排补课时间的补课信息</button>
			</div>		
			<table class="am-table am-table-striped am-table-hover table-main">
					<thead>
				        <tr>
				            <td class="">序号</td>
				            <td class="">课程</td>
				            <td class="">老师</td>
				            <td class="">期度</td>
				            <td class="">操作</td>
				        </tr>
			   		</thead>
			    	<tbody>					
						<s:iterator status="statu" value="#request.teacherOkList"
							id="reStudy">
							<tr>
							 	<td class="am-text-success"><s:property value="#statu.index+1" /></td>
							 	<td class="am-text-success"><s:property value="#reStudy.courseName"/></td>
							 	<td class="am-text-success"><s:property value="#reStudy.teacher" /></td>
					            <td class="am-text-success"><s:property value="#reStudy.period" /></td>
					            <td class="am-text-success">
					            	<a type="" href="<%=path%>/reStudy_deleteByid?rscid=<s:property value="#reStudy.id" />">删除</a>
					            </td>
				        	</tr>
						</s:iterator>
						
					</tbody>
			</table>	
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
	<script type="text/javascript">
		/* 课程改变，更新该课程适合的课老师*/
		$("#course").change(function() {
			var url = "<%=path%>/teacher_getFixTeacher";
			var courseName = $("#course option:selected").val();
			var args = {
				"cname" : courseName
			};
			$.post(url, args, function(data) {
				var teacherArr = data.split(",");
				$("#teacher").html("");
				for (var i = 0; i < teacherArr.length; i++)
					$("#teacher").append("<option value='" + teacherArr[i] + "'>" + teacherArr[i] + "</option>");
			}, "JSON");
		});
	</script>

</body>

</html>

