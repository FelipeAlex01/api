package br.com.up.project.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.up.project.model.Employee;
import br.com.up.project.model.Vacation;

public interface VacationRepository extends JpaRepository<Vacation, Long> {

	@Query("SELECT v FROM vacation v WHERE v.employee = ?1")
	public List<Vacation> findVacationByEmployee(@PathVariable Employee employee);

	@Query("SELECT v FROM vacation v WHERE v.employee = ?1 AND  v.finalDate > ?2")
	public Vacation findEmployeeVacationByPeriod(@PathVariable Employee employee, @PathVariable Date date);

	@Query("SELECT DISTINCT v.id, MAX(v.finalDate) AS final_date,v.initialDate, v.employee  FROM vacation v GROUP BY v.employee")
	public List<Vacation> findLastEmployeeVacation();
}
