<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="./css/style.css">
  <title>ユーザー削除確認</title>
</head>
<body>
  <header>
    <div class="page_body">
      <div class="user_area">
        <span><a>${userInfo.name}さん</a><a href="LogoutServlet">ログアウト</a></span>
      </div>
    </div>
  </header>
  <div class="main_ttl"><h1>ユーザー削除確認</h1></div>
  <div class="container">
    <div class="page_body">
      <div class="confirm_txt">
        <p>ログインID：${userDetail.loginId}<br>を本当に削除してよろしいでしょうか。</p>
      </div>
      <form class="confirm" action="UserDeleteServlet" method="post">
        <div class="submit">
          <input name="id" type="hidden" value="${userDetail.id}">
          <input name="cancel" type="submit" value="キャンセル" class="cancel">
          <input name="delete" type="submit" value="OK" class="ok">
        </div>
      </form>
      </div>
    </div>
</body>
</html>