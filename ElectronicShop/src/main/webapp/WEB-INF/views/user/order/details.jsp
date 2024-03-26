<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<title>Trang chủ</title>
<body>
	<h2 class="alert alert-warning">Chi tiết đơn hàng đã mua</h2>
	<h4 class="label label-success">${message }${param.message }</h4>
	
	<form:form action="order-details" modelAttribute="order">
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
				<label>Địa chỉ</label>
				<form:input path="address" class="form-control" readonly="true" />
			</div>
			<div class="form-group col-sm-6">
				<label>Tổng tiền</label>
				<form:input path="amount" class="form-control" readonly="true" />
			</div>
		</div>
		<div class="row">
			<div class="form-group col-sm-12">
				<label>Ghi chú</label>
				<form:textarea path="description" row="3" class="form-control" readonly="true" />
			</div>
		</div>

	</form:form>
	
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Id</th>
							<th>Name</th>
							<th>Unit Price</th>
							<th>Discount</th>
							<th>Quantity</th>
							<th>Amount</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="d" items="${details }">
							<tr>
								<td>${d.id}</td>
								<td>${d.productEntity.name}</td>
								<td><f:formatNumber value="${d.unitPrice}"
							pattern="#,###" /></td>
								
								<td>${d.productEntity.sale}</td>
								<td>${d.quantity}</td>
								<td><f:formatNumber value="${d.productEntity.price * d.quantity * ((100-d.productEntity.sale)/100)}"
							pattern="#,###" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

	<script type="text/javascript">
		bkLib.onDomLoaded(function() {
			nicEditors.allTextAreas()
		});
	</script>
</body>



















