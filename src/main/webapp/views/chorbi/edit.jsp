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

	<%--TODO Estos hay que mostrarlos--%>
	<form:hidden path="genre" />
	<form:hidden path="relationship" />
	<form:hidden path="creditCard" />
	<form:hidden path="search" />
	<form:hidden path="coordinate" />


	<acme:textbox path="picture" code="chorbi.picture"/>
	<br />
	<acme:textbox path="description" code="chorbi.description"/>
	<br />

	<!---------------------------- BOTONES -------------------------->
	<acme:submit name="save" code="general.save"/>

	<input type="button" name="cancel"
		value="<spring:message code="general.cancel" />"
		onclick="javascript: window.location.replace('/profile.do')" />

</form:form>