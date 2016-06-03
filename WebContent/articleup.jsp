<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規投稿画面</title>
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

<div class="form-area">
	<form action="articleup" method="post"><br />
		<label for="title">タイトル</label>
		<input name="title"  id="title" />(文字以下)<br />

		<label for="category">カテゴリー</label><br />
		<select name="category" id="category">
			<option value="">選択してください</option>
			<option value="人事異動">人事異動</option>
			<option value="賞罰報告">賞罰報告</option>
			<option value="イベント">イベント</option>
			<option value="重大発表">重大発表</option>
		</select><br />

		<label for="text">記事内容</label><br />
			<textarea name="text" cols="100" rows="5" class="text"></textarea>
			<br />
			<input type="submit" value="投稿">
	</form>

</div>
</div>
</body>
</html>