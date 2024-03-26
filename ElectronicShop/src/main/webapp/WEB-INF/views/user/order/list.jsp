<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


	<h2 class="alert alert-warning">Đơn hàng đã mua</h2>
	<h4 class="label label-success">${message }${param.message }</h4>
<div class="panel panel-default">
	<div class="panel-body">

	<table class="table table-hover">
		<thead>
			<tr>
				<th>Mã đơn hàng</th>
				<th>Ngày đặt hàng</th>
				<th>Địa chỉ giao hàng</th>
				<th>Tổng số tiền</th>
				<th>Trạng thái</th>
				<th><th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="e" items="${list }">
				<tr>
					<td>${e.id}</td>
					<td>${e.orderDate }</td>
					<td>${e.address }</td>
					<td><f:formatNumber value="${e.amount}"
							pattern="#,###" />
					</td>
					<td>${e.isPaid?'Đã thanh toán':'Chưa thanh toán'}</td>
					<td><a class="btn btn-sm btn-danger"
							href="<c:url value ='/order/order-detail/${e.id }'/>">Chi tiết</a></td>
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




















