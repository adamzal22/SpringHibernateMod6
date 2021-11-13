<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<c:forEach items="${books}" var="b">
    <strong><c:out value="${b.title}: ${b.publisher.name}"/></strong><br>
    <strong>Ocena: <strong/><c:out value="${b.rating}"/><br />
    <strong>Opis: <strong/><c:out value="${b.description}"/><br />
    <strong>Autorzy:</strong><c:forEach items="${b.authors}" var="a">
    <strong><c:out value="${a.firstName} ${a.lastName}"/> </strong><c:if test="${loop.index +1 lt b.authors.size()}"/>
</c:forEach>
    <hr>
    <br/>
</c:forEach>
<br />
<a href="/book/form">Dodaj nową książkę</a>