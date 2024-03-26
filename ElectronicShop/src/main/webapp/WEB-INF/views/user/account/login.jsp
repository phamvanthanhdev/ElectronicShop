<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h2 class="alert alert-warning">ĐĂNG NHẬP</h2>
<h4 class="label label-success">${message }</h4>
<div class="panel panel-default">
	<div class="panel-body">
		<form action="<c:url value='/account/login'/>" method="post">
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

			<button class="btn btn-primary">Đăng nhập</button>
		</form>
	</div>
</div>