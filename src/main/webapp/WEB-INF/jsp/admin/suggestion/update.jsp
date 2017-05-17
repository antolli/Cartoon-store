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
							class="bs-example form-horizontal" commandName="suggestion">
							<fieldset>
								<legend>Suggestion Form</legend>
								<div class="form-group">
									<label for="titleInput" class="col-lg-3 control-label">Title</label>
									<div class="col-lg-9">
									 	<input type="text" id="titleInput" class="form-control" placeholder="${suggestion.title}" disabled>
									</div>
								</div>
								<div class="form-group">
									<label for="volumeInput" class="col-lg-3 control-label">Volume</label>
									<div class="col-lg-9">
									 	<input type="text" id="volumeInput" class="form-control" placeholder="${suggestion.vol}" disabled>
									</div>
								</div>
								<div class="form-group">
									<label for="editionInput" class="col-lg-3 control-label">Edition</label>
									<div class="col-lg-9">
									 	<input type="text" id="editionInput" class="form-control" placeholder="${suggestion.edition}" disabled>
									</div>
								</div>
								<div class="form-group">
									<label for="publicationInput" class="col-lg-3 control-label">Publication</label>
									<div class="col-lg-9">
									 	<input type="text" id="publicationInput" class="form-control" placeholder="${suggestion.publication}" disabled>
									</div>
								</div>
								<div class="form-group">
									<label for="descriptionInput" class="col-lg-3 control-label">Description</label>
									<div class="col-lg-9">
									 	<textarea id="descriptionInput" class="form-control" placeholder="${suggestion.description}" disabled></textarea>
									</div>
								</div>
								<div class="col-lg-9 col-lg-offset-3">
									<input type="submit" value="Accept and Insert?" id="submitbutton" class="btn btn-primary" />
								</div>
							</fieldset>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>


	<script>
		$(function() {
			$('#publicationInput').datepicker();
		});
	</script>
<%@include file="../footer.jsp"%>