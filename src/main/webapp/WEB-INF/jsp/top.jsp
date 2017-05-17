<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<spring:url value="/assets/css/bootstrap-united.css" var="bootstrapun" />
<spring:url value="/assets/jquery-1.8.3.js" var="jqueryJs" />
<spring:url value="/bootstrap/js/bootstrap.js" var="bootstrapJs" />
<spring:url value="/datepicker/css/datepicker.css" var="dataCss" />
<spring:url value="/datepicker/js/bootstrap-datepicker.js" var="dataJs" />
<spring:url value="/assets/jquery.mask.min.js" var="maskJs" />

<link href="${bootstrapun}" rel="stylesheet" />
<link href="${bootstrapCss}" rel="stylesheet" />
<script src="${jqueryJs}"></script>
<script src="${bootstrapJs}"></script>
<link href="${dataCss}" rel="stylesheet" />
<script src="${dataJs}"></script>

<script src="${maskJs}"></script>
<script type="text/javascript">
	function showMenu() {
		$(".dropdown-menu").show();
	}
	function hideMenu() {
		$(".dropdown-menu").hide();
	}
</script>
</head>
<body>
	<div class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-responsive-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>
		<div class="navbar-collapse collapse navbar-responsive-collapse">
			<spring:url var="actionSearch" value='/home/search' />
			<form id="formSearch" method="get" class="navbar-form navbar-right"
				action="${actionSearch}" enctype="multipart/form-data">
				<ul class="nav navbar-nav navbar-left">
					<li class="active"><input type="text" class="form-control"
						placeholder="Search" name="title"></li>
					<li class="active"><button class="btn btn-primary"
							type="submit">Search</button></li>
				</ul>
			</form>
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="<c:url value="/home"/>">Home</a></li>
				<%
					if (session.getAttribute("loggedUser") == null) {
				%>
				<li><a href='javascript:void(0);' data-toggle="modal"
					data-target="#modalLoginGeral"
					onclick="jQuery('#modalLoginGeral').modal('hide');">Login</a></li>
				<li><a href="<c:url value="/userfront/insert"/>">Signup</a></li>
				<%
					} else {
				%>
				<li><a href="<c:url value="/logout"/>">Logout</a></li>
				<li><a href="<c:url value="/userfront/myAccount"/>">My
						account</a></li>
				<%
					}
				%>
				<li><a href="<c:url value="/suggestion/insert"/>">Suggestion</a></li>

				<li class="dropdown" onmouseover="showMenu()"
					onmouseout="hideMenu()"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">Explore<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="#">Contact us</a></li>
						<li class="divider"></li>
						<li><a href="#">Further Actions</a></li>
					</ul></li>
			</ul>

			<ul class="nav navbar-nav navbar-left">
				<%
					if (session.getAttribute("loggedUser") == null) {
				%>
				<li class="active"><a href="#">Bem vindo Visitante</a></li>
				<%
					} else {
				%>
				<li class="active"><a href="#">Bem vindo ${loggedUser.name }</a></li>
				<%
					}
				%>


			</ul>
		</div>
		<!-- /.nav-collapse -->
	</div>

	<div id="modalLoginGeral" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Confirm</h4>
				</div>
				<div class="modal-body">
					<form id="myForm" method="post" class="bs-example form-horizontal">
						<fieldset>
							<legend>Login Form</legend>
							<div class="form-group">
								<label for="emailInput" class="col-lg-3 control-label">E-mail</label>
								<div class="col-lg-9">
									<input type="text" class="form-control" name="email"
										id="emailLoginG" placeholder="Email" required="required"
										value="" />
								</div>
							</div>

							<div class="form-group">
								<label for="passwordInput" class="col-lg-3 control-label">Password</label>
								<div class="col-lg-9">
									<input type="password" class="form-control" name="password"
										id="passwordLoginG" placeholder="Password" required="required"
										value="" />
								</div>
							</div>
							<div class="form-group">
								<div class="col-lg-12">
									<a href="javascript:void(0);"
										onclick="jQuery('#modalForgot').modal('show'); jQuery('#modalLoginGeral').modal('hide'); jQuery('#modalLogin').modal('hide');">Forgot
										Password</a>
								</div>
							</div>
						</fieldset>
					</form>

				</div>
				<div class="modal-footer">
					<a href="#" class="btn btn-default" data-dismiss="modal">Close</a>
					<input type="submit" value="Logar" id="yesbutton"
						class="btn btn-primary" data-loading-text="Saving.."
						data-complete-text="Submit Complete!" onclick="doLogin()">
				</div>
			</div>
		</div>
	</div>

	<div id="modalForgot" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-body">
					<form id="myForm" method="post" class="bs-example form-horizontal">
						<fieldset>
							<legend>Forgot Password Form</legend>
							<div class="form-group">
								<label for="emailInput" class="col-lg-3 control-label">E-mail</label>
								<div class="col-lg-9">
									<input type="text" class="form-control" name="email"
										id="forgotemail" placeholder="Email" required="required"
										value="" />
								</div>
							</div>
						</fieldset>
					</form>
				</div>
				<div class="modal-footer">
					<a href="#" class="btn btn-default" data-dismiss="modal">Close</a>
					<input type="submit" value="Submit" id="forgotbutton"
						class="btn btn-primary" data-loading-text="Saving.."
						data-complete-text="Submit Complete!" onclick="forgotPassword()">
				</div>
			</div>
		</div>
	</div>
	<script>
		function doLogin() {
			email = jQuery('#emailLoginG').val();
			senha = jQuery('#passwordLoginG').val();

			$.post("${pageContext.request.contextPath}/loginajax", {
				email : email,
				password : senha
			}, function(data) {
				if (data == "loginOk") {

					alert('Login completed!');
					window.location.href = window.location.href;
					return true;
				} else {
					alert("E-mail or password invalid!")
				}
			});
		}

		function forgotPassword() {

			email = jQuery("#forgotemail").val();
			jQuery('#forgotbutton').attr("disabled", "disabled");

			jQuery.ajax({
				method : "GET",
				url : '${pageContext.request.contextPath}/forgotpassword',
				data : {
					email : email
				},
				success : function(data) {
					jQuery('#forgotbutton').removeAttr("disabled");

					alert('Check your e-mail');
					window.location.href = window.location.href;
					return true;

				}
			});
		}
	</script>