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

<form:form action="search/find.do" modelAttribute="search">

	<form:hidden path="id" />
	<form:hidden path="version" />


	<acme:textbox path="age" code="search.age"/>
	<br />
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
	<br />
	<acme:textbox path="coordinate.country" code="coordinate.country"/>
	<acme:textbox path="coordinate.state" code="coordinate.state"/>
	<acme:textbox path="coordinate.city" code="coordinate.city"/>
	<acme:textbox path="coordinate.province" code="coordinate.province"/>
	<br />

	<acme:textbox path="keyword" code="search.keyword"/>
	<br />

	<!---------------------------- BOTONES -------------------------->
	<acme:submit name="save" code="general.save"/>

	<input type="button" name="cancel"
		value="<spring:message code="general.cancel" />"
		onclick="javascript: window.location.replace('/profile.do')" />

</form:form>