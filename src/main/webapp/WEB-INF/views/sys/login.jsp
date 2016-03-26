<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
  <head>
  </head>
  <body>
  
  	<input value="${ctx}"/>
    	<form action="${ctx}/login" method="post">
    		<input type="text" id="username" name="username"
									class="required" value="${username}" placeholder="登录名">
    		<input type="submit" value="登录"/>
    	</form>
  </body>
</html>
