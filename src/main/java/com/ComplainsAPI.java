package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Complain;

/**
 * Servlet implementation class ComplainsAPI
 */
@WebServlet("/ComplainsAPI")
public class ComplainsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Complain comObj = new Complain();
    /**
     * Default constructor. 
     */
    public ComplainsAPI() {
    	super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		Complain comObj = new Complain();
		
		String output = "";
		output = comObj.readComplain();
		
		response.getWriter().append(output);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String output = comObj.insertComplain(
				request.getParameter("name"), 
				request.getParameter("date"),
				request.getParameter("complaintype"), 
				request.getParameter("nic"));
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		String output = "";
		if (paras.get("idcomplain") != null) {
			output = comObj.updateComplain(
					paras.get("idcomplain").toString(),
					paras.get("name").toString(),
					paras.get("date").toString(), 
					paras.get("complaintype").toString(), 
					paras.get("nic").toString());
		}
		else {
			output = comObj.updateComplain(
					request.getParameter("idcomplain"), 
					request.getParameter("name"), 
					request.getParameter("date"), 
					request.getParameter("complaintype"), 
					request.getParameter("nic"));
		}
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		String output = "";
		if (paras.get("idcomplain") != null) {
			output = comObj.deleteComplain(paras.get("idcomplain").toString());
		}
		else {
			output = comObj.deleteComplain(request.getParameter("idcomplain"));
		}
		System.out.println("ID: " + output);
		response.getWriter().write(output);
	}

	private static Map getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			for (String param : params) {

				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		} catch (Exception e) {
		}
		return map;
	}
}
