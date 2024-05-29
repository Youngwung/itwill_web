<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Lab 5</title>
<link
  href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
  rel="stylesheet"
  integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
  crossorigin="anonymous" />
</head>
<body>
  <div class="container-fluid">
    <c:set var="pageTitle" value="Post 목록" scope="page" />
    <%@ include file="../fragments/header.jspf"%>

    <main>
      <div class="mt-2 card">
        <div class="card-header text-center">
          <h2>POSTS</h2>
        </div>
        <div class="card-body">
          <table class="table table-striped table-hover">
            <thead>
              <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>수정 시간</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach items="${posts}" var="p">
                <tr>
                  <td>${p.id}</td>
                  <td>
                    <c:url var="postDetailsPage" value="/post/details">
                      <c:param name="id" value="${p.id}"></c:param>
                      <!-- 여기는 링크를 클릭했을 때 요청하는거라 Get방식 호출임. url 뒤쪽에 ?id=23 이런식으로 붙는거
                        저걸 질의문자열이라고 하고 id를 request parameter(요청 파라미터)라고 함.
                        요청 파라미터를 서블릿에 전달하기 위해서 c:param을 이용하면 편함. value에 jstl 문법으로 써주면
                        클릭한 링크에 해당하는 id가 질의 문자열로 들어감. (25번의 제목을 클릭하면 ?id=25) 
                        그러면 서블릿에서 {getParameter}를 써서 값을 읽고 그 값으로 서비스객체 호출하면 됨.
                        -->
                    </c:url>
                    <a href="${postDetailsPage}"> ${p.title} </a>
                  </td>
                  <td>${p.author}</td>
                  <td>${p.modifiedTime}</td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </main>
  </div>

  <script
    src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
    crossorigin="anonymous">
			
		</script>
</body>
</html>