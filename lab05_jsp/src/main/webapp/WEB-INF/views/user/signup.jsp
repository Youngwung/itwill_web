<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%> <%@ taglib prefix="c"
uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<title>Signup</title>
		<link
			href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
			rel="stylesheet"
			integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
			crossorigin="anonymous"
		/>
		<!-- 보안을 위한 코드라고 생각하면 됨 -->
	</head>
	<body>
		<!-- TODO: 회원 가입 양식(form) -->
		<div class="container-fluid">
			<c:set var="pageTitle" value="Signup" />
			<%@ include file="../fragments/header.jspf"%>
			<main>
				<div class="mt-2 card">
					<div class="card-header">
						<h2>회원가입</h2>
					</div>
					<div class="card-body">
                        <c:url var="signupPage" value="/user/signup" />
                        <form method="post" action="${signupPage}">
                            <div class="mt-2">
                                <input class="form-control" type="text" name="userid"
                                placeholder="아이디" required autofocus />
                            </div>
                            <div class="mt-2">
                                <input class="form-control" type="password" name="password"
                                placeholder="비밀번호" required autofocus />
                            <div class="mt-2">
                                <input class="form-control" type="text" name="email"
                                placeholder="이메일" required autofocus />
                            </div>
                            <div class="mt-2">
                                <input class="form-control btn btn-outline-primary"
                                type="submit" value="회원가입" />
                            </div>
                        </form>
                    </div>
				</div>
			</main>
		</div>
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
			integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
			crossorigin="anonymous"
		></script>
	</body>
</html>
