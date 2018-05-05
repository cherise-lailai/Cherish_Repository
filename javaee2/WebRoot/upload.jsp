<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<s:debug></s:debug>
	
	<s:form action="testUpload" method="post" enctype="multipart/form-data">
        
		<s:actionerror/>		
		PPTFile: <s:file name="ppt" ></s:file>
		PPTDesc: <s:textfield name="pptDesc"></s:textfield>
		<s:submit></s:submit>
	</s:form>
	
</body>
</html>