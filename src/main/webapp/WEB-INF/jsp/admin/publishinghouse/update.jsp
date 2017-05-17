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
						class="bs-example form-horizontal" commandName="publishinghouse">
						<fieldset>
							<legend>Publishing House Form</legend>
							<div class="form-group">
								<label for="nameInput" class="col-lg-3 control-label">Name</label>
								<div class="col-lg-9">
									<form:input type="text" class="form-control" path="name"
										id="nameInput" placeholder="Name" value="${publishinghouse.name}" />
								</div>
							</div>
							<div class="form-group">
								<label for="emailInput" class="col-lg-3 control-label">E-mail</label>
								<div class="col-lg-9">
									<form:input type="email" class="form-control" path="email"
										id="emailInput" placeholder="Email"  value="${publishinghouse.email}"/>
								</div>
							</div>
							<div class="form-group">
								<label for="typeInput" class="col-lg-3 control-label">Type Phone Number</label>
								<div class="col-lg-9">
									<select id="typeInput" class="form-control">
										<option selected value="0">Select a type</option>
										<option value="cell">Cell Phone</option>
										<option value="fix">Telephone</option>
									</select>	
								</div>
							</div>
							<div class="form-group">
								<label for="phoneInput" class="col-lg-3 control-label">Phone Number</label>
								<div class="col-lg-9">
									<form:input type="text" class="form-control" path="phone"
										id="phoneInput" placeholder="Phone Number" value="${publishinghouse.phone}" required="required" />	
								</div>
							</div>
							<form:errors path="name" cssClass="error" />
							<form:errors path="email" cssClass="error" />
							<form:errors path="phone" cssClass="error" />
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
	$('#typeInput').on('change', function() {
	  if(this.value == "fix"){
		  $('#phoneInput').mask('(999) 999999');
	  }else if(this.value == "cell"){
		  $('#phoneInput').mask('999 999 9999');
	  }
	});
	$(document).ready(function(){
		 $('#phoneInput').mask('(999) 999999');
	});

</script>
<%@include file="../footer.jsp"%>