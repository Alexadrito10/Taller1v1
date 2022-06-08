package com.icesi.samaca.backend.model.person;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.icesi.samaca.backend.validation.CountryRegionValidation;
import com.icesi.samaca.backend.validation.InfoValidation;

/**
 * The persistent class for the countryregion database table.
 *
 */
@Entity
@NamedQuery(name = "Countryregion.findAll", query = "SELECT c FROM Countryregion c")
public class Countryregion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "COUNTRYREGION_COUNTRYREGIONCODE_GENERATOR", allocationSize = 1, sequenceName = "COUNTRYREGION_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COUNTRYREGION_COUNTRYREGIONCODE_GENERATOR")
	private Integer countryregionid;
	
	@Size(min=1, max=4,groups= {CountryRegionValidation.class}, message= "El codigo debe tener entre 1 y 4 caracteres")
	private String countryregioncode;

	public String getCountryregioncode() {
		return countryregioncode;
	}

	public void setCountryregioncode(String countryregioncode) {
		this.countryregioncode = countryregioncode;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate modifieddate;

	@Size(min=5, groups ={CountryRegionValidation.class}, message= "El nombre debe tener minimo 5 caracteres")
	private String name;

	// bi-directional many-to-one association to Stateprovince
	@OneToMany(mappedBy = "countryregion")
	@JsonIgnore
	private List<Stateprovince> stateprovinces;

	public Countryregion() {
	}

	public Stateprovince addStateprovince(Stateprovince stateprovince) {
		getStateprovinces().add(stateprovince);
		stateprovince.setCountryregion(this);

		return stateprovince;
	}

	public LocalDate getModifieddate() {
		return this.modifieddate;
	}

	public String getName() {
		return this.name;
	}

	public List<Stateprovince> getStateprovinces() {
		return this.stateprovinces;
	}

	public Stateprovince removeStateprovince(Stateprovince stateprovince) {
		getStateprovinces().remove(stateprovince);
		stateprovince.setCountryregion(null);

		return stateprovince;
	}


	public void setModifieddate(LocalDate modifieddate) {
		this.modifieddate = modifieddate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStateprovinces(List<Stateprovince> stateprovinces) {
		this.stateprovinces = stateprovinces;
	}
	
	public Integer getCountryregionid() {
		return countryregionid;
	}

	public void setCountryregionid(Integer countryregionid) {
		this.countryregionid = countryregionid;
	}

}