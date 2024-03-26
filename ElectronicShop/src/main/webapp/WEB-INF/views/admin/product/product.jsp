<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<title>Trang chủ</title>
<body>
	<h2 class="alert alert-warning">Product Management</h2>
	<h4 class="label label-success">${message }${param.message }</h4>
	<ul class="nav nav-tabs">
		<li class="active"><a data-toggle="tab" href="#tab1">List</a></li>
		<li><a data-toggle="tab" href="#tab2">Edit</a></li>
	</ul>

	<div class="tab-content">
		<div id="tab1" class="tab-pane fade in active">
			<div class="panel panel-default">
				<div class="panel-body">
					<form:form action="admin/product/index" modelAttribute="entity" enctype="multipart/form-data">
						<div class="row">
							<div class="form-group col-sm-6">
								<label>Id</label><br>
								<form:errors path="id" style="color:red; font-style: italic;" ></form:errors>
								<form:input path="id" class="form-control" readonly="true"/>
							</div>
							<div class="form-group col-sm-6">
								<label>Name</label><br>
								<form:errors path="name" style="color:red; font-style: italic;" ></form:errors>
								<form:input path="name" class="form-control" />
							</div>
						</div>
						<div class="row">
							<div class="form-group col-sm-6">
								<label>Price</label><br>
								<form:errors path="price" style="color:red; font-style: italic;" ></form:errors>
								<form:input path="price" class="form-control"/>
							</div>
							<div class="form-group col-sm-6">
								<label>Sales</label><br>
								<form:errors path="sale" style="color:red; font-style: italic;" ></form:errors>
								<form:input path="sale" class="form-control" />
							</div>
						</div>
						<div class="row">
							<div class="form-group col-sm-6">
								<label>View Count</label><br>
								<form:errors path="viewCount" style="color:red; font-style: italic;" ></form:errors>
								<form:input path="viewCount" class="form-control"/>
							</div>
							<div class="form-group col-sm-6">
								<label>Quantity</label><br>
								<form:errors path="quantity" style="color:red; font-style: italic;" ></form:errors>
								<form:input path="quantity" class="form-control" />
							</div>
						</div>
						<div class="row">
							<div class="form-group col-sm-6">
								<label>Create At</label><br>
								<form:errors path="created_at" style="color:red; font-style: italic;" ></form:errors>
								<form:input path="created_at" class="form-control"/>
							</div>
							<div class="form-group col-sm-6">
								<label>Update At</label><br>
								<form:errors path="updated_at" style="color:red; font-style: italic;" ></form:errors>
								<form:input path="updated_at" class="form-control" />
							</div>
						</div>
						<div class="row">
							<div class="form-group col-sm-6">
								<label>Special</label>
								<form:radiobutton path="highlight" value="true" label="Yes" />
								<form:radiobutton path="highlight" value="false" label="No" />
							</div>
							<div class="form-group col-sm-6">
								<label>New Product</label>
								<form:radiobutton path="new_product" value="true" label="Yes" />
								<form:radiobutton path="new_product" value="false" label="No" />
							</div>
						</div>
						<div class="row">
							<div class="form-group col-sm-6">
								<label>Category</label>
								<%-- <form:select path="categoryEntity.id" class="form-control">
									<form:options items=${listCate } itemLabel="name" itemValue="id"/>
								</form:select> --%>
								<form:select path="categoryEntity.id" class="form-control">
								<c:forEach var="cate" items="${listCate}">
            						<form:option  value="${cate.id}">${cate.name}</form:option>
          						</c:forEach>
          						</form:select>
							</div>
							<div class="form-group col-sm-6">
								<label>Photo</label>
								<input type="file" name="photo-file" class="form-control">
								<form:hidden path="img_url"/>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-sm-12">
								<label>Description</label>
								<form:textarea path="title" row="3" class="form-control" />
							</div>
							<div class="form-group col-sm-12">
								<button class="btn btn-primary"
									formaction="<c:url value ='/admin/product/create'/>">Create</button>
								<button class="btn btn-warning"
									formaction="<c:url value ='/admin/product/update'/>">Update</button>
								<button class="btn btn-danger"
									formaction="<c:url value ='/admin/product/delete'/>">Delete</button>
								<a class="btn btn-default"
									href="<c:url value ='/admin/product/index'/>">Reset</a>
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
						<th>Name</th>
						<th>Unit Price</th>
						<th>Discount</th>
						<th>Quantity</th>
						<th>Update At</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="e" items="${list }">
						<tr>
							<td>${e.id}</td>
							<td>${e.name}</td>
							<td><f:formatNumber value="${e.price }" pattern="#,###"/></td>
							<td>${e.sale}</td>
							<td>${e.quantity}</td>
							<td>${e.updated_at}</td>
							<td><a class="btn btn-sm btn-info"
								href="<c:url value ='/admin/product/edit/${e.id }'/>">Edit</a>
								<a class="btn btn-sm btn-danger"
								href="<c:url value ='/admin/product/delete/${e.id }'/>">Delete</a>
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



















