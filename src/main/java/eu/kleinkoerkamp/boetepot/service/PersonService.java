package eu.kleinkoerkamp.boetepot.service;

import java.util.List;

import eu.kleinkoerkamp.boetepot.domain.Person;

public interface PersonService {
	List<Person> getAll();
	
	void delete(Person person);

	Person get(String username);

	void save(Person modelObject);
}
