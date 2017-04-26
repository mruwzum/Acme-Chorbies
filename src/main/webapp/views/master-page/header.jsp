<%--
 * header.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<img src="images/logo.png" alt="Acme Chorbies, Inc." />
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMINISTRATOR')">




			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="banner/list.do"><spring:message code="master.page.administrator.action.1" /></a></li>
				</ul>
			</li>
			<li><a href="chorbi/listAll.do"><spring:message code="master.page.chorbi.list" /></a></li>
			<li><a href="administrator/changeCache.do"><spring:message code="master.page.administrator.change" /></a></li>
			<li><a href="administrator/dashboard.do"><spring:message code="master.page.administrator.dashboard" /></a></li>


		</security:authorize>
		
		<security:authorize access="hasRole('CHORBI')">





			<li><a class="fNiv"><spring:message	code="master.page.customer" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="chorbi/edit.do"><spring:message code="master.page.customer.action.1" /></a></li>
					<li><a href="search/create.do"><spring:message code="master.page.customer.action.2" /></a></li>
					<li><a href="search/list.do"><spring:message code="master.page.customer.searc.2" /></a></li>
				</ul>
			</li>
			<li><a class="fNiv"><spring:message	code="master.page.like" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="chorbi/mylikes.do"><spring:message code="master.page.chorbi.my.likes" /></a></li>
					<li><a href="chorbi/likes.do"><spring:message code="master.page.chorbi.likes" /></a></li>

				</ul>
			</li>
			<li><a class="fNiv"><spring:message	code="master.page.chirp" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="chorbi/mychirps.do"><spring:message code="master.page.chorbi.my.chirps" /></a></li>
					<li><a href="chorbi/chirps.do"><spring:message code="master.page.chorbi.chirp" /></a></li>

				</ul>
			</li>
			<li><a href="chorbi/list.do"><spring:message code="master.page.chorbi.list" /></a></li>

		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
		<li><a class="fNiv" href="chorbi/create.do"><spring:message code="master.page.register.chorbi"/></a>
			<li><a href="event/okevents.do"><spring:message code="master.page.event.list" /></a></li>

		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
		<li>
			<a class="fNiv">
				<spring:message code="master.page.profile" />
				(<security:authentication property="principal.username" />)
			</a>
			<ul>
				<li class="arrow"></li>
				<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
			</ul>
		</li>


	</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

