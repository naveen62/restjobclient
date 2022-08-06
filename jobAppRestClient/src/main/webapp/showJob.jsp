<%@page import="com.spring.jobappclient.model.Candidate"%>
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
<title>Insert title here</title>
</head>
<body>
<jsp:include page="nav.jsp" />  

<div class="container" style="margin-top: 80px;">
	<div class="row justify-content-center">
		<div class="col-7 align-self-center">
			<h1>${job.title}</h1>
		</div>
	</div>
	<div class="row justify-content-center">
		<div class="col-7 align-self-center">
			<h5>Expirience requird: ${job.exp}</h5>
		</div>
	</div>
	<div class="row justify-content-center">
		<div class="col-7 align-self-center">
			<h5>Job Role: ${job.jobRole}</h5>
		</div>
	</div>
	<div class="row justify-content-center">
		<div class="col-7 align-self-center">
			<h5>Description:</h5>
			<h6>${job.jobDesc}</h6>
		</div>
	</div>
	<div class="row justify-content-evenly">
		<div class="col-7 align-self-center">
			<a class="btn btn-danger" href="/delete-job/${job.jodId}" role="button">Delete</a>
			<a class="btn btn-warning" href="/${job.jodId}-update-job" role="button">Update</a>
			<a class="btn btn-primary" href="/add-candidate-${job.jodId}" role="button">Add Candidate</a>
		</div>
	</div>
	<% Job job  = (Job) request.getAttribute("job"); %>
	<% List<Candidate> candidates = job.getCandidates(); %>
	
	<div class="row justify-content-evenly">
		<div class="col-7 align-self-center">
		<h3>Candidates</h3>
			<table class="table">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Name</th>
      <th scope="col">Email</th>
      <th scope="col">Eduction</th>
      <th scope="col">Contact No</th>
    </tr>
  </thead>
  <tbody>
    	<% if(candidates.size() > 0) { %>
    	<% for(int i=0;i<candidates.size();i++) {%>
    		<tr>
      <th scope="row"><%=i+1 %></th>
      <td><%=candidates.get(i).getName() %></td>
      <td><%=candidates.get(i).getEmail() %></td>
      <td><%=candidates.get(i).getEducation() %></td>
      <td><%=candidates.get(i).getContactNo() %></td>
    </tr>
    	<%  } %>
    	<% } %>
  </tbody>
</table>
		</div>
	</div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
</body>
</html>