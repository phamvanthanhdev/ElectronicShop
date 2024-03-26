<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>

<div class="row">
	<div class="col-sm-5 text-center">
		<img class="detail-img" src="<c:url value='/template/web/img/${prod.img_url }'/>"
			alt="Lights" style="width: 100%">
	</div>

	<div class="col-sm-7">
		<ul class="detail-info">
			<li>Tên sản phẩm: ${prod.name }</li>
			<li>Giá: <del><f:formatNumber value="${prod.price }" pattern="#,###"/></del></li>
			<li style="color:red">Giá ưu đãi :<f:formatNumber value="${prod.price * (1-prod.sale/100)} " pattern="#,###"/></li>
			<li>Ngày bắt đầu bán: <f:formatDate value = "${prod.created_at }" pattern="dd-MM-yyyy"/></li>
			<li>Loại sản phẩm: ${prod.categoryEntity.name }</li>
			<li>Số lượng: ${prod.quantity }</li>
			<li>Giảm giá: ${prod.sale } %</li>
			<li>Lượt xem: ${prod.viewCount }</li>
			<li>Sản phẩm đặc biệt: ${prod.highlight?'Có':'Không'}</li>
			<li>Sản phẩm mới: ${prod.new_product?'Có':'Không'}</li>

		</ul>
	</div>
</div>

<div class="text-justify">${prod.title }</div>

<ul class="nav nav-tabs">
  <li class="active"><a data-toggle="tab" href="#home">CÙNG LOẠI</a></li>
  <li><a data-toggle="tab" href="#menu1">YÊU THÍCH</a></li>
</ul>

<div class="tab-content">
  <div id="home" class="tab-pane fade in active">
    <h3 class="alert alert-warning">SẢN PHẨM CÙNG LOẠI</h3>
    <c:forEach var="p" items="${listProduct }">
<a href="<c:url value='/product/detail/${p.id }'/>">
	<img class= "thumb-img" style="height:150px" src="<c:url value='/template/web/img/${p.img_url }'/>" 
	  alt="Lights" style="width: 100%">
	  </a>
</c:forEach>
  </div>
  <div id="menu1" class="tab-pane fade">
  <h3 class="alert alert-warning">SẢN PHẨM YÊU THÍCH</h3>
    <c:forEach var="p" items="${favorites }">
<a href="<c:url value='/product/detail/${p.id }'/>">
	<img class= "thumb-img" style="height:150px" src="<c:url value='/template/web/img/${p.img_url }'/>" 
	  alt="Lights" style="width: 100%">
	  </a>
</c:forEach>
  </div>
</div>

<style>
.detail-img{
	max-height:400px;
	max-width: 100%;
}
.detail-info{
	font-variant: small-caps;
	font-size: larger;
	line-height: 30px;
}
.thumb-img{
	width:110px;
	height:30px;
	border-radius:5px;
	margin:3px;
	padding:3px;
	box-shadow: 0px 0px 2px blue;
}

.thumb-img:hover{
	box-shadow: 0px 0px 3px red;
}
</style>

















