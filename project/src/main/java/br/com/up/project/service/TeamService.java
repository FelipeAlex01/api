package br.com.up.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.up.project.model.Team;
import br.com.up.project.repository.TeamRepository;

@Service
public class TeamService {

	@Autowired
	private TeamRepository teamRepository;;

	public Team insert(Team team) {
		return this.teamRepository.save(team);
	}
	
	public void delete(Long id) {
		this.teamRepository.delete(id);
	}

	public List<Team> findAll() {
		return this.teamRepository.findAll();
	}

}
