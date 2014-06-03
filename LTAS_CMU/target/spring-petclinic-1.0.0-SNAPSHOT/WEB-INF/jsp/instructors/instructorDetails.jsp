<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<html lang="en">

<jsp:include page="../fragments/headTag.jsp"/>

<body>
<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>

    <h2>Activity Information</h2>

    <table class="table table-striped" style="width:600px;">
        <tr>
            <th>Name</th>
            <td><b><c:out value="${instructor.firstName} ${instructor.lastName}"/></b></td>
        </tr>
        <tr>
            <th>Faculty</th>
            <td><c:out value="${instructor.faculty.name}"/></td>
        </tr>
        <tr>
            <th>Email</th>
            <td><c:out value="${instructor.email}"/></td>
        </tr>

        <tr>
            <th>Telephone</th>
            <td><joda:format value="${instructor.telephone}"/></td>
        </tr>
        <tr>
            <th>User name</th>
            <td><joda:format value="${instructor.userName}"/></td>
        </tr>

        <tr>
            <th>Password</th>
            <td><joda:format value="${instructor.password}"/></td>
        </tr>


        <tr>
            <td>
                <spring:url value="{instructorId}/edit.html" var="editUrl">
                    <spring:param name="instructorId" value="${instructor.id}"/>
                </spring:url>
                <a href="${fn:escapeXml(editUrl)}" class="btn btn-info">Edit Profile</a></td>

        </tr>
    </table>




    <jsp:include page="../fragments/footer.jsp"/>

</div>

</body>

</html>
