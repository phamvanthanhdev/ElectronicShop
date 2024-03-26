<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h2 class="alert alert-warning">CẬP NHẬT TÀI KHOẢN</h2>
<h4 class="label label-success">${message }</h4>
<div class="panel panel-default">
	<div class="panel-body">
	<div class="row">
	<div class="col-sm-5 text-center">
		<img class="detail-img" src="<c:url value='/template/web/img/${image}'/>"
			alt="Lights" style="width: 100%">
	</div>
	<div class="col-sm-7">
		<form:form action="edit" modelAttribute="Customers" enctype="multipart/form-data">
			<div class="form-group">
				<label>Tên tài khoản</label><br>
				<form:errors path="id" style="color:red; font-style: italic;" ></form:errors>
				<form:input path="id" class="form-control"
					placeholder="Nhập tên tài khoản" readonly="true"/>
			</div>
			<div class="form-group">
				<label>Họ và tên</label><br>
				<form:errors path="fullname" style="color:red; font-style: italic;" ></form:errors>
				<form:input path="fullname" class="form-control"
					placeholder="Nhập mật khẩu" />
			</div>
			<div class="form-group">
				<label>Email</label><br>
				<form:errors path="email" style="color:red; font-style: italic;" ></form:errors>
				<form:input path="email" class="form-control"
					placeholder="Nhập email" />
			</div>
			<div class="form-group">
				<label>Photo</label> 
				<input type="file" name="photo-file" class="form-control">
				<form:hidden path="photo" />
			</div>
			<form:hidden path="password" />
			<form:hidden path="activated" />
			<form:hidden path="admin" />

			<button class="btn btn-primary">Cập nhật</button>
		</form:form>
		</div>
	</div>
</div>
</div>



<style>
.detail-img{
	max-height:400px;
	max-width: 100%;
}
</style>