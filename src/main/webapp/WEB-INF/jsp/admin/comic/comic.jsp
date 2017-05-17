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
			<li><a class="last" href="">Management Comics</a></li>
		</ul>
	</div>
	<br />
	<br />
	<br />
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
		<spring:url var="actionFilter" value='/admin/comic/filter' />
		<div id="perGenere" class="filter-box">
		<form id="formGenre" method="post" class="bs-example form-horizontal" action="${actionFilter}"  enctype="multipart/form-data" >
			<table class="table table-condensed">
				<tr class="info">
					<td>Choose the Genre:</td>
					<td>
						<select name="id" class="form-control" required="required">  
				       		<c:forEach var="genre" items="${genres}">
								<option value="${genre.id }">${genre.name } </option> 
							</c:forEach>
						</select>
						<input type="hidden" name="type" value="2"/>
					</td>
					<td><button class="btn btn-primary" type="submit">Submit</button></td>
				</tr>
			</table>
			</form>		
		</div>
		<div id="perAuthor" class="filter-box">
			<form id="formAuthor" method="post" class="bs-example form-horizontal" action="${actionFilter}"  enctype="multipart/form-data" >
				<table class="table table-condensed">
					<tr class="info">
						<td>Choose the Author:</td>
						<td>
							<select name="id" class="form-control" required="required">  
						         		<c:forEach var="author" items="${authors}">
											<option value="${author.id }">${author.name } </option> 
										</c:forEach>
							</select>
							<input type="hidden" name="type" value="3"/>	
						</td>
						<td><button class="btn btn-primary" type="submit">Submit</button></td>
					</tr>
				</table>
			</form>
		</div>
		<br/>
	<spring:url var = "action" value='/admin/comic/delete' />
	<form:form id="form-delete" method="post" action="${action}"
		class="bs-example form-horizontal">
		<ul class="nav nav-tabs">
			<li><a href="<c:url value="/admin/comic" />"><img align="absmiddle"
					src="${imgPath}list-icon.png">&nbsp;List</a></li>
			<li><a href="comic/insertComic?origine=1"><img align="absmiddle"
					src="${imgPath}insert-icon.png">&nbsp;Insert</a></li>
			<li><button type="button" class="btn-delete" data-toggle="modal"
					data-target="#modalDelete">
					<img align="absmiddle" src="${imgPath}delete-icon.png">Delete
				</button></li>
			<li><a href="<c:url value="/admin/comic/filter?type=1" />"><img align="absmiddle" src="${imgPath}list-icon.png">&nbsp;List Comics Reserved</a></li>
			<li><a href="<c:url value="/admin/comic/filter?type=0" />"><img align="absmiddle" src="${imgPath}list-icon.png">&nbsp;List Comics out of availability</a></li>
		</ul>
		<table id="result" class="table table-condensed">
			<%
				int i = 0;
			%>
			<c:forEach var="comic" items="${comics}">
				<%
					if (i % 2 == 0) {
				%>
				<tr class="warning">
					<td><input type="checkbox" name="id" value="${comic.id}" /></td>
					<td>${comic.id}</td>
					<td>${comic.title}</td>
					<td><a
						href="<c:url value="/admin/comic/editComic?id=${comic.id}" />"
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
					<td><input type="checkbox" name="id" value="${comic.id}" /></td>
					<td>${comic.id}</td>
					<td>${comic.title}</td>
					<td><a
						href="<c:url value="/admin/comic/editComic?id=${comic.id}" />"
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