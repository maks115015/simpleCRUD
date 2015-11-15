<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="fragments/headTag.jsp"/>

<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<br>
<center>

<a href="${pageContext.request.contextPath}/create.html">Create a new user</a><br/>
    <br>

   <i>${message}</i><br/>
<table style="text-align: center;" border="1px" cellpadding="0" cellspacing="0" >
<thead>
<tr>
<th width="25px">№</th><th width="150px">name</th><th width="25px">age</th><th width="50px">admin</th><th width="80px">timestamp</th><th width="50px">actions</th>
</tr>
</thead>
<tbody>
<c:forEach var="user" items="${page.getContent()}">
<tr>
    <td>${(page.getContent().indexOf(user)+1)+(page.getNumber()*10)}</td>
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
    <td>${user.date.getDay()}-${user.date.getMonth()}-${user.date.getYear()+1900}<br>${user.date.getHours()}:${user.date.getMinutes()}:${user.date.getSeconds()}</td>

    <td>
        <a href="${pageContext.request.contextPath}/edit?id=${user.id}">Edit</a><br/>
        <a href="${pageContext.request.contextPath}/delete?id=${user.id}">Delete</a><br/>
    </td>
</tr>
</c:forEach>
</tbody>
</table>
   <%-- paging--%>
<c:if test="${(page.hasNext())||(page.hasPrevious())}">
        <c:out value="${li}"></c:out>
        <c:forEach var="li" items="${list}">
        <c:if test="${(page.getNumber()+1)==li}">
            <c:out value="${li}"></c:out>
        </c:if>
        <c:if test="${(page.getNumber()+1)!=li}">
            <a href="index.html?page=${li}"><c:out value="${li}"></c:out></a>
        </c:if>
    </c:forEach>
</c:if>
</center>


</body>
</html>