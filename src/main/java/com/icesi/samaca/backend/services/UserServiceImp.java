package com.icesi.samaca.backend.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.samaca.backend.model.person.Stateprovince;
import com.icesi.samaca.backend.model.person.UserApp;
import com.icesi.samaca.backend.model.person.UserType;
import com.icesi.samaca.backend.repositories.UserRepository;

@Service
public class UserServiceImp implements UserService{

	private UserRepository userRepos;
	
	
	@Autowired
	public UserServiceImp(UserRepository userRepos) {
		this.userRepos = userRepos;
	}


	@Override
	@Transactional
	public UserApp save(UserApp user) {
		return userRepos.save(user);
	}

	@Override
	@Transactional
	public UserApp update(UserApp user) {
		UserApp aux=null;
		Optional<UserApp> temp = this.userRepos.findById(user.getId());
		if(temp.isPresent()) {
			aux = save(user);
		}
		return aux;
	}
	
	public Optional<UserApp> findById(long id) {
		return userRepos.findById(id);

	}
	
	public Iterable<UserApp> findAll(){
		return userRepos.findAll();
	}
	
	public UserType[] getTypes() {
		return UserType.values();
	}


	@Override
	public UserApp delete(long id) {
		Optional<UserApp> result = userRepos.findById(id);
		userRepos.delete(result.get());
		
		return result.get();
	}
	




}
