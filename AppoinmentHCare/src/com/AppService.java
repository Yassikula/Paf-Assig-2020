package com;

import model.Appointment;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
////import javax.annotation.Generated;
////For REST Service
//import javax.ws.rs.*; 
//import javax.ws.rs.core.MediaType;
// 
////For JSON 
//import com.google.gson.*; 
// 
////For XML 
//import org.jsoup.*; 
//import org.jsoup.parser.*; 
//import org.jsoup.nodes.Document; 
// 


@WebServlet("/AppService")
public class AppService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	Appointment appObj = new Appointment();
	
    public AppService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String output = appObj.insertAppointment(request.getParameter("HospitalName"),
				 
				request.getParameter("DoctorName"),
				request.getParameter("AppointmentDate"), 
			 	request.getParameter("Category"));
		
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	
	private Map<String, String> getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();  
		try  {   
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");   
			String queryString = scanner.hasNext() ?
					scanner.useDelimiter("\\A").next() : "";   
			scanner.close(); 
		 
		  String[] param = queryString.split("&");   
		  for (String para : param)   {
			  String[] p = para.split("=");    
			  map.put(p[0], p[1]);   
		  }  
		  
		}catch (Exception e)  {  
			
		} 
		return map;
	}
	
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		Map<?, ?> para = getParasMap(request);
		
		String result = appObj.updateAppointment(para.get("hidAppIDSave").toString(),
		 		para.get("HospitalName").toString().replace("+", " "), 
		 		para.get("DoctorName").toString().replace("+", " "),
				para.get("AppointmentDate").toString().replace("+", " "),     
		 		para.get("Category").toString().replace("+", " "));
 
		
		response.getWriter().write(result);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		Map<String, String> para = getParasMap(request);
		
		String output = appObj.deleteAppointment(para.get("AppointmentID").toString());
		
		response.getWriter().write(output);
	}

}





