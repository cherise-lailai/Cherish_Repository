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

<script type="text/javascript"
		src="<%=path%>/assets/js/jquery-2.1.0.js"></script>
<style>

 </style>
</head>
<body>
<script>
/* 好像必须放在前面 */
//回显老师的技能
$(".course").ready(function(){
	var url = "<%=path%>/course_getAllCourse";
	var args = null;
	$.post(url,args,function(data){
		$(".course").html("");
		var courseArr= data.split(",");
		/* 添加一个避免第一个是阅读理解选择，用户没有改变导致老师级联无法触发，所以添加一个无效option，用户解决 */
		$(".course").append("<option value='' >"+"请选择"+"</option>");
		for(var i = 0;i<courseArr.length;i++)
			$(".course").append("<option value='"+courseArr[i]+"'>"+courseArr[i]+"</option>"); 
	},"JSON");	
});

/* CT对象 */
 function ctObject(cid,course,teacher,xingqi,stuTime){
       this.cid=cid;
       this.course=course;
       this.teacher=teacher;
       this.xingqi=xingqi;
       this.stuTime=stuTime;
   }
   
  /* 上传CT */ 
function  addClassAndCT() {
	   var objarr = new Array();
	   for (var i = 1; i <= 5; i++) {
		   var mySelect=$("#select"+i).is(':checked');
	   	   if(mySelect==false){continue;}
	       var course = $('#course' + i + ' option:selected').val();
	       var teacher = $('#teacher' + i + ' option:selected').val();
	       var xingqi = $('#xingqi' + i + ' option:selected').val();
	       var stuTime = $('#stuTime' + i + ' option:selected').val();
	       var obj=new ctObject("course"+i,course,teacher,xingqi,stuTime);
	       objarr.push(obj);
	   }
       console.log(objarr);
       var json =JSON.stringify(objarr);
       console.log(json);
       
       var className=$("#className").val();
       var price=$("#price").val();

      
 	   var url = "<%=path%>/cla_openClass";
	   var args = {"ctJson":json,"className":className,"price":price};
	   $.post(url,args,function(data){
	   		alert(data);
		 <%-- 	window.location.href="<%=path%>/cla_all"; --%>
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
		<div class="content-page" >
			<div>
			    <div class="am-g am-panel " style="background-color: gainsboro">
			        <div>
			            <div class="am-u-sm-4">&nbsp;</div>
			            <div class="am-u-sm-4">
			                <form action="" class="am-form" enctype="multipart/form-data">
			                    <fieldset>
			                        <div class="am-form-group">
			                            <label for="username" class="am-text-success am-text-lg">班级名：</label>
			                            <input type="text" id="className" minlength="3" placeholder="输入班级名（至少 3 个字符）" required/>
			                        </div>
			                        <div class="am-form-group">
			                            <label for="price"class="am-text-success am-text-lg">学费：</label>
			                            <input type="text" id="price" placeholder="输入学费" required/>
			                        </div>
			                    </fieldset>
			                </form>
			            </div>
			            <div class="am-u-sm-4">&nbsp;</div>
			        </div>
			    </div>
			
			    <div class="am-g am-panel " style="background-color: gainsboro">
			        <div>
			            <div data-am-sticky>
			                <button class="am-btn am-btn-danger am-btn-block">每个班级开设课程最多五门</button>
			            </div>
			
			            <div class="am-u-sm-2">
			            	
			                <form action="" class="am-form" enctype="multipart/form-data">
			                    <fieldset>
						            <div>
						            	<label class="am-checkbox">
     										 <input id="select1" type="checkbox" value="" data-am-ucheck>选择
   										</label>
			                            <label for="course1" class="am-text-success">课程：</label>
			                            <select class="course" id="course1" data-am-selected><option  selected="selected"></option></select>
			                            <label for="teacher1" class="am-text-success">老师：</label>
			                            <select id="teacher1" ><option  selected="selected"></option></select>
			                            <label for="xingqi1"class="am-text-success">选择星期：</label>
			                            <select  id="xingqi1"  data-am-selected>
			                                <option value="1">星期一</option>
			                                <option value="2">星期二</option>
			                                <option value="3">星期三</option>
			                                <option value="4">星期四</option>
			                                <option value="5">星期五</option>
			                                <option value="6">星期六</option>
			                                <option value="7">星期七</option>
			                            </select>
			                            <label for="stuTime1"class="am-text-success">选择上课时间：</label>
			                            <select id="stuTime1"  data-am-selected>
			                                <option value="1">8:30~9:30</option>
			                                <option value="2">10:00~11:00</option>
			                                <option value="3">14:30~15:30</option>
			                                <option value="4">16:00~17:00</option>
			                            </select>
			                        </div>
			                    </fieldset>
			                </form>
			            </div>
			            <div class="am-u-sm-2">
			                <form action="" class="am-form" enctype="multipart/form-data">
			                    <fieldset>
			                        <!--课程安排选择-->
			                        <div>
			                        	<label class="am-checkbox">
     										 <input id="select2" type="checkbox" value="" data-am-ucheck>选择
   										</label>
			                            <label for="course2"class="am-text-success">课程：</label>
			                            <select data-am-selected id="course2" class="course">
			                            </select>
			                            <label for="teacher2">老师：</label>
			                            <select  id="teacher2"><option  selected="selected"></option></select>
			                            <label for="xingqi2"class="am-text-success">选择星期：</label>
			                            <select data-am-selected id="xingqi2">
			                                <option value="1">星期一</option>
			                                <option value="2">星期二</option>
			                                <option value="3">星期三</option>
			                                <option value="4">星期四</option>
			                                <option value="5">星期五</option>
			                                <option value="6">星期六</option>
			                                <option value="7">星期七</option>
			                            </select>
			                            <label for="stuTime2"class="am-text-success">选择上课时间：</label>
			                            <select data-am-selected id="stuTime2">
			                                <option value="1">8:30~9:30</option>
			                                <option value="2">10:00~11:00</option>
			                                <option value="3">14:30~15:30</option>
			                                <option value="4">16:00~17:00</option>
			                            </select>
			                        </div>
			                    </fieldset>
			                </form>
			            </div>
			            <div class="am-u-sm-2">
			                <form action="" class="am-form" enctype="multipart/form-data">
			                    <fieldset>
			                        <!--课程安排选择-->
			                        <div>
			                        	<label class="am-checkbox">
     										 <input id="select3" type="checkbox" value="" data-am-ucheck>选择
   										</label>
			                            <label for="course3"class="am-text-success">课程：</label>
			                            <select data-am-selected id="course3" class="course">
			                            </select>
			                            <label for="teacher3">老师：</label>
			                            <select id="teacher3"><option  selected="selected"></option></select>
			                            <label for="xingqi3"class="am-text-success">选择星期：</label>
			                            <select data-am-selected id="xingqi3">
			                                <option value="1">星期一</option>
			                                <option value="2">星期二</option>
			                                <option value="3">星期三</option>
			                                <option value="4">星期四</option>
			                                <option value="5">星期五</option>
			                                <option value="6">星期六</option>
			                                <option value="7">星期七</option>
			                            </select>
			                            <label for="stuTime3"class="am-text-success">选择上课时间：</label>
			                            <select data-am-selected id="stuTime3">
			                                <option value="1">8:30~9:30</option>
			                                <option value="2">10:00~11:00</option>
			                                <option value="3">14:30~15:30</option>
			                                <option value="4">16:00~17:00</option>
			                            </select>
			                        </div>
			                    </fieldset>
			                </form>
			            </div>
			            <div class="am-u-sm-2">
			                <form action="" class="am-form" enctype="multipart/form-data">
			                    <fieldset>
			                        <!--课程安排选择-->
			                        <div>
			                        	<label class="am-checkbox">
     										 <input id="select4" type="checkbox" value="" data-am-ucheck>选择
   										</label>
			                            <label for="course4"class="am-text-success">课程：</label>
			                            <select data-am-selected id="course4" class="course">
			                            </select>
			                            <label for="teacher4">老师：</label>
			                            <select  id="teacher4"><option  selected="selected"></option></select>
			                            <label for="xingqi4"class="am-text-success">选择星期：</label>
			                            <select data-am-selected id="xingqi4">
			                                <option value="1">星期一</option>
			                                <option value="2">星期二</option>
			                                <option value="3">星期三</option>
			                                <option value="4">星期四</option>
			                                <option value="5">星期五</option>
			                                <option value="6">星期六</option>
			                                <option value="7">星期七</option>
			                            </select>
			                            <label for="stuTime4"class="am-text-success">选择上课时间：</label>
			                            <select data-am-selected id="stuTime4">
			                                <option value="1">8:30~9:30</option>
			                                <option value="2">10:00~11:00</option>
			                                <option value="3">14:30~15:30</option>
			                                <option value="4">16:00~17:00</option>
			                            </select>
			                        </div>
			                    </fieldset>
			                </form>
			            </div>
			            <div class="am-u-sm-2">
			                <form action="" class="am-form" enctype="multipart/form-data">
			                    <fieldset>
			                        <!--课程安排选择-->
			                        <div>
			                        	<label class="am-checkbox">
     										 <input id="select5" type="checkbox" value="" data-am-ucheck>选择
   										</label>
			                            <label for="course5"class="am-text-success">课程：</label>
			                            <select data-am-selected id="course5" class="course">
			                            </select>
			                            <label for="teacher5">老师：</label>
			                            <select  id="teacher5"><option  selected="selected"></option></select>
			                            <label for="xingqi5"class="am-text-success">选择星期：</label>
			                            <select data-am-selected id="xingqi5">
			                                <option value="1">星期一</option>
			                                <option value="2">星期二</option>
			                                <option value="3">星期三</option>
			                                <option value="4">星期四</option>
			                                <option value="5">星期五</option>
			                                <option value="6">星期六</option>
			                                <option value="7">星期七</option>
			                            </select>
			                            <label for="stuTime5"class="am-text-success">选择上课时间：</label>
			                            <select data-am-selected id="stuTime5">
			                                <option value="1">8:30~9:30</option>
			                                <option value="2">10:00~11:00</option>
			                                <option value="3">14:30~15:30</option>
			                                <option value="4">16:00~17:00</option>
			                            </select>
			                        </div>
			                    </fieldset>
			                </form>
			            </div>
			            <div class="am-u-sm-2">
			            	<h2>温馨提示：</h2>
			            	<p><strong class="am-text-success">选课完别忘了勾选你要的课程哦！选错没关系，只要不勾选就可以啦！</strong></p>
			          	</div>
			        </div>
			    </div>
			    <div data-am-sticky>
			        <button id="addCT" onclick="addClassAndCT();" class="am-btn am-btn-success am-btn-block" style="height: 50px;"><span class="am-text-lg">提交</span></button>
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
<!-- 因为html和js的执行顺序关系，因此级联列表相关的js文件放置在此处  -->
<script type="text/javascript">
/* 级联老师下拉框 */
$("#course1").change(function(){
 	var url =  "<%=path%>/teacher_getFixTeacher";
	var cname = $("#course1 option:selected").text();
 	var args = {"cname":cname}; 
 	$.post(url,args,function(data){
		var arr = data.split(',');
		$("#teacher1").html("");
		for(var i = 0;i<arr.length;i++)
			$("#teacher1").append("<option>"+arr[i]+"</option>");
	},"JSON"); 
});

$("#course2").change(function(){
 	var url =  "<%=path%>/teacher_getFixTeacher";
	var cname = $("#course2 option:selected").text();
 	var args = {"cname":cname}; 
 	$.post(url,args,function(data){
		var arr = data.split(',');
		$("#teacher2").html("");
		for(var i = 0;i<arr.length;i++)
			$("#teacher2").append("<option>"+arr[i]+"</option>");
	},"JSON"); 
});

$("#course3").change(function(){
 	var url =  "<%=path%>/teacher_getFixTeacher";
	var cname = $("#course3 option:selected").text();
 	var args = {"cname":cname}; 
 	$.post(url,args,function(data){
		var arr = data.split(',');
		$("#teacher3").html("");
		for(var i = 0;i<arr.length;i++)
			$("#teacher3").append("<option>"+arr[i]+"</option>");
	},"JSON"); 
});

$("#course4").change(function(){
 	var url =  "<%=path%>/teacher_getFixTeacher";
	var cname = $("#course4 option:selected").text();
 	var args = {"cname":cname}; 
 	$.post(url,args,function(data){
		var arr = data.split(',');
		$("#teacher4").html("");
		for(var i = 0;i<arr.length;i++)
			$("#teacher4").append("<option>"+arr[i]+"</option>");
	},"JSON"); 
});
$("#course5").change(function(){
 	var url =  "<%=path%>/teacher_getFixTeacher";
	var cname = $("#course5 option:selected").text();
 	var args = {"cname":cname}; 
 	$.post(url,args,function(data){
		var arr = data.split(',');
		$("#teacher5").html("");
		for(var i = 0;i<arr.length;i++)
			$("#teacher5").append("<option>"+arr[i]+"</option>");
	},"JSON"); 
});
</script>
</html>

