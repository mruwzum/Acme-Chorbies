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
	name="searchs" requestURI="${requestURI}" id="row">


	<security:authorize access="hasRole('CHORBI')">
		<display:column>
			<a href="search/findR.do?searchId=${row.id}"> <spring:message
					code="general.view.search" />
			</a>
		</display:column>
	</security:authorize>
	<!-- Attributes -->
	
	<spring:message code="search.age" var="age" />
	<display:column property="age" title="${age}" sortable="true" />
	<spring:message code="search.keyword" var="keyword" />
	<display:column property="keyword" title="${keyword}" sortable="true" />
	<spring:message code="chorbi.genre" var="genre" />
	<display:column property="genre" title="${genre}" sortable="true" />
	<spring:message code="chorbi.relationship" var="relationship" />
	<display:column property="relationship" title="${relationship}" sortable="true" />

	<spring:message code="coordinate.country" var="coordinate.country" />
	<display:column property="coordinate.country" title="${country}" sortable="true" />


	<spring:message code="coordinate.state" var="coordinate.state" />
	<display:column property="coordinate.state" title="${state}" sortable="true" />
	<spring:message code="coordinate.province" var="coordinate.province" />
	<display:column property="coordinate.province" title="${province}" sortable="true" />
	<spring:message code="coordinate.city" var="coordinate.city" />
	<display:column property="coordinate.city" title="${city}" sortable="true" />
</display:table>