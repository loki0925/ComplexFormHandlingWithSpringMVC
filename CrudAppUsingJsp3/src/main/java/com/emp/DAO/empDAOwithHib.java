package com.emp.DAO;


import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.emp.model.Employee;
import com.emp.util.EmUtil;



@Component
public class empDAOwithHib {

	public String insertEmp(Employee e) {
		String s = "Not Inserted..";

		EntityManager em = EmUtil.getEntityManager();

		if (e != null) {
			em.getTransaction().begin();
			em.persist(e);
			em.getTransaction().commit();
			s = "Employee data inserted Successfully..";

			em.close();
		} else {
			s = "do not pass null value";
		}
        System.out.println(s);
		return s;
	}
	
	public List<Employee> ListAllEmployee() {
		
		EntityManager em = EmUtil.getEntityManager();

		 System.out.println("hello connecting");
		 String JPQuery = "SELECT p FROM employee p";
		 TypedQuery<Employee> query = em.createQuery(JPQuery, Employee.class);
		 List<Employee> emps = query.getResultList();
         
		
		return emps;
	}
	public String deleteEmployee(int id) {
		EntityManager em = EmUtil.getEntityManager();
		   Employee emp = em.find(Employee.class, id);
			if(emp!=null) {
				em.getTransaction().begin();
				em.remove(emp);
				em.getTransaction().commit();
				em.close();
				return "Employee Sucessfully deleted";
			}else{
				return "Employee not found in Database";
			}
	}
	public boolean updateEmployee(Employee emp) {
		EntityManager em = EmUtil.getEntityManager();
		Employee emp1 = em.find(Employee.class, emp.getEid());
		System.out.println(emp.getEid());
		System.out.println(emp);
//		String hobbie = " ";
//		for(String i:emp.getHobbies()) {
//			hobbie +=i;
//		}
		if(emp1 != null) {
			em.getTransaction().begin();
			emp1.setName(emp.getName());
			emp1.setAge(emp.getAge());
			emp1.setSalary(emp.getSalary());
			emp1.setSkills(emp.getSkills());
			emp1.setCountry(emp.getCountry());
			emp1.setHobbies(emp.getHobbies());
			em.merge(emp1);
			em.getTransaction().commit();
			em.close();
			return true;
		}
		else {
			return false;
		}
		
	}

	public Employee selectEmployee(int id) {
		// TODO Auto-generated method stub
		EntityManager em = EmUtil.getEntityManager();
		Employee emp1 = em.find(Employee.class, id);
		return emp1;
	}

	public boolean isEmployeeNameExists(String name) {
		// TODO Auto-generated method stub
		EntityManager em = EmUtil.getEntityManager();
		
        String JPQuery = "SELECT p FROM employee p";
		 TypedQuery<Employee> query = em.createQuery(JPQuery, Employee.class);
		 List<Employee> emps = query.getResultList();
		 
		 for(Employee i:emps) {
			 if(name == i.getName()) {
				 return true;
			 }
		 }
        
		return false;
	}
}
