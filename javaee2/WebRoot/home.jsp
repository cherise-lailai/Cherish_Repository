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
/*转义字符解码  */
function HTMLDecode(text) { 
    var temp = document.createElement("div"); 
    temp.innerHTML = text; 
    var output = temp.innerText || temp.textContent; 
    temp = null; 
    return output; 
} 
/* 动态渲染出html标签 */
$(function(){ 
	var intorHtmlCode='<s:property value="#request.introContent.content"/>';
	var styleHtmlCode='<s:property value="#request.styleContent.content"/>';
	var noticeHtmlCode='<s:property value="#request.noticeContent.content"/>';
	var introHtmlDecode=HTMLDecode(intorHtmlCode);
	var styleHtmlDecode=HTMLDecode(styleHtmlCode);
	var noticeDecode=HTMLDecode(noticeHtmlCode);
	$("#introShow").html(introHtmlDecode);
 	$("#styleShow").html(styleHtmlDecode);
 	$("#noticeShow").html(noticeDecode); 
});

function detailClaInfo(cid){
	var url = "<%=path%>/home_getClassBycid";
	var args = {"cid":cid};
	$.post(url,args,function(data){	
		var teaArr =data.teachers.split(";");
		var cnArr =data.courseName.split(";");
		var stuArr =data.studyTime.split(";");
		var detailHtml="";
	   	for(var i=0;i<cnArr.length;i++){
	   		detailHtml+=
	   		"<tr><td><span id=\"teaDetail\">"+teaArr[i]+"</span></td>"+
	   		"<td><span id=\"cnDetail\">"+cnArr[i]+"</span></td>"+
			"<td><span id=\"stuDetail\">"+stuArr[i]+"</span></td></tr>"; 
	   	}
	   	$("#datailTable").html(detailHtml);
	},"JSON");
}

/* 点击登入打开登入模态框 */
function openLoginMotal(){
 	$("#loginModal").modal();
}

/* 登入方法 */
function login(){
	var username=$("#username").val();
	var password=$("#passowrd").val();
	var role= $("input[type='radio']:checked").val();
	var url = "<%=path%>/login_myLogin";
	var args = {"username":username,"password":password,"role":role};
	$.post(url,args,function(data){	
		if(data=="true"){
			alert('欢迎<s:property value="#session.currentPeople.name"/>登入！');
			window.location.href="<%=path%>/home_all";
		}else if(data="false"){
			alert("登入失败！请再次核对信息！");
		}
	},"JSON");
}

/* 页面加载就从判断session中是否存在登入用户 */
$(function(){
	$("#currpeopleImg").html("");
	$("#currPeoName").text("");
	var peopleName='<s:property value="#session.currentPeople.name"/>'; 
	console.log(typeof(peopleName));
	console.log("peopleName"+peopleName);
	var headImgPath='<%=path%><s:property value="#session.currentPeople.imgPath"/>'; 
/* 	alert(peopleName);
	alert(headImgPath); */
 	if(peopleName!=""){
 		var welcome="你好：";
		$("#currPeoName").text(welcome+peopleName);
 		var imgHtml;
 		/* 商家没有单独的头像，使用默认头像 */
	 	if(headImgPath=="/javaee2"){
		 	imgHtml="<img class=\"img-responsive img-circle\" style=\"height:50px;\" src=\"<%=path%>/assets/img/monkey.jpg\"/>"; 
		/* 	alert(imgHtml); */
		} else{
			imgHtml="<img class=\"img-responsive img-circle\" style=\"height:50px;\" src=\""+headImgPath+"\"/>";
		}
		$("#currpeopleImg").append(imgHtml);
		var extiHtml="<button class=\"am-btn am-btn-danger\" style=\"float:left;\" onclick=\"exitLogin()\">退出登录</button>";
		$("#currpeopleImg").append(extiHtml);
	}else if(peopleName==""){
		var openMotalBtnHtml="<a href=\"javascript:void(0)\"><button onclick=\"openLoginMotal()\""+
		"type=\"button\" class=\"am-btn am-btn-success am-radius\" >登入</button></a>";
		$("#currpeopleImg").append(openMotalBtnHtml);
	}
	
});
/* 退出登入 */
function exitLogin(){
	var url = "<%=path%>/login_exitLogin";
	var args =null;
	$.post(url,args,function(data){	
		if(data=="true"){
			$("#currpeopleImg").html("");
			$("#currPeoName").text("");
			var openMotalBtnHtml="<a href=\"javascript:void(0)\"><button onclick=\"openLoginMotal()\""+
			"type=\"button\" class=\"am-btn am-btn-success am-radius\" >登入</button></a>";
			$("#currpeopleImg").append(openMotalBtnHtml);
		}
	},"JSON");
}


