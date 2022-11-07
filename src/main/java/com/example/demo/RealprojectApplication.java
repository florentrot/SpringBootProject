package com.example.demo;

import com.example.demo.dao.EmployeeRepository;
import com.example.demo.dao.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import com.example.demo.entities.Employee;
import com.example.demo.entities.Project;
import java.util.Arrays;

@SpringBootApplication(scanBasePackages = {"com.example.demo", "com.example.utils"})
public class RealprojectApplication {

/*	@Autowired
	EmployeeRepository empRepo;
	@Autowired
	ProjectRepository projRepo;*/

	public static void main(String[] args) {
		SpringApplication.run(RealprojectApplication.class, args);
	}
/**
 * CommandLineRunner for seeding the db
 */
/*	@Bean
	CommandLineRunner runner(){
		return args -> {

			Employee emp1 = new Employee("John", "Warton", "warton@gmail.com");
			Employee emp2 = new Employee("Mike", "Baxton", "baxton@gmail.com");
			Employee emp3 = new Employee("Ion", "Ionovici", "ionovici@gmail.com");

			Project pro1 = new Project("CSS Library", "NOTSTARTED", "Easy to do");
			Project pro2 = new Project("JS Code", "COMPLETED", "Hard to do");
			Project pro3 = new Project("C++ Learning", "INPROGRESS", "Easy to do");

		pro1.addEmployee(emp1);
		pro1.addEmployee(emp2);
		pro2.addEmployee(emp1);
		pro3.addEmployee(emp3);

		emp1.setTheProject(Arrays.asList(pro1,pro2));
		emp2.setTheProject(Arrays.asList(pro1));
		emp3.setTheProject(Arrays.asList(pro3));

		//save employees in db
		empRepo.save(emp1);
		empRepo.save(emp2);
		empRepo.save(emp3);

		//save projects in db
		projRepo.save(pro1);
		projRepo.save(pro2);
		projRepo.save(pro3);
		};
	}*/
}
