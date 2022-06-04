package com.icesi.samaca.model.person;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.icesi.samaca.model.sales.Salesorderheader;
import com.icesi.samaca.validation.AddressValidation;
import com.icesi.samaca.validation.CountryRegionValidation;

/**
 * The persistent class for the address database table.
 *
 */
@Entity
@NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a")
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ADDRESS_ADDRESSID_GENERATOR", allocationSize = 1, sequenceName = "ADDRESS_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADDRESS_ADDRESSID_GENERATOR")
	private Integer addressid;
	
	@NotBlank( groups ={AddressValidation.class}, message= "La linea 1 de direccion no puede ser nula")
	private String addressline1;

	private String addressline2;
	@Size( min=3 ,groups ={AddressValidation.class}, message= "La ciudad debe contener al menos 3 caracteres")
	private String city;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate modifieddate;

	@Size( min=6 ,max=6 ,groups ={AddressValidation.class}, message= "El codigo postal debe ser de 6 digitos")
	private String postalcode;
	
	@OneToMany(mappedBy = "shiptoaddress")
	private List<Salesorderheader> salesorderheaders;

	private Integer rowguid;

	private String spatiallocation;

	// bi-directional many-to-one association to Stateprovince
	@NotNull(groups = AddressValidation.class)
	@ManyToOne
	@JoinColumn(name = "stateprovinceid")
	private Stateprovince stateprovince;

	// bi-directional many-to-one association to Businessentityaddress
	@OneToMany(mappedBy = "address")
	private List<Businessentityaddress> businessentityaddresses;

	public Address() {
	}

	public Businessentityaddress addBusinessentityaddress(Businessentityaddress businessentityaddress) {
		getBusinessentityaddresses().add(businessentityaddress);
		businessentityaddress.setAddress(this);

		return businessentityaddress;
	}

	public Integer getAddressid() {
		return this.addressid;
	}

	public String getAddressline1() {
		return this.addressline1;
	}

	public String getAddressline2() {
		return this.addressline2;
	}

	public List<Businessentityaddress> getBusinessentityaddresses() {
		return this.businessentityaddresses;
	}

	public String getCity() {
		return this.city;
	}

	public LocalDate getModifieddate() {
		return this.modifieddate;
	}

	public String getPostalcode() {
		return this.postalcode;
	}

	public Integer getRowguid() {
		return this.rowguid;
	}

	public String getSpatiallocation() {
		return this.spatiallocation;
	}

	public Stateprovince getStateprovince() {
		return this.stateprovince;
	}

	public Businessentityaddress removeBusinessentityaddress(Businessentityaddress businessentityaddress) {
		getBusinessentityaddresses().remove(businessentityaddress);
		businessentityaddress.setAddress(null);

		return businessentityaddress;
	}

	public void setAddressid(Integer addressid) {
		this.addressid = addressid;
	}

	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}

	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}

	public void setBusinessentityaddresses(List<Businessentityaddress> businessentityaddresses) {
		this.businessentityaddresses = businessentityaddresses;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setModifieddate(LocalDate modifieddate) {
		this.modifieddate = modifieddate;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public void setRowguid(Integer rowguid) {
		this.rowguid = rowguid;
	}

	public void setSpatiallocation(String spatiallocation) {
		this.spatiallocation = spatiallocation;
	}

	public void setStateprovince(Stateprovince stateprovince) {
		this.stateprovince = stateprovince;
	}

}