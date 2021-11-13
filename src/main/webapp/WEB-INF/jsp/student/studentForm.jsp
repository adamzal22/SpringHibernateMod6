<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<form:form method="post" modelAttribute="student">
    Imie: <form:input path="firstName"/> <br />
    Nazwisko: <form:input path="lastName"/> <br />
    Płeć: <br />
    K: <form:radiobutton path="gender" value="K"/><br />
    M: <form:radiobutton path="gender" value="M"/><br />
    Kraj: <form:select path="country" items="${countries}"/> <br />
    Notki: <form:input path="notes"/> <br />
    Lista mail: <form:checkbox path="mailingList" value="Tak"/> <br />
    Język programowania:<form:select path="programmingSkills" items="${programmingSkills}" multiple="true"/> <br />
    Hobby: <form:checkboxes path="hobbies" items="${hobbies}" multiple="true"/> <br />
    <input type="submit" value="Wysyłam"> <br />
</form:form>