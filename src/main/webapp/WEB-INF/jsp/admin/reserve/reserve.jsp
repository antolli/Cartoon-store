<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="../top.jsp"%>
<spring:url value="/assets/img/" var="imgPath" />
<div class="generic">
	<div class="breadcrumbsdiv">
		<ul class="breadcrumbs">
			<li class="home"><a href="/admin"></a></li>
			<li><img align="absmiddle" src="${imgPath}seta-menor.png">
			</li>
			<li><a class="last" href="">Management Reserves</a></li>
		</ul>
	</div>
	<br />
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
	<spring:url var="action" value='/admin/reserve/delete' />
	<form:form id="form-delete" method="post" action="${action}"
		class="bs-example form-horizontal">
		<ul class="nav nav-tabs">
			<li class="active"><a href="#"><img align="absmiddle"
					src="../assets/img/list-icon.png">&nbsp;List</a></li>
			<li><a href="reserve/insertReserve"><img align="absmiddle"
					src="../assets/img/insert-icon.png">&nbsp;Insert</a></li>
			<li><button type="button" class="btn-delete" data-toggle="modal"
					data-target="#modalDelete">
					<img align="absmiddle" src="../assets/img/delete-icon.png">Delete
				</button></li>
		</ul>
		<table id="result" class="table table-condensed">
			<thead>
					<tr>
						<th></th>
						<th>Id</th>
						<th>Date</th>
						<th>User</th>
						<th>Comic</th>
						<th>Status</th>
					</tr>
			</thead>
			<tbody>
			<%
				int i = 0;
			%>
			<c:forEach var="reserve" items="${reserves}">
				<%
					if (i % 2 == 0) {
				%>
				<tr class="warning">
					<td><input type="checkbox" name="id" value="${reserve.id}" /></td>
					<td>${reserve.id}</td>
					<td>${reserve.data}</td>
					<td>${reserve.user.name}</td>
					<td>${reserve.comic.title}</td>
					<td>
						<c:if test="${reserve.status == 2}">Consegnata</c:if>
						<c:if test="${reserve.status == 1}">Disponibile</c:if>
						<c:if test="${reserve.status == 0}">In attesa</c:if>
					</td>
					<td><a
						href="<c:url value="/admin/reserve/editReserve?id=${reserve.id}" />"
						title="Editar"> <span class="glyphicon glyphicon-pencil"
							aria-hidden="true"></span></a></td>
				</tr>
				<%
					}
				%>
				<%
					if (i % 2 != 0) {
				%>
				<tr class="info">
					<td><input type="checkbox" name="id" value="${reserve.id}" /></td>
					<td>${reserve.id}</td>
					<td>${reserve.data}</td>
					<td>${reserve.user.name}</td>
					<td>${reserve.comic.title}</td>
					<td>
						<c:if test="${reserve.status == 2}">Consegnata</c:if>
						<c:if test="${reserve.status == 1}">Disponibile</c:if>
						<c:if test="${reserve.status == 0}">In attesa</c:if>
					</td>
					<td><a
						href="<c:url value="/admin/reserve/editReserve?id=${reserve.id}" />"
						title="Editar"> <span class="glyphicon glyphicon-pencil"
							aria-hidden="true"></span></a></td>
				</tr>
				<%
					}
				%>
				<%
					i++;
				%>
			</c:forEach>
			</tbody>
		</table>
		<!-- Modal -->
		<div id="modalDelete" class="modal fade" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Delete</h4>
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

<%@include file="../footer.jsp"%>