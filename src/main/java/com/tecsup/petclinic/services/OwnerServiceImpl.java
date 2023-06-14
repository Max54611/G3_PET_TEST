package com.tecsup.petclinic.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.repositories.OwnerRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OwnerServiceImpl implements OwnerService{
	
	OwnerRepository ownerRepository;
	
	public OwnerServiceImpl (OwnerRepository ownerRepository) {
		this.ownerRepository = ownerRepository;
	}
	
	
	
	@Override
	public Owner create(Owner owner) {
		return ownerRepository.save(owner);
	}

	@Override
	public Owner update(Owner owner) {
		return ownerRepository.save(owner);
	}

	@Override
	public void delete(Integer id) throws OwnerNotFoundException {
		Owner owner = findById(id);
		ownerRepository.delete(owner);
	}

	@Override
	public Owner findById(Integer id) throws OwnerNotFoundException {
		
		Optional<Owner> owner = ownerRepository.findById(id);
		
		if( !owner.isPresent())
			throw new OwnerNotFoundException("Record not found...!");
		
		return owner.get();
	}

	@Override
	public List<Owner> findAll() {
		return ownerRepository.findAll();
	}
	
}
