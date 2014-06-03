<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<html lang="en">

<jsp:include page="../fragments/headTag.jsp"/>

<body>



<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>
    <c:choose>
        <c:when test="${instructor['new']}"><c:set var="method" value="post"/></c:when>
        <c:otherwise><c:set var="method" value="put"/></c:otherwise>
    </c:choose>

    <h2>
        <c:if test="${instructor['new']}">New </c:if> Instructor
    </h2>
    <form:form modelAttribute="instructor" method="${method}" class="form-horizontal" id="add-instructor-form">
        <petclinic:inputField label="First Name" name="firstName"/>
        <petclinic:inputField label="Last Name" name="lastName"/>
        <div class="control-group">
            <petclinic:selectField name="faculty" label="Faculty " names="${faculties}" size="5"/>
        </div>
        <petclinic:inputField label="Email" name="email"/>
        <petclinic:inputField label="Telephone" name="telephone"/>
        <petclinic:inputField label="User name" name="userName"/>
        <petclinic:inputField label="Password" name="password"/>




        <div class="form-actions">
            <c:choose>
                <c:when test="${instructor['new']}">
                    <button type="submit">Add Instructor</button>
                </c:when>
                <c:otherwise>
                    <button type="submit">Update Instructor</button>
                </c:otherwise>
            </c:choose>
        </div>
    </form:form>
</div>
<jsp:include page="../fragments/footer.jsp"/>
</body>

</html>
