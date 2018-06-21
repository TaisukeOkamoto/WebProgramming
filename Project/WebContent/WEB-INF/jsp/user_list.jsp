<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <span><a>${userInfo.name}さん</a><a href="LogoutServlet">ログアウト</a></span>
      </div>
    </div>
  </header>
  <div class="main_ttl"><h1>ユーザ一覧</h1></div>
  <div class="container">
    <div class="page_body">
      <div class="new_register"><a href="UserRegistrationServlet">新規登録</a></div>
    </div>
  <form class="loginform form" action="UserListServlet" method="post">
    <div class="form_info_wrap">
      <dl>
        <dt>ログインID</dt><dd class="id"><input name="inputId" type="text" size="30"></dd>
        <dt>ユーザー名</dt><dd class="user"><input name="inputName" type="text" size="30"></dd>
        <dt>生年月日</dt><dd class="birthyear"><input name="birthDateFrom" type="text" maxlength="10" size="12" placeholder="年/日/月">&nbsp;～&nbsp;<input name="birthDateTo" type="text" maxlength="10" size="12" placeholder="年/日/月"></dd>
      </dl>
      <p class="errMsg">${inputDateErrMessage}</p>
      <div class="submit"><input type="submit" value="検索"></div>
    </div>
  </form>
  <hr>
    <div class="usertable">
      <div class="page_body">
        <table>
          <tr><th>ログインID</th><th>ユーザ名</th><th>生年月日</th><th></th></tr>
          <c:forEach var="user" items="${userList}">
          <c:if test="${!user.loginId.equals('admin')}">
          <tr>
            <td>${user.loginId}</td>
            <td>${user.name}</td>
            <td>${user.birth_date}</td>
            <td>
              <span class="edit">
                <a class="detail" href="UserDetailServlet?id=${user.id}">詳細</a>
                <c:if test="${userInfo.loginId.equals(user.loginId) || userInfo.loginId.equals('admin')}">
                <a class="update" href="UserUpdateServlet?id=${user.id}">更新</a>
                </c:if>
                <c:if test="${userInfo.loginId.equals('admin')}">
                <a class="delete">削除</a>
                </c:if>
              </span>
            </td>
          </tr>
          </c:if>
          </c:forEach>
        </table>
      </div>
    </div>
  </div>
</body>
</html>