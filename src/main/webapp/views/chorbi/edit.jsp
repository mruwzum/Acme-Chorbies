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
	<form:hidden path="creditCard" />
	<form:hidden path="search" />


	<%--Personal information--%>
	<spring:message code="chorbi.personal" var="personal"/>
	<jstl:out value="${personal}"/>

	<acme:textbox path="picture" code="chorbi.picture"/>
	<br />
	<acme:textbox path="description" code="chorbi.description"/>
	<br />

	<%-- TODO Hay que pasarle una lista con todos los elementos de los diferentes enum --%>
	<acme:select path="genre" code="chorbi.genre" items="${availableGenre}" itemLabel="*** Select Option ***"/>
	<acme:select path="relationship" code="chorbi.relationship" items="${availableRelation}" itemLabel="*** Select Option ***"/>



	<%--Location information--%>
	<spring:message code="chorbi.location" var="location"/>
	<jstl:out value="${location}"/>

	<acme:textbox path="coordinate.country" code="coordinate.country"/>
	<br />
	<acme:textbox path="coordinate.state" code="coordinate.state"/>
	<br />
	<acme:textbox path="coordinate.province" code="coordinate.province"/>
	<br />
	<acme:textbox path="coordinate.city" code="coordinate.city"/>
	<br />



	<!---------------------------- BOTONES -------------------------->
	<acme:submit name="save" code="general.save"/>

	<input type="button" name="cancel"
		value="<spring:message code="general.cancel" />"
		onclick="javascript: window.location.replace('/profile.do')" />

</form:form>