package com.icesi.samaca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icesi.samaca.model.sales.Salesorderheader;

public interface SalesorderheaderRepository  extends JpaRepository<Salesorderheader,Integer> {

}
