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

<script>
    $(function () {
        $("#startDate").datepicker({ dateFormat: 'yy/mm/dd'});
    });
    $(function () {
        $("#endDate").datepicker({ dateFormat: 'yy/mm/dd'});
    });
</script>

<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>
    <c:choose>
        <c:when test="${activity['new']}"><c:set var="method" value="post"/></c:when>
        <c:otherwise><c:set var="method" value="put"/></c:otherwise>
    </c:choose>

    <h2>
        <c:if test="${activity['new']}">New </c:if> Activity
    </h2>
    <form:form modelAttribute="activity" method="${method}" class="form-horizontal" id="add-activity-form">
        <petclinic:inputField label="Name" name="name"/>
        <petclinic:inputField label="Description" name="description"/>
        <petclinic:inputField label="Number of students" name="numberStudent"/>
        <petclinic:inputField label="Start date" name="startDate"/>
        <petclinic:inputField label="End date" name="endDate"/>
        <div class="control-group">
            <petclinic:selectField name="type" label="Type " names="${types}" size="5"/>
        </div>


        <div class="form-actions">
            <c:choose>
                <c:when test="${activity['new']}">
                    <button type="submit">Add Activity</button>
                </c:when>
                <c:otherwise>
                    <button type="submit">Update Activity</button>
                </c:otherwise>
            </c:choose>
        </div>
    </form:form>
</div>
<jsp:include page="../fragments/footer.jsp"/>
</body>

</html>
