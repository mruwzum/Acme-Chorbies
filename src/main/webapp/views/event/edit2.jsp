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
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="event/edit.do" modelAttribute="event">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="partakers" />
	<form:hidden path="announcements" />




	<acme:textbox path="title" code="event.title"/>
	<br />
	<acme:textbox path="date" code="event.date"/>
	<br />
	<acme:textbox path="description" code="event.description"/>
	<br />
	<acme:textbox path="picture" code="event.picture"/>
	<br />
	<acme:textbox path="numberOfSeats" code="event.numberOfSeats"/>
	<br />


	<!---------------------------- BOTONES -------------------------->
	<acme:submit name="save" code="general.save"/>

	<input type="button" name="cancel"
		   value="<spring:message code="general.cancel" />"
		   onclick="javascript: window.location.replace('/event/list.do')" />


<%--</form:form>--%>
<%--<br>--%>
<%--<display:table pagesize="5" class="displaytag" keepStatus="true"--%>
			   <%--name="events" id="row">--%>



	<%--<spring:message code="chirp.message" var="message" />--%>
	<%--<display:column property="message" title="${message}" sortable="true" />--%>
	<%--<spring:message code="chirp.subject" var="subject" />--%>
	<%--<display:column property="subject" title="${subject}" sortable="true" />--%>
	<%--<spring:message code="chirp.moment" var="moment" />--%>
	<%--<display:column property="moment" title="${moment}" sortable="true" />--%>
	<%--<spring:message code="chirp.sender" var="sender" />--%>
	<%--<display:column property="sender" title="${sender}" sortable="true" />--%>
	<%--<spring:message code="chirp.receiver" var="sender" />--%>
	<%--<display:column property="receivers" title="${receivers}" sortable="true" />--%>
<%--</display:table>--%>

<%--<br>--%>

<%--<display:table pagesize="5" class="displaytag" keepStatus="true"--%>
			   <%--name="chorbis" id="row">--%>

	<%--<spring:message code="administrator.name" var="name" />--%>
	<%--<display:column property="name" title="${name}" sortable="true" />--%>
	<%--<spring:message code="administrator.surname" var="surname" />--%>
	<%--<display:column property="surname" title="${surname}" sortable="true" />--%>





</display:table>