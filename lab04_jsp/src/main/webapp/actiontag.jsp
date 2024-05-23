<%@page import="com.itwill.lab04.model.Contact"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
        <style>
            p {
                border: 1px solid gray;
                border-radius: 8px;
                margin: 16px;
                padding: 16px;
            }
        </style>
	</head>
	<body>
		<%@ include file="header.jspf" %>
        <main>
			<h1>JSP Action Tag</h1>
			<%--
				JSP 액션 태그: 스크립트릿에서 사용되는 일부 자바 코드들을 HTML 또는 XML과 비슷하게 
                태그, 태그 속성, 태그 컨텐트를 작성해서 대체하는 문법
				JSP action tag는 대/소문자를 구분! (HTML 태그는 대/소문자를 구분하지 않음.)
                
                o. <jsp:forword></jsp:forward>
                o. <jsp:include></jsp:include>
                o. <jsp:useBean></jsp:useBean>: 생성자 호출
                o. <jsp:getProperty></jsp:getProperty>: getter 메서드 호출
                o. <jsp:setProperty></jsp:setProperty>: setter 메서드 호출
			--%>
            
            <h1>액션 태그를 사용하지 "않은" 자바 객체 생성</h1>
            <% 
            Contact contact1 = new Contact();
            contact1.setId(1); // setter메서드 호출
            contact1.setName("홍길동");
            contact1.setPhone("010-0000-0000");
            contact1.setEmail("hgd@itwill.com");
            %>
            <p>
            ID: <%=contact1.getId() %><br/> <%-- getter 메서드 출력 --%>
            Name: <%=contact1.getName() %><br/>
            Phone: <%=contact1.getPhone() %><br/>
            Email: <%=contact1.getEmail() %><br/>
            </p>
                        
            <h1>액션태그 자바 "빈"을 사용한 객체 생성</h1>
            <jsp:useBean id="contact2" class="com.itwill.lab04.model.Contact" />
            <%-- Contact contact2 = new Contact(); --%>
            
            <jsp:setProperty property="id" name="contact2" value="2"/>
            <%-- contact2.setId(2) --%>
            <jsp:setProperty property="name" name="contact2" value="김영웅"/>
            <jsp:setProperty property="phone" name="contact2" value="010-1234-5678"/>
            <jsp:setProperty property="email" name="contact2" value="duddnd@gmail.com"/>
            
            <p>
            ID: <jsp:getProperty property="id" name="contact2"/>   <br/>
            Name: <jsp:getProperty property="name" name="contact2"/> <br/>
            Phone: <jsp:getProperty property="phone" name="contact2"/><br/>
            Email: <jsp:getProperty property="email" name="contact2"/><br/>
            </p>
            
        </main>
        
        <jsp:include page="footer.jspf"></jsp:include>
        <%-- <%@ include file="" %>와 비슷하지만, JSP 파일들이 각각 컴파일 된 후 합쳐짐.
            page만 사용 가능함. jspf로 만들어서 뭔가 이상한게 생김.
         --%>
	</body>
</html>