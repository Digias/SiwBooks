package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.EndUser;

public interface UserRepository extends CrudRepository<EndUser, Long> {

}
