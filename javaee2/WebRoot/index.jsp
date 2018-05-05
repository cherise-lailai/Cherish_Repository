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
<script type="text/javascript" src="<%=path%>/assets/js/jquery-2.1.0.js"></script>
<script type="text/javascript">
	 $(function(){ 
	 	var url = "<%=path%>/home_all";
		window.location.href=url;
	});
</script>
</head>
<body>
</body>
</html>

