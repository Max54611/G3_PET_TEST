package com.tecsup.petclinic.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import com.tecsup.petclinic.entities.Owner;

@Repository
public interface OwnerRepository extends 
	CrudRepository<Owner,Integer>{
	
	//Busqueda por first_name
	List<Owner> findByFirstName(String first_name);
	
	//Busqueda por id
	List<Owner> findById(int id);
	
	@Override
	List<Owner> findAll();
}
