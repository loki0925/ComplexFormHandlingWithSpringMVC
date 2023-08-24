package com.emp.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.emp.DAO.empDAOwithHib;
import com.emp.model.Employee;

@Controller
//@RequestMapping(value ="/CrudAppUsingJsp2")
public class EmpControllerSpring {
	
	
	@RequestMapping(value="/")
	public ModelAndView test(HttpServletResponse response) throws IOException{
		return new ModelAndView("emp-update");
	}
	
   @RequestMapping(value= "register",method = RequestMethod.GET)
   public String viewRegistrationForm() {
	   return "Registration";
   }
   
	@Autowired
	private empDAOwithHib empdaohib;
	
	@RequestMapping(value= "/list",method = RequestMethod.GET)
    public String listEmployees(Model model) {
        List<Employee> listemp = empdaohib.ListAllEmployee();
        System.out.println("In spring controller");
        model.addAttribute("listemp", listemp);
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        
        List<String> countryList = Arrays.asList("UK","Australia","India","USA");
 	    model.addAttribute("countryList", countryList);
        return "emp-update"; // Assuming this is the view name
    }
	@RequestMapping(value="/edit",method=RequestMethod.GET)
    public String showEditForm(@RequestParam int id, Model model) {
		System.out.println("Inside Show edit form");
        Employee employee = empdaohib.selectEmployee(id);
        model.addAttribute("employee", employee);
        return "emp-update"; // Assuming this is the view name
    }
	@RequestMapping(value="/insert",method=RequestMethod.POST)
    public String insertEmployee( Employee emp, Model model) {
	if(isEmployeeNameExists(emp.getName())) {
		model.addAttribute("error", "Employee with the same name already exists!");
	}
		else {
        empdaohib.insertEmp(emp);
          }  
	 return "redirect:/list";
    }
	private boolean isEmployeeNameExists(String name) {
	    return empdaohib.isEmployeeNameExists(name);
	}
	@RequestMapping(value="/update",method=RequestMethod.POST)
	    public String updateEmployee(@ModelAttribute("employee")Employee emp) {
	        empdaohib.updateEmployee(emp);
	        return "redirect:/list";
	    }

	@RequestMapping(value="/delete",method=RequestMethod.GET)
	    public String deleteEmployee(@RequestParam int id) {
	        empdaohib.deleteEmployee(id);
	        return "redirect:/list";
	    }
}
