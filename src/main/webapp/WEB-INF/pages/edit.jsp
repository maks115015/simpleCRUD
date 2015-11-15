<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="fragments/headTag.jsp"/>

<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<form:form method="POST" commandName="user" action="edit" >
  <table>
    <tbody>
    <form:input type="hidden" path="id"/>
    <tr>
      <td>Name:</td>
      <td><form:input path="name" /></td>
      <td><form:errors path="name" cssStyle="color: red;"/></td>
    </tr>
    <tr>
      <td>Age:</td>
      <td><form:input path="age" /></td>
      <td><form:errors path="age" cssStyle="color: red;"/></td>
    </tr>
    <tr>
      <td>is admin?:</td>
      <c:if test="${user.isAdmin()==true}">
        <td><input type="checkbox" checked="checked" name="isAdmin" path="isAdmin" value="true"/></td>
      </c:if>
      <c:if test="${user.isAdmin()==false}">
        <td><input type="checkbox" name="isAdmin" path="isAdmin" value="true"/></td>
      </c:if>
    </tr>
    <tr>
      <td><input type="submit" value="Send" /></td>
      <td></td>
      <td></td>
    </tr>
    </tbody>
  </table>
</form:form>
<a href="${pageContext.request.contextPath}/">Home page</a>
</body>
</html>