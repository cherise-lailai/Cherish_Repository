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
<style>
</style>
</head>
<body>
<script>
function deleteCla(cid){
	var url = "<%=path%>/cla_deleteClass";
	var args = {"cid":cid};
	$.post(url,args,function(data){
		window.location.href="<%=path%>/cla_all";
	},"JSON");
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
			<div>
				<div class="am-u-sm-1">&nbsp;</div>
				<div class="am-u-sm-10">
					<ul class="am-nav am-navbar-nav am-navbar-left">
						<li><h4 class="page-title">开班信息</h4></li>
					</ul>
					<div>
						<div class="card-box">
							<div class="am-scrollable-horizontal am-text-ms"
								style="font-family: '微软雅黑';">
								<table class="am-table   am-text-nowrap">
									<thead>
										<tr>
											<th>行号</th>
											<th>班级</th>
											<th>期度<small>/年</small></th>
											<th>课程</th>
											<th>指导老师</th>
											<th>价格</th>
											<th>删除</th>
										</tr>
									</thead>
									<tbody>
										<s:iterator id="openClass" value="#request.openClassList"
											status="sta">
											<tr>
												<td><s:property value="#sta.index+1" /><input
													type="hidden" value="<s:property value="#openClass.cid"/>"></td>
												<td><s:property value="#openClass.cname" /></td>
												<td><s:property value="#openClass.year" /></td>
												<td><s:property value="#openClass.courseName" /></td>
												<td><s:property value="#openClass.teachers" /></td>
												<td><s:property value="#openClass.price" /></td>
												<td><input class="am-btn am-btn-primary"
													onclick="deleteCla('<s:property value="#openClass.cid"/>');" type="button"
													value="删除"></td>
											</tr>
										</s:iterator>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<div class="am-u-sm-1">&nbsp;</div>
			</div>
		</div>
	</div>
	
<div class="am-modal am-modal-alert" tabindex="-1" id="resultAlert">
  <div class="am-modal-dialog">
    <div class="am-modal-hd">操作结果</div>
    <div class="am-modal-bd" id="showResultInfo">
      
    </div>
    <div class="am-modal-footer">
      <span class="am-modal-btn">确定</span>
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

