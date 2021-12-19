package br.com.teste.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.teste.entities.Employee;
import br.com.teste.repositories.EmployeeRepository;

@Configuration
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Employee e1 = new Employee(null, "Marco Alexandre",2000.00);
		Employee e2 = new Employee(null, "Marcos Aurelio",2500.00);
		
		employeeRepository.saveAll(Arrays.asList(e1,e2));
		
	}

}
