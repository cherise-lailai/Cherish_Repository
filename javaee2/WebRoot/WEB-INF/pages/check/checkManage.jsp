<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

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
<%@ taglib prefix="s" uri="/struts-tags" %>
<body>
<script type="text/javascript">
/* 日期组件使用 */
var checkTime;
 $(function() {
 	$('#checkTime').datepicker().on('changeDate.datepicker.amui', function(event) {
     	checkTime=event.date.valueOf();
     }) 
 });

 $("#year").ready(function(){
	var url = "<%=path%>/cla_getAllActiveyear";
	var args = null;
	$.post(url,args,function(data){
		var arr=data.split(",");
		$("#year").append("<option value='' >"+"请选择"+"</option>");
		for(var i = 0;i<arr.length;i++)
			$("#year").append("<option value='"+arr[i]+"'>"+arr[i]+"</option>");
	},"JSON");
}); 

/* 为了后面的高亮查找，将data作用域提升 */
var stuData;
/* 查询出某堂课的考勤记录 */
function getCheck(){
	var url = "<%=path%>/check_findCheckList";
	/* 获取出所有的参数值 */
	var year = $("#year option:selected").val();
	var className = $("#cla option:selected").text();
	var courseName = $("#course option:selected").val();
	var stuTime = $("#stuTime option:selected").val();
	if(checkTime==null){
		alert("您是不是把日期给忘了！");
		return;
	}
	var args = {"year":year,"className":className,"courseName":courseName,"stuTime":stuTime,"checkTime":checkTime};
	$("#checkTable").html("");
	$.post(url,args,function(data){
		stuData=data;
		console.log(data);
		/* 展示表格 与统计信息*/
		var normal=0;
		var late=0;
		var leaveEarly=0;
		var truancy=0;
		var askForLeave=0;
		
		
		for(var i = 0;i<data.length;i++){
			var state;
			if(data[i].checkState==0){
				normal++;
				state="正常";
			}else if(data[i].checkState==1){
				late++;
				state="迟到";
			}else if(data[i].checkState==2){
				leaveEarly++;
				state="早退";
			}else if(data[i].checkState==3){
				truancy++;
				state="旷课";
			}else if(data[i].checkState==4){
				askForLeave++;
				state="请假";
			}
			var userNameId="unId"+i;
			/* 添加一行 */
			var trHtml="<tr align=\"center\">"+
							"<td class=\"text-info\"><strong id=\""+userNameId+"\">"+data[i].userName+"</strong></td>"+
							"<td><span class=\"text-success\">"+state+"</span></td>"+
						"</tr>";
						
		
			$("#checkTable").append(trHtml);	
		}
		
		//展示统计信息
		$("#normalSpan").text(normal);
		$("#latelSpan").text(late);
		$("#leavelSpan").text(leaveEarly);
		$("#truancylSpan").text(truancy);
		$("#askForLeavelSpan").text(askForLeave);
		
		
	},"JSON");
}

