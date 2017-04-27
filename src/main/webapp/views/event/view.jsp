<%@page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
          uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<img src="${picture}" width="260" height="200" >
<br>

<spring:message code="event.title" var="title1"/>
<h3><jstl:out value="${title1}"/></h3>
<jstl:out value="${title}"/>


<spring:message code="event.date" var="date1"/>
<h3><jstl:out value="${date1}"/></h3>
<jstl:out value="${date}"/>

<spring:message code="event.description" var="description1"/>
<h3><jstl:out value="${description1}"/></h3>
<jstl:out value="${description}"/>

<spring:message code="event.numberOfSeats" var="numberOfSeats1"/>
<h3><jstl:out value="${numberOfSeats1}"/></h3>
<jstl:out value="${numberOfSeats}"/>


<jstl:if test="${reg}">

    <a href="event/unregister.do?eventId=${id}"> <spring:message
            code="event.unregister" />
    </a>


</jstl:if>


<jstl:if test="${not reg}">

    <a href="event/register.do?eventId=${id}"> <spring:message
            code="event.register" />
    </a>


</jstl:if>





<security:authorize access="hasRole('MANAGER')">

<br>
<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="chorbis" id="row">

    <spring:message code="administrator.name" var="name" />
    <display:column property="name" title="${name}" sortable="true" />
    <spring:message code="administrator.surname" var="surname" />
    <display:column property="surname" title="${surname}" sortable="true" />

</display:table>

</security:authorize>