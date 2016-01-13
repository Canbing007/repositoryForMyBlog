<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>OTP page</title>
</head>
<body>
	<spring:message code="OTP_RESEND"/>
    <form:form action="verifyOTP" method="post" name="userProfile" commandName="userProfile">
    	<input type="text" name="otp" maxlength="6" autocomplete="off">
    	<form:errors path="otp" cssStyle="color: #ff0000;"/>
    	<input type="submit" value="Submit">
    </form:form>
</body>
</html>