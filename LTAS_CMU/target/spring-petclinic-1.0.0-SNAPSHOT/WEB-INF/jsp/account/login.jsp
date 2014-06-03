<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html lang="en">
<jsp:include page="../fragments/headTag.jsp"/>

<head>
    <meta charset="utf-8">

    <title>Social-Tooltip</title>
    <style type="text/css">
        @charset "utf-8";
        /* CSS Document */




        .facebook-before {
            background-color: #0064ab;
            border-radius: 3px 0px 0px 3px;
            -moz-border-radius: 3px 0px 0px 3px;
            -webkit-border-radius: 3px 0px 0px 3px;
            color: #f4f4f4;
            display: block;
            float: left;
            height: 50px;
            line-height: 50px;
            text-align: center;
            width: 50px;
        }

        .facebook {
            background-color: #0079ce;
            border: none;
            border-radius: 0px 3px 3px 0px;
            -moz-border-radius: 0px 3px 3px 0px;
            -webkit-border-radius: 0px 3px 3px 0px;
            color: #f4f4f4;
            cursor: pointer;
            height: 50px;
            text-transform: uppercase;
            width: 250px;
        }
    </style>


        </head>


<body onload='document.loginForm.username.focus();'>


<div class="container" id="login-box">
    <jsp:include page="../fragments/bodyHeader.jsp"/>
    <h3>Login with Username and Password</h3>

    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>
    <c:if test="${not empty msg}">
        <div class="msg">${msg}</div>
    </c:if>

    <form name='loginForm'
          action="<spring:url value="/hello.html" htmlEscape="true"/>" >

        <table>
            <tr>
                <td>User name:</td>
                <td><input type='text' name='username'></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type='password' name='password' /></td>
            </tr>
            <tr>
                <td colspan='2'><input name="submit" type="submit"
                                       value="submit" /></td>
            </tr>

        </table>

        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}" />

    </form>

    <p><a href='<spring:url value="/reset.html" htmlEscape="true"/> '> Forgot Password?</a>         <a href='<spring:url value="/register.html" htmlEscape="true"/>'>Register Now!</a>
    </p>




    <p>

        <a class="facebook-before"><span class="fontawesome-facebook"></span></a>
        <button class="facebook">Login Using Facbook</button>

    </p>



    <jsp:include page="../fragments/footer.jsp"/>



</div>


</body>

</html>
