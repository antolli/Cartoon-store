<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="../../top.jsp"%>
<div class="container">
	<div class="fumetti" style="height: auto; padding-bottom: 30px;">
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
								<li><a
									href="<c:url value="/home/category?genre=${genre.id}"/>"
									class="btnGenreCategory">${genre.name}</a></li>
							</c:forEach>
						</ul>
					</td>
				</tr>
			</table>
		</div>
		<div class="row principal">
			<div class="col-md-3" style="padding: 30px">
				<div class="row">
					<div class="col-md-12">
						<img src="<c:url value="${imageurl }"/>" alt="..."
							class="img-thumbnail">
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<h2>£ ${comic.value }</h2>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<h3>Quantity:</h3>
					</div>
					<div class="col-md-3"></div>
					<div class="col-md-6">
						<input type="number" name="qty" id="qtyComic" min="1"
							max="${comic.stock}" class="form-control" />
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-md-12">
						<c:if test="${comic.stock > 0}">
							<button type="button" class="btn-reserve" data-toggle="modal"
								data-target="#modalReserve">Reserve</button>
						</c:if>
						<c:if test="${comic.stock <= 0}">
							<div style="color: red">
								<p class="">out of stock</p>
							</div>
							<p class="text-left">
								<a href="javascript:void(0);" data-toggle="modal"
									data-target="#modalOut">get notified when this product is
									back in stock</a>
							</p>
						</c:if>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="row">
					<div class="col-md-12">
						<h2>${comic.title}</h2>
					</div>
				</div>
				<div class="row">
					<div class="col-md-1"></div>
					<div class="col-md-10">
						<p class="text-left">${comic.description}</p>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="row">
					<div class="col-md-1"></div>
					<div class="col-md-10">
						<h3 class="text-left">Authors</h3>
					</div>
					<div class="col-md-1"></div>
				</div>
				<div class="row">
					<div class="col-md-1"></div>
					<div class="col-md-10">
						<h4 class="text-left">Written By</h4>
						<c:forEach var="writter" items="${authorsWritter}">
							<p class="text-left">${writter.name }</p>
						</c:forEach>
					</div>
					<div class="col-md-1"></div>
				</div>
				<div class="row">
					<div class="col-md-1"></div>
					<div class="col-md-10">
						<h4 class="text-left">Art By</h4>
						<c:forEach var="finalist" items="${authorsFinalist}">
							<p class="text-left">${finalist.name }</p>
						</c:forEach>
					</div>
					<div class="col-md-1"></div>
				</div>
				<div class="row">
					<div class="col-md-1"></div>
					<div class="col-md-10">
						<h4 class="text-left">Cover By</h4>
						<c:forEach var="editor" items="${authorsEditor}">
							<p class="text-left">${editor.name }</p>
						</c:forEach>
					</div>
					<div class="col-md-1"></div>
				</div>
				<div class="row">
					<div class="col-md-1"></div>
					<div class="col-md-10">
						<h3 class="text-left">About Book</h3>
					</div>
					<div class="col-md-1"></div>
				</div>
				<div class="row">
					<div class="col-md-1"></div>
					<div class="col-md-10">
						<h4 class="text-left">Vol</h4>
						<p class="text-left">${comic.vol }</p>
					</div>
					<div class="col-md-1"></div>
				</div>
				<div class="row">
					<div class="col-md-1"></div>
					<div class="col-md-10">
						<h4 class="text-left">Publication</h4>
						<p class="text-left">${comic.publication }</p>
					</div>
					<div class="col-md-1"></div>
				</div>
				<div class="row">
					<div class="col-md-1"></div>
					<div class="col-md-10">
						<h4 class="text-left">Edtion</h4>
						<p class="text-left">${comic.edition}</p>
					</div>
					<div class="col-md-1"></div>
				</div>
				<div class="row">
					<div class="col-md-1"></div>
					<div class="col-md-10">
						<h4 class="text-left">Genre</h4>
						<c:forEach var="genre" items="${comic.genres}">
							<p class="text-left">${genre.name }</p>
						</c:forEach>
					</div>
					<div class="col-md-1"></div>
				</div>
				<div class="row">
					<div class="col-md-1"></div>
					<div class="col-md-10">
						<h4 class="text-left">Stock</h4>
						<p class="text-left">${comic.stock }</p>
					</div>
					<div class="col-md-1"></div>
				</div>
			</div>
		</div>
		<input type="hidden" name="comicId" id="comicId" value="${comic.id }" />
		<!--  Modal Reserve -->
		<div id="modalReserve" class="modal fade" role="dialog">
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
							data-complete-text="Submit Complete!" onclick="Reserve()">
					</div>
				</div>
			</div>
		</div>

		<div id="modalLogin" class="modal fade" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Confirm</h4>
					</div>
					<div class="modal-body">
						<form id="myForm" method="post" class="bs-example form-horizontal">
							<fieldset>
								<legend>Login Form</legend>
								<div class="form-group">
									<label for="emailInput" class="col-lg-3 control-label">E-mail</label>
									<div class="col-lg-9">
										<input type="text" class="form-control" name="email"
											id="emailInput" placeholder="Email" required="required"
											value="" />
									</div>
								</div>

								<div class="form-group">
									<label for="passwordInput" class="col-lg-3 control-label">Password</label>
									<div class="col-lg-9">
										<input type="password" class="form-control" name="password"
											id="passwordInput" placeholder="Password" required="required"
											value="" />
									</div>
								</div>
								<div class="form-group">

									<div class="col-lg-12">
										<a href="javascript:void(0);" onclick="jQuery('#modalForgot').modal('show');jQuery('#modalLoginGeral').modal('hide');jQuery('#modalLogin').modal('hide');">Forgot Password</a>
									</div>
								</div>
								
							</fieldset>
						</form>

					</div>
					<div class="modal-footer">
						<a href="#" class="btn btn-default" data-dismiss="modal">Close</a>
						<input type="submit" value="Logar" id="yesbutton"
							class="btn btn-primary" data-loading-text="Saving.."
							data-complete-text="Submit Complete!" onclick="Logar()">
					</div>
				</div>
			</div>
		</div>

		<div id="modalOut" class="modal fade" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-body">
						<form id="myForm" method="post" class="bs-example form-horizontal">
							<fieldset>
								<legend>Out of Stock</legend>
								<div class="form-group">
									<div class="row">
										<div class="col-lg-12">
											<input type="text" class="form-control" placeholder="E-mail"
												id="emailOut" />
										</div>
									</div>
								</div>
							</fieldset>
						</form>
					</div>
					<div class="modal-footer">
						<a href="#" class="btn btn-default" data-dismiss="modal">Close</a>
						<input type="submit" value="Send" id="yesbutton"
							class="btn btn-primary" data-loading-text="Saving.."
							data-complete-text="Submit Complete!" onclick="AddOut()">
					</div>
				</div>
			</div>
		</div>

		<div id="modalComment" class="modal fade" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-body">
						<form id="myForm" method="post" class="bs-example form-horizontal">
							<fieldset>
								<legend>Add Comment</legend>
								<div class="form-group">
									<div class="row">
										<div class="col-lg-12">
											<textarea class="form-control" rows="3"
												placeholder="Add your comment" id="comment"></textarea>
										</div>
									</div>
								</div>
							</fieldset>
						</form>
					</div>
					<div class="modal-footer">
						<a href="#" class="btn btn-default" data-dismiss="modal">Close</a>
						<input type="submit" value="Send" id="yesbutton"
							class="btn btn-primary" data-loading-text="Saving.."
							data-complete-text="Submit Complete!" onclick="AddComment()">
					</div>
				</div>
			</div>
		</div>

		<div class="row" style="padding: 0 10px; border-top: 2px solid black;">

			<div class="col-md-12">
				<p class="text-right">
					<a href="javascript:void(0);" onclick="modalComment()">+ADD
						COMMENT</a>
				</p>
			</div>
		</div>
		<div class="row" style="padding: 0 10px;">

			<div class="col-md-12">
				<h4 class="text-left">Comments</h4>
			</div>
		</div>
		<br>
		<c:forEach var="note" items="${comic.notes}">
			<c:if test="${note.status ==  1}">
				<div class="row" style="padding: 0 20px;">
					<div class="col-md-12">
						<p class="text-left">" ${note.note } "</p>
					</div>
				</div>
			</c:if>
		</c:forEach>

	</div>
