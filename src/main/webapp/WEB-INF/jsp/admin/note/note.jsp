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
			<li><a class="last" href="">Management Notes</a></li>
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
	<spring:url var="action" value='/admin/author/delete' />
	<form:form id="form-delete" method="post" action="${action}"
		class="bs-example form-horizontal">
		<ul class="nav nav-tabs">
			<li ><a href="<c:url value="/admin/note" />"><img align="absmiddle" src="${imgPath}list-icon.png">&nbsp;List</a></li>
			<li><a href="<c:url value="/admin/note/filter?status=1" />"> <img align="absmiddle" src="${imgPath}list-approved.png">&nbsp;List - Approved</a></li>
			<li><a href="<c:url value="/admin/note/filter?status=0" />"> <img align="absmiddle" src="${imgPath}list-rejected.png">List - Rejected
				</a>
			</li>
		</ul>
		<table id="result" class="table table-condensed">
			<thead>
						<tr>
							<th>Id</th>
							<th>Comic</th>
							<th>User</th>
							<th>Status</th>
						</tr>
				</thead>
				<tbody>
			<%
				int i = 0;
			%>
			<c:forEach var="note" items="${notes}">
				<%
					if (i % 2 == 0) {
				%>
				<tr class="warning">
					<td>${note.id}</td>
					<td>${note.comic.title}</td>
					<td>${note.user.name}</td>
					<td>
						<c:if test="${note.status == 1}">Approved</c:if>
						<c:if test="${note.status == 0}">Rejected</c:if>
					</td>
					<td><a
						href="<c:url value="/admin/note/editNote?id=${note.id}" />"
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
					<td>${note.id}</td>
					<td>${note.comic.title}</td>
					<td>${note.user.name}</td>
					<td>
						<c:if test="${note.status == 1}">Approved</c:if>
						<c:if test="${note.status == 0}">Rejected</c:if>
					</td>
					<td><a
						href="<c:url value="/admin/note/editNote?id=${note.id}" />"
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
	</form:form>
</div>

<%@include file="../footer.jsp"%>
