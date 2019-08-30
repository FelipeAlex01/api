package br.com.up.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.up.project.exception.ApplicationException;
import br.com.up.project.model.Vacation;
import br.com.up.project.service.VacationService;

@RestController
@RequestMapping("api/vacation")
@CrossOrigin(origins="http://localhost:4200")
public class VacationController {

	@Autowired
	private VacationService vacationService;

	@PostMapping(path = "/insert")
	public void insert(@RequestBody Vacation vacation) {

		try {
			this.vacationService.insert(vacation);
		} catch (ApplicationException ex) {
			System.out.print(ex.getMessage());
		}
	}

	@GetMapping("/find")
	public List<Vacation> findAll() {
		return this.vacationService.findAll();
	}

	@GetMapping("/find/employee/{registration}")
	public List<Vacation> findVacationByEmployee(@PathVariable(value = "registration") Long registration) {
		return this.vacationService.findVacationByEmployee(registration);
	}

}
