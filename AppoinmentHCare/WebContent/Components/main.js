$(document).ready(function() 
{  
	if ($("#alertSuccess").text().trim() == "")  
	{   
		$("#alertSuccess").hide();  
	} 
	$("#alertError").hide(); 
}); 

//SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 

	// Form validation-------------------  
	var status = validateAppointmentForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 

	// If valid------------------------  
	var t = ($("#hidAppIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		url : "AppService",
		type : t,
		data : $("#formAppointment").serialize(),	
		dataType : "text",
		complete : function(response, status)
		{
			onAppointmentSaveComplete(response.responseText, status);
		}
	});
	
	
}); 

function onAppointmentSaveComplete(response, status)
{
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Saved.");
			$("#alertSuccess").show();
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error")
	{
		$("#alertError").text("Error While Saving.");
		$("#slertError").show();
	}
	else{
		$("#alertError").text("Unknown Error while Saving.");
		$("#alertError").show();
	}
	$("#hidAppIDSave").val("");
	$("#formAppointment")[0].reset();
}

//UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
		{     
	$("#hidAppIDSave").val($(this).closest("tr").find('#hidAppIDUpdate').val());     
	$("#HospitalName").val($(this).closest("tr").find('td:eq(0)').text()); 
	$("#DoctorName").val($(this).closest("tr").find('td:eq(1)').text()); 
	$("#AppointmentDate").val($(this).closest("tr").find('td:eq(2)').text()); 
	$("#Category").val($(this).closest("tr").find('td:eq(3)').text());

});


//Remove Operation
$(document).on("click", ".btnRemove", function(event){
	$.ajax(
	{
		url : "AppService",
		type : "DELETE",
		data : "AppointmentID=" + $(this).data("appid"),
		dataType : "text",
		complete : function(response, status)
		{
			onAppointmentDeletedComplete(response.responseText, status);
		}
	});
});

function onAppointmentDeletedComplete(response, status)
{
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Deleted.");
			$("#alertSuccess").show();
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error")
	{
		$("#alertError").text("Error While Deleting.");
		$("#alertError").show();
	}else
	{
		$("#alertError").text("Unknown Error While Deleting.");
		$("#alertError").show();
	}
}

//CLIENTMODEL
function validateAppointmentForm() {  
	

	
	// HOSPITALname  
	if ($("#HospitalName").val().trim() == "")  {   
		return "Insert Hospital Name.";  
		
	} 
	
	// DocName  
	if ($("#DoctorName").val().trim() == "")  {   
		return "Insert Doctor Name.";  
		
	} 
	// Date  
	if ($("#AppointmentDate").val().trim() == "")  {   
		return "Insert date.";  
		
	} 
	
	
	// category 
	if ($("#Category").val().trim() == "")  {   
		return "Insert Category.";  
		
	} 
	 
	 return true; 
	 
}
