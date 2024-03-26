<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="//js.nicedit.com/nicEdit-latest.js"></script> 

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Admin Tool</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="<c:url value='/admin/home'/>">Home</a></li>
      <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Quản lý
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="<c:url value='/admin/category/index'/>">Category</a></li>
          <li><a href="<c:url value='/admin/customer/index'/>">Customer</a></li>
          <li><a href="<c:url value='/admin/product/index'/>">Product</a></li>
          <li><a href="<c:url value='/admin/order/index'/>">Order</a></li>
        </ul>
      </li>
      <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Thống kê
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="<c:url value='/admin/inventory/index'/>">Tồn kho theo loại</a></li>
          <li><a href="<c:url value='/admin/revenue/category'/>">Doanh số theo loại</a></li>
          <li><a href="<c:url value='/admin/revenue/customer'/>">Doanh số theo khách hàng</a></li>
          <li><a href="<c:url value='/admin/revenue/year'/>">Doanh số theo năm</a></li>
          <li><a href="<c:url value='/admin/revenue/quarter'/>">Doanh số theo quý</a></li>
          <li><a href="<c:url value='/admin/revenue/month'/>">Doanh số theo tháng</a></li>
        </ul>
      </li>
      <li><a href="#">Tài khoản</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
    <c:if test="${sessionScope.account != null}">
      	<li><a href="<c:url value='/account/edit'/>"><span class="glyphicon glyphicon-user"></span>  ${sessionScope.account.id}</a></li>
      </c:if>
      <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Sign Up</a></li>
      <li><a href="<c:url value='/account/logout'/>"><span class="glyphicon glyphicon-log-out"></span> Login</a></li>
    </ul>
  </div>
</nav>

