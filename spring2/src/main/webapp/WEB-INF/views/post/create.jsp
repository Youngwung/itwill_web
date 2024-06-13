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
	</head>
	<body>
		<div class="container-fluid">
			<header>
				<c:set var="pageTitle" value="Post Create" />
				<%@ include file="../fragments/header.jspf" %>
			</header>
			<main>
				<div class="mt-2 card">
					<div class="card-header">
						<h2>새 글 작성</h2>
					</div>
					<div class="card-body">
						<form method="post">
							<!-- form에서 action 속성 값을 설정하지 않으면 현재 요청 주소로 다시 요청을 보냄. -->
							<div class="mt-2">
								<input
									class="form-control"
									type="text"
									name="title"
									placeholder="제목 입력"
									required
									autofocus
								/>
							</div>
							<div class="mt-2">
								<textarea
									class="form-control"
									name="content"
									rows="5"
									placeholder="내용 입력"
									required
								></textarea>
							</div>
							<div class="mt-2">
								<input
									class="form-control"
									type="text"
									name="author"
									placeholder="작성자"
									required
								/>
							</div>
							<div class="mt-2">
								<input
									class="form-control btn btn-outline-success"
									type="submit"
									value="저장"
								/>
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
