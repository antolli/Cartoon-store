<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="top.jsp"%>
<spring:url value="/assets/img/" var="imgPath" />
<div class="container">
	<div class="fumetti">
		<div class="breadcrumbsdiv">
			<table>
				<tr>
					<td>
						<ul class="breadcrumbs">
							<li class="home"><a href="<c:url value="/home"/>"></a></li>
							<li><img align="absmiddle" src="${imgPath}seta-menor.png">
							</li>
							<li><a class="last" href="">HOME</a></li>
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
			</table>
		</div>
		<table class="table table-hover">
			<tr>
				<td>Genres: <select id="genre" class="form-control">
						<option selected value="0">Select a genre</option>
						<c:forEach var="genre" items="${genres}">
							<option value="${genre.id}">${genre.name}</option>
						</c:forEach>
				</select>
				</td>
				<td>Authors: <select id="author" class="form-control">
						<option selected value="0">Select a author</option>
						<c:forEach var="author" items="${authors}">
							<option value="${author.id}">${author.name}</option>
						</c:forEach>
				</select>
				</td>
				<td>Publishing Houses: <select id="pubhouse" class="form-control selectFilter">
						<option selected value="0">Select a publishing house</option>
						<c:forEach var="phs" items="${publishinghouses}">
							<option value="${phs.id}">${phs.name}</option>
						</c:forEach>
				</select>
				</td>
				<td>Price From: <input id="priceFrom" type="text" class="form-control pricesFilter" placeholder="From" /></td>
				<td>Price To: <input id="priceTo" type="text" class="form-control pricesFilter" placeholder="To"  /></td>
				<td style="line-height:1,5px;">
					<button class="btn btn-danger"  id="Filter" onclick="Filtering()">Search</button>
				</td>
			</tr>
		</table>
		<br />
		<table id="result" class="table table-condensed">
			<%int i =1; %>
			<tr class="info">
				<c:forEach var="comic" items="${comics}">
					<td>
						<table>
							<tr><td><img src="<c:url value="${baseUrl}/${comic.urlImageCover}"/>" alt="${comic.title}" width="170" height="270"/></td></tr>
							<tr><td><p>${comic.title}</p></td></tr>
							<tr><td><p class="price">£ ${comic.value}</p></td></tr>
							<tr><td><p><a href="<c:url value="/comic/view?id=${comic.id}"/>"><img src="${imgPath}btn-detail.png" alt="view details"/></a></p></td></tr>
							<tr>
								<td>
									<c:forEach var="genre" items="${comic.genres}">
										<p class="genre-category">-${genre.name}-</p>
									</c:forEach>
								</td>
							</tr>
						</table>
					</td>
					<%if(i % 4 == 0){%>
						</tr>
						<tr>
					<%} %>
					<% i++; %>
				</c:forEach>
			</tr>
		</table>
	</div>
	<div></div>
</div>
<%@include file="footer.jsp"%>
<script type="text/javascript">
	//codigo ajax do filtro de busca
	function Filtering() {
		var genre = $('#genre option:selected').val();
		var author = $('#author option:selected').val();
		var pubhouse = $('#pubhouse option:selected').val();
		var pricefrom = 0;
		if($("#priceFrom").val() != ""){ 
			pricefrom = $("#priceFrom").val();
		}
		var priceto = 0;
		if($("#priceTo").val()!= ""){
			priceto = $("#priceTo").val();
		}
		jQuery.ajax({
			method : "GET",
			url : 'ajaxFilterHome',
			data : { 
				genreId: genre,
				authorId: author, 
				publishingId : pubhouse,
				priceFrom: pricefrom,
				priceTo: priceto
			},
			success : function(data) {
				$('#result').html(data);
			}
		});
	}
</script>