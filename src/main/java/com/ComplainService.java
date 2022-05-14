package com;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*;

import model.Complain; 

@Path("/complain")
public class ComplainService {
	
	Complain complainObj = new Complain();
	
	 @POST
	 @Path("/")
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.TEXT_PLAIN)
	 public String insertComplain(String insertData)
	 {
			//Convert the input string to a JSON object 
		 JsonObject insertObject = new JsonParser().parse(insertData).getAsJsonObject();
		 
		 String name = insertObject.get("name").getAsString();
		 String date = insertObject.get("date").getAsString();
		 String complaintype = insertObject.get("complaintype").getAsString();
		 String nic = insertObject.get("nic").getAsString();

		 String output = complainObj.insertComplain(name, date, complaintype, nic);
		 return output; 
	 }
	 
	 @GET
	 @Path("/") 
	 @Produces(MediaType.TEXT_HTML) 
	 public String readComplain() 
	  { 
		  return complainObj.readComplain(); 
	  }
	 
	 @PUT
	 @Path("/")
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.TEXT_PLAIN)
	 public String updateComplain(String updateData)
	 {
		 JsonObject updateObject = new JsonParser().parse(updateData).getAsJsonObject();
		 
		 String idcomplain = updateObject.get("idcomplain").getAsString();
		 String name = updateObject.get("name").getAsString();
		 String date = updateObject.get("date").getAsString();
		 String complaintype = updateObject.get("complaintype").getAsString();
		 String nic = updateObject.get("nic").getAsString();
		 
		 String output = complainObj.updateComplain(idcomplain, name, date, complaintype, nic);
		 return output;
	 }
	 
	 @DELETE
	 @Path("/")
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.TEXT_PLAIN)
	 public String deleteCustomer(String deleteData)
	 {
		 JsonObject updateObject = new JsonParser().parse(deleteData).getAsJsonObject();
		 
		 String idcomplain = updateObject.get("idcomplain").getAsString();
		 
		 String output = complainObj.deleteComplain(idcomplain);
		 return output;

	 }
	
}
