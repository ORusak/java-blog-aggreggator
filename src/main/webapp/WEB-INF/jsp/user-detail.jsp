<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras"
	prefix="tilesx"%>

<tilesx:useAttribute name="current" />

<style>
div.tabs {
	padding-top: 1em;
}
</style>

<h1>
	<c:out value="${user.name}" />
</h1>

<!-- В своем аккаунте можно заводить блоги. Админ другим пользователям добавлять не имеет прав. -->
<c:if test="${current=='account'}">
	<jsp:include page="/WEB-INF/layout/add-blog.jsp" />
</c:if>

<div class="tabs" role="tabpanel">

	<!-- Blog tabs -->
	<ul class="nav nav-tabs" role="tablist">
		<c:forEach items="${user.blogs}" var="blog">
			<li><a href="#blog_${blog.id}" data-toggle="tab"><c:out
						value="${blog.name}" /></a></li>
		</c:forEach>
	</ul>

	<!-- Tab panes -->
	<div class="tab-content">
		<c:forEach items="${user.blogs}" var="blog">
			<div role="tabpanel" class="tab-pane" id="blog_${blog.id}">

				<h1>
					Blog -
					<c:out value="${blog.name}" />
					<a class="btn btn-danger triggerRemove"
						href='<spring:url value="/blog/remove/${blog.id}.html"/>'>Remove</a>
				</h1>

				<p>
					<c:out value="${blog.url}" />
				</p>

				<table class="table table-bordered table-hover table-striped">
					<thead>
						<tr>
							<th>Date</th>
							<th>Link</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${blog.items}" var="item">
							<tr>
								<td><c:out value="${item.publishedDate}" /></td>
								<td>
								<strong>
									<a href='<c:out value="${item.link}" />' target="_blank">
										<c:out value="${item.title}" />
									</a>
								</strong>
								<br/>
								${item.description}
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:forEach>
	</div>

</div>

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
		$('.nav-tabs a:first').tab('show'); // Select first tab

		$('.triggerRemove').click(function(e) {
			e.preventDefault();
			$('#modalRemove .removeBtn').attr('href', $(this).attr('href'));
			$('#modalRemove').modal();
		});
	});
</script>
