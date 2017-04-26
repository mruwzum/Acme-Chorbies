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

<form:form action="manager/edit.do" modelAttribute="manager">

	<form:hidden path="id" />
	<form:hidden path="version" />



	<%--Personal information--%>
	<spring:message code="chorbi.personal" var="personal"/>
	<jstl:out value="${personal}"/>

	<acme:textbox path="name" code="customer.name"/>
	<acme:textbox path="surname" code="customer.surname"/>
	<acme:textbox path="email" code="customer.email"/>
	<acme:textbox path="phone" code="customer.phone"/>
	<acme:textbox path="age" code="customer.birth"/>

	<acme:textbox path="company" code="manager.company"/>
	<acme:textbox path="vatNumber" code="manager.vatNumber"/>


	<%--Location information--%>


	<form:label path="creditCard.brand">
	<spring:message code="creditCard.brandName"/>:
	</form:label>
	<form:select path="creditCard.brand" code="creditCard.brandName">
	<form:options/>
	</form:select>

	<acme:textbox path="creditCard.holder" code="creditCard.holderName"/>
	<acme:textbox path="creditCard.number" code="creditCard.number"/>
	<acme:textbox path="creditCard.expirationYear" code="creditCard.expirationYear"/>
	<acme:textbox path="creditCard.expirationMonth" code="creditCard.expirationMonth"/>
	<acme:textbox path="creditCard.CVV" code="creditCard.CVV"/>


	<!---------------------------- BOTONES -------------------------->
	<input type="submit" name="save"
		   value="<spring:message code="actor.save" />"/>

	<input type="button" name="cancel"
		value="<spring:message code="general.cancel" />"
		onclick="javascript: window.location.replace('/manager/list.do')" />

</form:form>