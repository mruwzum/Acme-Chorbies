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
			<a href="chorbi/create.do"> <spring:message
					code="general.create" />
			</a>
		</H5>
	</div>
</security:authorize>

<!-- Listing grid -->
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="chorbis" requestURI="${requestURI}" id="row">


	<!-- Attributes -->

	<security:authorize access="permitAll">
		<display:column>
			<a href="chorbi/edit.do?chorbiId=${row.id}"> <spring:message
					code="general.edit" />
			</a>
		</display:column>
	</security:authorize>
	
	<spring:message code="administrator.name" var="name" />
	<display:column property="name" title="${name}" sortable="true" />
	<spring:message code="administrator.surname" var="surname" />
	<display:column property="surname" title="${surname}" sortable="true" />
	<spring:message code="chorbi.description" var="description" />
	<display:column property="description" title="${description}" sortable="true" />
	<spring:message code="chorbi.genre" var="genre" />
	<display:column property="genre" title="${genre}" sortable="true" />
	<spring:message code="chorbi.relationship" var="relationship" />
	<display:column property="relationship" title="${relationship}" sortable="true" />
	<spring:message code="chorbi.coordinate" var="coordinate" />
	<display:column property="coordinate" title="${coordinate}" sortable="true" />


</display:table>