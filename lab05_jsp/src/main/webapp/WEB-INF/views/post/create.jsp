<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%> <%@ taglib prefix="c"
uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<title>Create Page</title>
		<link
			href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
			rel="stylesheet"
			integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
			crossorigin="anonymous"
		/>
		<!-- 보안을 위한 코드라고 생각하면 됨 -->
	</head>
	<body>
		<div class="container-fluid">
			<!-- header.jspf의 변수로 선언한 pageTitle에 집어넣을 값을 설정하는 코드.
  여기에 넣은게 맨 위에 표시됨 -->
			<c:set var="pageTitle" value="New post" scope="page" />
			<%@ include file="../fragments/header.jspf"%>
			<!-- 제목> 내용> 작성자> 저장버튼 만들기. -->
			<main>
				<div class="mt-2 card">
					<div class="card-header">
						<h2>새 글 작성</h2>
					</div>
					<div class="card-body">
						<c:url var="newPostPage" value="/post/create" />
						<form method="post" action="${newPostPage}">
							<div class="mt-2">
								<input
									class="form-control"
									type="text"
									name="title"
									placeholder="제목"
									required
									autofocus
								/>
							</div>
							<div class="mt-2">
								<!-- 처음에 5줄을 보여주고 5줄을 넘어가면 스크롤을 만들어줌 -->
								<textarea
									class="form-control"
									rows="5"
									name="content"
									placeholder="내용"
									required
								></textarea>
							</div>
							<div class="mt-2 d-none">
								<input
									class="form-control"
									type="text"
									name="author"
									value="${signedInUser}"
									readonly
								/>
							</div>
							<div class="mt-2">
								<input
									class="form-control btn btn-outline-success"
									type="submit"
									value="저장"
								/>
								<!-- 이 버튼 눌렀을 때 405오류 뜨면 
              그 이유는 createController에 doPost메서드가 없거나 오류가 있다는 뜻.
              오류뜨면 doPost찾아가면됨. -->
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
