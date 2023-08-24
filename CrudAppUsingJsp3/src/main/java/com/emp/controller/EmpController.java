package com.emp.controller;



import java.io.IOException;
import java.sql.SQLException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.emp.DAO.empDAOwithHib;

import com.emp.model.Employee;

//@WebServlet(name = "empServ", urlPatterns = {"/employee", "/new", "/delete","/update","/insert","/edit","/list"})
//@Controller
//@RequestMapping("/CrudAppUsingJsp2")
public class EmpController   extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	//private EmpDAO empdao;
	//@Autowired
	private empDAOwithHib empdaohib;
   
	public EmpController() {
		//this.empdao = new EmpDAO();
		this.empdaohib = new empDAOwithHib();
	}
	

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException {

	String action = request.getServletPath();
		
		switch (action) {
		case "/delete":
			try {
				deleteEmployee(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		default:
			try {
				listemp(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
         //System.out.println("hello this is deDelete method ");
     	//this.doGet(request,response);
	}
    
//	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		switch (action) {
    
		case "/update":
			try {
				updateEmployee(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		default:
			try {
				listemp(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		} //System.out.println("hello this is dePut method ");
	}

	@Override// employee/new == new//false
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		//System.out.println("do get method from emp cont start");
		switch (action) {
		// employee/new 
		case "/new":
			try {
				showNewForm(request, response);
				listemp(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case "/edit":
			try {
				showEditForm(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case "/delete":
			try {
				this.doDelete(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		default:
			try {
				System.out.println("i am default ");
				listemp(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		System.out.println("hello this is doGet Method  end");
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		switch (action) {

		case "/insert":
			try {
				insertEmployee(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case "/update":
			try {
				this.doPut(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		default:
			try {
				listemp(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		System.out.println("hello this is doPost Method ");
	}
	
	
	private void listemp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Employee> listemp = empdaohib.ListAllEmployee();
		
		request.setAttribute("listemp", listemp);
		System.out.println(listemp+"hiii");
		RequestDispatcher rd = request.getRequestDispatcher("/emp-update.jsp");
		 
		rd.forward(request, response);
	
	}
//    @GetMapping("/list")
//    public String listEmployees(Model model) {	
//        List<Employee> listemp = empdaohib.ListAllEmployee();
//        model.addAttribute("listemp", listemp);
//        return "emp-update"; // Assuming this is the view name
//    }

	private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ParseException {
		//System.out.println(request.toString()); 
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		String name = request.getParameter("name");
		System.out.println(name);
		int age = Integer.parseInt(request.getParameter("age"));
		
		Double salary = Double.parseDouble(request.getParameter("salary"));
		
		// String skills = request.getParameter("skills");
        String[] hobbie = request.getParameterValues("hobbiename");
		
		String skills = request.getParameter("skills");
		String Country = request.getParameter("country");
		
		ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(hobbie));
		Employee emp = new Employee(id,name, age, salary,skills, arrayList,Country);
		empdaohib.updateEmployee(emp);
		//response.sendRedirect("list");
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		Employee existingemp = empdaohib.selectEmployee(id);

		System.out.println("exist " + existingemp);
		RequestDispatcher rd = request.getRequestDispatcher("emp-update.jsp");
		request.setAttribute("emp", existingemp);
		
		rd.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("emp-update.jsp");
		rd.forward(request, response);
	}

	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		
		empdaohib.deleteEmployee(id);
		
		//response.sendRedirect("list");
	}

	private void insertEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ParseException {
        System.out.println("in insert Employee"); 
		String name = request.getParameter("name");
		
		int age = Integer.parseInt(request.getParameter("age"));
		Double salary = Double.parseDouble(request.getParameter("salary"));
        String[] hobbie = request.getParameterValues("hobbiename");
        System.out.println(hobbie);
		String skills = request.getParameter("skills");
		String Country = request.getParameter("country");
		//System.out.println(hobbie);
		ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(hobbie));
		Employee emp = new Employee(name, age, salary,skills, arrayList,Country);
		System.out.println(emp + "hiii");
		empdaohib.insertEmp(emp);
		//response.sendRedirect("list");
	}

	
	
}

