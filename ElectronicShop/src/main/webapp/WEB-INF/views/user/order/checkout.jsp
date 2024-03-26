<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


	<h2 class="alert alert-warning">Thủ tục đặt hàng</h2>
	<h4 class="label label-success">${message }${param.message }</h4>
<div class="panel panel-default">
	<div class="panel-body">

	<form:form action="checkout" modelAttribute="order">
		<div class="row">
			<div class="form-group col-sm-6">
				<label>Tên đăng nhập</label>
				<form:input path="customer.id" class="form-control" readonly="true" />
			</div>
			<div class="form-group col-sm-6">
				<label>Ngày mua</label>
				<form:input path="orderDate" class="form-control" readonly="true" />
			</div>
		</div>
		<div class="row">
			<div class="form-group col-sm-6">
				<label>Địa chỉ</label><br>
				<form:errors path="address" style="color:red; font-style: italic;" ></form:errors>
				<form:input path="address" class="form-control" />
			</div>
			<div class="form-group col-sm-6">
				<label>Tổng tiền</label>
				<form:input path="amount" class="form-control" readonly="true" />
			</div>
		</div>
		<div class="row">
			<div class="form-group col-sm-12">
				<label>Description</label>
				<form:textarea path="description" row="3" class="form-control" />
			</div>
			<div class="form-group col-sm-12">
				<button class="btn btn-primary">Đặt hàng</button>
			</div>
		</div>

	</form:form>

	<table class="table table-hover">
		<thead>
			<tr>
				<th>Tên sản phẩm</th>
				<th>Giá</th>
				<th>Số lượng</th>
				<th>Giảm giá</th>
				<th>Tổng giá</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="e" items="${list }">
				<tr>
					<td>${e.products.name}</td>
					<td><f:formatNumber value="${e.products.price}"
							pattern="#,###" /></td>
					<td>${e.quantity }</td>
					<td>${e.products.sale}%</td>
					<td><f:formatNumber
							value="${(e.products.price * e.quantity) *((100 - e.products.sale)/100)}"
							pattern="#,###" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<script type="text/javascript">
	bkLib.onDomLoaded(function() {
		nicEditors.allTextAreas()
	});
</script>




















