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

<spring:message code="event.near" var="name1"/>
<h3><jstl:out value="${name1}"/></h3>

<jstl:if test="${all}">
<div class="highlighted">
</jstl:if>


<!-- Listing grid ok events -->

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
		<display:column>
			<a href="chirpMultiple/create.do?eventId=${row.id}"> <spring:message
					code="chirp.allRegistered" />
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
				code="event.view" />
		</a>
	</display:column>

</display:table>

<jstl:if test="${all}">
</div>
</jstl:if>

<spring:message code="event.rest" var="name1"/>
<h3><jstl:out value="${name1}"/></h3>

<!-- Listing grid rest of events -->

<jstl:if test="${all}">
<display:table pagesize="5" class="displaytag" keepStatus="true"
			   name="restOfEvents" id="row">


	<!-- Attributes -->



	<spring:message code="event.title" var="title" />
	<display:column property="title" title="${title}" sortable="true" />
	<spring:message code="event.date" var="date" />
	<display:column property="date" title="${date}" sortable="true" />
	<spring:message code="event.description" var="description" />
	<display:column property="description" title="${description}" sortable="true" />

	<jstl:if test="${not all}">
	<display:column>
		<a href="event/view.do?eventId=${row.id}"> <spring:message
				code="event.view" />
		</a>
	</display:column>
	</jstl:if>

</display:table>

</jstl:if>

<spring:message code="event.past" var="name1"/>
<h3><jstl:out value="${name1}"/></h3>

<!-- Listing grid past events -->
<div class="overlay">

<jstl:if test="${all}">
<display:table pagesize="5" class="displaytag" keepStatus="true"
			   name="lastEvents" id="row" >


	<!-- Attributes -->



	<spring:message code="event.title" var="title" />
	<display:column property="title" title="${title}" sortable="true" />
	<spring:message code="event.date" var="date" />
	<display:column property="date" title="${date}" sortable="true" />
	<spring:message code="event.description" var="description" />
	<display:column property="description" title="${description}" sortable="true" />

	<jstl:if test="${not all}">
	<display:column>
		<a href="event/view.do?eventId=${row.id}"> <spring:message
				code="event.view" />
		</a>
	</display:column>
	</jstl:if>
</display:table>

</jstl:if>

</div>