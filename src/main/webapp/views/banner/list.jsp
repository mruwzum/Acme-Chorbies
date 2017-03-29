<%@page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security"
          uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>

<security:authorize access="hasRole('ADMINISTRATOR')">
    <div>
        <H5>
            <a href="banner/create.do"> <spring:message
                    code="banner.create"/>
            </a>
        </H5>
    </div>
</security:authorize>

<!-- Listing grid -->
<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="banner" requestURI="${requestURI}" id="row">

    <security:authorize access="hasRole('ADMINISTRATOR')">
        <display:column>
            <a href="banner/edit.do?bannerId=${row.id}"> <spring:message
                    code="banner.edit"/></a>
        </display:column>
    </security:authorize>

    <!-- Attributes -->

    <spring:message code="banner.url" var="url"/>
    <display:column property="url" title="${url}" sortable="true"/>
    <spring:message code="banner.url" var="url"/>



    <security:authorize access="hasRole('ADMINISTRATOR')">
        <display:column>
            <a href="banner/delete.do?bannerId=${row.id}"> <spring:message
                    code="comment.delete"/>
            </a>
        </display:column>
    </security:authorize>
</display:table>