<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%> <%@ taglib prefix="c"
uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<title>Spring Legacy</title>
		<link
			href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
			rel="stylesheet"
			integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
			crossorigin="anonymous"
		/>
	</head>

	<body>
    <div class="container-fluid">
      <c:set var="pageTitle" value="회원가입" />
      <%@ include file="../fragments/header.jspf" %>
    </div>
    <main>
      <div class="my-2 card card-body">
        <c:url var="signupPage" value="/user/signup" />
        <form action="${signupPage}" method="post">
          <div class="my-2">
            <input type="text" class="form-control" id="userid" name="userid" placeholder="사용자 아이디" required autofocus/>
          </div>
          <!-- userid 중복 체크 결과 표시할 영역 -->
           <div id="checkUseridResult"></div>
           
           <div class="my-2">
             <input type="password" class="form-control" id="password" name="password" required placeholder="비밀번호 입력"/>
            </div>

            
            <!-- password 중복 체크 결과 표시할 영역 -->
             <div id="checkUseridResult"></div>
            
           <div class="my-2">
            <input type="email" class="form-control" id="email" name="email" placeholder="이메일" required>
           </div>
           
           <!-- password 중복 체크 결과 표시할 영역 -->
            <div id="checkEmailResult"></div>


           <div class="my-2">
             <button id="btnSignup" class="form-control btn btn-outline-success disabled">작성 완료</button>
           </div>
          </form>
      </div>
    </main>

		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
			integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
			crossorigin="anonymous"
		></script>
        <!-- Axio JS 라이브러리 -->
        <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

        <!-- 우리가 만드는 JS 파일 -->
        <c:url var="userSignUpJS" value="/js/user_signup.js" />
        <script src="${userSignUpJS}"></script>

	</body>
</html>
