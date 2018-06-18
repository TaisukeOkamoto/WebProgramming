<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="./css/style.css">
  <title>ユーザ一覧</title>
</head>
<body>
  <header>
    <div class="page_body">
      <div class="user_area">
        <span><a>●●●●さん</a><a>ログアウト</a></span>
      </div>
    </div>
  </header>
  <div class="main_ttl"><h1>ユーザ一覧</h1></div>
  <div class="container">
    <div class="page_body">
      <div class="new_register"><a href="">新規登録</a></div>
    </div>
  <form class="loginform form" action="index.html" method="post">
    <div class="form_info_wrap">
      <dl>
        <dt>ログインID</dt><dd class="id"><input name="id" type="text" size="30"></dd>
        <dt>ユーザー名</dt><dd class="user"><input name="user" type="text" size="30"></dd>
        <dt>生年月日</dt><dd class="birthyear"><input name="birthyear" type="text" size="12">&nbsp;～&nbsp;<input name="birthyear" type="text" size="12"></dd>
      </dl>
      <div class="submit"><input type="submit" value="検索"></div>
    </div>
  </form>
  <hr>
    <div class="usertable">
      <div class="page_body">
        <table>
          <tr><th>ログインID</th><th>ユーザ名</th><th>生年月日</th><th></th></tr>
          <tr>
            <td>id0001</td>
            <td>田中太郎</td>
            <td>1989年04月26日</td>
            <td>
              <span class="edit">
                <a class="detail">詳細</a>
                <a class="update">更新</a>
                <a class="delete">削除</a>
              </span>
            </td>
          </tr>
          <tr>
            <td>id0001</td>
            <td>田中太郎</td>
            <td>1989年04月26日</td>
            <td>
              <span class="edit">
                <a class="detail">詳細</a>
                <a class="update">更新</a>
                <a class="delete">削除</a>
              </span>
            </td>
          </tr>
          <tr>
            <td>id0001</td>
            <td>田中太郎</td>
            <td>1989年04月26日</td>
            <td>
              <span class="edit">
                <a class="detail">詳細</a>
                <a class="update">更新</a>
                <a class="delete">削除</a>
              </span>
            </td>
          </tr>
        </table>
      </div>
    </div>
  </div>
</body>
</html>