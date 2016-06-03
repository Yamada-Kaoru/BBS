<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>掲示板システム</title>
	<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="main-contents">

<div class="header">




<a href="manage">ユーザー管理画面へ</a>
<a href="articleup">新規投稿へ</a>
<a href="logout">ログアウト</a>
</div>

<c:if test="${ not empty loginUser }">
	<div class="profile">
		<div class="name">ログインユーザー：<c:out value="${loginUser.name}" /></div>
		<div class="account">
			@<c:out value="${loginUser.account}" />
		</div>
	</div>
</c:if>
<br>
<br>
<div class="form-area">
<form action="home" method="get"><br />
<label for="category">カテゴリー</label><br />
		<select name="category" id="category">
			<option value="人事異動">人事異動</option>
			<option value="賞罰報告">賞罰報告</option>
			<option value="イベント">イベント</option>
			<option value="重大発表">重大発表</option>
		</select>
<label for="year">表示期間</label><br />
		<label for="firstday"></label>
	<input name="firstday"id="firstday" />～
	<label for="lastday"></label>
	<input name="lastday"id="lastday" />
		<input type="submit" value="検索">
</form>
<br>
<br>

<div class="articles">
	<c:forEach items="${articles}" var="article">
			<div class="article">
				<span class="title">タイトル：<c:out value="${article.title}" /></span><br>
				<span class="category">カテゴリー：<c:out value="${article.category}" /></span><br>
				<div class="text">内容：<c:out value="${article.text}" /></div>
				<div class="name">投稿者：<c:out value="${article.name}" /></div>
				<div class="date">投稿日：<fmt:formatDate value="${article.insertDate}" pattern="yyyy/MM/dd HH:mm:ss" /></div>
				<br>

	<div class="comments">＜この記事に対する皆の反応＞
		<c:forEach items="${comments}" var="comment">
			<c:if test="${comment.articleId == article.id}" var="check">
			<c:if test="${check}">
					<div class="name">投稿者：<c:out value="${comment.name}" /></div>
					<div class="text">コメント：<c:out value="${comment.commentText}" /></div>
					<div class="date">投稿日：<fmt:formatDate value="${comment.insertDate}" pattern="yyyy/MM/dd HH:mm:ss" /></div>
				</c:if>
				</c:if>
				<br>
		</c:forEach>
	</div>



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
	<form action="comment" method="post"><br />
			<label for="commentText">コメント</label><br />
			<textarea name="commentText" cols="50" rows="3" class="comment"></textarea>
			<input type="hidden" name="article_id" value="${article.id}">
			<input type="submit" value="コメントを送信する">
	</form>
			<br />
			<br>
			</div>
			</div>
</div>
</div>
</c:forEach>


</body>
</html>