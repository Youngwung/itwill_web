<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Lab 4</title>
    </head>
    <body>
        <h1>Contents</h1>
        <h2><%= LocalDateTime.now() %></h2>
        <h3>Hello, ${ nickname }! </h3>
    </body>
    <main>
        <ul>
            <li> <!-- Servlet: Server + Applet, Applet: 플러그인같은거 -->
               <a href ="ex1">첫번째 서블릿</a>
            </li>
            <li>
               <a href ="ex2">두번째 서블릿</a>
            </li>
            <li>
               <a href ="ex3">포워드(forward)</a>
            </li>
            <li>
               <a href ="ex4">리다이렉트(redirect)</a>
            </li>
            <li>
               <a href ="intro.jsp">JSP 소개</a>
            </li>
            <li>
               <a href ="main.jsp">include 지시문</a>
            </li>
            <li>
               <a href ="scriptlet.jsp">스크립트릿(scriptlet)</a>
            </li>
            <li>
               <a href ="form.jsp">폼 양식</a>
            </li>
            <li>
               <a href ="actiontag.jsp">JSP Action Tag</a>
            </li>
            <li>
               <a href ="el.jsp">EL(Expression Language, 표현식 언어)</a>
            </li>
            <li>
               <a href ="jstl.jsp">JSTL</a>
            </li>
            <li>
               <a href ="mvc">MVC</a>
            </li>
            <li>
               <a href ="cookie">Cookie</a>
            </li>
            <li>
               <a href ="session">Session</a>
            </li>
            <li>
               <p>Filter는 jsp없음. FilterEx.java 참고하셈</p>
               <p>서블릿에 도착하는 요청에 조건문을 걸 수 있음.</p>
            </li>
            <li>
               <p>Lister도 jsp없음. ListenerEx.java참고하셈</p>
               <p>세션 생성 등의 서버에서 이벤트가 일어났을 때 할 일이 있으면 이거 써야됨.</p>
            </li>

        </ul>
    </main>
</html>