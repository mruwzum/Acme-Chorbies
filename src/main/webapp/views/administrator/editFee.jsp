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

<form:form action="administrator/editFee.do" modelAttribute="manager">

    <form:hidden path="id" />
    <form:hidden path="version" />
    <form:hidden path="name" />
    <form:hidden path="surname" />
    <form:hidden path="email" />
    <form:hidden path="phone" />
    <form:hidden path="age" />
    <form:hidden path="company" />
    <form:hidden path="vatNumber" />
    <form:hidden path="creditCard" />
    <form:hidden path="totalFee" />


    <acme:textbox path="fee" code="manager.fee"/>




    <!---------------------------- BOTONES -------------------------->
    <input type="submit" name="save"
           value="<spring:message code="actor.save" />"/>

    <input type="button" name="cancel"
           value="<spring:message code="general.cancel" />"
           onclick="javascript: window.location.replace('/manager/list.do')" />


</form:form>