<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h2 class="alert alert-warning">Gửi thông tin sản phẩm tới bạn bè</h2>
<h4 class="label label-success">${message }</h4>
<div class="panel panel-default">
	<div class="panel-body">
		<form action="<c:url value='/product/send-mail'/>" method="post">
			<div class="form-group">
					<label>Họ tên người gửi</label>
					<p style="color:red; font-style: italic;">${error_name }</p>
					<input name="name" class="form-control" value="${name}"/>
			</div>
			<div class="form-group">
					<label>Email nhận</label>
					<p style="color:red; font-style: italic;">${error_to }</p>
					<input name="to" class="form-control" value="${to}"/>
			</div>
			<div class="form-group">
					<label>Tiêu đề</label>
					<p style="color:red; font-style: italic;">${error_subject }</p>
					<input name="subject" class="form-control" value="${subject}"/>
			</div>
			<div class="form-group">
					<label>Nội dung</label>
					<input name="message" class="form-control" value="${message}"/>
			</div>
			<input name="id" type="hidden" value="${id}">

			<button class="btn btn-primary">Gửi</button>
		</form>
	</div>
</div>

<script type="text/javascript">
	bkLib.onDomLoaded(function() {
		nicEditors.allTextAreas()
	});
</script>