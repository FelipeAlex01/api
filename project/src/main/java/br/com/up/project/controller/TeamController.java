package br.com.up.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.up.project.model.Team;
import br.com.up.project.service.TeamService;

@RestController
@RequestMapping("api/team")
@CrossOrigin(origins="http://localhost:4200")
public class TeamController {

	@Autowired
	private TeamService teamService;

	@PostMapping(path = "/insert")
	public void insert(@RequestBody Team team) {
		this.teamService.insert(team);
	}
	
	@DeleteMapping(path = "/delete/{id}")
	public void delete(@PathVariable Long id) {
		this.teamService.delete(id);
	}

	@GetMapping("/find")
	public List<Team> find() {
		return this.teamService.findAll();
	}
	
	/*
	 * @GetMapping("/students/{studentId}/courses/{courseId}") public Course
	 * retrieveDetailsForCourse(@PathVariable String studentId, @PathVariable String
	 * courseId) { return studentService.retrieveCourse(studentId, courseId); }
	 */

}
