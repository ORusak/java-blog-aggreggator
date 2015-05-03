<%@ include file="../layout/taglib.jsp"%>

<!-- Button trigger modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal"
	data-target="#myModal">New blog</button>

<!-- Modal -->
<form:form commandName="blog" cssClass="form-horizontal blogForm">
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true" data-show="${show_add_blog}">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">New blog</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">Name:</label>
						<div class="col-sm-10">
							<form:input path="name" cssClass="form-control" />
							<form:errors path="name"/>
						</div>
					</div>
					<div class="form-group">
						<label for="url" class="col-sm-2 control-label">URL:</label>
						<div class="col-sm-10">
							<form:input path="url" cssClass="form-control" />
							<form:errors path="url"/>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<input type="submit" class="btn btn-primary" value="Save" />
				</div>
			</div>
		</div>
	</div>
</form:form>

<script type="text/javascript">
$(document).ready(function() {
		$('#myModal').modal();

		$('.triggerRemove').click(function(e) {
			e.preventDefault();
			$('#modalRemove .removeBtn').attr('href', $(this).attr('href'));
			$('#modalRemove').modal();
		});
		
		$(".blogForm").validate ({
			rules:{
				name: {
					required: true,
					minlength: 1
				},
				url: {
					required: true,
					url: true
				}
			},
			highlight: function(element){
				$(element).closest(".form-group").removeClass("has-success").addClass("has-error");
			},
			unhighlight: function(element){
				$(element).closest(".form-group").removeClass("has-error").addClass("has-success");
			}
		});
	});
</script>