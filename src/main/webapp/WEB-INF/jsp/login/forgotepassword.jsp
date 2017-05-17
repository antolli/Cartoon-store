<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@include file="../top.jsp"%>
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
<div class="row">
	<div class="col-lg-6 col-lg-offset-3">
		<div class="well">
			<div class="container">
				<div class="row">
					<div class="col-lg-6">
						<form id="formF" method="post" class="bs-example form-horizontal"
							action="${pageContext.request.contextPath}/forgotpassword/change">
							<fieldset>
								<legend>Forgot Form</legend>
								<div class="form-group">
									<label for="emailInput" class="col-lg-3 control-label">New
										Password</label>
									<div class="col-lg-9">
										<input type="password" class="form-control" name="newPassword"
											id="newPassword" placeholder="Password" required="required"
											value="" />
									</div>
								</div>
								<input type="hidden" value="${user_id }" name="user_id"
									id="user_id" />
								<div class="form-group">
									<label for="emailInput" class="col-lg-3 control-label">Confirm
										Password</label>
									<div class="col-lg-9">
										<input type="password" class="form-control"
											name="confirmPassword" id="confirmPassword"
											placeholder="New Password" required="required" value="" />
									</div>
								</div>
								<div class="col-lg-9 col-lg-offset-3">
									<input type="button" value="Submit" id="submitbutton"
										class="btn btn-primary" data-loading-text="Saving.."
										data-complete-text="Submit Complete!" onclick="sendForm()">
								</div>

							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<%@include file="../footer.jsp"%>
<script>
	function sendForm() {
		if (jQuery('#newPassword').val() == jQuery('#confirmPassword').val()) {
			changePassword();
		} else {
			alert("password and confirm password doesn't match");
			return false;
		}
	}

	function changePassword() {
		newPassword = jQuery("#newPassword").val();
		user_id = jQuery("#user_id").val();
		jQuery('.btn-primary').attr("disabled", "disabled");

		$.post("${pageContext.request.contextPath}/forgotpassword/changed", {
			newPassword : newPassword,
			user_id : user_id
		}, function(data) {
			jQuery('.btn-primary').removeAttr("disabled");

			alert('Password changed with success, try to do login!');

			window.location.href = "${contextPath}";
			return true;
		});
	}
</script>