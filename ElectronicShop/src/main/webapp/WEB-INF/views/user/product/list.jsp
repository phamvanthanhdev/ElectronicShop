<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>
<link href="<c:url value='/template/web/css/style.css'/>"
	rel="stylesheet">
<body>
<h3 class="alert alert-warning">${message }</h3>

<!-- <div class="bg-light p-5 rounded"> -->
<jsp:useBean id="pagedListHolder" scope="request"
	type="org.springframework.beans.support.PagedListHolder" />
<c:url value="${value }" var="pagedLink">
	<c:param name="p" value="~" />
</c:url>


<c:if test="${pagedListHolder.pageList.size() > 0 }">
	<c:forEach items="${pagedListHolder.pageList}" var="p" varStatus="prod">
	<%-- <c:forEach items="${products}" var="p" varStatus="prod"> --%>
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
		<%-- </c:forEach> --%>
		<c:if
			test="${(prod.index + 1) % 3 == 0 || (prod.index + 1) == pagedListHolder.pageList.size()}">

			<c:if test="${(prod.index + 1) < pagedListHolder.pageList.size()}">

			</c:if>
		</c:if>
	</c:forEach>
</c:if>
<div>
<tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}" />
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

</body>