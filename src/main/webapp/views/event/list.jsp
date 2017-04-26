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



<security:authorize access="hasRole('MANAGER')">

	<a href="event/create.do"> <spring:message
			code="general.create" />
	</a>

</security:authorize>
<!-- Listing grid -->
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="event" id="row">


	<!-- Attributes -->

	<security:authorize access="hasRole('MANAGER')">

			<display:column>
				<a href="event/delete.do?eventId=${row.id}"> <spring:message
						code="general.delete" />
				</a>
			</display:column>

			<display:column>
				<a href="event/edit.do?eventId=${row.id}"> <spring:message
						code="general.edit" />
				</a>
			</display:column>
	</security:authorize>
	
	<spring:message code="event.title" var="title" />
	<display:column property="title" title="${title}" sortable="true" />
	<spring:message code="event.date" var="date" />
	<display:column property="date" title="${date}" sortable="true" />
	<spring:message code="event.description" var="description" />
	<display:column property="description" title="${description}" sortable="true" />

	<display:column>
		<a href="event/view.do?eventId=${row.id}"> <spring:message
				code="general.viewz" />
		</a>
	</display:column>

</display:table>