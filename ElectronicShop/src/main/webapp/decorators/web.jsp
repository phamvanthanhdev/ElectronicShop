<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Electronic Shop</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="//ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
  <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <link href="<c:url value='/template/web/css/style.css'/>" rel="stylesheet">
  <link href="<c:url value='/template/web/css/style.css'/>" rel="stylesheet" type="text/css">
</head>
<body>

<div class="container">
	<header class="row">
		<h1 class="alert alert-success">Electronic Shopping</h1>
	</header>
  	<nav class="row">
  		<%@include file="/common/web/menu.jsp" %>
  	</nav>
  	<div class="row">
  		<article class="col-sm-9">
  			<dec:body/>
  		</article>
  		<aside class="col-sm-3">
  			<%@include file="/common/web/aside.jsp" %>
  		</aside>
  	</div>
  	<footer class="row">
  		<p class="text-center">&copy;2023. All rights reserved</p>
  	</footer>
</div>

</body>
</html>


<%-- <!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <!-- <title>Twitter Bootstrap shopping cart</title> -->
    <title><dec:title default = "Master Layout"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    
    <link href="<c:url value='/template/web/css/bootstrap.css'/>" rel="stylesheet" type="text/css">
    
    <link href="<c:url value='/template/web/style.css'/>" rel="stylesheet" type="text/css">
    
	<link href="<c:url value='/template/web/font-awesome/css/font-awesome.css'/>" rel="stylesheet" type="text/css">
		

	<!-- Favicons -->
   <!--  <link rel="shortcut icon" href="assets/ico/favicon.ico"> -->
    <link href="<c:url value='/template/web/ico/favicon.ico'/>" rel="stylesheet" type="text/css">
  </head>
<body>
<!-- 
	Upper Header Section 
-->
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="topNav">
		<div class="container">
			<div class="alignR">
				<div class="pull-left socialNw">
					<a href="#"><span class="icon-twitter"></span></a>
					<a href="#"><span class="icon-facebook"></span></a>
					<a href="#"><span class="icon-youtube"></span></a>
					<a href="#"><span class="icon-tumblr"></span></a>
				</div>
				<!-- <a class="active" href="index.html"> <span class="icon-home"></span> Home</a>  -->
				<a class="active" href="<c:url value='/index'/>"> <span class="icon-home"></span> Home</a>
				<a href="<c:url value='/myAccount'/>"><span class="icon-user"></span> My Account</a> 
				<c:if test="${sessionScope.account != null}">
					
                    <a href="<c:url value='/login'/>"><span class="icon-edit"></span> Hello ${sessionScope.account.id} </a> 
                    <a href="<c:url value='/logout'/>"><span class="icon-envelope"></span> Đăng xuất </a> 
                </c:if>
                <c:if test="${sessionScope.account == null}">
                <a href="<c:url value='/login'/>"><span class="icon-user"></span> My Account</a> 
				<a href="<c:url value='/login'/>"><span class="icon-edit"></span> Đăng nhập </a> 
				</c:if>
				<a href="<c:url value='/signin'/>"><span class="icon-envelope"></span> Đăng ký </a>
				
				<c:if test="${sessionScope.account != null}">
				<a href="<c:url value= '/list-shopping-cart/'/>"><span class="icon-shopping-cart"></span> ${count } Item(s) - <span class="badge badge-warning"> ${total } VNĐ</span></a>
				</c:if>
				<c:if test="${sessionScope.account == null}">
				<a href="<c:url value= '/login'/>"><span class="icon-shopping-cart"></span> Item(s) - <span class="badge badge-warning"> $</span></a>
				</c:if>
			</div>
		</div>
	</div>
</div>

<!--
Lower Header Section 
-->
<div class="container">
<div id="gototop"> </div>

<%@include file="/common/web/header.jsp" %>

<!--  Body Section  -->

<dec:body/>

<!-- Footer -->

<%@include file="/common/web/footer.jsp" %>

</div><!-- /container -->

<div class="copyright">
<div class="container">
	<p class="pull-right">
		<a href="#"><img src="assets/img/maestro.png" alt="payment"></a>
		<a href="#"><img src="assets/img/mc.png" alt="payment"></a>
		<a href="#"><img src="assets/img/pp.png" alt="payment"></a>
		<a href="#"><img src="assets/img/visa.png" alt="payment"></a>
		<a href="#"><img src="assets/img/disc.png" alt="payment"></a>
	</p>
	<span>Copyright &copy; 2013<br> bootstrap ecommerce shopping template</span>
</div>
</div>
<a href="#" class="gotop"><i class="icon-double-angle-up"></i></a>
    <!-- Placed at the end of the document so the pages load faster -->
    <!-- <script src="assets/js/jquery.js"></script> -->
    <script src="<c:url value='/template/web/js/jquery.js'/>"></script>
	<script src="<c:url value='/template/web/js/bootstrap.min.js'/>"></script>
	<script src="<c:url value='/template/web/js/jquery.easing-1.3.min.js'/>"></script>
    <script src="<c:url value='/template/web/js/jquery.scrollTo-1.4.3.1-min.js'/>"></script>
    <script src="<c:url value='/template/web/assets/js/shop.js'/>"></script>
  </body>
</html> --%>



















