<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${targetUser.name}の設定</title>
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

<form action="setting" method="post" enctype="multipart/form-data"><br />
	<label for="name">名前</label>
	<input name="name" value="${targetUser.name}" id="name"/><br />

	<label for="account">アカウント名</label>
	<input name="account" value="${targetUser.account}" /><br />

	<label for="password">パスワード</label>
	<input name="password"  type="password" id="password"/> <br />
	<label for="checkPassword">確認用パスワード</label>
	<input name="checkPassword" type="password" id="checkPassword"/> <br />

	<label for="branch_Id">支店名</label><br />
	<select name="branch_Id"  id="branch_Id">
	<c:forEach items="${branches}" var="branch">
		<c:if test="${targetUser.getBranchId() == branch.id}">
			<option value="${branch.id}" selected="selected"><c:out value="${branch.getName()}"/></option>
		</c:if>

		<c:if test="${targetUser.getBranchId() != branch.id}">
			<option value="${branch.id}"><c:out value="${branch.getName()}"/></option>
		</c:if>
	</c:forEach>
	</select><br />

	<label for="possition_Id">役職名</label><br />
	<select name="possition_Id" id="possition_Id">
	<c:forEach items="${possitions}" var="possition">
		<c:if test="${targetUser.getPossitionId() == possition.id}">
			<option value="${possition.id}" selected="selected"><c:out value="${possition.getName()}"/></option>
		</c:if>
		<c:if test="${targetUser.getPossitionId() != Possition.id}">
			<option value="${possition.id}"><c:out value="${possition.getName()}"/></option>
		</c:if>
	</c:forEach>
	</select><br />

	<input type="hidden" name="id" value="${targetUser.getId()}"/>
	<input type="submit" value="登録" /> <br />
	<a href="manage">戻る</a>
</form>

</div>
</body>
</html>