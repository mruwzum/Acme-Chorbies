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
			<a href="credit-card/create.do"> <spring:message
					code="general.create" />
			</a>
		</H5>
	</div>
</security:authorize>

<!-- Listing grid -->
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="cards" requestURI="${requestURI}" id="row">


	<!-- Attributes -->

	<security:authorize access="permitAll">
		<display:column>
			<a href="credit-card/edit.do?credit-cardId=${row.id}"> <spring:message
					code="general.edit" />
			</a>
		</display:column>
	</security:authorize>
	
	<spring:message code="cc.holderr" var="holder" />
	<display:column property="holder" title="${holder}" sortable="true" />
	<spring:message code="cc.number" var="number" />
	<display:column property="number" title="${number}" sortable="true" />
	<spring:message code="cc.ExpirationYear" var="ExpirationYear" />
	<display:column property="ExpirationYear" title="${ExpirationYear}" sortable="true" />
	<spring:message code="cc.ExpirationMonth" var="ExpirationMonth" />
	<display:column property="ExpirationMonth" title="${ExpirationMonth}" sortable="true" />


</display:table>