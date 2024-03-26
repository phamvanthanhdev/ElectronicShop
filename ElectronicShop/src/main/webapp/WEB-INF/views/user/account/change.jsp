<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h2 class="alert alert-warning">ĐỔI MẬT KHẨU</h2>
<h4 class="label label-success">${message }</h4>
<div class="panel panel-default">
	<div class="panel-body">
		<form action="<c:url value='/account/change'/>" method="post">
			<div class="form-group">
					<label>Tên tài khoản</label>
					<p style="color:red; font-style: italic;">${error_id }</p>
					<input name="id" class="form-control" placeholder="Nhập tên tài khoản" value="${id}"/>
			</div>
			<div class="form-group">
					<label>Mật khẩu</label>
					<p style="color:red; font-style: italic;">${error_pw }</p>
					<input name="pw" class="form-control" placeholder="Nhập mật khẩu" value="${pw}"/>
			</div>
			<div class="form-group">
					<label>Mật khẩu mới</label>
					<p style="color:red; font-style: italic;">${error_pw1 }</p>
					<input name="pw1" class="form-control" placeholder="Nhập mật khẩu" value="${pw1}"/>
			</div>
			<div class="form-group">
					<label>Mật khẩu xác nhận</label>
					<p style="color:red; font-style: italic;">${error_pw2 }</p>
					<input name="pw2" class="form-control" placeholder="Nhập mật khẩu" value="${pw2}"/>
			</div>
			<button class="btn btn-primary">Thay đổi</button>
		</form>
	</div>
</div>