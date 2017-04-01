<%--
 * footer.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="date" class="java.util.Date" />

<hr />

<div id="barraaceptacion" style="display: block;">
    <div class="inner">
        Solicitamos su permiso para obtener datos estadísticos de su navegación en esta web, en cumplimiento del Real
        Decreto-ley 13/2012. Si continúa navegando consideramos que acepta el uso de cookies.
        <a href="javascript:void(0);" class="ok" onclick="PonerCookie();"><b>OK</b></a> |
        <a href="http://politicadecookies.com" target="_blank" class="info">Más información</a>
    </div>
</div>


<b>Copyright &copy; <fmt:formatDate value="${date}" pattern="yyyy" /> Acme Co., Inc.</b>


<br>
<a href="http://ourdisclaimer.com/?i=AcmeBnb Co.,Inc."><img src="http://ourdisclaimer.com/ourdisclaimer.gif"
                                                            width="80" height="15"
                                                            alt="Privacy policy / Política de privacidad"/></a>