<!DOCTYPE html> 

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>

<html lang="en">

<jsp:include page="../fragments/headTag.jsp"/>

<body>
<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>
    <h2>Activities</h2>
    
    <datatables:table id="activities" data="${selections}" cdn="true" row="activity" theme="bootstrap2"
                      cssClass="table table-striped" paginate="false" info="false" export="pdf">
        <datatables:column title="Name" cssStyle="width: 150px;" display="html">
            <spring:url value="/activities/{activityId}.html" var="activityUrl">
                <spring:param name="activityId" value="${activity.id}"/>
            </spring:url>
            <a href="${fn:escapeXml(activityUrl)}"><c:out value="${activity.name}"/></a>
        </datatables:column>
        <datatables:column title="Name" display="pdf">
            <c:out value="${activity.name}"/>
        </datatables:column>
        <datatables:column title="Description" property="description" cssStyle="width: 200px;"/>
        <datatables:column title="Member" property="numberStudent"  />
        <datatables:column title="Start date" property="startDatet" cssStyle="width: 100px;"/>
        <datatables:column title="End date" property="endDatet" cssStyle="width: 100px;"/>
        <datatables:column title="Type" property="type"/>
        <datatables:column title="Students" cssStyle="width: 100px;">
            <c:forEach var="student" items="${activity.students}">
                <c:out value="${student.name}"/>
            </c:forEach>
        </datatables:column>
        <datatables:export type="pdf" cssClass="btn btn-small" />
    </datatables:table>
    
    <jsp:include page="../fragments/footer.jsp"/>

</div>
</body>

</html>
