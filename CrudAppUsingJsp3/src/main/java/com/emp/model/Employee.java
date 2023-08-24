package com.emp.model;




import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity(name="employee")
public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int eid;
  
	@NotBlank(message = "Name cannot be blank")
	@Size(max = 20, message = "Name length must not exceed 20 characters")
	private String name;
	
	@Min(value = 18, message = "Age must be at least 18")
	private int age;
	
	@NotNull(message = "Salary cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Salary must be greater than 0")
	private Double salary;
	
	private String skills;
	
	private ArrayList<String> hobbies;
	
	@NotEmpty(message = "Country cannot be empty")
	private String country;
	 
	

	public Employee(String name, int age, Double salary, String skills, ArrayList<String> hobbies, String country) {
		super();
		this.name = name;
		this.age = age;
		this.salary = salary;
		this.skills = skills;
		this.hobbies = hobbies;
		this.country = country;
	}

	public Employee(int eid, String name, int age, Double salary, String skills, ArrayList<String> hobbies,
			String country) {
		super();
		this.eid = eid;
		this.name = name;
		this.age = age;
		this.salary = salary;
		this.skills = skills;
		this.hobbies = hobbies;
		this.country = country;
	}

	public Employee() {
		super();
		
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public ArrayList<String> getHobbies() {
		return hobbies;
	}

	public void setHobbies(ArrayList<String> hobbies) {
		this.hobbies = hobbies;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", name=" + name + ", age=" + age + ", salary=" + salary + ", skills=" + skills
				+ ", hobbies=" + hobbies + ", country=" + country + "]";
	}

	public String setHobbies(String hobbie) {
		// TODO Auto-generated method stub
		return hobbie;
	}
	

}