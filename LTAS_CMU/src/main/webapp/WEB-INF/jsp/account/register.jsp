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
            <td>First name:</td> <td>
            <input type='text' name='firstName'></td>

        </tr>

        <tr>
            <td>Last name:</td> <td>
            <input type='text' name='lastName'></td>

        </tr>
        <tr>
            <td>Faculty:</td>
            <td><input type='text' name='faculty' /></td>
        </tr>

        <tr>
            <td>Email:</td>
            <td><input type='text' name='email'></td>
        </tr>
        <tr>
            <td>Phone-number:</td>
            <td><input type='text' name='telNum' /></td>
        </tr>
        <tr>
            <td>User name:</td>
            <td><input type='text' name='username'></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password' name='password' /></td>
        </tr>
        <tr>
            <td>Repeat Password:</td>
            <td><input type='password' name='re-password' /></td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit"
                                   value="submit" /></td>
        </tr>

    </table>
    </form>




</div>
<jsp:include page="../fragments/footer.jsp"/>

</body>
</html>