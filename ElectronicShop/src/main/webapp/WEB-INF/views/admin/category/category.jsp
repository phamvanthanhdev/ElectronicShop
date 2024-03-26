<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<title>Trang chủ</title>
<body>
	<h2 class="alert alert-warning">Category Management</h2>
	<h4 class="label label-success">${message }${param.message }</h4>
	<ul class="nav nav-tabs">
		<li class="active"><a data-toggle="tab" href="#tab1">List</a></li>
		<li><a data-toggle="tab" href="#tab2">Edit</a></li>
	</ul>

	<div class="tab-content">
		<div id="tab1" class="tab-pane fade in active">
			<div class="panel panel-default">
			<div class="panel-body">
			<form:form action="admin/category/index" modelAttribute="entity">
				<div class="form-group">
					<label>Id</label>
					<form:input path="id" class="form-control" readonly="true" />
				</div>
				<div class="form-group">
					<label>Name</label><br>
					<form:errors path="name" style="color:red; font-style: italic;" ></form:errors>
					<form:input path="name" class="form-control" />
				</div>
				<div class="form-group">
					<label>Description</label>
					<form:textarea path="description" class="form-control" />
				</div>
				<div class="form-group">
					<button class="btn btn-primary"
						formaction="<c:url value ='/admin/category/create'/>">Create</button>
					<button class="btn btn-warning"
						formaction="<c:url value ='/admin/category/update'/>">Update</button>
					<button class="btn btn-danger"
						formaction="<c:url value ='/admin/category/delete'/>">Delete</button>
					<a class="btn btn-default"
						href="<c:url value ='/admin/category/index'/>">Reset</a>
				</div>
				</div>
			</form:form>
			</div>
		</div>
		<div id="tab2" class="tab-pane fade">

			<table class="table table-hover">
				<thead>
					<tr>
						<th>Id</th>
						<th>Name</th>
						<th>Description</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="e" items="${list }">
						<tr>
							<td>${e.id}</td>
							<td>${e.name}</td>
							<td>${e.description}</td>
							<td><a class="btn btn-sm btn-info"
								href="<c:url value ='/admin/category/edit/${e.id }'/>">Edit</a>
								<a class="btn btn-sm btn-danger"
								href="<c:url value ='/admin/category/delete/${e.id }'/>">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
	</div>
	<script type="text/javascript">
 
        bkLib.onDomLoaded(function() { nicEditors.allTextAreas() });
  </script>
</body>



















