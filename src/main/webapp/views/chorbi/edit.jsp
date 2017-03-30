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

<form:form action="chorbi/edit.do" modelAttribute="chorbi">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="chirps" />
	<form:hidden path="likes" />
	<form:hidden path="myChirps" />
	<form:hidden path="myLikes" />
	<form:hidden path="search" />

	<form:hidden path="userAccount" />

	<%--Personal information--%>
	<spring:message code="chorbi.personal" var="personal"/>
	<jstl:out value="${personal}"/>

	<acme:textbox path="name" code="customer.name"/>
	<acme:textbox path="surname" code="customer.surname"/>
	<acme:textbox path="email" code="customer.email"/>
	<acme:textbox path="phone" code="customer.phone"/>
	<acme:textbox path="birthDate" code="customer.birth"/>
	<form:label path="genre">
		<spring:message code="chorbi.genre"/>:
	</form:label>
	<form:select path="genre">
		<form:options/>
	</form:select>
	<br>
	<form:label path="relationship">
		<spring:message code="chorbi.relationship"/>:
	</form:label>
	<form:select path="relationship">
		<form:options/>
	</form:select>
	<acme:textbox path="description" code="chorbi.description"/>
	<br>

	<img src="${chorbi.picture}" width="130" height="100" >
	<acme:textbox path="picture" code="chorbi.picture"/>



	<%--Location information--%>
	<h1>Location</h1>
	<form:label path="coordinate.country">
		<spring:message code="coordinate.country"/>:
	</form:label>
	<form:input path="coordinate.country"/>
	<form:errors cssClass="error" path="coordinate.country"/>


	<form:label path="coordinate.state">
		<spring:message code="coordinate.state"/>:
	</form:label>
	<form:input path="coordinate.state"/>
	<form:errors cssClass="error" path="coordinate.state"/>


	<form:label path="coordinate.city">
		<spring:message code="coordinate.city"/>:
	</form:label>
	<form:input path="coordinate.city"/>
	<form:errors cssClass="error" path="coordinate.city"/>


	<form:label path="coordinate.province">
		<spring:message code="coordinate.province"/>:
	</form:label>
	<form:input path="coordinate.province"/>
	<form:errors cssClass="error" path="coordinate.province"/>


	<h3><spring:message code="edit.creditcard" var="cc"/>
		<jstl:out value="${cc}"/>
	</h3>
	<acme:textbox path="creditCard.holder" code="creditCard.holderName"/>
	<form:label path="creditCard.brand">
	<spring:message code="creditCard.brandName"/>:
	</form:label>
	<form:select path="creditCard.brand" code="creditCard.brandName">
	<form:options/>
	</form:select>
	<acme:textbox path="creditCard.number" code="creditCard.number"/>
	<acme:textbox path="creditCard.expirationYear" code="creditCard.expirationYear"/>
	<acme:textbox path="creditCard.expirationMonth" code="creditCard.expirationMonth"/>
	<acme:textbox path="creditCard.CVV" code="creditCard.CVV"/>



	<!---------------------------- BOTONES -------------------------->
	<acme:submit name="save" code="general.save"/>

	<input type="button" name="cancel"
		value="<spring:message code="general.cancel" />"
		onclick="javascript: window.location.replace('/profile.do')" />

</form:form>