/* 根据用户名高亮显示用户 */
function findByStuName(){
	var stuName=$("#stuName").val();
	for(var i=0;i<stuData.length;i++){
		/* if(stuName==stuData[i].userName){ */
		var index=(stuData[i].userName).indexOf(stuName);
		if(index!=-1){
			$("#unId"+i).css('color',"red");
		}
	}
		
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
			<div class="content-page">
				<!-- Start content -->
				<div class="content">
					<div class="card-box">
						<div class="am-g am-panel" style="background-color: gainsboro">
							<div class="am-u-sm-2">
								<h2>年度:</h2>
								<select data-am-selected id="year" style="width: 50px;" name="year"></select>
							</div>
							<div class="am-u-sm-2">
								<h2>班级:</h2>
								<select data-am-selected id="cla" style="width: 50px;" name="cla"></select>
							</div>
							<div class="am-u-sm-2">
								<h2>课程:</h2>
								<select data-am-selected id="course" name="course">
									<option selected="selected"></option>
								</select>
							</div>
							<div class="am-u-sm-2">
								<h2>上课时间:</h2>
								<select data-am-selected id="stuTime" name="stuTime">
									<option selected="selected"></option>
								</select>
							</div>
							<div class="am-u-sm-2">
								<h2>考勤日期:</h2>
								<p><input type="text"  id="checkTime" class="am-form-field" placeholder="日历组件" data-am-datepicker="{theme: 'success'}" readonly/></p>
							</div>
							<div class="am-u-sm-2">
								<button class="" onclick="getCheck()" value="查询">查询</button>
							</div>
						</div>
						<!-- Row start -->
						<div class="am-g">
							<div class="am-u-sm-10 am-u-md-10"></div>
							<div class="am-u-sm-2 am-u-md-2">
								<div class="am-input-group am-input-group-sm">
									<input id="stuName" type="text" class="am-form-field" placeholder="学生名">
									<span class="am-input-group-btn">
										<button class="am-btn am-btn-default" type="button" onclick="findByStuName()">搜索</button>
									</span>
								</div>
							</div>
						</div>
						<!-- Row end -->
					</div>
					<table class="am-table am-table-striped am-table-hover table-main">
						<thead>
							<tr align="center">
								<td class="text-success" style="font-size: larger">学生</td>
								<td class="text-success" style="font-size: larger">状态</td>
							</tr>
						</thead>
						<tbody id="checkTable">
					
						</tbody>
					</table>
					<div id="total">
						<div class="am-u-sm-4">&nbsp;</div>
						<div class="am-u-sm-4">
							<form class="">
								<fieldset>
									<div class="am-form-group">
										<span class="text-default"><Strong>汇总：</strong>&nbsp;&nbsp;</span>
										<label for="normalSpan">正常：</label>&nbsp; <span id="normalSpan"
											class="text-success">0</span>&nbsp;&nbsp; <label
											for="askForLeavelSpan">请假：</label>&nbsp; <span class="text-success"
											id="askForLeavelSpan">0</span>&nbsp; <label for="latelSpan">迟到：</label>&nbsp;&nbsp;
										<span class="text-danger" id="latelSpan">0</span>&nbsp; <label
											for="leavelSpan">早退：</label>&nbsp;&nbsp; <span
											class="text-danger" id="leavelSpan">0</span>&nbsp; <label
											for="truancylSpan">旷课：</label>&nbsp;&nbsp; <span
											class="text-danger" id="truancylSpan">0</span>&nbsp;
									</div>											
								</fieldset>
							</form>
						</div>
						<div class="am-u-sm-4">&nbsp;</div>

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
<script type="text/javascript">
$("#year").change(function(){
 	var url =  "<%=path%>/user_getClassByYear";
	var year = $("#year option:selected").val();
 	var args = {"year":year}; 
 	$.post(url,args,function(data){
		$("#cla").html("");
		for(var i = 0;i<data.length;i++)
			$("#cla").append("<option value='"+data[i].id+"'>"+data[i].num+"</option>");
	},"JSON"); 
});

/* 班级改变，更新该班的课程 */
$("#cla").change(function(){
 	var url =  "<%=path%>/check_getCourseListBycid";
	var cid = $("#cla option:selected").val();
 	var args = {"cid":cid}; 
  	$.post(url,args,function(data){
		var arr = data.split(',');
		$("#course").html("");
		for(var i = 0;i<arr.length;i++)
			$("#course").append("<option value='"+arr[i]+"'>"+arr[i]+"</option>"); 
	},"JSON"); 
});

/* 课程改变更新上课时间 列表*/
$("#course").change(function(){
 	var url =  "<%=path%>/check_getStuTimeList";
	var courseName = $("#course option:selected").val();
	var cid = $("#cla option:selected").val();
	var courseName=$("#course option:selected").val();
 	var args = {"cid":cid,"courseName":courseName}; 
  	$.post(url,args,function(data){
  		var arr = data.split(',');
		$("#stuTime").html("");
		for(var i = 0;i<arr.length;i++)
			$("#stuTime").append("<option value='"+arr[i]+"'>"+arr[i]+"</option>"); 
	},"JSON");
});

</script>
</body>

</html>

