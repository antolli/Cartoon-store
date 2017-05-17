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
	function showMenu() {
		$(".dropdown-menu").show();
	}
	function hideMenu() {
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

<c:if test="${not empty param.message}">
	<c:if test="${param.typeMessage == 1}">
		<div class="alert alert-success">
			<strong>Success! </strong><%=request.getParameter("message")%></div>
	</c:if>
	<c:if test="${param.typeMessage == 0}">
		<div class="alert alert-danger">
			<strong>Warning! </strong><%=request.getParameter("message")%></div>
	</c:if>
</c:if>
<div class="col-lg-6 col-lg-offset-3">
	<div class="well">
		<div class="container">
			<div class="row">
				<div class="col-lg-6">
					<form:form id="myForm" method="post"
						class="bs-example form-horizontal" commandName="user">
						<fieldset>
							<legend>Login Form</legend>
							<div class="form-group">
								<label for="emailInput" class="col-lg-3 control-label">E-mail</label>
								<div class="col-lg-9">
									<form:input type="text" class="form-control" path="email"
										id="emailInput" placeholder="Email" required="required"
										value="" />
									<form:errors path="email" cssClass="error" />
								</div>
							</div>

							<div class="form-group">
								<label for="passwordInput" class="col-lg-3 control-label">Password</label>
								<div class="col-lg-9">
									<form:password class="form-control" path="password"
										id="passwordInput" placeholder="Password" required="required"
										value="" />
									<form:errors path="password" cssClass="error" />
								</div>
							</div>
							<div class="col-lg-9 col-lg-offset-3">
								<input type="submit" value="Submit" id="submitbutton"
									class="btn btn-primary" data-loading-text="Saving.."
									data-complete-text="Submit Complete!">
							</div>

						</fieldset>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>

<%@include file="../admin/footer.jsp"%>