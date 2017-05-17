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
								<li><a
									href="<c:url value="/home/category?genre=${genre.id}"/>"
									class="btnGenreCategory">${genre.name}</a></li>
							</c:forEach>
						</ul>
					</td>
				</tr>
			</table>
		</div>
		<h1>Opppps...</h1>
		<h2>Sorry, it's not you</h2>
		<h2>It's us :(</h2>
		
	</div>
	<div></div>
</div>
<script type="text/javascript">
	//codigo ajax do filtro de busca
	function Filtering() {
		var genre = $('#genre option:selected').val();
		var author = $('#author option:selected').val();
		var pubhouse = $('#pubhouse option:selected').val();
		var pricefrom = 0;
		if ($("#priceFrom").val() != "") {
			pricefrom = $("#priceFrom").val();
		}
		var priceto = 0;
		if ($("#priceTo").val() != "") {
			priceto = $("#priceTo").val();
		}
		jQuery.ajax({
			method : "GET",
			url : 'ajaxtest',
			data : {
				genreId : genre,
				authorId : author,
				publishingId : pubhouse,
				priceFrom : pricefrom,
				priceTo : priceto
			},
			success : function(data) {
				$('#result').html(data);
			}
		});
	}
</script>