<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Contact</title>
	</head>
	<body>
		<%@ include file="../../header.jspf" %>
        <%-- ../는 상위폴더로 이동하는 문법 --%>
        
        <main>
            <h1>MVC(Model-View-Controller)</h1>
            <h2>연락처 입력 페이지</h2>
            <form action="mvc" method="post">
            <%-- action은 보낼 페이지, method는 전송 방식(get, post 등) --%>
                <div>
                    <input type="number" name="id" placeholder="번호" required="required" />
                    <%-- required: 반드시 입력해야하는 속성. 입력 안하고 submit하려고 하면 메세지 출력 --%>
                </div>
                <div>
                    <input type="text" name = "name" placeholder="이름" required="required" />
                </div>
                <div>
                    <input type="text" name = "phone" placeholder="전화번호" required="required" />
                </div>
                <div>
                    <input type="text" name = "email" placeholder="이메일"  />
                </div>
                <div>
                    <input type="submit" value="저장" />
                    <input type="reset" value="취소" />
                </div>
                
            </form>
        </main>
	</body>
</html>