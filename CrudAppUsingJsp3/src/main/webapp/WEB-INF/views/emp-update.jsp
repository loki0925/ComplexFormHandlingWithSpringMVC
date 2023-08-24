<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div align="center">
				<a class="navbar-brand"> Employee Management App </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link"></a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<%-- <c:if test="${emp != null}">
					<form >
				</c:if>
				<c:if test="${emp == null}">
					<form>
				</c:if>

				<caption>
					<h2>
						<c:if test="${emp != null}">
            			Edit User
            		    </c:if>
						<c:if test="${emp == null}">
            			Add New User
            		    </c:if>
					</h2>
				</caption>

				<c:if test="${emp != null}">
					<input type="hidden" id="id" name="id"
						value="<c:out value='${emp.eid}' />" />
					<input type="hidden" name="id1" value="<c:out value='${emp.skillid}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Name</label> <input type="text" id="name"
						value="<c:out value='${emp.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Age</label> <input type="number" id="age"
						value="<c:out value='${emp.age}' />" class="form-control"
						name="age" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Salary</label> <input type="Number" step="0.01" id="salary"
						value="<c:out value='${emp.salary}' />" class="form-control"
						name="salary" required="required">
				</fieldset><br>


				<fieldset class="form-group">
					<label>Skills: </label>
						 <label>   Java: </label><input
						type="radio" id="Java" value="Java"
						 name="skills" required="required"> 
						 <label>MySql:
					</label><input type="radio" id="mysql"
						value="MySql" 
						name="skills" required="required">

				</fieldset><br>	
				<fieldset class="form-group">
					<label>Hobbies: </label> 
						<label>Cricket:	</label><input type="checkbox" id="cricket"
						value="Cricket"
						name="hobbiename" > 
						<label>Chess:
					</label><input type="checkbox" id="chess"
						value="Chess" 
						name="hobbiename" > <label>Music:
					</label><input type="checkbox" id="music"
						value="Music" 
						name="hobbiename" >
				</fieldset>
				<fieldset>
				<label>Country: </label>
					<select name="country">
						<option value="Uk">UK</option>
						<option value="Australia">Australia</option>
						<option value="India">India</option>
						<option value="USA">USA</option>
						<option value="Brazil">Brazil</option>
						<option value="Canada">Canada</option>
						<option value="China">China</option>
					</select>
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form> --%>

               
               <div id="errorMessages"></div>
               
               
				<form:form modelAttribute="employee">


					<form:input path="eid" type="hidden" id="id" name="id"/>


					<form:label path="name"> Name:</form:label>
					<form:input path="name" type="text" id="name"
						value="" class="form-control"
						name="name"  maxlength="20" />
					<br />

					<form:label path="Age"> Age</form:label>
					<form:input path="age" type="number" id="age"  
						value="" class="form-control"
						name="age"  />
					<br />

					<form:label path="salary"> Salary</form:label>
					<form:input path="salary" type="Number" step="0.01" id="salary" min="0.1" 
						value="" class="form-control"
						name="salary"  />
					<br />


					<form:label path="Skills"> Skills: </form:label>
					<form:radiobutton path="Skills" 
						 name="skills" id="Java" value="Java" /> Java
                   <form:radiobutton path="Skills" name="skills" id="MySql" value="MySql" /> MySql<br />
                    
                    
					
					<form:label path="hobbies"> Hobbies: </form:label>
					<form:label path="hobbies"> Cricket </form:label>
					<form:checkbox path="hobbies" id="cricket" value="Cricket"
						name="hobbiename" />
					<form:label path="hobbies"> Chess </form:label>
					<form:checkbox path="hobbies" id="chess" value="Chess"
						name="hobbiename" />
					<form:label path="hobbies"> Music </form:label>
					<form:checkbox path="hobbies" id="music" value="Music"
						name="hobbiename" />
					<br />
					
					<form:label path="country"> Country:</form:label>
					<form:select path="country" items="${countryList}" />
					<br />

					<form:button class="btn btn-success"> Register</form:button>
					<br />
				</form:form>
			</div>
		</div>
		<div class="row">
			<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

			<div class="container">
				<h3 class="text-center">List of Employee</h3>
				<hr>


				<br>

				<table id="employee-table" class="table table-bordered">
					<thead>
						<tr>
							<th>Name</th>
							<th>Age</th>
							<th>Salary</th>
							<th>Skills</th>
							<th>Hobbies</th>
							<th>Country</th>
							<th>Actions</th>
						</tr>
					<tbody>

						<c:forEach var="employee" items="${listemp}">
							<tr>
								<td><c:out value="${employee.name}" /></td>
								<td><c:out value="${employee.age}" /></td>
								<td><c:out value="${employee.salary}" /></td>
								<td><c:out value="${employee.skills}" /></td>
								<td><c:out value="${employee.hobbies}" /></td>
								<td><c:out value="${employee.country}" /></td>


								<td><a class="edit-link" onclick="onclickedit(this)"
									data-id="<c:out value='${employee.eid}' />">Edit</a> <a
									class="delete-link" onclick="deleteEmployee()"
									data-id="<c:out value='${employee.eid}' />">Delete</a></td>
							</tr>
						</c:forEach>

					</tbody>

				</table>
			</div>

		</div>
	</div>

