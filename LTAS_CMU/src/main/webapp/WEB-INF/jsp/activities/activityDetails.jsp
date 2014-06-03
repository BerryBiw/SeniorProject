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
            <td><b><c:out value="${activity.name}"/></b></td>
        </tr>
        <tr>
            <th>Description</th>
            <td><c:out value="${activity.description}"/></td>
        </tr>
        <tr>
            <th>Member</th>
            <td><c:out value="${activity.numberStudent}"/></td>
        </tr>

        <tr>
            <th>Start date</th>
            <td><joda:format value="${activity.startDate}" pattern="yyyy-MM-dd"/></td>
        </tr>
        <tr>
            <th>End date</th>
            <td><joda:format value="${activity.endDate}" pattern="yyyy-MM-dd"/></td>
        </tr>

        <tr>
            <th>Type</th>
            <td><c:out value="${activity.type.name}"/></td>
        </tr>

         <tr>
            <td> 
            	<spring:url value="{activityId}/edit.html" var="editUrl">
                    <spring:param name="activityId" value="${activity.id}"/>
                </spring:url>
                <a href="${fn:escapeXml(editUrl)}" class="btn btn-info">Edit Activity</a></td>
            <td>
            	<spring:url value="{activityId}/students/new.html" var="addUrl">
                    <spring:param name="activityId" value="${activity.id}"/>
                </spring:url>
                <a href="${fn:escapeXml(addUrl)}"  class="btn btn-success">Add New Student</a></td>
        </tr>
    </table>

    <h2>Students and Comments</h2>

    <c:forEach var="student" items="${activity.students}">
        <table class="table" style="width:600px;">
            <tr>
                <td valign="top" style="width: 120px;">
                    <dl class="dl-horizontal">
                        <dt>ID</dt>
                        <dd><c:out value="${student.studentId}"/></dd>
                        <dt>Name</dt>
                        <dd><c:out value="${student.name}"/></dd>
                        <dt>Faculty</dt>
                        <dd><c:out value="${student.faculty.name}"/></dd>
                    </dl>
                </td>
                <td valign="top">
                    <table class="table-condensed">
                        <thead>
                        <tr>
                            <th>Visit Date</th>
                            <th>Comment</th>
                        </tr>
                        </thead>
                        <c:forEach var="visit" items="${student.visits}">
                            <tr>
                                <td><joda:format value="${visit.date}" pattern="yyyy-MM-dd"/></td>
                                <td><c:out value="${visit.description}"/></td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td> 
                            	<spring:url value="/activities/{activityId}/students/{studentId}/edit" var="studentUrl">
			                        <spring:param name="activityId" value="${activity.id}"/>
			                        <spring:param name="studentId" value="${student.id}"/>
			                    </spring:url>
			                    <a href="${fn:escapeXml(studentUrl)}">Edit Student</a>
			                </td>
                            <td>
			                    <spring:url value="/activities/{activityId}/students/{studentId}/visits/new" var="visitUrl">
			                        <spring:param name="activityId" value="${activity.id}"/>
			                        <spring:param name="studentId" value="${student.id}"/>
			                    </spring:url>
			                    <a href="${fn:escapeXml(visitUrl)}">Add Comment</a>
                            </td>
                       	</tr>
                    </table>
                </td>
            </tr>
        </table>
    </c:forEach>

    <jsp:include page="../fragments/footer.jsp"/>

</div>

</body>

</html>
