<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<title>Trang chủ</title>
<body>
<h2 class="alert alert-success">Doanh số bán hàng</h2>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>Tên loại</th>
				<th>Tổng số lượng</th>
				<th>Doanh số</th>
				<th>Giá bán thấp nhất</th>
				<th>Giá bán cao nhất</th>
				<th>Giá bán trung bình</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="e" items="${data }">
				<tr>
					<td>${e[0]}</td>
					<td>${e[1]}</td>
					<td><f:formatNumber value="${e[2]}" pattern="#,###"/></td>
					<td><f:formatNumber value="${e[3]}" pattern="#,###"/></td>
					<td><f:formatNumber value="${e[4]}" pattern="#,###"/></td>
					<td><f:formatNumber value="${e[5]}" pattern="#,###"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>


</body>