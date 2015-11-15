<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="fragments/headTag.jsp"/>

<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<h1>User page</h1>
<center>
    <table style="text-align: center;" border="1px" cellpadding="0" cellspacing="0" >
        <thead>
        <tr>
            <th width="25px">id</th><th width="150px">name</th><th width="25px">age</th><th width="50px">admin</th><th width="50px">actions</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${(users.indexOf(user)+1)}</td>
                    <td>${user.name}</td>
                    <td>${user.age}</td>
                    <td>
                        <c:if test="${user.isAdmin()==true}">
                            <c:out value="Yes"/>
                        </c:if>
                        <c:if test="${user.isAdmin()==false}">
                            <c:out value="No"/>
                        </c:if>
                    </td>

                    <td>
                        <a href="${pageContext.request.contextPath}/edit?id=${user.id}">Edit</a><br/>
                        <a href="${pageContext.request.contextPath}/delete?id=${user.id}">Delete</a><br/>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</center>
<i>${message}</i><br/>
<a href="${pageContext.request.contextPath}/">Home page</a>
</body>
</html>