function getAllTeacher(){
	window.location.href="<%=path%>/teacher_getAllTeacher";
}
</script>
	<header class="am-topbar am-topbar-fixed-top">
		<div class="contain" style="background-color: #f8f8f8">
			<div data-am-sticky>
			     <button class="am-btn am-btn-danger am-btn-block" >fjut英语培训机构，欢迎您的到来!</button>
			</div>
			<div class="am-g doc-am-g">
				<div class="am-u-sm-4 am-u-md-4 am-u-lg-4">&nbsp;
					<br>
					<span id="currPeoName" class="am-text-success" style=""></span>
					<s:if test="#session.permission=='teacher'||#session.permission=='user'">
						<a href="<%=path%>/ct_getCT"><span class="am-text-danger">&nbsp;&nbsp;前去查看课表》》》</span></a>
					</s:if>
					<s:if test="#session.permission=='merchant'">
						<a href="<%=path%>/cla_all"><span class="am-text-danger">&nbsp;&nbsp;前去管理后台》》》</span></a>
					</s:if>
				</div>
				<div class="am-u-sm-4 am-u-md-4 am-u-lg-4">&nbsp;</div>
				<div class="am-u-sm-1 am-u-md-1 am-u-lg-1">
					<div id="currpeopleImg" style="float: left"></div>
					
				</div>
			</div>
		</div>
	</header>
	<!-- end page -->
	<div class="admin">
