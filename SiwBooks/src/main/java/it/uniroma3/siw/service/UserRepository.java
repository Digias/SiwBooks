package it.uniroma3.siw.service;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.EndUser;

public interface UserRepository extends CrudRepository<EndUser, Long> {

}
