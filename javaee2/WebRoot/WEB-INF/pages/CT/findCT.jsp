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
	//页面加载判断当前用户是否是学生，如果是学生则给出学生的最近一周的反馈
	$(function(){
		var role='<s:property value="#session.permission" />'
		//是学生
		if(role=="user"){
			var url = "<%=path%>/eva_getListOneWeek";
			var args=null;
			$.post(url,args,function(data){
				var detailHtml="";
				for(var i=0;i<data.length;i++){
			   		detailHtml+=
			   		"<tr><td><span>"+data[i].courseName+"</span></td>"+
			   		"<td><span>"+data[i].teacher+"</span></td>"+
			   		"<td><span>"+new Date(data[i].date).getDate()+"号</span></td>"+
					"<td><span>"+data[i].feedback+"</span></td></tr>"; 
	   			}
	  			$("#datailTable").html(detailHtml);
	  			$("#feedbackModal").modal();
			},"JSON");
		}
	});
		
	$(function(){
		var role='<s:property value="#session.permission" />'
		//是学生
		if(role=="user"){
				var url = "<%=path%>/reStudy_getReStuPreHalfMonth";
				var args=null;
				$.post(url,args,function(data){
					var detailHtml2="";
					if(data.length!=0){
						for(var i=0;i<data.length;i++){
				   			 detailHtml2+=
					   		"<tr><td><span>"+data[i].course+"</span></td>"+
					   		"<td><span>"+data[i].teacher+"</span></td>"+
					   		"<td><span>"+data[i].reStuNotice+"</span></td>"+
							"<td><span>"+data[i].reStuNum+"</span></td></tr>";
			  			}
			 			$("#reStuDatailTable2").html(detailHtml2);
			 			/* $("#reStuModal").modal(); */
					}
				},"JSON");
			}
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
			<h1>上课时间</h1>
			<table class="am-table am-table-striped am-table-hover table-main">
				<s:if test="#session.permission=='teacher'">
					<thead>
				        <tr>
				            <td class="">序号</td>
				            <td class="">班级</td>
				            <td class="">老师</td>
				            <td class="">课程</td>
				            <td class="">上课时间</td>
				        </tr>
			   		 </thead>
			    	<tbody>
						<s:iterator status="statu" value="#request.teacherCTList"
							id="ct">
							<tr>
							 	<td class=""><s:property value="#statu.index+1" /></td>
							 	<td class=""><s:property value="#ct.stuClass.num"/></td>
							 	<td class=""><s:property value="#ct.teacher" /></td>
					            <td class=""><s:property value="#ct.courseName" /></td>
					            <td class=""><s:property value="#ct.time"/></td>
					            
				        	</tr>
						</s:iterator>
					</tbody>
				</s:if>
			    <s:if test="#session.permission=='user'">
					<thead>
				        <tr>
				            <td class="">序号</td>
				            <td class="">老师</td>
				            <td class="">课程</td>
				            <td class="">上课时间</td>
				        </tr>
			  		</thead>
			    <tbody>
					<s:iterator status="statu" value="#request.userCTList"
						id="ct">
						<tr>
						 	<td class=""><s:property value="#statu.index+1" /></td>
						 	<td class=""><s:property value="#ct.teacher" /></td>
				            <td class=""><s:property value="#ct.courseName" /></td>
				            <td class=""><s:property value="#ct.time"/></td>
			        	</tr>
					</s:iterator>
			    </tbody>
			   </s:if>
			</table>	
		</div>
		
	<!-- 反馈信息提示模态框 -->
	<div class="am-modal am-modal-alert" tabindex="-1" id="feedbackModal">
	  <div class="am-modal-dialog">
	    <div class="am-modal-hd am-text-success">温馨提示</div>
	    <div class="am-modal-bd">
			<span class="am-text-primary">在最近一周内，您的任课老师对您的反馈如下所示</span>
			<table class="am-table" >
			 	<thead>
			        <tr>
			            <th align="center">课程</th>
			            <th align="center">老师</th>
			            <th align="center">日期</th>
			            <th align="center">反馈</th>
			        </tr>
			    </thead>
			    <tbody id="datailTable">

			 	</tbody>
			</table>
			<span class="am-text-primary">补课安排</span>
			<table class="am-table" >
			 	<thead>
			        <tr>
			            <th align="center">课程</th>
			            <th align="center">老师</th>
			            <th align="center">通知</th>
			            <th align="center">节数</th>
			        </tr>
			    </thead>
			    <tbody id="reStuDatailTable2">

			 	</tbody>
			</table>
	    </div>
	    <div class="am-modal-footer">
	      <button class="am-modal-btn">关闭</button>
	    </div>
	  </div>
	</div>

	<!-- 补课信息提示模态框 -->
	<div class="am-modal am-modal-alert" style="margin-right: 10px" tabindex="-1" id="reStuModal">
	  <div class="am-modal-dialog">
	    <div class="am-modal-hd am-text-success">温馨提示</div>
	    <div class="am-modal-bd">
			<span class="am-text-primary">补课安排</span>
			<table class="am-table" >
			 	<thead>
			        <tr>
			            <th align="center">课程</th>
			            <th align="center">老师</th>
			            <th align="center">通知</th>
			            <th align="center">节数</th>
			        </tr>
			    </thead>
			    <tbody id="reStuDatailTable">

			 	</tbody>
			</table>
	    </div>
	    <div class="am-modal-footer">
	      <button class="am-modal-btn">关闭</button>
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

