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
					<form id="myForm" method="post" class="bs-example form-horizontal"
						action="editComic" enctype="multipart/form-data">

						<fieldset>
							<legend>Comic Form</legend>
							<div class="form-group">
								<label for="titleInput" class="col-lg-3 control-label">Title</label>
								<div class="col-lg-9">
									<input type="text" class="form-control" name="title"
										id="titleInput" placeholder="Title" value="${comic.title }" required="required" />
								</div>
							</div>
							<div class="form-group">
								<label for="descriptionInput" class="col-lg-3 control-label">Description</label>
								<div class="col-lg-9">
									<textarea  class="form-control" name="description"
											id="descriptionInput" placeholder="Description"  required="required">${comic.description}</textarea>
								</div>
							</div>
							<div class="form-group">
								<label for="volumeInput" class="col-lg-3 control-label">Volume</label>
								<div class="col-lg-9">
									<input type="text" class="form-control" name="vol"
										id="volumeInput" placeholder="Volume" value="${comic.vol }" required="required"/>
								</div>
							</div>
							<div class="form-group">
								<label for="editionInput" class="col-lg-3 control-label">Edition</label>
								<div class="col-lg-9">
									<input type="text" class="form-control" name="edition"
										id="editionInput" placeholder="Edition" required="required"
										value="${comic.edition }" />
								</div>
							</div>
							<div class="form-group">
								<label for="publicationInput" class="col-lg-3 control-label">Publication</label>
								<div class="col-lg-9">
									<input type="text" class="form-control" name="publication"
										id="publicationInput" placeholder="Publication" required="required"
										value="${publicationdate }" />
								</div>
							</div>
							<div class="form-group">
								<label for="stockInput" class="col-lg-3 control-label">Stock</label>
								<div class="col-lg-9">
									<input type="text" class="form-control" name="stock"
										id="stockInput" placeholder="Stock" value="${comic.stock }" required="required"/>
								</div>
							</div>
							<div class="form-group">
								<label for="stockInput" class="col-lg-3 control-label">Publishing
									House</label>
								<div class="col-lg-9">
									<select name="phouse" class="form-control" required="required">
										<c:forEach var="ph" items="${publishingHouses}">
											<option value="${ph.id }"
												<c:if test="${comic.publishingHouse.id eq ph.id}">selected="selected"</c:if>>${ph.name }
											</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label for="valueInput" class="col-lg-3 control-label">Value</label>
								<div class="col-lg-9">
									<input type="numeric" class="form-control" name="value"
										id="valueInput" placeholder="Value" value="${comic.value }" required="required" />
								</div>
							</div>
							<div class="form-group">
								<label for="showHomeInput" class="col-lg-3 control-label">Show
									Home</label>
								<div class="col-lg-9">
									<select name="showHome" id="showHomeInput" class="form-control" required="required">
										<option value="1"  <c:if test="${comic.showHome eq 1}">selected="selected"</c:if> >Yes</option>
										<option value="0" <c:if test="${comic.showHome eq 0}">selected="selected"</c:if> >No</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label for="imageCoverInput" class="col-lg-3 control-label">Image
									Cover </label>
								<div class="col-lg-9">
									<img src='<c:url value="${imageurl }"/>' style="width: 60px;" />
									<input type="file" class="" name="fileUpload"
										id="imageCoverInput" placeholder="Image Cover" />
								</div>
							</div>
							<fieldset class="scheduler-border">
								<legend class="scheduler-border">Author</legend>
								<div class="form-group">
									<label for="authorInput" class="col-lg-3 control-label">Editor</label>
									<div class="col-lg-9">
										<select name="comicAuthorsEditorId" class="form-control" required="required"
											multiple="multiple">
											<c:forEach var="author" items="${authors}">
												<option value="${author.id }"
													<c:forEach var="ca" items="${authorsEditor }">
							                	   <c:if test="${ca.id eq author.id}">selected="selected"</c:if>
							                    </c:forEach>>
													${author.name }</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="authorInput" class="col-lg-3 control-label">Finalist
										Art</label>
									<div class="col-lg-9">
										<select name="comicAuthorsFinalistId" class="form-control" required="required"
											multiple="multiple">
											<c:forEach var="author" items="${authors}">
												<option value="${author.id }"
													<c:forEach var="ca" items="${authorsFinalist }">
							                	   <c:if test="${ca.id eq author.id}">selected="selected"</c:if>
							                    </c:forEach>>
													${author.name }</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="authorInput" class="col-lg-3 control-label">Writer</label>
									<div class="col-lg-9">
										<select name="comicAuthorsWriterId" class="form-control" required="required"
											multiple="multiple">
											<c:forEach var="author" items="${authors}">
												<option value="${author.id }"
													<c:forEach var="ca" items="${authorsWriter }">
							                	   <c:if test="${ca.id eq author.id}">selected="selected"</c:if>
							                    </c:forEach>>
													${author.name }</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</fieldset>
							<div class="form-group">
								<label for="genreInput" class="col-lg-3 control-label">Genre</label>
								<div class="col-lg-9">
									<select name="genresIds" class="form-control" required="required"
										multiple="multiple">
										<c:forEach var="genre" items="${genres}">
											<option value="${genre.id }"
												<c:forEach var="g" items="${comic.genres }">
							                	   <c:if test="${g.id eq genre.id}">selected="selected"</c:if>
							                    </c:forEach>>
												${genre.name }</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="col-lg-9 col-lg-offset-3">
								<button class="btn btn-default">Cancel</button>
								<button class="btn btn-primary" type="submit">Submit</button>
							</div>

						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>


<script>
	$(function() {
		$('#publicationInput').datepicker();
	});
</script>

<script type="text/javascript">
	$(function() {
		var yesButton = $("#yesbutton");
		var progress = $("#doitprogress");

		yesButton.click(function() {
			yesButton.button("loading");

			var counter = 0;
			var countDown = function() {
				counter++;
				if (counter == 11) {
					yesButton.button("complete");
				} else {
					progress.width(counter * 10 + "%");
					setTimeout(countDown, 100);
				}
			};

			setTimeout(countDown, 100);
		});

		$('#publication').mask('MM/dd/YYYY');

	});
</script>
<%@include file="../footer.jsp"%>