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

import br.com.up.project.exception.ApplicationException;
import br.com.up.project.model.Employee;
import br.com.up.project.model.Vacation;
import br.com.up.project.repository.EmployeeRepository;
import br.com.up.project.repository.VacationRepository;

@Service
public class VacationService {

	@Autowired
	private VacationRepository vacationRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	public Vacation insert(Vacation vacation) {

		Employee employee = this.employeeRepository.findOne(vacation.getEmployee().getRegistration());
		vacation.setEmployee(employee);
		this.validateInsertEmployeeVacation(vacation);

		return this.vacationRepository.save(vacation);
	}

	public List<Vacation> findAll() {
		return this.vacationRepository.findAll(new Sort(Sort.Direction.ASC, "initialDate"));
	}

	public List<Vacation> findVacationByEmployee(Long registration) {

		Employee employee = this.employeeRepository.findOne(registration);
		return this.vacationRepository.findVacationByEmployee(employee);
	}

	private void validateInsertEmployeeVacation(Vacation vacation) {

		List<Employee> employees = new ArrayList<Employee>();

		if (vacation.getEmployee().getTeam() != null) {
			employees = this.employeeRepository.findEmployeeByTeam(vacation.getEmployee().getTeam());

			if (employees.size() <= 4)
				for (Employee e : employees) {
					Vacation v = this.vacationRepository.findEmployeeVacationByPeriod(e, vacation.getInitialDate());
					if (v != null) {

						throw new ApplicationException("Não é permitido duas pessoas tirarem férias em\r\n"
								+ "períodos que tenha ao menos um dia coincidente");
					}
				}
		}

		Instant instant = vacation.getEmployee().getHiringDate().toInstant();
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

		long dias = ChronoUnit.DAYS.between(localDateTime.toLocalDate(), LocalDate.now());

		if (dias < 365) {
			throw new ApplicationException("Funcionário não pode tirar férias antes de 1 ano de contrato");
		}
	}
}
