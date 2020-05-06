package model;

//import java.util.*;
//import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
//import com.AppService;

public class Appointment {
	public Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/appoinment", "root", "");

			// For testing
			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertAppointment(String HospitalName, String DoctorName, String AppointmentDate, String Category) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = " insert into appointment(`AppointmentID`,`HospitalName`,`DoctorName`,`AppointmentDate`,`Category`)"
					+ " values (?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, HospitalName);
			preparedStmt.setString(3, DoctorName);
			preparedStmt.setString(4, AppointmentDate);
			preparedStmt.setString(5, Category);
			

			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newApp = readAppointment();
			output = "{\"status\":\"success\", \"data\": \"" + newApp + "\"}";

		} 
		
		catch (Exception e)

		{
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the details \"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readAppointment() {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
			/*output = "<table border=\"1\"><tr><th>Appointment ID</th><th>Hospital Name</th>"
					+ "<th>Doctor Name</th><th>App Date</th><th>Category</><th>Update</th><th>Remove</th></tr>";
*/
	
			output = "<table border='1'><tr><th>Hospital Name</th>  "
					+ " <th>Doctor Name</th>"
					+ "<th>Appointment Date</th>    "
					+ " <th>Category</th>      "
					+ "   <th>Update</th><th>Remove</th></tr>"; 
			 
			
			 
			String query = "select * from appointment";

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				String AppointmentID = Integer.toString(rs.getInt("AppointmentID"));
				String HospitalName = rs.getString("HospitalName");
				String DoctorName = rs.getString("DoctorName");
				String AppointmentDate = rs.getString("AppointmentDate");
				String Category = rs.getString("Category");

				output += "<tr><td><input id='hidAppIDUpdate' name='hidAppIDUpdate' type='hidden' value='" + AppointmentID + "'>" + HospitalName +  "</td>";
				//output += "<td>" + HospitalName + "</td>";
				//output += "<td>" + AppointmentID + "</td>";	  
				output += "<td>" + DoctorName + "</td>";
				output += "<td>" + AppointmentDate + "</td>";
				output += "<td>" + Category + "</td>";
				// Add into the html table  
				
				
				// buttons
//				output += "<td><input name=\"btnUpdate\" " + " type=\"button\" value=\"Update\" class=\"btnUpdate btn-secondry\"></td>"
//						+ "<td><form method=\"post\" action=\"app.jsp\">" + "<input name=\"btnRemove\" "
//						+ " type=\"submit\" value=\"Remove\">" + "<input name=\"hidAppIDDelete\" type=\"hidden\" " + " value=\""
//						+ AppointmentID + "\">" + "</form></td></tr>";

				output += "<td><input name='btnUpdate'  type='button' value='Update' class='btnUpdate btn-secondry'></td>"
						 + "<td><input name='btnRemove'  type='button' value='Remove' class='btnRemove btn-danger' data-appid='"
						+ AppointmentID + "'>" + "</td></tr>";
							
				
			}
			con.close();
			// Complete the html table

			output += "</table>";

		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;

	}

	public String updateAppointment(String AppointmentID, String HospitalName, String DoctorName, String AppointmentDate, String Category) 
	{
		String output = "";
		try {

			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement

			String query = "UPDATE Appointment SET HospitalName=?,DoctorName=?,AppointmentDate=?,Category=? WHERE AppointmentID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			
			preparedStmt.setString(1, HospitalName);
			preparedStmt.setString(2, DoctorName);
			preparedStmt.setString(3, AppointmentDate);
			preparedStmt.setString(4, Category);
			preparedStmt.setInt(5, Integer.parseInt(AppointmentID));
			// execute the statement

			preparedStmt.execute();
			con.close();

			String newApp = readAppointment();
			output = "{\"status\":\"success\", \"data\": \"" + newApp + "\"}";
			
		} 
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while updating the details \"}";
			System.err.println(e.getMessage());

		}
		return output;

	}

	public String deleteAppointment(String AppointmentID) {
		String output = "";

		try {

			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from appointment where AppointmentID=?";
                                       
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values

			//String AppointmentID = null;
			preparedStmt.setInt(1, Integer.parseInt(AppointmentID));
			// execute the statement

			preparedStmt.execute();
			con.close();

			String newApp = readAppointment();
			output = "{\"status\":\"success\", \"data\": \"" + newApp + "\"}";

		} catch (Exception e) {

			output = "{\"status\":\"error\", \"data\": \"Error while deleting the details \"}";
			System.err.println(e.getMessage());

		}
		return output;

	}

	

}
