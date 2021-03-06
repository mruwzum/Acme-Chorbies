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


<!-- Listing grid -->
<display:table pagesize="15" class="displaytag" keepStatus="true"
	name="chorbies" id="row">


	<%--<!-- Attributes -->--%>




	<spring:message code="administrator.name" var="name" />
	<display:column property="name" title="${name}" sortable="true" />
	<spring:message code="administrator.surname" var="surname" />
	<display:column property="surname" title="${surname}" sortable="true" />
	<%--<spring:message code="chorbi.description" var="description" />--%>
	<%--<display:column property="description" title="${description}" sortable="true" />--%>
	<spring:message code="administrator.totalFeeToPay" var="fee" />
	<display:column property="totalFeeToPay" title="${fee}" sortable="true" />

</display:table>