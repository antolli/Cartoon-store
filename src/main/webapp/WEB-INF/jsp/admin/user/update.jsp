<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<div class="col-lg-6 col-lg-offset-3">
	<div class="well">
		<div class="container">
			<div class="row">
				<div class="col-lg-6">
					<form:form id="myForm" method="post"
						class="bs-example form-horizontal" commandName="user">
						<fieldset>
							<legend>User Form</legend>
							<div class="form-group">
								<label for="nameInput" class="col-lg-3 control-label">Name</label>
								<div class="col-lg-9">
									<form:input type="text" class="form-control" path="name"
										id="nameInput" placeholder="Name" value="${user.name}"
										required="required" />
									<form:errors path="name" cssClass="error" />
								</div>
							</div>
							<div class="form-group">
								<label for="lastnameInput" class="col-lg-3 control-label">Last
									Name</label>
								<div class="col-lg-9">
									<form:input type="text" class="form-control" path="lastName"
										id="lastnameInput" placeholder="Lastname"
										value="${user.lastName}" required="required" />
									<form:errors path="lastName" cssClass="error" />
								</div>
							</div>
							<div class="form-group">
								<label for="emailInput" class="col-lg-3 control-label">E-mail</label>
								<div class="col-lg-9">
									<form:input type="email" class="form-control" path="email"
										id="emailInput" placeholder="Email" value="${user.email}"
										required="required" />
									<form:errors path="email" cssClass="error" />
								</div>
							</div>
							<div class="form-group"  >
								<label for="passwordInput" class="col-lg-3 control-label">Change Password</label>
								<div class="col-lg-9">
									<input type="radio" class=" changepass"
										 id="passwordInput" name="changepass" placeholder="Password"
										value="yes"  />Yes
										<input type="radio" class=" changepass"
										 id="passwordInput" name="changepass" placeholder="Password"
										value="no" checked="checked" />No
								</div>
							</div>
							<div class="form-group" id="divpass" style="display:none">
								<label for="passwordInput" class="col-lg-3 control-label">New Password</label>
								<div class="col-lg-9">
									<form:input type="password" class="form-control"
										path="password" id="passwordInput" placeholder="Password"
										value="" />
									<form:errors path="password" cssClass="error" />
								</div>
							</div>
							<%
								int i = 1;
							%>
							<fieldset class="scheduler-border">
								<legend class="scheduler-border">Phones</legend>
								<c:forEach items="${user.phones}" var="phone">
									<div class="form-group">
										<label for="typeInput<%= i%>" class="col-lg-3 control-label">Phone number </label>
										<div class="col-lg-3">
											<select id="typeInput<%= i%>" class="form-control" name="types">
												<option value="cell" <c:if test="${phone.type eq 'cell'}">selected="selected"</c:if>>Cell Phone</option>
												<option value="fix" <c:if test="${phone.type eq 'fix'}">selected="selected"</c:if>>Telephone</option>
											</select>
										</div>
										<div class="col-lg-6">
											<input type="text" class="form-control" name="phones"
												id="phoneNumberInput<%= i%>" placeholder="Phone Number"
												value="${phone.number}" /> <input type="hidden"
												id="phoneIdInput" name="ids" value="${phone.id}" />
										</div>
									</div>
									<%
										i++;
									%>
								</c:forEach>
							</fieldset>
							<div class="form-group">
								<label for="typeuserInput" class="col-lg-3 control-label">Type
									User</label>
								<div class="col-lg-9">
									<form:select id="typeuserInput" path="typeUser"
										class="form-control">
										<option value="NONE">-- Select --</option>
										<c:forEach items="${typeList}" var="type">
											<option
												<c:if test="${type eq user.typeUser}">selected="selected"</c:if>
												value="${type}">${type}</option>
										</c:forEach>
									</form:select>
									<form:errors path="typeUser" cssClass="error" />
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
		if ($('#phoneNumberInput3').val() == "fix") {
			$('#phoneNumberInput3').mask('(999) 999999');
		} else if ($('#phoneNumberInput3').val() == "cell") {
			$('#phoneNumberInput3').mask('999 999 9999');
		}
		
		if ($('#phoneNumberInput2').val() == "fix") {
			$('#phoneNumberInput2').mask('(999) 999999');
		} else if ($('#phoneNumberInput2').val() == "cell") {
			$('#phoneNumberInput2').mask('999 999 9999');
		}
		if ($('#phoneNumberInput1').val() == "fix") {
			$('#phoneNumberInput1').mask('(999) 999999');
		} else if ($('#phoneNumberInput1').val() == "cell") {
			$('#phoneNumberInput1').mask('999 999 9999');
		}
		//cambiare la password
		jQuery('.changepass').click(function(){
			if(jQuery(this).val() == 'yes'){
				jQuery('#divpass').slideDown('slow');
				jQuery('#divpass').find('input').val('');
			}else{
				jQuery('#divpass').slideUp('slow');
				jQuery('#divpass').find('input').val('');
			}
			
		});
		
	});
</script>
<%@include file="../footer.jsp"%>