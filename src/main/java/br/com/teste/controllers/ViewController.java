package br.com.teste.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.teste.entities.Employee;
import br.com.teste.repositories.EmployeeRepository;

@Component
@Controller
public class ViewController {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@RequestMapping(value = "/getEmployees/", method = RequestMethod.GET)
	public String getEmployees(Model model) {
		List<Employee> employees = new ArrayList<Employee>();
		employees = employeeRepository.findAll();
		model.addAttribute("employees", employees);
		return "getEmployee";
	}
	
	@RequestMapping(value = "/addEmployees/", method = RequestMethod.GET)
	public String addEmployee(Model model) {
		model.addAttribute("employee",new Employee());
		return "addEmployee";
	}
	
	@RequestMapping(value = "/saveEmployee/", method = RequestMethod.POST)
	public String saveEmployee(@ModelAttribute Employee employee, Model model, BindingResult result) {
		employeeRepository.save(employee);
		return "redirect:/getEmployees/";
	}
	
	@RequestMapping(value = "/updateEmployees/{id}", method = RequestMethod.GET)
	public String updateEmployee(@PathVariable Long id, Model model) {
		Optional<Employee> employee = employeeRepository.findById(id);
		model.addAttribute("employee",employee);
		return "updateEmployee";
	}
	
	@RequestMapping(value = "/saveUpdateEmployee/{id}", method = RequestMethod.POST)
	public String saveUpdateEmployee(@PathVariable Long id, @ModelAttribute Employee newEmployee, Model model, BindingResult result) {
		Optional<Employee> oldEmployee = employeeRepository.findById(id);
		oldEmployee.get().setName(newEmployee.getName());
		oldEmployee.get().setSalary(newEmployee.getSalary());
		employeeRepository.save(oldEmployee.get());
		return "redirect:/getEmployees/";
	}
	
	@RequestMapping(value = "/deleteEmployees/{id}", method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable Long id, Model model) {
		Optional<Employee> employee = employeeRepository.findById(id);
		model.addAttribute("employee", employee.get());
		return "confirmDeleteEmployee";
	}
	
	@RequestMapping(value = "/saveDeleteEmployees/{id}", method = RequestMethod.POST)
	public String saveDeleteEmployee(@ModelAttribute Employee employee, @PathVariable Long id, BindingResult result) {
		employeeRepository.delete(employeeRepository.getById(id));
		return "redirect:/getEmployees/";
	}
}
