package com.icesi.samaca.services;

import com.icesi.samaca.model.person.UserApp;

public interface UserService {
	public UserApp save(UserApp user);
	public UserApp update(UserApp user, long id);

}
