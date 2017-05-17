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
						class="bs-example form-horizontal" commandName="reserve">
						<fieldset>
							<legend>Reserve Form</legend>
							<div class="form-group">
								<label for="dateInput" class="col-lg-3 control-label">Date</label>
								<div class="col-lg-9">
									<form:input type="text" class="form-control" path="data"
										id="dateInput" placeholder="Date" required="required" value="${reserve.data}"/>
									<form:errors path="data" cssClass="error" />
								</div>
							</div>
							<div class="form-group">
									<label for="customerInput" class="col-lg-3 control-label">Customer</label>
									<div class="col-lg-9">
										<select id="customerInput" name="user.id" class="form-control">  
         								   <c:forEach var="user" items="${users}">
							                 <option value="${user.id}">${user.name}</option> 
							               </c:forEach>
							            </select>
									</div>
							</div>
							<div class="form-group">
									<label for="comicInput" class="col-lg-3 control-label">Comic</label>
									<div class="col-lg-9">
										<select id="comicInput" name="comic.id" class="form-control">  
         								   <c:forEach var="comic" items="${comics}">
							                 <option value="${comic.id}">${comic.title}</option> 
							               </c:forEach>
							            </select>
									</div>
							</div>
							
							<div class="form-group">
								<label for="statusInput" class="col-lg-3 control-label">Status</label>
								<div class="col-lg-9">
									<label class="radio-inline"><form:radiobutton path="status" value="0" checked="checked"/>In attesa</label>
									<label class="radio-inline"><form:radiobutton path="status" value="1" checked="checked"/>Disponibile</label>
									<label class="radio-inline"><form:radiobutton path="status" value="2" />Consegnata</label>
									<form:errors path="status" cssClass="error" />
								</div>
							</div>
							<div class="form-group">
								<label for="valueInput" class="col-lg-3 control-label">Value</label>
								<div class="col-lg-9">
									<input type="text" class="form-control" id="valueInput" placeholder="Value" disabled />
								</div>
							</div>
							<div class="form-group">
								<label for="qtyInput" class="col-lg-3 control-label">Qty</label>
								<div class="col-lg-9">
								<form:input type="numeric" class="form-control" path="qty"
										id="qtyInput" placeholder="Qty" required="required" onBlur="calculateTotal()"/>
									<form:errors path="qty" cssClass="error" />
								</div>
							</div>
							<div class="form-group">
								<label for="totalInput" class="col-lg-3 control-label">Total</label>
								<div class="col-lg-9">
									<input type="text" class="form-control" id="totalInput" placeholder="Total" disabled />
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
		$(function() {
			$('#dateInput').datepicker();
		});
		function calculateTotal(){
			jQuery("#totalInput").val(jQuery('#valueInput').val() * jQuery('#qtyInput').val());

		}
		$("#comicInput").change(function() {
			  /*funzione che chiama l'ajax per riempire il campo prezzo
			  */ 
			var comicId = $('#comicInput option:selected').val();
			jQuery.ajax({
				method : "GET",
				url : 'ajaxSetValue',
				data : { 
					comicId: comicId
				},
				success : function(data) {
					$('#valueInput').val(data);
					calculateTotal();
				}
			});
		});
		
		/* .ready() == funzione che riempe il campo prezzo quando la pagina fa il load*/
		$(document).ready(function() {
				var comicId = $('#comicInput option:selected').val();
				jQuery.ajax({
					method : "GET",
					url : 'ajaxSetValue',
					data : { 
						comicId: comicId
					},
					success : function(data) {
						$('#valueInput').val(data);
						calculateTotal();
					}
				});
		});
</script>
<%@include file="../footer.jsp"%>