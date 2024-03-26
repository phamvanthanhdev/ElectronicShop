<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<title>Trang chủ</title>
<body>
	<h2 class="alert alert-warning">Customer Management</h2>
	<h4 class="label label-success">${message }${param.message }</h4>
	<ul class="nav nav-tabs">
		<li class="active"><a data-toggle="tab" href="#tab1">List</a></li>
		<li><a data-toggle="tab" href="#tab2">Edit</a></li>
	</ul>

	<div class="tab-content">
		<div id="tab1" class="tab-pane fade in active">
			<div class="panel panel-default">
				<div class="panel-body">
					<form:form action="admin/customer/index" modelAttribute="entity" enctype="multipart/form-data">
						<div class="row">
							<div class="form-group col-sm-6">
								<label>Id</label><br>
								<form:errors path="id" style="color:red; font-style: italic;" ></form:errors>
								<form:input path="id" class="form-control" readonly="${!empty entity.id }"/>
							</div>
							<div class="form-group col-sm-6">
								<label>Password</label><br>
								<form:errors path="password" style="color:red; font-style: italic;" ></form:errors>
								<form:input path="password" class="form-control" />
							</div>
						</div>
						<div class="row">
							<div class="form-group col-sm-6">
								<label>FullName</label><br>
								<form:errors path="fullname" style="color:red; font-style: italic;" ></form:errors>
								<form:input path="fullname" class="form-control" />
							</div>
							<div class="form-group col-sm-6">
								<label>Email</label><br>
								<form:errors path="email" style="color:red; font-style: italic;" ></form:errors>
								<form:input path="email" class="form-control" />
							</div>
						</div>
						<div class="row">
							<div class="form-group col-sm-6">
								<label>Role</label>
								<div class="form-control">
									<form:radiobutton path="admin" value="true" label="Yes" />
									<form:radiobutton path="admin" value="false" label="No" />
								</div>
							</div>
							<div class="form-group col-sm-6">
								<label>Activated</label>
								<div class="form-control">
									<form:radiobutton path="activated" value="true" label="Yes" />
									<form:radiobutton path="activated" value="false" label="No" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-sm-12">
								<label>Photo</label>
								<input type="file" name="photo-file" class="form-control">
								<form:hidden path="photo"/>
							</div>
							<div class="form-group col-sm-12">
								<button class="btn btn-primary"
									formaction="<c:url value ='/admin/customer/create'/>">Create</button>
								<button class="btn btn-warning"
									formaction="<c:url value ='/admin/customer/update'/>">Update</button>
								<button class="btn btn-danger"
									formaction="<c:url value ='/admin/customer/delete'/>">Delete</button>
								<a class="btn btn-default"
									href="<c:url value ='/admin/customer/index'/>">Reset</a>
							</div>
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
						<th>Full Name</th>
						<th>Email Adress</th>
						<th>Activated ?</th>
						<th>Role</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="e" items="${list }">
						<tr>
							<td>${e.id}</td>
							<td>${e.fullname}</td>
							<td>${e.email}</td>
							<td>${e.activated?'Yes':'No'}</td>
							<td>${e.admin?'Administrator':'User'}</td>
							<td><a class="btn btn-sm btn-info"
								href="<c:url value ='/admin/customer/edit/${e.id }'/>">Edit</a>
								<a class="btn btn-sm btn-danger"
								href="<c:url value ='/admin/customer/delete/${e.id }'/>">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
	</div>
</body>



















