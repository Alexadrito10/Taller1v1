package com.icesi.samaca.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.samaca.model.person.UserApp;
import com.icesi.samaca.model.person.UserType;
import com.icesi.samaca.repositories.UserRepository;

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
	public UserApp update(UserApp user, long id) {
		UserApp aux=null;
		Optional<UserApp> temp = this.userRepos.findById(id);
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
	




}
