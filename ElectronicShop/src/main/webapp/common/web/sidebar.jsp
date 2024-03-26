<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>

<div id="sidebar" class="span3">
<div class="well well-small">
	<ul class="nav nav-list">
	<c:forEach items="${listCate}" var="cate">
			<li><a href="<c:url value='/list-by-category/${cate.id }'/>"><span class="icon-circle-blank"></span> ${cate.name }</a></li>
			<%-- <li><a href="/OnlineShop/list-by-category/${cate.id }"><span class="icon-circle-blank"></span> ${cate.name }</a></li> --%>
	</c:forEach>
		<li style="border:0"> &nbsp;</li>
		<li> <a class="totalInCart" href="cart.html"><strong>Total Amount  <span class="badge badge-warning pull-right" style="line-height:18px;">$448.42</span></strong></a></li>
	</ul>
</div>

<div class="well well-small">
	<ul class="nav nav-list">
			<li><a href="<c:url value='/product-new'/>"><span class="icon-circle-blank"></span> Sản phẩm mới</a></li>
			<li><a href="<c:url value='/product-special'/>"><span class="icon-circle-blank"></span> Sản phẩm đặc biệt</a></li>
			<li><a href="<c:url value='/product-sales'/>"><span class="icon-circle-blank"></span> Sản phẩm bán chạy</a></li>
			<li><a href="<c:url value='/product-discount'/>"><span class="icon-circle-blank"></span> Sản phẩm giảm giá</a></li>
	</ul>
</div>

			  <div class="well well-small alert alert-warning cntr">
				  <h2>50% Discount</h2>
				  <p> 
					 only valid for online order. <br><br><a class="defaultBtn" href="#">Click here </a>
				  </p>
			  </div>
			  <div class="well well-small" ><a href="#"><img src="assets/img/paypal.jpg" alt="payment method paypal"></a></div>
			
			<a class="shopBtn btn-block" href="#">Upcoming products <br><small>Click to view</small></a>
			<br>
			<br>
			<ul class="nav nav-list promowrapper">
			<li>
			  <div class="thumbnail">
				<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
				<img src="assets/img/bootstrap-ecommerce-templates.png" alt="bootstrap ecommerce templates">
				<div class="caption">
				  <h4><a class="defaultBtn" href="product_details.html">VIEW</a> <span class="pull-right">$22.00</span></h4>
				</div>
			  </div>
			</li>
			<li style="border:0"> &nbsp;</li>
			<li>
			  <div class="thumbnail">
				<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
				<img src="assets/img/shopping-cart-template.png" alt="shopping cart template">
				<div class="caption">
				  <h4><a class="defaultBtn" href="product_details.html">VIEW</a> <span class="pull-right">$22.00</span></h4>
				</div>
			  </div>
			</li>
			<li style="border:0"> &nbsp;</li>
			<li>
			  <div class="thumbnail">
				<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
				<img src="assets/img/bootstrap-template.png" alt="bootstrap template">
				<div class="caption">
				  <h4><a class="defaultBtn" href="product_details.html">VIEW</a> <span class="pull-right">$22.00</span></h4>
				</div>
			  </div>
			</li>
		  </ul>

	</div>