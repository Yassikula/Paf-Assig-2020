<%@page import="model.Appointment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Appointment Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery-3.2.1.min.js"></script> 
<script src="Components/main.js"></script> 

</head>

<body>
	
<div class="container"> 
 	<div class="row"> 
 	 	<div class="col-6"> 
 	 	 	<h1 class="m-3">Appointment details</h1> 
 	 	 	
 	 	 	 <form id="formAppointment" name="formAppointment" method="post" action="appointment.jsp">  
					<br> 
					
					
					 <br>
					 Hospital Name:  
					 <input id="HospitalName" name="HospitalName" type="text" class="form-control form-control-sm">
					 
					 <br> 
					 Doctor Name:  
					 <input id="DoctorName" name="DoctorName" type="text" class="form-control form-control-sm">
					 <br> 
					 Date:  
					 <input id="AppointmentDate" name="AppointmentDate" type="text" class="form-control form-control-sm"> 
					 
					 
					 <br> 
					 Category:  
					 <input id="Category" name="Category" type="text" class="form-control form-control-sm">

				 	<br>  
					 <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">  
					 <br>
					 <input type="hidden" id="hidAppIDSave" name="hidAppIDSave" value=""> 
					 <br>
					 
				</form> 
				
				<div id="alertSuccess" class="alert alert-success"></div>  
				<div id="alertError" class="alert alert-danger"></div> 
				
				<br>  
				<div id="divItemsGrid">   
					<%    
						Appointment appObj = new Appointment();
						out.print(appObj.readAppointment());   
					%>  
					
				</div> 
				  
 			</div>
 		 
 		</div>    
 		
 
	</div> 
 	 	 	 
 	 	
 	
 	 
 	<br> 
 	

</body>
</html>