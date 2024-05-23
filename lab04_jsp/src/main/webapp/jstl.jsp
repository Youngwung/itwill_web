<%@page import="com.itwill.lab04.model.Contact"%>
<%@page import="java.util.ArrayList"%>
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
    <%@ include file = "header.jspf" %>
		<main>
            <h1>JSTL(JSP Standard Tag Library)</h1>
            <%-- JSTL 라이브러리 사용하기
            1. pom.xml 파일에 의존성(dependency)을 추가.
               o. jakarta.servlet.jsp.jstl: jakarta.servlet.jsp.jstl-api:3.0.0
               o. org.galssfish.web:jakarta.servlet.jsp.jstl:3.0.1
            2. JSTL을 사용하는 JSP 파일에서 <%@ taglib prefix="" uri="" %> 지시문을 설정.
             --%>
             
            <%
            String[] sns = {"인*", "싸이월드", "얼굴책", "X"};
            pageContext.setAttribute("sns", sns);
            %>
            <h2>스크립트릿, 식 사용한 리스트</h2>
            <ul>
                <% for(String s : sns) { %>
                   <li><%= s %></li> 
                <% } %>
            </ul>

            <h2>JSTL, EL을 사용한 리스트</h2>
            <ul>
                <c:forEach items="${ sns }" var="s">
                    <li> ${s} </li>
                </c:forEach>
            </ul>
            
            <%
            ArrayList<Contact> data = new ArrayList<>();
            for (int i = 1; i <= 5; i++) {
             data.add(new Contact(i, "name_" + i, "phone_" + i, "email_" + i ));
            }
            pageContext.setAttribute("contactList", data);
            %>
            
            <h2>Scriptlet, expression 사용한 테이블</h2>

            <table>
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>이름</th>
                        <th>전화번호</th>
                        <th>이메일</th>
                    </tr>
                </thead>
            <tbody>
                <% for (Contact c : data)  { %>
                    <tr>
                       <td><%= c.getId() %></td> 
                       <td><%= c.getName() %></td> 
                       <td><%= c.getPhone() %></td> 
                       <td><%= c.getEmail()%></td>
                    </tr> 
                <% } %>
              </tbody>
          </table>

          <h2>JSTL, EL 사용한 테이블</h2>
        
          <table>
             <thead>
                <tr>
                    <th>번호</th>
                    <th>이름</th>
                    <th>전화번호</th>
                    <th>이메일</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${ contactList }" var="c" >
                    <tr>
                        <td> ${ c.id } </td> <%-- EL은 프로퍼티 이름으로 getter 메서드를 찾음.  --%>
                        <td> ${ c.name } </td>
                        <td> ${ c.phone } </td>
                        <td> ${ c.email } </td>
                    </tr>
                </c:forEach> 
              </tbody>
            </table>
            
            
            <h2>URL 태그</h2>
            <a href ="result.jsp?username=gu&est&color=skyblue">클릭1</a>
            <%-- 이 방법은 &라는 특수문자를 사용하고 싶을 때
             특수문자 번호를 외워야 한다는 단점이 있다.
             그냥 &를 쓰면 username=gu 와 est로 나뉘어버림. 
             안녕하세요, gu님! 출력--%>
             
            <%-- 질의 문장열의 요청 파라미터 값에 특수 기호가 포함될 때 사용하는 태그 --%>
            
            <c:url value="result.jsp" var="url">
                <c:param name="username" value="gu&est" />
                <c:param name="color" value="skyblue"/>
            </c:url>
            <a href="${url }">클릭2</a>
            <%-- 안녕하세요, gu&est님! 출력. --%>
            <c:url value="result.jsp" var="url">
                <c:param name="username" value="guest" />
                <c:param name="color" value="skyblue"/>
            </c:url>
            <a href="${url }">클릭3</a>
            
        </main>
	</body>
</html>

























