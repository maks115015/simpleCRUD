<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<header id="header" style="background-color: black">
    <H2 style="color: whitesmoke">Simple Crud Application</H2>
<form method="POST" action="user">
    <table>
        <tbody>
        <tr><td style="color: beige">Find by name</td></tr>
        <tr>
            <td><input name="searchName" type="text"></td>
            <td><input type="submit" value="search" /></td>
        </tr>
        </tbody>
    </table>
</form>
</header>