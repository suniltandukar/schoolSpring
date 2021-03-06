<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../include.jsp"></jsp:include>
<spring:url value="/operation/insertExam" var="formUrl"/>

<html>
<head></head>
<body class="background">
	<div class="breadcrumb-line">
		<nav aria-label="breadcrumb" role="navigation">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><i class="fa fa-home"
					aria-hidden="true"></i>&nbsp;<a href="#">General</a></li>
				<li class="breadcrumb-item active" aria-current="page">Exam</li>
				<li class="breadcrumb-item active" aria-current="page">Create
					Exam</li>
			</ol>
		</nav>
	</div>
	<div class="col-md-12 col-sm-12 col-xs-12">

		<div class="x_panel">
			<div class="x_title">
				<h2>CREATE EXAM</h2>
				<ul class="nav navbar-right panel_toolbox">
					<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
					</li>
				</ul>
				<div class="clearfix"></div>
			</div>
			<div class="x_content">
				<button type="submit" class="btn btn-success" form="form">
					<i class="fa fa-check"></i> Save
				</button>
				<form:form action="${formUrl }" id="form"></form:form>
				<table class="table">
					<tbody>
						<tr>
							<td><h6>Exam Code *</h6> <input type="text"
								class="form-control" name="examcode" form="form"
								value="${sessionScope.systemdetail[5].settingsdescription }"
								required readonly></td>
							<td><h6>Exam Name *</h6> <input type="text"
								class="form-control" name="examname" form="form" required>
							</td>
							<td><h6>Exam Type *</h6> <select class="form-control"
								name="examtype" form="form" required>
									<option value="" selected>Select exam type</option>
									<c:forEach items="${examlist}" var="exam">
										<option value="${exam.examTypeModel.examtypeid }">${exam.examTypeModel.examtypename }</option>
									</c:forEach>

							</select></td>
							<td><h6>Start Date(B.S) *</h6> <input type="text"
								pattern=".{10,10}" required title="Invalid Date Format"
								class="form-control startdate" name="startdatenep"
								onblur="nepaliToEnglish('.startdate','.startdateen')"
								form="form" required></td>

							<td><h6>Start Date(A.D) *</h6> <input type="text"
								class="form-control startdateen" name="startdate"
								onblur="englishToNepali('.startdate','.startdateen')"
								id="englishDate1" form="form" pattern=".{10,10}" required
								title="Invalid Date Format" required></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="col-md-12 col-sm-12 col-xs-12">

		<div class="x_panel">
			<div class="x_title">
				<h2>AVAILABLE EXAM DETAILS</h2>
				<ul class="nav navbar-right panel_toolbox">
					<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
					</li>
				</ul>
				<div class="clearfix"></div>
			</div>
			<div class="x_content">
				<table id="datatable"
					class="table jambo_table table-striped table-bordered"
					cellspacing="0" width="100%" id="table" style="font-size: 95%">
					<thead>
						<tr>
							<th>Exam Name</th>
							<th>Exam Type</th>
							<th>Start Date</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${examlist }" var="elist">
							<tr>
								<td>${elist.examname }</td>
								<td>${elist.examTypeModel.examtypename }</td>
								<td>${elist.startdate }</td>
								<td><a href="#">Edit</a></td>

							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
<jsp:include page="../msgmodal.jsp"></jsp:include>
	<script>
		$('#form').submit(function() {
			return confirm('CONFIRM SAVE?');
		});
		$('.delete').click(function() {
			return confirm('CONFIRM EXAM DELETE?');
		});
		
	</script>
</body>
</html>
