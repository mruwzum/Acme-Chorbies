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

<security:authorize access="permitAll">
	<div>
		<H5>
			<a href="coordinate/create.do"> <spring:message
					code="general.create" />
			</a>
		</H5>
	</div>
</security:authorize>

<!-- Listing grid -->
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="coordinates" requestURI="${requestURI}" id="row">


	<!-- Attributes -->

	<security:authorize access="permitAll">
		<display:column>
			<a href="coordinate/edit.do?coordinateId=${row.id}"> <spring:message
					code="general.edit" />
			</a>
		</display:column>
	</security:authorize>
	
	<spring:message code="coordinate.country" var="country" />
	<display:column property="country" title="${country}" sortable="true" />
	<spring:message code="coordinate.state" var="state" />
	<display:column property="state" title="${state}" sortable="true" />
	<spring:message code="coordinate.province" var="province" />
	<display:column property="province" title="${province}" sortable="true" />
	<spring:message code="coordinate.city" var="city" />
	<display:column property="city" title="${city}" sortable="true" />


</display:table>