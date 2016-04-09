<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page
	import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
</head>
<body>
	<%
		String error = (String) request
				.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
	%>
	<div style="" id="messageBox">
		<button data-dismiss="alert" class="close">×</button>
		<label id="loginError"> <spring:message
				code="${error!=null?\"\":(\"com.exam.mserver.common.persistence.util.CaptchaException\".equals(error)?\"verification.code.error\":\"username.or.password.error\")}" />
		</label>
	</div>
	<form action="${ctx}/login" method="post">
	<div  style="margin:auto;padding:auto;TEXT-ALIGN: center;">
		<div>
			<label>用户名：</label> <input type="text" id="username" name="username"
				class="required" placeholder="登录名">
		</div>
		<div style="margin-top:10px">
			<label>密码：&nbsp;</label>
			<input type="password" name="password" id="password" placeholder="密码" />
		</div>
		<div style="margin-top:10px">
			<input type="submit" value="登录" /> <input type="checkbox"
				id="rememberMe" name="rememberMe"><span>记住我</span>
		</div>
		</div>
	</form>
</body>
</html>
