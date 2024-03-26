<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h2 class="alert alert-warning">KÍCH HOẠT TÀI KHOẢN</h2>
<h4 class="label label-success">${message }</h4>
<div class="panel panel-default">
	<div class="panel-body">
		<form action="<c:url value='/account/activate'/>" method="post">
			<div class="form-group">
					<label>Tên tài khoản</label>
					<input name="id" class="form-control" placeholder="Nhập tên tài khoản" value="${id}"/>
			</div>
			<div class="form-group">
					<label>Nhập mã kích hoạt</label>
					<p style="color:red; font-style: italic;">${error }</p>
					<input name="makh" class="form-control" placeholder="Nhập mã kích hoạt" value="${makh}"/>
			</div>

			<button class="btn btn-primary">Kích hoạt tài khoản</button>
		</form>
	</div>
</div>