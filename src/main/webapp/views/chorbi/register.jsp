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
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<form:form action="chorbi/register.do" modelAttribute="chorbi">

    <form:hidden path="id"/>
    <form:hidden path="version"/>


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
    <acme:textbox path="picture" code="chorbi.picture"/>
    <h1>Location</h1>
    <br>
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

    <%--<h3><spring:message code="edit.creditcard" var="cc"/>--%>
        <%--<jstl:out value="${cc}"/>--%>
    <%--</h3>--%>
    <%--<acme:textbox path="creditCard.holder" code="creditCard.holderName"/>--%>
    <%--<br />--%>
    <%--<form:label path="creditCard.brand">--%>
        <%--<spring:message code="creditCard.brandName"/>:--%>
    <%--</form:label>--%>
    <%--<form:select path="creditCard.brand" code="creditCard.brandName">--%>
        <%--<form:options/>--%>
    <%--</form:select>--%>
    <%--<acme:textbox path="creditCard.number" code="creditCard.number"/>--%>
    <%--<br />--%>
    <%--<acme:textbox path="creditCard.ExpirationYear" code="creditCard.expirationYear"/>--%>
    <%--<br />--%>
    <%--<acme:textbox path="creditCard.ExpirationMonth" code="creditCard.expirationMonth"/>--%>
    <%--<br />--%>
    <%--<acme:textbox path="creditCard.CVV" code="creditCard.CVV"/>--%>
    <%--<br />--%>


    <br/>
    <h1>User Account</h1>
    <br>
    <form:label path="userAccount.username">
        <spring:message code="actor.username"/>:
    </form:label>
    <form:input path="userAccount.username"/>
    <form:errors cssClass="error" path="UserAccount.username"/>
    <br/>
    <br>
    <form:label path="userAccount.password">
        <spring:message code="actor.password"/>:
    </form:label>
    <form:password path="userAccount.password"/>
    <form:errors cssClass="error" path="UserAccount.password"/>
    <br/>
    <br/>

    <!---------------------------- BOTONES -------------------------->
    <input type="submit" name="save"
           value="<spring:message code="actor.save" />"/>
    <jstl:if test="\$\{customer.id != 0}">
        <input type="submit" name="delete"
               value="<spring:message code="actor.delete" />"
               onclick="return confirm('<spring:message code="actor.confirm.delete"/>')"/>&nbsp;
    </jstl:if>
    <input type="button" name="cancel"
           value="<spring:message code="actor.cancel" />"
           onclick="window.location.replace('customer/list.do')"/>
</form:form>