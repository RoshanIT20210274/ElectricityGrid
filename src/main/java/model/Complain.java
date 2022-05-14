package model;

import java.sql.*; 

public class Complain {
	
	private Connection connect()
	{
			Connection con = null;
			try
			{
				Class.forName("com.mysql.jdbc.Driver");

				//Provide the correct details: DBServer/DBName, user name, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", "1234");
			}
			catch (Exception e)
			{e.printStackTrace();}
			return con;
	}
	
	public String insertComplain(String name, String date, String complaintype, String nic) {
		
		String output = "";
		
		try {
			Connection con = connect();
			
			if (con == null)
			{return  "Error while connecting to the database for inserting.";}
			
			// create a prepared statement
			String query = "  insert into complain (`idcomplain`,`name`,`date`,`complaintype`,`nic`)" + " values (?, ?, ?, ?,?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			 // binding values
			 preparedStmt.setInt(1, 0); 
			 preparedStmt.setString(2, name); 
			 preparedStmt.setString(3, date); 
			 preparedStmt.setString(4, complaintype); 
			 preparedStmt.setString(5, nic); 
			 
			// execute the statement
			 
			 preparedStmt.execute(); 
			 con.close(); 
				String newUse = readComplain();
				output = "{\"status\":\"success\", \"data\": \"" +newUse+ "\"}";

		}
		catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the Users.\"}";
			 System.err.println(e.getMessage()); 
		}
		
		return output;
	}
	
	public String readComplain() {
		
		String output = "";
		
		try {
			
			Connection con = connect();
			
			if(con == null) {
				return "Error while connecting to the database for reading.";
			}
			
			 // Prepare the html table to be displayed
			 output = "<table class='table table-hover'><tr><th>Complain ID</th><th>Complain</th><th>Date</th>" +
			 "<th>Complain Type</th>" + 
			 "<th>NIC</th><th>Update</th><th>Remove</th></tr>";
			 
			 String query = "select * from complain"; 
			 Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
			 
			 while(rs.next()) {
				 String idcomplain = Integer.toString(rs.getInt("idcomplain")); 
				 String name = rs.getString("name"); 
				 String date = rs.getString("date"); 
				 String complaintype = rs.getString("complaintype"); 
				 String nic = rs.getString("nic"); 
				 
				 // Add into the html table
				 output += "<tr><td><input id='hididUpdate' name='hididUpdate' type='hidden' value='" + idcomplain
							+ "'>" + idcomplain + "</td>";
				 output += "<td>" + name + "</td>"; 
				 output += "<td>" + date + "</td>"; 
				 output += "<td>" + complaintype + "</td>"; 
				 output += "<td>" + nic + "</td>";
				// buttons
					output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
							+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='"
							+ idcomplain + "'>" + "</td></tr>";
				 
			 }
			 con.close(); 
			 // Complete the html table
			 output += "</table>";
		}
		catch(Exception e)
		{
			 output = "Error while reading the Complain."; 
			 System.err.println(e.getMessage()); 
		}
		
		return output;
	}
	
	public String updateComplain(String idcomplain,String name, String date, String complaintype, String nic) 
	{ 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; } 
		 // create a prepared statement
		 String query = "UPDATE complain SET name=?,date=?,complaintype=?,nic=? WHERE idcomplain=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setString(1, name); 
		 preparedStmt.setString(2, date); 
		 preparedStmt.setString(3, complaintype); 
		 preparedStmt.setString(4, nic); 
		 preparedStmt.setInt(5, Integer.parseInt(idcomplain)); 

		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newUse =readComplain();
			output = "{\"status\":\"success\", \"data\": \"" +newUse+ "\"}";
		 
		 } 
		 catch (Exception e) 
		 { 
			 output = "{\"status\":\"error\", \"data\": \"Error while updating the user.\"}";
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
	
	public String deleteComplain(String idcomplain) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for deleting."; } 
	 // create a prepared statement
	 String query = "delete from complain where idcomplain=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(idcomplain)); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 String newUse = readComplain();
		output = "{\"status\":\"success\", \"data\": \"" +newUse + "\"}";
	 } 
	 catch (Exception e) 
	 { 
		 output = "{\"status\":\"error\", \"data\":\"Error while deleting the user.\"}";
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }
}
