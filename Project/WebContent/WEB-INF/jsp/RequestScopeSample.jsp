<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.Human" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>リクエストスコープ取得サンプル</title>
</head>
<body>
<%
Human h = (Human)request.getAttribute("human");
%>
<p><%=h.getName() %>は<%=h.getAge() %>歳です。</p>
</body>
</html>