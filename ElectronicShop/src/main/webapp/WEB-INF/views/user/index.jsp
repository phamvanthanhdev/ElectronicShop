<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>
    
  <div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
      <li data-target="#myCarousel" data-slide-to="3"></li>
      <li data-target="#myCarousel" data-slide-to="4"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner">
	
      <div class="item active">
        <a href="<c:url value='/product/detail/${p.id }'/>">
        <img src="<c:url value='/template/web/img/${p.img_url }'/>" alt="Chicago" style="width:100%;">
        <div class="carousel-caption">
          <h3>${p.name }</h3>
          <p><f:formatNumber value="${p.price }" pattern="#,###"/></p>
        </div>
        </a>
      </div>
	
	<c:forEach var="p" items="${prods }">
      <div class="item">
      <a href="<c:url value='/product/detail/${p.id }'/>">
        <img src="<c:url value='/template/web/img/${p.img_url }'/>" alt="Chicago" style="width:100%;">
        <div class="carousel-caption">
          <h3>${p.name }</h3>
          <p><f:formatNumber value="${p.price }" pattern="#,###"/></p>
        </div>
        </a>
      </div>
  </c:forEach>
		
    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>


<h3 class="alert alert-success">SẢN PHẨM NỔI BẬT NHẤT</h3>
	<c:forEach items="${products}" var="p" varStatus="prod">
		<div class="col-sm-4">
			<div class="thumbnail product-icon-wapper">
				<a href="<c:url value='/product/detail/${p.id }'/>"> <img
					class="product-img" style="height: 150px"
					src="<c:url value='/template/web/img/${p.img_url }'/>" alt="Lights"
					style="width: 100%">
				</a>
				<div class="caption">
					<p>${p.name }</p>


					<div class="pull-right">
						<a href="<c:url value='/cart/add-cart-item/${p.id }'/>">
							<button class="btn btn-sm btn-danger">
								<i class="glyphicon glyphicon-shopping-cart"></i>
							</button>
						</a> <a href="<c:url value='/product/favourite-product/${p.id }'/>">
							<button class="btn btn-sm btn-warning">
								<i class="glyphicon glyphicon-star"></i>
							</button>
						</a> <a href="<c:url value='/product/form-mail/${p.id }'/>">
							<button class="btn btn-sm btn-success">
								<i class="glyphicon glyphicon-envelope"></i>
							</button>
						</a>
					</div>
					<p><del><f:formatNumber value="${p.price }" pattern="#,###"/></del></p>
					<p style="color:red"><f:formatNumber value="${p.price * (1-p.sale/100)} " pattern="#,###"/></p>
				</div>
				<c:choose>
					<c:when test="${p.highlight }">
						<img class="prod-icon" src="<c:url value='/template/web/img/tag.png'/>"> 
					</c:when>
					<c:when test="${p.new_product }">
						<img class="prod-icon" src="<c:url value='/template/web/img/new.png'/>"> 
					</c:when>
					<c:when test="${p.sale > 0 }">
						<img class="prod-icon" src="<c:url value='/template/web/img/sale.png'/>"> 
					</c:when>
				</c:choose>
			</div>
		</div>
		</c:forEach>
		<div>
<a class="btn btn-success" href="<c:url value='/product/best-sales'/>">Xem thêm</a>
</div>

<style>
.product-img {
	height: 150px !important;
	max-width: 100%;
}

.product-icon-wapper {
	height: 250px !important;
	max-width: 100%;
	position: relative;
}

.prod-icon{
	position: absolute;
	top:0px;
	right:0px;
}


</style>
  
  



