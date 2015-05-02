<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>user name</th>
			<th>&nbsp;</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${users}" var="user">
			<tr>
				<td><a href='<spring:url value="/users/${user.id}.html"/>'> <c:out
							value="${user.name}" /></a></td>
				<td><a class="btn btn-danger triggerRemove"
					href='<spring:url value="/users/remove/${user.id}.html"/>'>
						Remove</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<!-- Modal -->
<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Remove blog</h4>
			</div>
			<div class="modal-body">Really remove?</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
				<a class="btn btn-danger removeBtn" href=''>Remove</a>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
<!--
	//-->
	$(document).ready(function() {
		$('.triggerRemove').click(function(e) {
			e.preventDefault();
			$('#modalRemove .removeBtn').attr('href', $(this).attr('href'));
			$('#modalRemove').modal();
		});
	});
</script>