<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<form:form modelAttribute="book" action="/book/save">
    Tytuł: <form:input path="title"/> <br />
    Rating: <form:checkboxes path="rating" items="${ratings}"/> <br />
    Opis: <form:textarea path="description" cols="50" rows="15"/> <br />
    <%--Wydawcy: <form:select path="publisher.id" items="${publishers}" itemLabel="name" itemValue="id"/><br />--%>
    Autorzy: <form:select multiple="true" path="authors" items="${authors}" itemLabel="fullName" itemValue="id"/><br />

    <input type="submit" value="Wysyłam"> <br />
</form:form>