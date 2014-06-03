<!DOCTYPE html> 

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<html lang="en">

<jsp:include page="../fragments/headTag.jsp"/>
<body>


<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>
    <c:choose>
        <c:when test="${student['new']}">
            <c:set var="method" value="post"/>
        </c:when>
        <c:otherwise>
            <c:set var="method" value="put"/>
        </c:otherwise>
    </c:choose>

    <h2>
        <c:if test="${student['new']}">New </c:if>
        Student
    </h2>

    <form:form modelAttribute="student" method="${method}"
               class="form-horizontal">
        <div class="control-group" id="activity">
            <label class="control-label">Activity </label>

            <c:out value="${student.activity.name}"/>
        </div>
        <petclinic:inputField label="ID" name="studentId"/>
        <petclinic:inputField label="Name" name="name"/>
        <div class="control-group">
            <petclinic:selectField name="faculty" label="Faculty " names="${faculties}" size="5"/>
        </div>
        <div class="form-actions">
            <c:choose>
                <c:when test="${student['new']}">
                    <button type="submit">Add Student</button>
                </c:when>
                <c:otherwise>
                    <button type="submit">Update Student</button>
                </c:otherwise>
            </c:choose>
        </div>
    </form:form>
    <c:if test="${!student['new']}">
    </c:if>
    <jsp:include page="../fragments/footer.jsp"/>
</div>
</body>

</html>
