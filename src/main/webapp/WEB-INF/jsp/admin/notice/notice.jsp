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
			<li><a class="last" href="">Management Notices</a></li>
		</ul>
	</div>
	<br />
		<ul class="nav nav-tabs">
			<li ><a href="<c:url value="/admin/note" />"><img align="absmiddle" src="${imgPath}list-icon.png">&nbsp;List</a></li>
		</ul>
		<table id="result" class="table table-condensed">
			<thead>
						<tr>
							<th>Id</th>
							<th>Email</th>
							<th>Comic</th>
						</tr>
				</thead>
				<tbody>
			<%
				int i = 0;
			%>
			<c:forEach var="notice" items="${notices}">
				<%
					if (i % 2 == 0) {
				%>
				<tr class="warning">
					<td>${notice.id}</td>
					<td>${notice.email}</td>
					<td>${notice.comic.title}</td>
					
				</tr>
				<%
					}
				%>
				<%
					if (i % 2 != 0) {
				%>
				<tr class="info">
					<td>${notice.id}</td>
					<td>${notice.email}</td>
					<td>${notice.comic.title}</td>
					
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
</div>

<%@include file="../footer.jsp"%>
