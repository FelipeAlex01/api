package br.com.up.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.up.project.model.Employee;
import br.com.up.project.model.Team;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query("SELECT e FROM employee e WHERE  e.team = ?1")
	public List<Employee> findEmployeeByTeam(@PathVariable Team team);

}
