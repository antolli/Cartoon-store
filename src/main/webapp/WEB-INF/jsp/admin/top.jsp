<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html class="html_admin">
	<head>
	<spring:url value="/assets/css/bootstrap-united.css" var="bootstrapun" />
	<spring:url value="/assets/jquery-1.8.3.js" var="jqueryJs" />
	<spring:url value="/assets/jquery.mask.min.js" var="maskJs" />
	<spring:url value="/bootstrap/js/bootstrap.js" var="bootstrapJs" />
	
	
	<spring:url value="/datepicker/css/datepicker.css" var="dataCss" />
	<spring:url value="/datepicker/js/bootstrap-datepicker.js" var="dataJs" />
	<spring:url value="/assets/css/styles.css" var="slytesCss" />
	
	
	
		<link href="${bootstrapun}" rel="stylesheet" />
		<script src="${jqueryJs}"></script>
		<script src="${bootstrapJs}"></script>
		<script src="${maskJs}"></script>
		<link href="${dataCss}" rel="stylesheet" />
		<link href="${slytesCss}" rel="stylesheet" />
		<script src="${dataJs}"></script>
		<link href="${slytesCss}" rel="stylesheet" />
		
		<script type="text/javascript">
			function showMenu(){
				$(".dropdown-menu").show();
			}
			function hideMenu(){
				$(".dropdown-menu").hide();
			}
		</script>
		<style>
			.green {
				font-weight: bold;
				color: green;
			}
			
			.message {
				margin-bottom: 10px;
			}
			
			.error {
				color: #ff0000;
				font-size: 0.9em;
				font-weight: bold;
			}
			
			.errorblock {
				color: #000;
				background-color: #ffEEEE;
				border: 3px solid #ff0000;
				padding: 8px;
				margin: 16px;
			}
		</style>
	</head>
	<body class="body_admin">
		<div class="navbar navbar-default">
	
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-responsive-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
	
			<div class="navbar-collapse collapse navbar-responsive-collapse">
				<ul class="nav navbar-nav navbar-right">
					<li class="active"><a href="<c:url value="/admin" />">Admin</a></li>
					<li><a href="<c:url value="/admin/genre" />">Genre</a></li>
					<li><a href="<c:url value="/admin/author" />">Author</a></li>
					<li><a href="<c:url value="/admin/publishinghouse" />">Publishing House</a></li>
					<li><a href="<c:url value="/admin/user" />">User</a></li>
					<li><a href="<c:url value="/admin/comic" />">Comic</a></li>
					<li><a href="<c:url value="/admin/note" />">Notes</a></li>
					<li><a href="<c:url value="/admin/reserve" />">Reserve</a></li>
					<li><a href="<c:url value="/admin/suggestion" />">Suggestion</a></li>
					<li><a href="<c:url value="/admin/notice" />">Notice</a></li>
					<li class="dropdown" onmouseover="showMenu()" onmouseout="hideMenu()">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">Explore<b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="#">Contact us</a></li>
								<li class="divider"></li>
								<li><a href="#">Further Actions</a></li>
							</ul>
						</li>
				</ul>
				<ul class="nav navbar-nav navbar-left">
				<% if (session.getAttribute("loggedUser") == null) { %>
				    <li class="active"><a href="#">Bem vindo Visitante</a></li>
				<% } else {%>
				    <li class="active"><a href="#">Bem vindo ${loggedUser.name }</a></li>
				    <spring:url value="/logout" var="logout" />
				      <li><a href="${logout }">Logout</a></li>
				<% } %>
				</ul>
			</div>
			<!-- /.nav-collapse -->
		</div>