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
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="likes" requestURI="${requestURI}" id="row">


	<!-- Attributes -->

	<security:authorize access="hasRole('CHORBI')">
		<jstl:if test="${my}">
		<display:column>
			<a href="liked/delete.do?likeId=${row.id}"> <spring:message
					code="general.delete" />
			</a>
		</display:column>
		</jstl:if>
	</security:authorize>
	
	<spring:message code="like.moment" var="moment" />
	<display:column property="moment" title="${moment}" sortable="true" />
	<spring:message code="like.text" var="text" />
	<display:column property="text" title="${text}" sortable="true" />
	<spring:message code="like.sender" var="sender" />
	<display:column property="sender" title="${sender}" sortable="true" />
	<spring:message code="like.receiver" var="receiver" />
	<display:column property="receiver" title="${receiver}" sortable="true" />
	<security:authorize access="hasRole('CHORBI')">
		<jstl:if test="${my}">
		<display:column>
				<a href="chorbi/view.do?likeId=${row.id}"> <spring:message
						code="general.viewz" />
				</a>
			</display:column>
		</jstl:if>
	</security:authorize>
</display:table>