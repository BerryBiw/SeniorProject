<!DOCTYPE html> 

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<html lang="en">

<jsp:include page="../fragments/headTag.jsp"/>


<body>
<script>
    $(function () {
        $("#date").datepicker({ dateFormat: 'yy/mm/dd'});
    });
</script>
<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>
    <h2><c:if test="${visit['new']}">New </c:if>Visit</h2>

    <b>Student</b>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Faculty</th>
            <th>Activity</th>
        </tr>
        </thead>
        <tr>
            <td><c:out value="${visit.student.studentId}"/></td>
            <td><c:out value="${visit.student.name}"/></td>
            <td><c:out value="${visit.student.faculty.name}"/></td>
            <td><c:out value="${visit.student.activity.name}"/></td>
        </tr>
    </table>

    <form:form modelAttribute="visit">
        <div class="control-group">
            <label class="control-label">Date </label>

            <div class="controls">
                <form:input path="date"/>
                <span class="help-inline"><form:errors path="date"/></span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Comment </label>

            <div class="controls">
                <form:input path="description"/>
                <span class="help-inline"><form:errors path="description"/></span>
            </div>
        </div>
        <div class="form-actions">
            <input type="hidden" name="studentId" value="${visit.student.id}"/>
            <button type="submit">Add Comment</button>
        </div>
    </form:form>

    <br/>
    <b>Previous Comments</b>
    <table style="width: 333px;">
        <tr>
            <th>Date</th>
            <th>Comment</th>
        </tr>
        <c:forEach var="visit" items="${visit.student.visits}">
            <c:if test="${!visit['new']}">
                <tr>
                    <td><joda:format value="${visit.date}" pattern="yyyy/MM/dd"/></td>
                    <td><c:out value="${visit.description}"/></td>
                </tr>
            </c:if>
        </c:forEach>
    </table>

</div>
<jsp:include page="../fragments/footer.jsp"/>
</body>

</html>
