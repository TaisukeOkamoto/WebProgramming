<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="./css/style.css">
  <title>ユーザー情報更新</title>
</head>
<body>
  <header>
    <div class="page_body">
      <div class="user_area">
        <span><a>${userInfo.name}さん</a><a href="LogoutServlet">ログアウト</a></span>
      </div>
    </div>
  </header>
  <div class="main_ttl"><h1>ユーザー情報更新</h1></div>
  <div class="container">
  <form class="loginform form" action="index.html" method="post">
    <div class="form_info_wrap">
      <dl>
        <dt>ログインID</dt><dd>${updateUser.loginId}</dd>
        <dt>パスワード</dt><dd><input name="password" type="password"></dd>
        <dt>パスワード（確認）</dt><dd><input name="password" type="password"></dd>
        <dt>ユーザー名</dt><dd><input name="user" type="text"></dd>
        <dt>生年月日</dt><dd><input name="birthyear" type="text"></dd>
      </dl>
      <div class="submit"><input type="submit" value="更新"></div>
      <div class="back"><a href="UserListServlet">戻る</a></div>
    </div>
  </form>
  </div>
</body>
</html>