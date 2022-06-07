package com.icesi.samaca.backend.services;

import com.icesi.samaca.backend.model.person.UserApp;

public interface UserService {
	public UserApp save(UserApp user);
	public UserApp update(UserApp user);
	public UserApp delete( long id);

}
