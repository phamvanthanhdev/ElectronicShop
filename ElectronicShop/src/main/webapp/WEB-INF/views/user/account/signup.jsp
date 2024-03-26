<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h2 class="alert alert-warning">TẠO TÀI KHOẢN</h2>
<h4 class="label label-success">${message }</h4>
<div class="panel panel-default">
	<div class="panel-body">
		<form:form action="signup" modelAttribute="Customers" enctype="multipart/form-data">
			<div class="form-group">
				<label>Tên tài khoản</label>
				<form:input path="id" class="form-control"
					placeholder="Nhập tên tài khoản"/>
				<form:errors path="id" style="color:red; font-style: italic;" ></form:errors>
			</div>
			<div class="form-group">
				<label>Mật khẩu</label>
				<form:input path="password" class="form-control"
					placeholder="Nhập mật khẩu"/>
				<form:errors path="password" style="color:red; font-style: italic;" ></form:errors>
			</div>
			<div class="form-group">
				<label>Họ và tên</label>
				<form:input path="fullname" class="form-control"
					placeholder="Nhập họ tên" />
				<form:errors path="fullname" style="color:red; font-style: italic;"/>
			</div>
			<div class="form-group">
				<label>Email</label>
				<form:input path="email" class="form-control"
					placeholder="Nhập email" />
				<form:errors path="email" style="color:red; font-style: italic;"/>
			</div>
			<div class="form-group">
				<label>Photo</label> 
				<input type="file" name="photo-file" class="form-control">
				<form:hidden path="photo" />
			</div>
			<form:hidden path="activated" />
			<form:hidden path="admin" />

			<button class="btn btn-primary">Đăng ký</button>
		</form:form>
	</div>
</div>





<style>
.detail-img{
	max-height:400px;
	max-width: 100%;
}
</style>