package br.com.up.project.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.up.project.model.Employee;
import br.com.up.project.model.Team;
import br.com.up.project.model.Vacation;
import br.com.up.project.repository.EmployeeRepository;
import br.com.up.project.repository.TeamRepository;
import br.com.up.project.repository.VacationRepository;

@Service
public class EmployeeService {

	private static final int MONTH_REQUEST_VACATION = 2;

	@Autowired
	private EmployeeRepository employeeRepository;;

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private VacationRepository vacationRepository;

	public Employee insert(Employee employee) {

		if (employee.getTeam() != null) {
			Team team = this.teamRepository.findOne(employee.getTeam().getId());
			employee.setTeam(team);
		}
		return this.employeeRepository.save(employee);
	}

	public List<Employee> findAll() {
		return this.employeeRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
	}

	public List<Employee> findShouldRequestEmployeeVacation(int month) {
		List<Vacation> vacations = this.vacationRepository.findLastEmployeeVacation();
		List<Employee> employees = new ArrayList<Employee>();
		for (Vacation vacation : vacations) {
			LocalDate localDateNow = LocalDate.now().plusMonths(month);
			Instant instant = vacation.getFinalDate().toInstant();
			LocalDateTime lastVacation = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
			long year = ChronoUnit.YEARS.between(lastVacation.toLocalDate(), localDateNow);
			if (year > 1 && year <= EmployeeService.MONTH_REQUEST_VACATION) {
				employees.add(vacation.getEmployee());
			}
		}
		return employees;
	}

}
