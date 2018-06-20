<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="./css/style.css">
  <title>ユーザー情報詳細参照</title>
</head>
<body>
  <header>
    <div class="page_body">
      <div class="user_area">
        <span><a>${userInfo.name}さん</a><a href="LogoutServlet">ログアウト</a></span>
      </div>
    </div>
  </header>
  <div class="main_ttl"><h1>ユーザー情報詳細参照</h1></div>
  <div class="container">
    <div class="page_body">
      <div class="user_info">
        <div class="user_info_inner">
          <dl>
            <dt>ログインID</dt><dd>${user.loginId}</dd>
            <dt>ユーザー名</dt><dd>${user.name}</dd>
            <dt>生年月日</dt><dd>${user.birth_date}</dd>
            <dt>登録日時</dt><dd>${user.create_date}</dd>
            <dt>更新日時</dt><dd>${user.update_date}</dd>
          </dl><br>
              <div class="back"><a href="UserListServlet">戻る</a></div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>