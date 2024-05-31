<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link
  href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
  rel="stylesheet"
  integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
  crossorigin="anonymous">
<!-- 보안을 위한 코드라고 생각하면 됨 -->
</head>
<body>
  <div class="container-fluid">
    <c:set var="pageTitle" value="Post Modify" scope="page" />
    <!-- scope의 기본값은 page임. 생략 가능. -->
    <%@ include file="../fragments/header.jspf"%>

    <main>
      <div class="mt-2 card">
        <div class="card-header">
          <h2>포스트 수정 페이지</h2>
        </div>
        <div class="card-body">
          <form id="modifyForm">
            <%-- form 작성. --%>
            <div class="mt-2">
              <label for="id" class="form-label">번호</label>
              <%-- for: 다른 html 태그에 id 속성 값을 찾음 --%>
              <input id="id" class="form-control" type="text"
                name = "id" value="${post.id}" readonly />
              <%-- for의 대상. value에 EL로 가져온 post의 id를 써 input에 표시되게한다.
              여기서 EL로 id를 가져올 수 있었던 건 PostDetailsController에서 req.setAttribute("post", post)로 
              보내주었기 때문에 사용할 수 있었던 것.  --%>
            </div>
            <div class="mt-2">
              <label for="title" class="form-label">제목</label>
              <%-- for: 다른 html 태그에 id 속성 값을 찾음 --%>
              <input id="title" class="form-control" type="text"
                name = "title" value="${post.title}" />
            </div>
            <div class="mt-2">
              <label for="content" class="form-label">내용</label>
              <textarea id="content" class="form-control" rows="5" name="content"
                >${post.content}</textarea>
            </div>
            <div class="mt-2 d-none">
              <label for="author" class="form-label">작성자</label>
              <input id="author" class="form-control" type="text"
                value="${post.author}" readonly />
            </div>
          </form>
        </div>
        <div class="card-footer">
          <button id="btnDelete" class = "btn btn-outline-danger">삭제</button>
          <%-- 버튼은 form안에 있으면 아무런 기능을 주지 않아도 자동으로 form액선 안에 주소로
          요청을 보낸다. form밖에 만들면 이벤트 핸들러가 등록되지 않으면 아무런 기능이 없어진다.
          우리가 원하는 기능으로 만들기 위해 form밖에 버튼을 만들었다.
          버튼으로 만든 이유가 삭제 버튼을 눌렀을 때 새로운 창을 띄우고 사용자에게 
          확인을 한번 받는 기능을 구현하려고 하는건데
          그것은 자바스크립트로 구현해야한다. 
          그래서 자바스크립트로 컨트롤 할 수 있는 태그인 버튼으로 한 것이다. --%>
          <button id="btnUpdate" class = "btn btn-outline-success">업데이트</button>
        </div>
      </div>
    </main>
  </div>

  <script
    src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
    integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
    crossorigin="anonymous"></script>
  <%--
  <c:url var="post_modify_js" value="/js/post_modify.js" />
  <script src="${post_modify_js}"></script>
       경로같은거 헷갈리면 이런식으로 써도 될거같음  
       c:url태그안의 value는 맨 앞 /가 webapp/이라고 생각하면 됨.  
  --%>
  <script src="../js/post_modify.js">
  </script>
</body>
</html>