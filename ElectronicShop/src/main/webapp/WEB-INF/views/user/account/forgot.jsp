<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h2 class="alert alert-warning">QUÊN MẬT KHẨU</h2>
<h4 class="label label-success">${message }</h4>
<div class="panel panel-default">
	<div class="panel-body">
			<form:form action="forgot" modelAttribute="Customers" method="post">
					<label class="control-label">Tên đăng nhập</label><br>
					<form:errors path="id" style="color:red; font-style: italic;" ></form:errors>
						<form:input path="id" class="form-control"
					placeholder="Nhập tên tài khoản"/> </br>
						<button  class="btn btn-primary">Send Mail</button>
					</form:form>
	</div>
</div>





<style>
.detail-img{
	max-height:400px;
	max-width: 100%;
}
</style>