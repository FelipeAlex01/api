package br.com.up.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.up.project.model.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {

	Team findByName(String name);

}
