<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<title>Trang chủ</title>
<body>
	<h2 class="alert alert-warning">Order Management</h2>
	<h4 class="label label-success">${message }${param.message }</h4>
	<ul class="nav nav-tabs">
		<li class="active"><a data-toggle="tab" href="#tab1">List</a></li>
		<li><a data-toggle="tab" href="#tab2">Edit</a></li>
	</ul>

	<div class="tab-content">
		<div id="tab1" class="tab-pane fade in active">
			<div class="panel panel-default">
				<div class="panel-body">
					<form:form action="admin/order/index" modelAttribute="entity">
						<div class="row">
							<div class="form-group col-sm-6">
								<label>Id</label>
								<form:input path="id" class="form-control" readonly="true" />
							</div>
							<div class="form-group col-sm-6">
								<label>Customer ID</label>
								<form:input path="customer.id" class="form-control" />
							</div>
						</div>
						<div class="row">
							<div class="form-group col-sm-6">
								<label>Amount</label>
								<form:input path="amount" class="form-control" />
							</div>
							<div class="form-group col-sm-6">
								<label>Address</label>
								<form:input path="address" class="form-control" />
							</div>
						</div>
						<div class="row">
							<div class="form-group col-sm-6">
								<label>Order Date</label>
								<form:input path="orderDate" class="form-control" />
							</div>
							<div class="form-group col-sm-6">
								<label>Is Paid ?</label>
								<form:radiobutton path="isPaid" value="true" label="Yes" />
								<form:radiobutton path="isPaid" value="false" label="No" />
							</div>
						</div>
						<div class="row">
							<div class="form-group col-sm-12">
								<label>Description</label>
								<form:textarea path="description" row="3" class="form-control" />
							</div>
							<div class="form-group col-sm-12">
								<%-- <button class="btn btn-primary"
									formaction="<c:url value ='/admin/order/create'/>">Create</button> --%>
								<button class="btn btn-warning"
									formaction="<c:url value ='/admin/order/update'/>">Update</button>
								<button class="btn btn-danger"
									formaction="<c:url value ='/admin/order/delete'/>">Delete</button>
								<a class="btn btn-default"
									href="<c:url value ='/admin/order/index'/>">Reset</a>
							</div>
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
								<td><f:formatNumber value="${d.unitPrice}" pattern="#,###"/></td>
								<td>${d.productEntity.sale}</td>
								<td>${d.quantity}</td>
								<td><f:formatNumber value="${d.productEntity.price * d.quantity * (100-d.productEntity.sale)}" pattern="#,###"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>
		</div>
		<div id="tab2" class="tab-pane fade">

			<table class="table table-hover">
				<thead>
					<tr>
						<th>Id</th>
						<th>Customer Id</th>
						<th>Order Date</th>
						<th>Shipping Address</th>
						<th>Is Paid ?</th>
						<th>Total Amount</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="e" items="${list }">
						<tr>
							<td>${e.id}</td>
							<td>${e.customer.id}</td>
							<td>${e.orderDate}</td>
							<td>${e.address}</td>
							<td>${e.isPaid?'Đã thanh toán':'Chưa thanh toán'}</td>
							<td><f:formatNumber value="${e.amount}" pattern="#,###"/></td>
							<td><a class="btn btn-sm btn-info"
								href="<c:url value ='/admin/order/edit/${e.id }'/>">Edit</a> <a
								class="btn btn-sm btn-danger"
								href="<c:url value ='/admin/order/delete/${e.id }'/>">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
	</div>
	<script type="text/javascript">
		bkLib.onDomLoaded(function() {
			nicEditors.allTextAreas()
		});
	</script>
</body>



