<%-- 	
	<jsp:include page="./com/fun.jsp"></jsp:include> --%>
		<div class="content-page">
			<!-- Start content -->
			<div class="content">
				<div class="am-g">
					<div class="am-u-md-12">
					  <div data-am-widget="slider" class="am-slider am-slider-b3" data-am-slider='{&quot;controlNav&quot;:false}' style="height:400px;">
					 <!--  <div data-am-widget="slider" class="am-slider am-slider-default" data-am-slider='{}'  style="height:400px;"> -->
						    <ul class="am-slides">
						    	<s:iterator id="path" value="#request.runImgPathArr">
						    		<li><img style="height:400px;" src="<%=path%>/<s:property value="#path"/>"/></li>
						    	</s:iterator>
						    </ul>
						</div>
					</div>
				</div>
				<div class="am-g">
					<!-- Row start -->
					<div class="am-u-md-3">
						<div class="card-box">
							<h4 class="header-title m-t-0 m-b-30 text-success"><s:property value="#request.introContent.title"/></h4>
							<div class="widget-chart-1 am-cf" id="introShow">
								
							</div>
							<div class="am-progress am-progress-xs am-margin-bottom-0">
								<div class="am-progress-bar" style="width: 100%"></div>
							</div>
						</div>
					</div>
					<!-- col end -->
					<div class="am-u-md-3">
						<div class="card-box">
							<h4 class="header-title m-t-0 m-b-30 text-info"><s:property value="#request.styleContent.title"/></h4>
							<div class="widget-box-2">
								<div class="widget-chart-1 am-cf"  id="styleShow">
									
								</div>
								<div class="am-progress am-progress-xs am-margin-bottom-0">
									<div class="am-progress-bar" style="width: 100%"></div>
								</div>
							</div>
						</div>
					</div>
					<!-- col end -->
					<div class="am-u-md-6">
						<div class="card-box">
							<h4 class="header-title m-t-0 m-b-30 text-danger"><s:property value="#request.noticeContent.title"/></h4>
							<div class="widget-chart-1 am-cf"  id="noticeShow">
							</div>
							<div class="am-progress am-progress-xs am-margin-bottom-0">
								<div class="am-progress-bar" style="width: 100%"></div>
							</div>
						</div>

					</div>
					<!-- Row end -->
				</div>
				
				<div class="am-g">
					<div class="contain">
						<ul class="am-nav am-navbar-nav am-navbar-left">
							<li><h4 class="page-title">名师指导，离梦更近</h4></li>
						</ul>
					</div>
				</div>
				<div class="am-g">
					<!-- Row start -->
					<s:iterator id="teacher" value="#request.teacherList">
						<div class="am-u-md-3">
							<div class="card-box widget-user">
								<div>
									<img src="<%=path%>/<s:property value="#teacher.imgPath"/>"
										class="img-responsive img-circle" alt="user">
									<div class="wid-u-info">
										<h4 class="m-t-0 m-b-5 font-600"><s:property value="#teacher.name"/></h4>
										<p class="text-muted m-b-5 font-13"><s:property value="#teacher.email"/></p>
										<a href="<%=path%>/home_addScore?tid=<s:property value="#teacher.id" />">
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
				<!-- Row start -->
				<div data-am-sticky>
			         <button class="am-btn am-btn-danger am-btn-block" onclick="getAllTeacher()">更多老师》》</button>
			    </div>
				<div class="am-g">
					<ul class="am-nav am-navbar-nav am-navbar-left">
						<li><h4 class="page-title">开班信息</h4></li>
					</ul>
					<!-- col start -->
					<div class="am-u-md-12">
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
											<th>查看详细</th>
										</tr>
									</thead>
									<tbody>
										<s:iterator id="openClass" value="#request.openClassList" status="sta">
											<tr>
												<td><s:property value="#sta.index+1"/><input type="hidden" value="<s:property value="#openClass.cid"/>"></td>
												<td><s:property value="#openClass.cname"/></td>
												<td><s:property value="#openClass.year"/></td>
												<td><s:property value="#openClass.courseName"/></td>
												<td><s:property value="#openClass.teachers"/></td>
												<td><s:property value="#openClass.price"/></td>
												<td><input  class="am-btn am-btn-primary" onclick="detailClaInfo('<s:property value="#openClass.cid"/>');" data-am-modal="{target: '#my-alert'}" type="button" value="详细"></td>
											</tr>	
										</s:iterator>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<!-- col end -->
				</div>
				<!-- Row end -->
			</div>
		</div>
		<!-- end right Content here -->
		<!--</div>-->
	</div>
	<!-- 详细开班信息模态框 -->
	<div class="am-modal am-modal-alert" tabindex="-1" id="my-alert">
	  <div class="am-modal-dialog">
	    <div class="am-modal-hd">详细时间表</div>
	    <div class="am-modal-bd">
			<table class="am-table" >
			 	<thead>
			        <tr>
			            <th align="center">老师</th>
			            <th align="center">课程</th>
			            <th align="center">上课时间</th>
			        </tr>
			    </thead>
			    <tbody id="datailTable">

			    </tbody>
			</table>
	    </div>
	    <div class="am-modal-footer">
	      <span class="am-modal-btn">确定</span>
	    </div>
	  </div>
	</div>
	
<!--登入的模态框  -->
<!-- 详细开班信息模态框 -->
	<div class="am-modal am-modal-alert" tabindex="-1" id="loginModal">
	  <div class="am-modal-dialog">
	    <div class="am-modal-hd"><span>登入</span></div>
	    <div class="am-modal-bd">
          <div class="am-form-group">
              <label>用户名：</label>
              <input type="text" id="username" minlength="3" placeholder="输入用户名" class="am-form-field" required/>
              <label>密码：</label>
              <input type="password" id="passowrd" minlength="3" placeholder="密码" class="am-form-field" required/>
          </div>
 		  <h3>角色：</h3>
		  <label class="am-radio-inline" >
		    <input type="radio" name="role" value="user" data-am-ucheck checked> 学生
		  </label>
		  <label class="am-radio-inline">
		    <input type="radio" name="role" value="teacher" data-am-ucheck> 教师
		  </label>
		  <label class="am-radio-inline">
		    <input type="radio" name="role" value="merchant" data-am-ucheck> 商家
		  </label>
	    </div>
	    <div class="am-modal-footer">
	       <span class="am-modal-btn" onclick="login()">登入</span>
	    </div>
	  </div>
	</div>

	<!-- navbar -->
	<a href="admin-offcanvas"
		class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu"
		data-am-offcanvas="{target: '#admin-offcanvas'}">
		<!--<i class="fa fa-bars" aria-hidden="true"></i>-->
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

