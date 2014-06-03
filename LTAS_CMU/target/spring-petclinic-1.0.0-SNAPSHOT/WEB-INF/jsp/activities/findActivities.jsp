<!DOCTYPE html> 

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html lang="en">

<jsp:include page="../fragments/headTag.jsp"/>

<body>
<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>

    <h2>Find Activities</h2>

    <spring:url value="/activities.html" var="formUrl"/>
    <form:form modelAttribute="activity" action="${fn:escapeXml(formUrl)}" method="get" class="form-horizontal"
               id="search-activity-form">
        <fieldset>
            <div class="control-group" id="name">
                <label class="control-label">Name </label>
                <form:input path="name" size="30" maxlength="80"/>
                <span class="help-inline"><form:errors path="*"/></span>
            </div>
            <div class="form-actions">
                <button type="submit">Find Activity</button>
            </div>
        </fieldset>
    </form:form>

    <br/>
    <a href='<spring:url value="/activities/new" htmlEscape="true"/>'>Add Activity</a>

    <jsp:include page="../fragments/footer.jsp"/>

</div>
</body>

</html>
