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
<display:table pagesize="15" class="displaytag" keepStatus="true"
	name="managers" id="row">


	<%--<!-- Attributes -->--%>



	<spring:message code="manager.name" var="name" />
	<display:column property="name" title="${name}" sortable="true" />
	<spring:message code="manager.surname" var="surname" />
	<display:column property="surname" title="${surname}" sortable="true" />
	<spring:message code="manager.company" var="company" />
	<display:column property="company" title="${company}" sortable="true" />
	<spring:message code="manager.vatNumber" var="vatNumber" />
	<display:column property="vatNumber" title="${vatNumber}" sortable="true" />




</display:table>