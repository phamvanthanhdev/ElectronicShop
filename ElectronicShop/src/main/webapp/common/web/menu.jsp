<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="<c:url value='/'/>">Home</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="#"><a href="#">About Us</a></li>
      <li><a href="#">Contact Us</a></li>
      <li><a href="#">Feedback</a></li>
      <li><a href="#">FAQs</a></li>
      <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="<c:url value='/account/'/>">Account
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
        <c:if test="${sessionScope.account == null}">
        	<li><a href="<c:url value='/account/login'/>">Đăng nhập</a></li>
            <li><a href="<c:url value='/account/signup'/>">Đăng ký</a></li>
        </c:if>
        <c:if test="${sessionScope.account != null}">
      		<li><a href="<c:url value='/account/logout'/>">Đăng xuất</a></li>
      		<li><a href="<c:url value='/account/forgot'/>">Quên mật khẩu</a></li>
          	<li><a href="<c:url value='/account/change'/>">Đổi mật khẩu</a></li>
          	<li><a href="<c:url value='/account/edit'/>">Cập nhật tài khoản</a></li>
          	<li><a href="<c:url value='/order/list-orders'/>">Đơn hàng</a></li>
          	<li><a href="<c:url value='/order/items'/>">Sản phẩm đã mua</a></li>
      	</c:if>
          
        </ul>
      </li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <c:if test="${sessionScope.account != null}">
      	<li><a href="<c:url value='/account/edit'/>"><span class="glyphicon glyphicon-user"></span>  ${sessionScope.account.id}</a></li>
      </c:if>
      <li><a href="<c:url value='/account/login'/>"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
      <li><a href="<c:url value='/account/logout'/>"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
    </ul>
  </div>
</nav>