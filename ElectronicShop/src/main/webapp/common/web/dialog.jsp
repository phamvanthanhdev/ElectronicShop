<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/common/taglib.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
   <!-- Trigger the modal with a button -->
  <!-- <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Open Modal</button> -->
<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Login</h4>
      </div>
      <div class="modal-body">
      <!-- <div class = "form-group">
        	<label>Name</label>
			<input name="name" class = "form-control" type="text" placeholder="Nhập Họ tên"/>
        </div>
        <div class = "form-group">
        	<label>Recipient Email</label>
        	<input name="to" class = "form-control" type="text" placeholder="Nhập mail người nhận"/>
        </div>
        <div class = "form-group">
        	<label>Subject</label>
			<input name="subject" type="text" placeholder="Nhập tiêu đề"/>
        </div>
        <div class = "form-group">
        	<label>Message</label>
			<textarea name="message" rows = "3" placeholder="Nhập Nội dung"></textarea>
        </div>
        <input id = "id" value = "abc"> -->
        <label class="control-label">${message }</label>
        <form action="check-login" method="post">
			<label class="control-label" for="inputEmail">Tên đăng nhập</label>
			<input name="id" type="text" placeholder="Nhập Username"/>
			<label class="control-label" for="inputEmail">Mật khẩu</label>
			<input name="password" type="text" placeholder="Nhập Password"/>
			
			<!-- <button  class="btn btn-primary">Save</button> -->
		
      </div>
      <div class="modal-footer">
        <!-- <button class="btn btn-default" >Send</button> -->
        <button  class="btn btn-primary">Save</button>
        <button class="btn btn-default" data-dismiss="modal">Close</button>
        </form>
      </div>
    </div>

  </div>
</div>