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
						class="bs-example form-horizontal" commandName="genre">
						<fieldset>
							<legend>Genre Form</legend>
							<div class="form-group">
								<label for="nameInput" class="col-lg-3 control-label">Name</label>
								<div class="col-lg-9">
									<form:input type="text" class="form-control" path="name"
										id="nameInput" placeholder="Name" required="required" value="${genre.name}"/>
									<form:errors path="name" cssClass="error" />
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

<%@include file="../footer.jsp"%>