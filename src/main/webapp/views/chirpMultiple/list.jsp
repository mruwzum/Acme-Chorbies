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


<!-- Listing grid -->
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="chirps" id="row">


	<!-- Attributes -->

	<security:authorize access="hasRole('MANAGER')">

	</security:authorize>
	
	<spring:message code="chirp.message" var="message" />
	<display:column property="message" title="${message}" sortable="true" />
	<spring:message code="chirp.subject" var="subject" />
	<display:column property="subject" title="${subject}" sortable="true" />
	<spring:message code="chirp.moment" var="moment" />
	<display:column property="moment" title="${moment}" sortable="true" />
	<spring:message code="chirp.sender" var="sender" />
	<display:column property="sender" title="${sender}" sortable="true" />
	<spring:message code="chirp.receiver" var="sender" />
	<display:column property="receivers" title="${receivers}" sortable="true" />
</display:table>