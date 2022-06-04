package com.icesi.samaca.backend.services;

import com.icesi.samaca.backend.model.person.Person;
import com.icesi.samaca.backend.model.person.UserApp;

public interface PersonService {
	public Person save(Person user);
	public Person update(Person user);
}

