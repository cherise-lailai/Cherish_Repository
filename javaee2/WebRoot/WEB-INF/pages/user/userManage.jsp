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

<%@ include file="/com/userQueryCondition.jsp" %>
</head>
<body>
<script type="text/javascript">
	$(function(){
		$("#pageNo").change(function(){
			var val = $(this).val();
			val = $.trim(val);
			//1.校验 val 是否为数字
			var reg = /^\d+$/g;
			var flag = false;
			if(reg.test(val)){
				var pageNo = parseInt(val);
				if(val >= 1 && val <= parseInt("${userPage.totalPageNumber}")){
					flag = true;
				}
			}
			
			if(!flag){
				alert('输入的不是合法的页码！');
				$(this).val("");
				return;
			}
			//2.校验 val 是否在一个合法范围内，即1~totalPageNumber
			
			//3.页面跳转
			var href = "user_all?pageStrNo=" + pageNo+ "&"+ $(":hidden").serialize() ; 
			window.location.href = href;
		});
	})
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
								<h2 style="float: left; display: block">-------------</h2>
								&nbsp;&nbsp;&nbsp;
								<h2 class="text-success" style="float: left"><hr></h2>
							</div>
							<div class="am-u-sm-2">
								<h2 style="float: left; display: block">--------------</h2>
								&nbsp;&nbsp;&nbsp;
								<h2 class="text-success" style="float: left"><hr></h2>
							</div>
						</div>
						<!-- Row start -->
						<div class="am-g">
							<div class="am-u-sm-2 am-u-md-2">
								<a class="need" href="<%=path%>/user_exportUserInfo?"><button class="am-btn am-btn-default" type="button">导出信息</button></a>	
							</div>
							<div class="am-u-sm-8 am-u-md-8"></div>
							<div class="am-u-sm-2 am-u-md-2">
								<div class="am-input-group am-input-group-sm">
									<form action="<%=path%>/user_all" method="post">
										<input type="text" name="classNum" class="am-form-field" value="<s:property value="#classConditon"/>" placeholder="班级">
										<input type="text" name="stuName" class="am-form-field" value="<s:property value="#nameConditon"/>" placeholder="学生名">
										<span class="am-input-group-btn">
											<button class="am-btn am-btn-default" type="submit">搜索</button>
										</span>
									</form>
								</div>
							</div>
						</div>
						<!-- Row end -->
					</div>
					<table class="am-table am-table-striped am-table-hover table-main">
						<thead>
							<tr align="center">
								<td class="text-success" style="font-size: larger">头像</td>
								<td class="text-success" style="font-size: larger">学生</td>
								<td class="text-success" style="font-size: larger">邮箱</td>
								<td class="text-success" style="font-size: larger">班级</td>
								<td class="text-success" style="font-size: larger">操作</td>
							</tr>
						</thead>
						<tbody>
							<s:iterator id="user" value="#request.userPage.list">
								<tr align="center">
									<td class=""><a href="<%=path%>/user_downImg?uid=<s:property value="#user.id"/>"><img style="width: 70px;height: 70px" src="<%=path%>/<s:property value="#user.imgPath"/>"
										alt="未知"></img></a></td>
									<td><span class="text-success"><s:property value="#user.name"/></span></td>
									<td><span class="text-success"><s:property value="#user.email"/></span></td>
									<td><span class="text-success"><s:property value="#user.classID"/></span></td>
									<td><a href="<%=path%>/user_update?uid=<s:property value="#user.id"/>"><button >修改</button></a>
									<a href="<%=path%>/user_delete?uid=<s:property value="#user.id"/>"><button >删除</button></a></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
							
				<div class="am-g">
						<div class="am-u-sm-1 ">&nbsp;	</div>
						<div class="am-u-sm-10 ">
							<ul data-am-widget="pagination" class="am-pagination am-pagination-default">
							     <li class="">
							       <a href="javascript:void(0);" class="">当前第<s:property value="#request.userPage.pageNo"/>页</a>
							     </li>
							     <li class="">
							       <a href="javascript:void(0);" class="">总页数<s:property value="#request.userPage.totalPageNumber"/>页</a>
							     </li>
							     <li class="">
							       <a href="javascript:void(0);" class="">共<s:property value="#request.userPage.totalItemNumber"/>条记录</a>
							     </li>
							      <li class="am-pagination-first ">
							        <a class="need" href="<%=path%>/user_all?pageStrNo=1" class="">第一页</a>
							      </li>
							      <s:if test="#request.userPage.hasPrev">
									<li class="am-pagination-prev "><a class="need" href="<%=path%>/user_all?pageStrNo=<s:property value="#request.userPage.prevPage"/>" class="">上一页</a>	</li>
								  </s:if>
						          <s:iterator status="sta" begin="1" end="#request.userPage.totalPageNumber">
						          		<li><a class="need" href="<%=path%>/user_all?pageStrNo=<s:property value="#sta.index+1"/>"><s:property value="#sta.index+1"/></a></li> 
						          </s:iterator>
									<s:if test="#request.userPage.hasNext">
									 	<li class="am-pagination-next "><a class="need" href="<%=path%>/user_all?pageStrNo=<s:property value="#request.userPage.nextPage"/>" class="">下一页</a></li>	
									</s:if>
							      <li class="am-pagination-last ">
							        <a class="need" href="<%=path%>/user_all?pageStrNo=<s:property value="#request.userPage.totalPageNumber"/>">最末页</a>
							      </li>
							      <font>转到&nbsp;&nbsp;</font><input type="text" id="pageNo" style="width: 25px"/>
	 						 </ul>
						</div>
						<div class="am-u-sm-1">&nbsp;	</div>
					</div>
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

