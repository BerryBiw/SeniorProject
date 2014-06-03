<!DOCTYPE html> 

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>

<html lang="en">


<jsp:include page="../fragments/headTag.jsp"/>

<body>
<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>

    <h2>Instructors</h2>

    <datatables:table id="instructors" data="${instructors.instructorList}" cdn="true" row="instructor" theme="bootstrap2" cssClass="table table-striped" paginate="false" info="false">
        <datatables:column title="Name">
            <c:out value="${instructor.firstName} ${instructor.lastName}"></c:out>
        </datatables:column>
        <datatables:column title="Faculties">
            <c:forEach var="faculty" items="${instructor.faculties}">
                <c:out value="${faculty.name}"/>
            </c:forEach>
            <c:if test="${instructor.nrOfFaculties == 0}">none</c:if>
        </datatables:column>
        <datatables:column title="Email">
            <c:out value="${instructor.email}"></c:out>
        </datatables:column>
        <datatables:column title="Telephone">
            <c:out value="${instructor.telephone}"></c:out>
        </datatables:column>
        <datatables:column title="User name">
            <c:out value="${instructor.userName}"></c:out>
        </datatables:column>

    </datatables:table>
    
    <table class="table-buttons">
        <tr>
            <td>
                <a href="<spring:url value="/instructors.xml" htmlEscape="true" />">View as XML</a>
            </td>
            <td>
                <a href="<spring:url value="/instructors.atom" htmlEscape="true" />">Subscribe to Atom feed</a>
            </td>
        </tr>
    </table>

    <jsp:include page="../fragments/footer.jsp"/>
</div>
</body>

</html>