</div>
<%@include file="../../footer.jsp"%>

<script type="text/javascript">
	//codigo ajax do filtro de busca
	var modalcomment = 0;
	function Reserve() {
		comicId = jQuery('#comicId').val();
		qty = jQuery('#qtyComic').val();
		if (qty <= 0) {
			alert("Quantity invalid!");
			jQuery('#modalReserve').modal('hide');
			return false;
		}

		jQuery
				.ajax({
					method : "GET",
					url : 'doReserve',
					data : {
						comicId : comicId,
						qty : qty

					},
					success : function(data) {
						if (data == "notlogged") {
							jQuery('#modalLogin').modal("show");

						} else {
							if (data == 'reserveOk') {
								alert('Reserve registred');
								jQuery('#modalReserve').modal('hide');
								return true;
							} else if (data == 'reserveFail') {
								alert("Ops...We're really sorry, but this quantity is unavailable.");
								jQuery('#modalReserve').modal('hide');
							}
						}
					}
				});
	}

	function Logar() {
		email = jQuery('#emailInput').val();
		senha = jQuery('#passwordInput').val();

		

		$.post("${pageContext.request.contextPath}/comic/doLogin", {
			email : email,
			password : senha
		}, function(data) {
			if (data == "loginOk") {
				jQuery('#modalLogin').modal("hide");
				alert('Login completed!');
				if (modalcomment == 1) {
					jQuery('#modalComment').modal('show');
				}
				return true;
			} else {
				alert("E-mail or password invalid!")
			}
		});
	}

	function modalComment() {
		jQuery('#modalComment').modal("show");
		modalcomment = 1;
	}

	function AddComment() {
		comment = jQuery('#comment').val();
		comicId = jQuery('#comicId').val();
		jQuery.ajax({
			method : "GET",
			url : 'doComment',
			data : {
				comment : comment,
				comicId : comicId
			},
			success : function(data) {
				if (data == "notlogged") {
					jQuery('#modalComment').modal('hide');
					jQuery('#modalLogin').modal("show");

				} else {
					if (data == 'commentOk') {
						alert('Comment registred');
						jQuery('#modalComment').modal('hide');
						modalcomment = 0;
						return true;
					}
				}
			}
		});
	}

	function AddOut() {
		outemail = jQuery('#emailOut').val();
		comicId = jQuery('#comicId').val();

		if (outemail == "") {
			alert("Invalid e-mail!!");
			return false;
		}
		jQuery.ajax({
			method : "GET",
			url : 'outemail',
			data : {
				email : outemail,
				comicId : comicId
			},
			success : function(data) {
				if ("emailok") {
					alert("Successful to register your request!");
					Query('#modalOut').modal("hide");
				} else {
					alert("Error")
				}
			}
		});
	}
</script>
