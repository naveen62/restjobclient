<%@page import="javax.security.auth.message.callback.PrivateKeyCallback.Request"%>
<%@page import="com.spring.jobappclient.model.Job"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
<link rel="stylesheet" href="/css/index.css">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="nav.jsp" />  
<% String job = (Job) request.getAttribute("success"); %>

<div class="page-container">
<% if(job.getTitle() != null) { %>
	<div class="alert alert-success alert-dismissible fade show alert-container" role="alert">
  Successfully updated
  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>
<% } %>
<form action="/search-job" method="post" class="search-form">
	<div class="input-group search-input">
  	<input type="text" name="search" class="form-control" aria-label="Text input with checkbox" placeholder="Search Job">
	  	<div class="input-group-text">
		  	<select name="type" class="form-select">
			  <option selected value="title">By Title</option>
			  <option value="jobRole">By job role</option>
			</select>
		 </div>
	</div>
	<div class="input-group search-btn">
		<button type="submit" class="btn btn-secondary">Search</button>
	</div>
</form>
<% List<Job> jobList = (List<Job>) request.getAttribute("jobs"); %>
<% for(int i=0;i<jobList.size();i++) {%>
		<div class="card-container">
				<div class="card-flex">
					 <div>
					    <h5 class="card-title"><%= jobList.get(i).getTitle() %></h5>
					    <h6 class="card-subtitle mb-2 text-muted"><%= jobList.get(i).getjobRole() %></h6>
					    <a href="/<%=jobList.get(i).getJodId() %>" class="card-link">View Details</a>
	 				 </div>
	 				 <div>
	 				 	<a href="/delete-job/<%=jobList.get(i).getJodId()%>" class="link-danger card-links">Delete</a>  <a href="/<%=jobList.get(i).getJodId()%>-update-job" class="link-warning card-links">Edit</a>
	 				 </div>
				</div>
		</div>
<%  } %>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
</body>
</html>