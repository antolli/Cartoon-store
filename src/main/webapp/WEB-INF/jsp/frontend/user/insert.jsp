<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="../../top.jsp"%>
<div id="info">
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
</div>
<div class="row">
	<div class="col-lg-6 col-lg-offset-3">
		<div class="well">
			<div class="container">
				<div class="row">
					<div class="col-lg-6">
						<form id="myForm" method="post" class="bs-example form-horizontal"
							action="insert">
							<fieldset>
								<legend>User Form</legend>
								<div class="form-group">
									<label for="nameInput" class="col-lg-3 control-label">Name</label>
									<div class="col-lg-9">
										<input type="text" class="form-control" name="name"
											id="nameInput" placeholder="Name" required="required" />
									</div>
								</div>
								<div class="form-group">
									<label for="lastnameInput" class="col-lg-3 control-label">Last
										Name</label>
									<div class="col-lg-9">
										<input type="text" class="form-control" name="lastName"
											id="lastnameInput" placeholder="Lastname" required="required" />
									</div>
								</div>
								<div class="form-group">
									<label for="emailInput" class="col-lg-3 control-label">E-mail</label>
									<div class="col-lg-9">
										<input type="email" class="form-control" name="email"
											id="emailInput" placeholder="Email" required="required" />
									</div>
								</div>
								<div class="form-group">
									<label for="passwordInput" class="col-lg-3 control-label">Password</label>
									<div class="col-lg-9">
										<input type="password" class="form-control" name="password"
											id="passwordInput" placeholder="Password" required="required" />
									</div>
								</div>
								<fieldset class="scheduler-border">
									<legend class="scheduler-border">Phones</legend>
									<div class="form-group">
										<label for="phoneNumberInput1" class="col-lg-3 control-label">Phone
											number 1</label>
										<div class="col-lg-3">
											<select id="typeInput1" class="form-control" name="types">
												<option value="cell">Cell Phone</option>
												<option value="fix" selected="selected">Telephone</option>
											</select>
										</div>
										<div class="col-lg-6">
											<input type="text" class="form-control" name="phones"
												id="phoneNumberInput1" placeholder="Phone Number" />
										</div>
									</div>
									<div class="form-group">
										<label for="phoneNumberInput2" class="col-lg-3 control-label">Phone
											number 2</label>
										<div class="col-lg-3">
											<select id="typeInput2" class="form-control" name="types">
												<option value="cell">Cell Phone</option>
												<option value="fix" selected="selected">Telephone</option>
											</select>

										</div>
										<div class="col-lg-6">
											<input type="text" class="form-control" name="phones"
												id="phoneNumberInput2" placeholder="Phone Number" />
										</div>
									</div>
									<div class="form-group">
										<label for="phoneNumberInput3" class="col-lg-3 control-label">Phone
											number 3</label>
										<div class="col-lg-3">
											<select id="typeInput3" class="form-control" name="types">
												<option value="cell">Cell Phone</option>
												<option value="fix" selected="selected">Telephone</option>
											</select>
										</div>
										<div class="col-lg-6">
											<input type="text" class="form-control" name="phones"
												id="phoneNumberInput3" placeholder="Phone Number" />
										</div>
									</div>
								</fieldset>



								<div class="col-lg-9 col-lg-offset-3">
									<input type="submit" value="Submit" id="submitbutton"
										class="btn btn-primary" data-loading-text="Saving.."
										data-complete-text="Submit Complete!">
								</div>
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	/*mask for phone 1*/
	$('#typeInput1').on('change', function() {
		if (this.value == "fix") {
			$('#phoneNumberInput1').mask('(999) 999999');
		} else if (this.value == "cell") {
			$('#phoneNumberInput1').mask('999 999 9999');
		}
	});
	/*mask for phone 2*/
	$('#typeInput2').on('change', function() {
		if (this.value == "fix") {
			$('#phoneNumberInput2').mask('(999) 999999');
		} else if (this.value == "cell") {
			$('#phoneNumberInput2').mask('999 999 9999');
		}
	});
	/*mask for phone 3*/
	$('#typeInput3').on('change', function() {
		if (this.value == "fix") {
			$('#phoneNumberInput3').mask('(999) 999999');
		} else if (this.value == "cell") {
			$('#phoneNumberInput3').mask('999 999 9999');
		}
	});
	/*mask when initialize the page*/
	$(document).ready(function() {
		$('#phoneNumberInput1').mask('(999) 999999');
		$('#phoneNumberInput2').mask('(999) 999999');
		$('#phoneNumberInput3').mask('(999) 999999');
	});
</script>
<%@include file="../../footer.jsp"%>