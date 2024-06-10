<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%> <%@ taglib prefix="c"
uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<title>Spring 1</title>
		<link
			href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
			rel="stylesheet"
			integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
			crossorigin="anonymous"
		/>
		<!-- 보안을 위한 코드라고 생각하면 됨 -->
	</head>
	<body>
		<header>
			<h1>Home</h1>
			<h2>${now }</h2>
			<!-- <img alt="집" src="/spring1/images/home.jpg" /> -->
			<!-- <img alt="집" src="./images/home.jpg" /> -->
			<c:url var="home" value="/images/home.jpg" />
			<img alt="집" src="${home}" />
		</header>
		<main>
			<h1>Contents</h1>
			<nav>
				<ul>
					<li>
						<c:url var="exPage" value="/example" />
						<a href="${exPage}">컨트롤러 예제</a>
					</li>
					<li>
						<c:url var="testPage" value="/test" />
						<a href="${testPage}">테스트 페이지</a>
					</li>
					<li>
						<c:url var="forwardPage" value="/testForward" />
						<a href="${forwardPage}">포워드</a>
					</li>
					<li>
						<c:url var="redirectPage" value="/testRedirect" />
						<a href="${redirectPage}">리다이렉트</a>
					</li>
				</ul>
			</nav>
		</main>

		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
			integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
			crossorigin="anonymous"
		></script>
	</body>
</html>
