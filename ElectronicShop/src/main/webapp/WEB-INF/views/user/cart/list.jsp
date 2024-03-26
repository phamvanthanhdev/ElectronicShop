<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<title>Trang chủ</title>
<body>
	<h2 class="alert alert-warning">Category Management</h2>
	<h4 class="label label-success">${message }${param.message }</h4>
	

			<table class="table table-hover">
				<thead>
					<tr>
						<th>Tên sản phẩm</th>
						<th>Giá </th>
						<th>Số lượng</th>
						<th>Giảm giá</th>
						<th>Tổng giá</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="e" items="${list }">
						<tr>
						<form action="<c:url value= '/cart/cart-item/update'/>">
							<td>${e.products.name}</td>
							<td><f:formatNumber value="${e.products.price}" pattern="#,###"/></td>
							<td>
								<input style="max-width: 50px" name = "quantity" 
								type="number" min="0" value= ${e.quantity }>
								
							</td>
							<td>${e.products.sale} %</td>
							<td>
								<f:formatNumber value="${(e.products.price * e.quantity) *((100 - e.products.sale)/100)}" pattern="#,###"/>
							</td>
							<td>
								<input name = "id" value="${e.id}" hidden="true">
								<button type="submit" class="btn btn-sm btn-info">
									Update
								</button>
								<a class="btn btn-sm btn-danger"
								href="<c:url value ='/cart/cart-item/delete/${e.id }'/>">Delete</a>
							</td>
							
							</form>
						</tr>
					</c:forEach>
				</tbody>
			</table>
<a class="btn btn-sm btn-danger"
		href="<c:url value ='/cart/clear'/>">Clear</a>
<a class="btn btn-sm btn-success"
		href="<c:url value ='/order/checkout'/>">Đặt hàng</a>

	<script type="text/javascript">
 
        bkLib.onDomLoaded(function() { nicEditors.allTextAreas() });
  </script>
</body>



















