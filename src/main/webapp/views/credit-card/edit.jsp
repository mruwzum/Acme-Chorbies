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

<form:form action="credit-card/edit.do" modelAttribute="creditcard">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="holder" />


	<acme:textbox path="number" code="cc.number"/>
	<acme:textbox path="ExpirationMonth" code="cc.ExpirationMonth"/>
	<acme:textbox path="ExpirationYear" code="cc.ExpirationYear"/>
	<acme:textbox path="CVV" code="cc.cvv"/>

	<br />


	<!---------------------------- BOTONES -------------------------->
	<acme:submit name="save" code="general.save"/>
	<input type="button" name="cancel"
		   value="<spring:message code="general.cancel" />"
		   onclick="javascript: window.location.replace('/chorbi/list.do')" />


</form:form>