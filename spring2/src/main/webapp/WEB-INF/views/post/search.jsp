<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%> <%@ taglib prefix="c"
uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<title>Spring Legacy 2</title>
		<link
			href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
			rel="stylesheet"
			integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
			crossorigin="anonymous"
		/>
	</head>
	<body>
		<div class="container-fluid">
			<c:set var="pageTitle" value="Search Result" />
			<%@ include file="../fragments/header.jspf" %>
		</div>
		<main>
			<div class="mt-2 card">
				<div class="card-header text-center">
					<c:url var="postSearchPage" value="/post/search" />
					<form method="get" action="${postSearchPage}">
						<div class="row">
							<div class="col-3">
								<select class="form-control" name="category">
									<option value="t">제목</option>
									<option value="c">내용</option>
									<option value="tc">제목+내용</option>
									<option value="a">작성자</option>
								</select>
							</div>
							<div class="col-7">
								<input
									class="form-control"
									type="text"
									name="keyword"
									placeholder="검색어를 입력하세요"
									required
								/>
							</div>
							<div class="col-2">
								<input
									type="submit"
									class="btn btn-outline-secondary"
									value="검색"
								/>
							</div>
						</div>
					</form>
				</div>
				<div class="card-body">
					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>작성자</th>
								<th>수정시간</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="r" items="${results}">
								<tr>
									<td>${r.id}</td>
									<td>
										<c:url var="postDetailPage" value="/post/detail">
											<c:param name="id" value="${r.id}"></c:param>
										</c:url>
										<a href="${postDetailPage}">${r.title}</a>
									</td>
									<td>${r.author}</td>
									<td>${r.modifiedTime}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</main>
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
			integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
			crossorigin="anonymous"
		></script>
	</body>
</html>
