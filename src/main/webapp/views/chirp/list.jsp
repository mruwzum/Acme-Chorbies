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
			<a href="chirp/create.do"> <spring:message
					code="general.create" />
			</a>
		</H5>
	</div>
</security:authorize>

<!-- Listing grid -->
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="chirps" requestURI="${requestURI}" id="row">


	<!-- Attributes -->

	<security:authorize access="hasRole('CHORBI')">
		<jstl:if test="${re}">
			<display:column>
				<a href="chirp/resend.do?chirpId=${row.id}"> <spring:message
						code="chirp.resend" />
				</a>
			</display:column>
		</jstl:if>
		<jstl:if test="${pl}">
			<display:column>
				<a href="chirp/reply.do?chirpId=${row.id}"> <spring:message
						code="chirp.reply" />
				</a>
			</display:column>
		</jstl:if>
	</security:authorize>
	
	<spring:message code="chirp.message" var="message" />
	<display:column property="message" title="${message}" sortable="true" />
	<spring:message code="chirp.subject" var="subject" />
	<display:column property="subject" title="${subject}" sortable="true" />
	<spring:message code="chirp.moment" var="moment" />
	<display:column property="moment" title="${moment}" sortable="true" />


</display:table>