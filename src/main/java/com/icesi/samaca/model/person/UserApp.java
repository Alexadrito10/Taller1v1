package com.icesi.samaca.model.person;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import com.icesi.samaca.validation.InfoValidation;

import lombok.Data;

@Data
@Entity
public class UserApp {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	@Size(min = 5, groups = {AddUserInter1.class,UserUpdateInter.class}, message = "el usuario tiene que tener minimo 5 caracteres")
	@NotBlank(groups = AddUserInter1.class)
	private String username;
	
	@Size(min = 8,groups = {AddUserInter1.class,UserUpdateInter.class}, message = "Se necesita minimo 8 caracteres")
	@NotBlank(groups = AddUserInter1.class)
	private String password;
	
	@NotNull(groups= {InfoValidation.class, UserUpdateInter.class}, message= "No puede estar vacia")
	private UserType type;


	

}
