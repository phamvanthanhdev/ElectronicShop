<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<div class="panel panel-default">
  <div class="panel-heading">Shopping Cart</div>
	<div class="panel-body">
		<img alt="" src="<c:url value='/template/web/img/shopping-cart.png'/>" class="col-sm-5">
		<ul class="col-sm-7">
		<li>10 mặt hàng</li>
		<li>150.000 VNĐ</li>
		<li>
			<a href="<c:url value='/cart/list-cart-item'/>">Xem giỏ hàng</a>
		</li>
		</ul>
	</div>
		
</div>    
    
    
<div class="panel panel-default">
  <div class="panel-heading">TÌM KIẾM</div>
	<div class="panel-body">
		<form action="<c:url value='/product/list-by-keyword'/>">
			<input value="${param.keywords }" name = "keywords" class = "form-control" placeholder="Keywords">
		</form>
	</div>
		
</div>

<div class="panel panel-default">
  <div class="panel-heading">DANH MỤC</div>
	<div class="list-group">
		<c:forEach var="c" items="${listCate }">
			<a href="<c:url value='/product/list-by-category/${c.id }'/>" class="list-group-item">${c.name}</a> 
		</c:forEach>
	</div>
</div>

<div class="panel panel-default">
  <div class="panel-heading">ĐẶC BIỆT</div>
	<div class="list-group">
		<a href="<c:url value='/product/new-product'/>" class="list-group-item">Hàng mới</a> 
		<a href="<c:url value='/product/best-sales'/>"
			class="list-group-item">Hàng bán chạy</a> 
		<a href="<c:url value='/product/favourite-product'/>"
			class="list-group-item">Hàng yêu thích</a>
		<a href="<c:url value='/product/discount-product'/>"
			class="list-group-item">Hàng giảm giá</a>
	</div>
</div>


