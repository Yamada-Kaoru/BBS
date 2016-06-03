<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー登録</title>
</head>
<body>
<div class="main-contents">
<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items="${errorMessages}" var="message">
				<li><c:out value="${message}" />
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>
<form action="signup" method="post"><br />
	<label for="name">名前</label>
	<input name="name" value="${temporalUser.name}" id="name" />(10文字以下)<br />

	<label for="account">ログインID</label>
	<input name="account" value="${temporalUser.account}" id="account" />(半角英数字6～20)<br />

	<label for="password">パスワード</label>
	<input type="password" name="password" id="password" />(6～225文字)<br />
	<label for="checkPassword">パスワード確認</label>
	<input type="password" name="checkPassword" id="checkPassword" /><br />


	<label for="branch_id">支店名</label><br />
	<select name="branch_id"  id="branch_id">
	<c:forEach items="${branches}" var="branch">
	<option value="${branch.id}"><c:out value="${branch.getName()}"/></option>
	</c:forEach>

	</select><br />

	<label for="possition_id">役職名</label><br />
	<select name="possition_id" id="possition_id">
	<c:forEach items="${possitions}" var="possition">
	<option value="${possition.id}"><c:out value="${possition.getName()}"/></option>
	</c:forEach>
	</select><br />

 <input type="hidden" name="status" value="1"/>
 <br />


	<input type="submit" value="登録" /> <br />
	<a href="manage">戻る</a>




</form>
</div>

</body>
</html>