<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
System.out.println(path);
System.out.println(basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>测试文件上载</title>
  </head>
  <body>
  	<form action="doUploadFile.jsp" method=post name=fm target="fraSubmit">
  		<input type=text name="descName"   /><br/>
  		<input type=text name="remark"   /><br/>
  		<input type=text name="sdPath"  /><br/>
  	
  		
  		<input type=button onclick="fm.submit();" value="文件上传测试" />
  	</form>
  </body>
</html>