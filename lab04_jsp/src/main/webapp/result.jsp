<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%-- JSTL 사용하기위한 선언문! --%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JSTL</title>
	</head>
	<body>
		<%@ include file="header.jspf" %>
        
        <main>
            <h1>RESULT</h1>
            <h2>안녕하세요, 
                <span style="color: ${param.color }">${param.username }님</span>!
                <%-- request parameter 가져오기 --%>
            </h2>
            <%-- EL에서 request.getParameter("username")을 대신하는 방법  --%>
            
            <%-- JSTL의 조건문 --%>
            <c:choose>
                <c:when test="${ param.username eq 'admin' }">
                <%-- eq 대신 == 사용해도 됨. ==가 equls를 호출함.--%>
                    <h3>관리자 페이지</h3>
                </c:when>
                <c:when test="${ param.username eq 'guest' }">
                    <h3>손님 페이지</h3>
                </c:when>
                <c:otherwise>
                    <h3>일반 사용자 페이지</h3>
                </c:otherwise>
            </c:choose>
            
            <c:if test="${param.username eq 'admin' }">
                <h3>Admin Page</h3>
            </c:if> <%-- if태그는 else와 else if가 없음. --%>
            <c:if test="${ param.username ne 'admin' }">
                <h3>User Page</h3>
            </c:if>
            <%-- 완전 반대되는 조건을 나열해서 else if와 비슷하게 할수 있음
                 조건을 2번 검사함. 완전 반대되는 조건을 만들 수 없다면 
                 if 태그 말고 choose when을 사용해야함. when은 위 문장에서 
                 조건을 만족하면 아래쪽 when 문장은 실행하지 않음. --%>
        </main>
	</body>
</html>