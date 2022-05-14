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
var status = validateUserForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }

var type = ($("#idcomplain").val() == "") ? "POST" : "PUT";


$.ajax(
		{
		 url : "ComplainsAPI",
		 type : type,
		 data : $("#formUser").serialize(),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 onUserSaveComplete(response.responseText, status);
		 }
		});

});

function onUserSaveComplete(response, status)
{
if (status == "success")
 {
	var resultSet = JSON.parse(response);
	if (resultSet.status.trim() == "success")
	{
		$("#alertSuccess").text("Successfully saved.");
		$("#alertSuccess").show();
		
		$("#divUserGrid").html(resultSet.data);
	} else if (resultSet.status.trim() == "error")
	{
		$("#alertError").text(resultSet.data);
		$("#alertError").show();
	}
 	} else if (status == "error")
 	{
 		$("#alertError").text("Error while saving.");
 		$("#alertError").show();
 	} else
 	{
 		$("#alertError").text("Unknown error while saving..");
 		$("#alertError").show();
 	}
		$("#idcomplain").val("");
		$("#formUser")[0].reset();
}

//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
 $("#idcomplain").val($(this).closest("tr").find('#hididUpdate').val());
 $("#name").val($(this).closest("tr").find('td:eq(0)').text());
 $("#date").val($(this).closest("tr").find('td:eq(1)').text());
 $("#complaintype").val($(this).closest("tr").find('td:eq(2)').text());
 $("#nic").val($(this).closest("tr").find('td:eq(3)').text());
});


$(document).on("click", ".btnRemove", function(event)
		{
		 $.ajax(
		 {
		 url : "ComplainsAPI",
		 type : "DELETE",
		 data : "idcomplain=" + $(this).data("idcomplain"),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 onUserDeleteComplete(response.responseText, status);
		 }
		 });
		});

function onUserDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divUserGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}
//CLIENTMODEL=========================================================================
function validateUserForm()
{
	
//first_name
if ($("#name").val().trim() == "")
{
return "Insert name.";
}


//date
if ($("#date").val().trim() == "")
{
return "Insert date.";
}


//complaintype
if ($("#complaintype").val().trim() == "")
{
return "Insert complaintype.";
} 

//nic
if ($("#nic").val().trim() == "")
{
return "Insert nic.";
}
return true;
}



