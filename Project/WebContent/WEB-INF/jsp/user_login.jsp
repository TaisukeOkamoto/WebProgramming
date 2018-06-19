<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="./css/style.css">
  <title>ログイン画面</title>
</head>
<body>
  <div class="main_ttl"><h1>ログイン画面</h1></div>
  <p class="errMsg">${errMsg}</p>
  <div class="container">
    <form class="loginform form" action="LoginServlet" method="post">
      <div class="form_info_wrap">
        <dl>
          <dt>ログインID</dt><dd><input name="loginId" type="text"></dd>
          <dt>パスワード</dt><dd><input name="password" type="password"></dd>
        </dl>
        <div class="submit"><input type="submit" value="ログイン"></div>
      </div>
    </form>
  </div>
</body>
</html>