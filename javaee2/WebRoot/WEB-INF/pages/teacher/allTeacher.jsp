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
			<div class="am-g">
					<div data-am-sticky>
			         	<button class="am-btn am-btn-success am-btn-block" >对喜欢的老师点赞吧！助他/她上榜哦</button>
			    	</div>
			    	<br>
					<!-- Row start -->
					<s:iterator id="teacher" value="#request.teacherList">
						<div class="am-u-md-3">
							<div class="card-box widget-user">
								<div>
									<img src="<%=path%>/<s:property value="#teacher.imgPath"/>"
										class="img-responsive img-circle" alt="teacher">
									<div class="wid-u-info">
										<h4 class="m-t-0 m-b-5 font-600"><s:property value="#teacher.name"/></h4>
										<p class="text-muted m-b-5 font-13"><s:property value="#teacher.email"/></p>
										<a href="<%=path%>/teacher_addScore?tid=<s:property value="#teacher.id"/>">
											<img class="img-responsive img-circle" alt="start" src="<%=path%>/assets/img/start.jpg">
											<small class="text-warning">赞<s:property value="#teacher.score"/><all>
										</a>
									</div>
								</div>
							</div>
						</div>
					</s:iterator>
					
					<!-- Row end -->
				</div>
			
		</div>
		

	<!-- navbar -->
	<a href="admin-offcanvas"
		class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu"
		data-am-offcanvas="{target: '#admin-offcanvas'}"> <!--<i class="fa fa-bars" aria-hidden="true"></i>-->
	</a>

	<script type="text/javascript"
		src="<%=path%>/assets/js/jquery-2.1.0.js"></script>
	<script type="text/javascript" src="<%=path%>/assets/js/amazeui.min.js"></script>
	<script type="text/javascript" src="<%=path%>/assets/js/app.js"></script>
	<script type="text/javascript" src="<%=path%>/assets/js/blockUI.js"></script>
	<script type="text/javascript"
		src="<%=path%>/assets/js/charts/echarts.min.js"></script>
	<script type="text/javascript"
		src="<%=path%>/assets/js/charts/indexChart.js"></script>
</body>

</html>

