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
			
		<div>
			<div class="am-g am-panel" style="background-color: gainsboro">
				<div class="am-u-sm-6">
					
					<span><strong  class="am-text-default">您好，<s:property value="#session.currentPeople.name"/>老师！</strong>鉴于机构安排，我们为你生成了补课清单，请查阅！</span>
				</div>
			</div>
		</div>

			
		<div data-am-sticky>
		     <button class="am-btn am-btn-danger am-btn-block" >未安排的补课</button>
		</div>
		<table class="am-table am-table-striped am-table-hover table-main">
				<thead>
			        <tr>
			            <td class="am-text-primary">序号</td>
			            <td class="am-text-primary">课程</td>
			            <td class="am-text-primary">老师</td>
			            <td class="am-text-primary">期度</td>
			            <td class="am-text-primary">补课时间</td>
			            <td class="am-text-primary">操作</td>
			        </tr>
		   		</thead>
		    	<tbody>
					<s:iterator status="statu" value="#request.reStuCourseList"
						id="reStudy">
						<form action="<%=path%>/reStudy_addByTeacher" method="post">
							<tr>
							 	<td class=""><s:property value="#statu.index+1" /></td>
							 	<input type="hidden" name="rscid" value="<s:property value="#reStudy.id"/>">
							 	<td class="">
								 	<s:property value="#reStudy.courseName"/>
								 	<input type="hidden" name="courseName" value="<s:property value="#reStudy.courseName"/>">
								</td>
							 	<td class="">
							 		<s:property value="#reStudy.teacher" />
							 		<input type="hidden" name="teacher" value="<s:property value="#reStudy.teacher"/>">
							 	</td>
					            <td class="">
					            	<s:property value="#reStudy.period" />
					          		 <input type="hidden" name="period" value=<s:property value="#reStudy.period"/>>
					            </td>
					            <td class=""><input type="text" name="studyTime" class="am-form-field"  placeholder="上课时间通知"></td>
					            <td class="">
					            	<input type="submit" class="am-btn am-btn-success" value="确认">
					            </td>
				        	</tr>
			        	</form>
					</s:iterator>
				</tbody>
		</table>	
		
		<div data-am-sticky>
		     <button class="am-btn am-btn-danger am-btn-block" >已安排的补课</button>
		</div>
		<table class="am-table am-table-striped am-table-hover table-main">
				<thead>
			        <tr>
			            <td class="am-text-success">序号</td>
			            <td class="am-text-success">课程</td>
			            <td class="am-text-success">老师</td>
			            <td class="am-text-success">期度</td>
			            <td class="am-text-success">补课时间</td>
			            <td class="am-text-success">操作</td>
			        </tr>
		   		</thead>
		    	<tbody>
					<s:iterator status="statu" value="#request.havaTimeList"
						id="reStudy">
							<tr>
							 	<td class=""><s:property value="#statu.index+1" /></td>
							 	<td class=""><s:property value="#reStudy.courseName"/></td>
							 	<td class=""><s:property value="#reStudy.teacher" /></td>
					            <td class=""> <s:property value="#reStudy.period" /> </td>
					            <td class=""><s:property value="#reStudy.studyTime" /></td>
					            <td class="">
					            	<a type="" href="<%=path%>/reStudy_reSetStuTime?rscid=<s:property value="#reStudy.id"/>">删除</a>
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
		
	</script>

</body>

</html>

