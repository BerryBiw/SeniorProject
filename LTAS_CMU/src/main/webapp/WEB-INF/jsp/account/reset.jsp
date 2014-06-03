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

    <h2>Register information</h2>

    <form name='loginForm'
          action="<spring:url value="/login.html" htmlEscape="true"/>" >

        <table>
            <tr>
                <td>Please enter your email:</td> <td>
                <input type='text' name='email'></td>

            </tr>

        </table>
    </form>




</div>
<jsp:include page="../fragments/footer.jsp"/>

</body>
</html>