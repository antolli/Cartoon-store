<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="../../top.jsp"%>

<div class="fumetti myaccount" style="height: auto; padding-bottom: 30px; min-height: 100%;">
	<div id="info">
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
	</div>
	<spring:url value="/assets/img/" var="imgPath" />
	<div class="breadcrumbsdiv">
			<table>
				<tr>
					<td>
						<ul class="breadcrumbs">
							<li class="home"><a href="<c:url value="/home"/>"></a></li>
							<li><img align="absmiddle" src="${imgPath}seta-menor.png">
							</li>
							<li><a class="last" href="<c:url value="/home"/>">HOME</a></li>
						</ul>
					</td>
					<td>
						<ul class="category">
							<c:forEach var="genre" items="${genres}">
								<li><a href="<c:url value="/home/category?genre=${genre.id}"/>" class="btnGenreCategory">${genre.name}</a></li>
							</c:forEach>
						</ul>
					</td>
				</tr>
				<tr>
					<td>
						<ul class="menu-my-account">
							<li><a href="<c:url value="/userfront/myAccount"/>">My Account</a></li>
							<li><a href="<c:url value="/userfront/myReserves"/>">My Reserves</a></li>	
						</ul>
					</td>
				</tr>
			</table>
	</div>
	<br/>
	<br/>
	<!-- listaggio -->
	<div class="row">
		<table id="result" class="table table-condensed">
			<thead>
					<tr>
						<th>Date</th>
						<th>Qty</th>
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
					<td>${reserve.data}</td>
					<td>${reserve.qty}</td>
					<td>${reserve.comic.title}</td>
					<td>
						<c:if test="${reserve.status == 2}">Consegnata</c:if>
						<c:if test="${reserve.status == 1}">Disponibile</c:if>
						<c:if test="${reserve.status == 0}">In attesa</c:if>
					</td>
				</tr>
				<%
					}
				%>
				<%
					if (i % 2 != 0) {
				%>
				<tr class="info">
					<td>${reserve.data}</td>
					<td>${reserve.qty}</td>
					<td>${reserve.comic.title}</td>
					<td>
						<c:if test="${reserve.status == 2}">Consegnata</c:if>
						<c:if test="${reserve.status == 1}">Disponibile</c:if>
						<c:if test="${reserve.status == 0}">In attesa</c:if>
					</td>
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
</div>
<%@include file="../../footer.jsp"%>