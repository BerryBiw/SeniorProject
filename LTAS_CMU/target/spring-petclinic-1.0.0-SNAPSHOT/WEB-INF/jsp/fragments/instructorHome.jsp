<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<spring:url value="/resources/images/banner-graphic.png" var="banner"/>
<img src="${banner}"/>

<div class="navbar" style="width: 1000px;">
    <div class="navbar-inner">
        <ul class="nav">
            <li style="width: 135px;"><a href="<spring:url value="/hello.html" htmlEscape="true" />"><i class="icon-home"></i>
                Home</a></li>
            <li style="width: 135px;"><a href="<spring:url value="/instructors.html" htmlEscape="true" />"><i
                    class="icon-th-list"></i> Activities</a></li>
            <li style="width: 135px;"><a href="<spring:url value="/oups.html" htmlEscape="true" />"
                                         title="trigger a RuntimeException to see how it is handled"><i
                    class="icon-edit"></i> Management</a></li>
            <li style="width: 135px;"><a href="<spring:url value="/oups.html" htmlEscape="true" />"
                                         title="trigger a RuntimeException to see how it is handled"><i
                    class="icon-calendar"></i> Download</a></li>
            <li style="width: 135px;"><a href="<spring:url value="/instructor.html" htmlEscape="true" />"><i
                    class="icon-th-large"></i> Profile</a></li>
            <li style="width: 135px;"><a href="<spring:url value="/oups.html" htmlEscape="true" />"
                                         title="trigger a RuntimeException to see how it is handled"><i
                    class="icon-star"></i> Contact us</a></li>
            <li style="width: 135px;"><a href="<spring:url value="/login.html" htmlEscape="true" />"
                                         title="trigger a RuntimeException to see how it is handled"><i
                    class="icon-user"></i> Logout</a></li>
        </ul>
    </div>
</div>
	