</body>
</html>
<script>
	var selectedrow = null;
	function deleteEmployee() {
		$(document).ready(function() {

			$(".delete-link").click(function(e) {
				e.preventDefault();

				var empId = $(this).data("id");
				console.log(empId);

				$.ajax({
					url : "/CrudAppUsingJsp2/delete",
					type : "GET",
					headers : {
						"X-HTTP-Method-Override" : "DELETE"
					},
					data : {
						id : empId
					},

					success : function(response) {

						$("#employee-table").load(" #employee-table > *");
					},
					error : function(error) {
						console.log("Error:", error);
					}
				});

			});
		});
	}

	$(document).ready(function() {

		$("form").submit(function(e) {
			e.preventDefault();
           
			var name  = $("#name").val();
			var age = $("#age").val();
			var salary = $("#age").val(); 
			var skills = $("#skills").val();
			var errorMessages = "";
         
        if(name == "" ||  age == "" || salary == "" || skills == ""){
        	 if(age < 18 || age >100){
             	alert("Age should be between 18 to 100 ");
             	errorMessages+= "Age should be between 18 to 100";
             	//$("#errorMessages").html("Age should be between 18 to 100");
             }
             if(salary <0){
             	alert("Salary can not be minus");
             	errorMessages+="salary can not be minus ";
             	//$("#errorMessages").html("salary can not be minus");
             }
             
        	alert("All Fields are Required");
        	errorMessages+="All Fields are Required";
        	$("#errorMessages").html(errorMessages);
        }  
        else{
			var formData = $(this).serialize();
			$("#errorMessages").html("");
			if (selectedrow == null) {

				$.ajax({
					url : "/CrudAppUsingJsp2/insert",
					type : "POST",
					data : formData,
					success : function(response) {
						//console.log(formData);
						//console.log("in if condition");
						$("#employee-table").load(" #employee-table > *");
					},
					error : function(error) {
						console.log("Error:", error);
					}
				});
			} else {
				var empId = $("#id").val();
				formData += "&id=" + globalId;
				$.ajax({
					url : "/CrudAppUsingJsp2/update",
					type : "POST",
					data : formData,
					success : function(response) {
						console.log(formData);
						console.log("in else condition");
						$("#employee-table").load(" #employee-table > *");
					},
					error : function(error) {
						console.log("Error:", error);
					}
				});
			}
			selectedrow = null;
        }
		});
	});
	var globalId;
	function onclickedit(td) {
		selectedrow = td.parentElement.parentElement;
		$(document)
				.ready(
						function() {
							$(".edit-link")
									.click(
											function(e) {
												e.preventDefault();
												var empId = $(this).data("id");
												globalId = empId;
												var name = this.parentElement.parentElement.cells[0].innerHTML;
												var age = this.parentElement.parentElement.cells[1].innerHTML;
												var salary = this.parentElement.parentElement.cells[2].innerHTML;

												var skills = this.parentElement.parentElement.cells[3].innerHTML;
												var hobbies = this.parentElement.parentElement.cells[4].innerHTML;
                                                var country = this.parentElement.parentElement.cells[5].innerHTML;
												console.log(skills);
												console.log(hobbies);
												$
														.ajax({
															url : "/CrudAppUsingJsp2/edit",
															type : "GET",
															data : {
																id : empId
															},
															success : function(
																	response) {
																console.log("inside success function")
																$("#id").val(
																		empId);
																$("#name").val(
																		name);
																$("#age").val(
																		age);
																$("#salary")
																		.val(
																				salary);
																if(skills.includes("Java")){
																	$("#Java")
																	.prop("checked", true
																			);	
																}else{
																	$("#MySql")
																	.prop(
																			"checked", true);
																}
																if(hobbies.includes("Cricket")){
																	$("#cricket")
																	.prop("checked", true
																			);	
																}
																else{
																	$("#cricket")
																	.prop("checked", false
																			);
																}
																if(hobbies.includes("Chess")){
																	$("#chess")
																	.prop("checked", true
																			);	
																}
																else{
																	$("#chess")
																	.prop("checked", false
																			);
																}
																if(hobbies.includes("Music")){
																	$("#music")
																	.prop("checked", true
																			);	
																}
																else{
																	$("#music")
																	.prop("checked", false
																			);
																}
																/* $("#skills")
																		.val(
																				skills); */
                                                                $("#country").val(country)
															},
															error : function(
																	error) {
																console
																		.log(
																				"Error:",
																				error);
															}
														});
											});

						});
	}
</script>



