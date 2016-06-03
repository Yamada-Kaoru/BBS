<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー管理画面</title>
</head>
<body>
<div class="main-contents">

<div class="header">
<a href="signup">ユーザー新規登録</a>
</div>
<table border="3">
        <thead>
            <tr>
                <th>名前</th>
                <th>ログインID</th>
                <th>ステータス</th>
                <th>編集</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${displayUsers}" var="displayUser">
                <tr>
                    <td><c:out value="${displayUser.getName()}" /></td>
                    <td><c:out value="${displayUser.getAccount()}" /></td>
                    <td><c:if test="${displayUser.getStatus() == 1}">
                    		<a href="stop?id=${displayUser.getId()}">活動中</a></c:if>
                    	<c:if test="${displayUser.getStatus() == 0}">
                    		<a href="revival?id=${displayUser.getId()}">停止中</a></c:if></td>
                    <td><a href="setting?id=${displayUser.getId()}" >このユーザーを編集する</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>