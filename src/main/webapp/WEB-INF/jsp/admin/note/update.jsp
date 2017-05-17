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
						class="bs-example form-horizontal" commandName="note">
						<fieldset>
							<legend>Note Form</legend>
							<div class="form-group">
								<label for="disabledComicInfo" class="col-lg-3 control-label">Comic: </label>
								<div class="col-lg-9">
									<input type="text" id="disabledComicInfo" class="form-control" placeholder="${note.comic.title}" disabled>
								</div>
							</div>
							<div class="form-group">
								<label for="disabledUserInfo" class="col-lg-3 control-label">User: </label>
								<div class="col-lg-9">
									<input type="text" id="disabledUserInfo" class="form-control" placeholder="${note.user.name}" disabled>
								</div>
							</div>
							<div class="form-group">
								<label for="disabledUserInfo" class="col-lg-3 control-label">Note: </label>
								<div class="col-lg-9">
									<textarea readonly class="form-control" rows="3" placeholder="${note.note}"></textarea>
								</div>
							</div>							
							<div class="form-group">
								<label for="statusInput" class="col-lg-3 control-label">Status</label>
								<div class="col-lg-9">
									<label class="radio-inline"><form:radiobutton path="status" value="1" />Approved </label>
									<label class="radio-inline"><form:radiobutton path="status" value="0" />Rejected </label>
									<form:errors path="status" cssClass="error" />
								</div>
							</div>
							<div class="col-lg-9 col-lg-offset-3">
								<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalConfirm">Submit</button>
							</div>
						</fieldset>
						<!-- Modal -->
						<div id="modalConfirm" class="modal fade" role="dialog">
							<div class="modal-dialog">
								<!-- Modal content-->
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
										<h4 class="modal-title">Confirm</h4>
									</div>
									<div class="modal-body">
										<p>Are you sure you want to do this?</p>
									</div>
									<div class="modal-footer">
										<a href="#" class="btn btn-default" data-dismiss="modal">Close</a>
										<input type="submit" value="Yes" id="yesbutton"
											class="btn btn-primary" data-loading-text="Saving.."
											data-complete-text="Submit Complete!">
									</div>
								</div>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>

<%@include file="../footer.jsp"%>