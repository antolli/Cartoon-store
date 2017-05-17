<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="../../top.jsp"%>

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
<div class="row" style="height: auto; padding-bottom: 30px; min-height: 100%;">
	<div class="col-lg-6 col-lg-offset-3">
		<div class="well">
			<div class="container">
				<div class="row">
					<div class="col-lg-6">
						<form id="formSuggestion" method="post"
							class="bs-example form-horizontal" action="insert"
							enctype="multipart/form-data">
							<fieldset>
								<legend>Suggestion Form</legend>
								<div class="form-group">
									<label for="titleInput" class="col-lg-3 control-label">Title</label>
									<div class="col-lg-9">
										<input type="text" class="form-control" name="title"
											id="titleInput" placeholder="Title" value=""
											required="required" />

									</div>
								</div>
								<div class="form-group">
									<label for="descriptionInput" class="col-lg-3 control-label">Description</label>
									<div class="col-lg-9">
										<textarea class="form-control" name="description"
											id="descriptionInput" placeholder="Description"
											required="required"></textarea>

									</div>
								</div>
								<div class="form-group">
									<label for="volumeInput" class="col-lg-3 control-label">Volume</label>
									<div class="col-lg-9">
										<input type="text" class="form-control" name="vol"
											id="volumeInput" placeholder="Volume" value=""
											required="required" />
									</div>
								</div>
								<div class="form-group">
									<label for="editionInput" class="col-lg-3 control-label">Edition</label>
									<div class="col-lg-9">
										<input type="text" class="form-control" name="edition"
											id="editionInput" placeholder="Edition" value=""
											required="required" />
									</div>
								</div>
								<div class="form-group">
									<label for="publicationInput" class="col-lg-3 control-label">Publication</label>
									<div class="col-lg-9">
										<input type="text" class="form-control" name="publication"
											id="publicationInput" placeholder="Publication"
											required="required" />
									</div>
								</div>

								<div class="col-lg-9 col-lg-offset-3">
									<button class="btn btn-default">Cancel</button>
									<button class="btn btn-primary" onclick="return isLogged()"
										type="button">Submit</button>
								</div>
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="modalLogin" class="modal fade" role="dialog">
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
									id="emailInput" placeholder="Email" required="required"
									value="" />
							</div>
						</div>

						<div class="form-group">
							<label for="passwordInput" class="col-lg-3 control-label">Password</label>
							<div class="col-lg-9">
								<input type="password" class="form-control" name="password"
									id="passwordInput" placeholder="Password" required="required"
									value="" />
							</div>
						</div>
						<div class="form-group">

							<div class="col-lg-12">
								<a href="javascript:void(0);" onclick="jQuery('#modalForgot').modal('show');jQuery('#modalLoginGeral').modal('hide');jQuery('#modalLogin').modal('hide');">Forgot Password</a>
							</div>
						</div>
					</fieldset>
				</form>

			</div>
			<div class="modal-footer">
				<a href="#" class="btn btn-default" data-dismiss="modal">Close</a> <input
					type="submit" value="Logar" id="yesbutton" class="btn btn-primary"
					data-loading-text="Saving.." data-complete-text="Submit Complete!"
					onclick="Logar()">
			</div>
		</div>
	</div>
</div>
<%@include file="../../footer.jsp"%>
<script>
	$(function() {
		$('#publicationInput').datepicker();
	});
</script>
<script type="text/javascript">
	function isLogged() {
		jQuery.ajax({
			method : "GET",
			//url : '${pageContext.request.contextPath}/doLogin',
			url : 'isLogged',
			data : {},
			success : function(data) {
				if (data == "loginOk") {

					jQuery('#formSuggestion').submit();
				} else {
					jQuery("#modalLogin").modal('show');
					return false;
				}
			}
		});
	}
	function Logar() {
		email = jQuery('#emailInput').val();
		senha = jQuery('#passwordInput').val();

		$.post("${pageContext.request.contextPath}/suggestion/doLogin", {
			email : email,
			password : senha
		}, function(data) {
			if (data == "loginOk") {
				jQuery('#modalLogin').modal("hide");
				alert('Login completed!');
				jQuery('#formSuggestion').submit();
				return true;
			} else {
				alert("E-mail or password invalid!")
			}
		});
	}
</script>