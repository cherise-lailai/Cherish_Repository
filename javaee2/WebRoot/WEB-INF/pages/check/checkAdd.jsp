<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
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
/* 老师教授的班级列表查询 */
 $("#cla").ready(function(){
	var url = "<%=path%>/check_getClassByDC";
	var args = null;
	$.post(url,args,function(data){
		$("#cla").append("<option value='' >"+"请选择"+"</option>");
		for(var i = 0;i<data.length;i++)
			$("#cla").append("<option value='"+data[i].cid+"'>"+data[i].cname+"</option>");
	},"JSON");
}); 



/* 页面年度值获取 */
$(function(){
	var date=new Date;  
	var year=date.getFullYear();   
	$("#year").text(year);
});

/* 日期组件使用 */
var checkTime;
 $(function() {
 	$('#checkTime').datepicker().on('changeDate.datepicker.amui', function(event) {
     	checkTime=event.date.valueOf();
     }) 
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
			<div>
				<div class="am-g am-panel" style="background-color: gainsboro">
					<div class="am-u-sm-2">
						<h2 style="float: left; display: block">年度:</h2>
						&nbsp;&nbsp;&nbsp;
						<h2 class="text-success" id="year" style="float: left">听力</h2>
					</div>
					<div class="am-u-sm-3">
						<h2 style="float: left; display: block">班级:</h2>
						<select data-am-selected id="cla" style="width: 50px;" name="cla"></select>
					</div>
					
					
				</div>
			</div>
			<table class="am-table am-table-striped am-table-hover table-main">
				<thead>
					<tr align="center">
						<td class="text-success" style="font-size: larger">学生</td>
						<td class="text-success" style="font-size: larger">状态</td>
						<td class="text-success" style="font-size: larger">简单反馈</td>
					</tr>
				</thead>
				<tbody id="stuTable">

				</tbody>
			</table>
			<div>
				<div class="am-u-sm-10">
					<div class="am-u-sm-3">
						<h2>课程:</h2>
						<select data-am-selected id="course" name="course">
							<option selected="selected"></option>
						</select>
					</div>
					<div class="am-u-sm-3">
						<h2 >上课时间:</h2>
						<select data-am-selected id="stuTime" name="stuTime"></select>
					</div>
					<div class="am-u-sm-2">
						<h2 >考核老师:</h2>
						<div class="text-success" style="float: left"><s:property value="#session.currentPeople.name"/></div>
					</div>
					<div class="am-u-sm-2">
						<h2>考勤日期:</h2>
						<p><input type="text"  id="checkTime" class="am-form-field" placeholder="日历组件" data-am-datepicker="{theme: 'success'}" readonly/></p>
					</div>
				</div>
				<div class="am-u-sm-2">
					<h2>&nbsp;</h2>
					<button class="btn-lg am-btn-success" onclick="addCheck()">提交:</button>
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
/* 班级改变，更新该班的课程 */
$("#cla").change(function(){
 	var url =  "<%=path%>/check_getCourseByCid";
	var cid = $("#cla option:selected").val();
 	var args = {"cid":cid}; 
  	$.post(url,args,function(data){
		var arr = data.split(',');
		$("#course").html("");
		for(var i = 0;i<arr.length;i++)
			$("#course").append("<option value='"+arr[i]+"'>"+arr[i]+"</option>"); 
	},"JSON"); 
});
/* 班级改变更新该班的学生列表 */
$("#cla").change(function(){
 	var url =  "<%=path%>/cla_getStudentsByCid";
	var cid = $("#cla option:selected").val();
 	var args = {"cid":cid}; 
  	$.post(url,args,function(data){
  	 	$("#stuTable").html(""); 
		stuNum=data.length;
		/* var arr=eval(data); 不需要转对象也行，格式一样 */
  	 	for(var i=0;i<data.length;i++){
/* 			var stateHtml=
			 	"<tr align=\"center\">"+
					"<td><span class=\"text-success\" >"+data[i].name+"</span></td>"+
					"<td>"+
						"<select data-am-selected>"+
							"<option value=\"0\" selected=\"selected\">正常</option>"+
							"<option value=\"1\">迟到</option>"+
							"<option value=\"2\">早退</option>"+
							"<option value=\"3\">旷课</option>"+
							"<option value=\"4\">请假</option>"+
						"</select>"+
					"</td>"+
				"</tr>"; */
			
			var selectId="select"+i;
			var stuId="stu"+i;
			var feedbackId="feedback"+i;
			var stateHtml=
			 	"<tr align=\"center\">"+
					"<td><span class=\"text-success\"  id=\""+stuId+"\" >"+data[i].name+"</span></td>"+
					"<td>"+
						"<select data-am-selected id=\""+selectId+"\" >"+
							"<option value=\"0\">正常</option>"+
							"<option value=\"1\">迟到</option>"+
							"<option value=\"2\">早退</option>"+
							"<option value=\"3\">旷课</option>"+
							"<option value=\"4\">请假</option>"+
						"</select>"+
					"</td>"+
					"<td><input  type=\"text\" style=\"width:250px\" placeholder=\"认真、走神、吃零食、打岔、睡觉等等描述信息\" id=\""+feedbackId+"\" /></td>"+
				"</tr>";			
  	 		$("#stuTable").append(stateHtml);
  	 	}

	},"JSON"); 
});


/* 课程改变更新上课时间 列表*/

$("#course").change(function(){
 	var url =  "<%=path%>/check_getStudyTimeByCourse";
	var courseName = $("#course option:selected").val();
	var cid = $("#cla option:selected").val();
 	var args = {"cid":cid,"courseName":courseName}; 
  	$.post(url,args,function(data){
		$("#stuTime").html("");
		$("#stuTime").append("<option value='"+data+"'>"+data+"</option>"); 
	},"JSON");
});


/* 查询到的学生人数 */
var stuNum;
/*提交考勤信息  */
function addCheck(){
	/* 获得班级id、班级名称、上课时间、考核日期 */
	var cid = $("#cla option:selected").val();
	var courseName = $("#course option:selected").val();
	var stuTime= $("#stuTime option:selected").val();
/* 	alert(cid);
	alert(courseName);
	alert(stuTime);
	alert(checkTime); */
	
	
	/* checkRecode对象 */
 	function checkRecodeObj(stuName,checkState,feedback){
       this.stuName=stuName;
       this.checkState=checkState;
       this.feedback=feedback;
   	}
	
	/* 获得所有学生的考勤记录 */
	/* alert(stuNum); */
	
	/* 构建出考核结果的对象数组 */
	var objarr = new Array();
 	for(var i=0;i<stuNum;i++){
		var selectVal= $('#select'+ i +' option:selected').val();
		var stuVal=$('#stu'+i).text();
		var feedbackStr=$('#feedback'+i).val();
	/* 	alert(feedbackStr); */
	    var obj=new checkRecodeObj(stuVal,selectVal,feedbackStr);
	    objarr.push(obj);
	   }
     console.log(objarr);
     var json =JSON.stringify(objarr);
     console.log(json);
      
    /* 传递到后台 */
    var url =  "<%=path%>/check_addCheck";
 	var args = {"cid":cid,"courseName":courseName,"stuTime":stuTime,"checkTime":checkTime,"checkRecordeJson":json}; 
  	$.post(url,args,function(data){
		alert(data);
	},"JSON");
       
       
}
</script>

</body>

</html>

