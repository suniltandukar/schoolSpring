<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../include.jsp"></jsp:include>
<spring:url value="/account/update" var="formUrl"></spring:url>
<html>
<head>
<style>
h5 {
	font-size: 80%;
	font-weight: bold;
}
</style>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.26/jquery.form-validator.min.js"></script>
</head>
<body class="background">
	<div class="breadcrumb-line">
		<nav aria-label="breadcrumb" role="navigation">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><i class="fa fa-home"
					aria-hidden="true"></i>&nbsp;<a href="#">Home</a></li>
				<li class="breadcrumb-item active" aria-current="page">Account</li>
				<li class="breadcrumb-item active" aria-current="page">Update
					Account</li>
			</ol>
		</nav>
	</div>
	<div class="col-md-12 col-sm-12 col-xs-12">

		
			
			<div class="col-md-8 col-xs-12">
		<div class="x_panel">
			<form:form class="form-horizontal form-label-left input_mask"
				action="${formUrl }">

				<div class="x_title">
					<h2>Add New Account</h2>
					<div class="col-md-4 col-sm-4 col-xs-12 pull-right">
						<input type="text" id="membername"
									class="form-control" name="membername"
									value="" readonly >
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="x_content">
					<br />

					<div class="form-group">
						
					<div class="form-group">
						<div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3">
							<button class="btn btn-info" type="button" id="validate">Validate</button>
							<button class="btn btn-primary" type="reset">Reset</button>
								<a href="<spring:url value="/account/delete/${accountDetail.accountNumber }" />"
									class="clickbtn btn btn-danger">Delete</a> 
							<input type="submit" class="btn btn-success" value="Update">
						</div>
					</div>
					<div class="ln_solid"></div>
						<label class="control-label col-md-2 col-sm-2 col-xs-12">ID</label>
						<div class="col-md-2 col-sm-2 col-xs-12">
							 <input type="text" id="memberid"
									class="form-control sharecertmemberid memberid" name="memberId"
									value="${accountDetail.memberId }" required>
						</div>
						<div class="col-md-8 col-sm-8 col-xs-12 ">
							<span id="customername"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2 col-sm-2 col-xs-12">Account 
							No</label>
						<div class="col-md-4 col-sm-4 col-xs-12">
						<input type = "hidden" name= "previousAccountNumber" value="${accountDetail.accountNumber }">
							 <input type="text"
									class="form-control accountNumber" id="accountno" name="accountNumber"
									value="${accountDetail.accountNumber }" required readonly>
						</div>
					</div>
					<div class="form-group" id="cashWithdraw">
						<label class="control-label col-md-2 col-sm-2 col-xs-12">Alternate
							Account No</label>
						<div class="col-md-4 col-sm-4 col-xs-12">
							<input type="text" 
									class="form-control" name="alternativeAccountId" value="${accountDetail.alternativeAccountId }"
									data-validation="number"  >
						</div>
						<div class="col-md-6 col-sm-6 col-xs-12 ">
							<span class="accountName"></span>
						</div>
					</div>
					<div class="form-group" id="cashDeposit">
						<label class="control-label col-md-2 col-sm-2 col-xs-12">
							Category</label>
						<div class="col-md-4 col-sm-4 col-xs-12">
							<select class="form-control categoryId" name="categoryModel.categoryId" id="categories">
										<option value="${accountDetail.categoryModel.categoryId }">${accountDetail.categoryModel.categoryHead }</option>
										<c:forEach items="${categorylist }" var="c">
											<option value="${c.categoryId }">${c.categoryHead }</option>
										</c:forEach>
								</select>

						</div>
						<div class="col-md-6 col-sm-6 col-xs-12 ">
							<span class="accountName"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2 col-sm-2 col-xs-12">
							Account Type</label>
						<div class="col-md-4 col-sm-4 col-xs-12">
							<select class="form-control"
									name="accountTypeModel.accountType" id="accounttype">
										<option value="${accountType.accountType }">${accountType.accountTypeHead }</option>
								</select>

						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2 col-sm-2 col-xs-12">Account Name
						</label>
						<div class="col-md-3 col-sm-3 col-xs-12">
							<input type="text"
									class="form-control memberid" name="accountName" id="accountname" value="${accountDetail.accountName }">
						</div>
					</div>
				</div>
			</form:form>
		</div>
							</div>
	</div>
	<jsp:include page="../msgmodal.jsp"></jsp:include>
	
<script>
 $("#validate").click(function()
		{
		var id=$('.memberid').val();
		var dataString = 'id='+ id;
		$.ajax
		({
		type: "POST",
		url: "../../account/membername",
		data: dataString,
		cache: false,
		success: function(html)
		{
		$("#membername").val(html);
		$("#accountname").val(html);
		$("#accountno").val(id);
		} 
		});
		}); 
$("#categories").change(function()
		{
		var id=$(this).val();
		var dataString = 'id='+ id;

		$.ajax
		({
		type: "POST",
		url: "../../category/getAccountType",
		
		data: dataString,
		cache: false,
		success: function(html)
		{
		$("#accounttype").html(html);
		} 
		});

		});

</script>
</body>
</html>
