<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%> <%@ taglib prefix="c"
uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<title>Insert title here</title>
		<link
			href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
			rel="stylesheet"
			integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
			crossorigin="anonymous"
		/>
		<!-- 보안을 위한 코드라고 생각하면 됨 -->
	</head>
	<body>
		<div class="card mt-2">
			<div class="card-header">
				<h2>로그인</h2>
			</div>
			<div class="card-body">
				<c:if test="${not empty param.result && param.result eq 'f'}">
					<div class="text-danger">아이디와 패스워드를 확인하세요.</div>
				</c:if>
				<c:url var="signInPage" value="/user/signin" />
				<form method="post" action="${signInPage}">
					<div class="mt-2">
						<input
							type="text"
							name="userid"
							placeholder="아이디"
							class="form-control"
							required
							autofocus
						/>
					</div>

					<div class="mt-2">
						<input
							type="password"
							name="password"
							class="form-control"
							placeholder="비밀번호"
							required
						/>
					</div>
					<div class="mt-2">
						<input type="submit" value="로그인" class="form-control" />
					</div>
					<div class="d-none">
						<input name="target" value="${param.target}" readonly />
					</div>
				</form>
			</div>
		</div>

		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
			integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
			crossorigin="anonymous"
		></script>
	</body>
</html>
