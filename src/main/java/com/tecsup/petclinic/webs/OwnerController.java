package com.tecsup.petclinic.webs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.domain.OwnerTO;
import com.tecsup.petclinic.mapper.OwnerMapper;
import com.tecsup.petclinic.services.OwnerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class OwnerController {
	
	@Autowired
	private OwnerService ownerService;
	
	@Autowired
	private OwnerMapper mapper;
	
	public OwnerController(OwnerService ownerService, OwnerMapper mapper) {
		this.ownerService = ownerService;
		this.mapper = mapper;
	}
	
	@GetMapping(value = "/owners")
	public ResponseEntity<List<OwnerTO>> findAllOwners(){
		List<Owner> owners = (List<Owner>) ownerService.findAll();
		log.info("owners: "+owners);
		owners.forEach(item -> log.info("Owner >> {} ", item));
		
		List<OwnerTO> ownersTO = this.mapper.toOwnerTOList(owners);
		log.info("ownersTO: "+ownersTO);
		ownersTO.forEach(item -> log.info("PetTO >> {}", item));
		
		return ResponseEntity.ok(ownersTO);
	}
	
	@PostMapping(value="/owners")
	@ResponseStatus(HttpStatus.CREATED)
	ResponseEntity<OwnerTO> create(@RequestBody OwnerTO ownerTO){
		
		Owner newOwner = this.mapper.toOwner(ownerTO);
		OwnerTO newOwnerTO = this.mapper.toOwnerTO(ownerService.create(newOwner));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newOwnerTO);
	}
	
	@GetMapping(value="/owners/{id}")
	ResponseEntity<OwnerTO> findbyId(@PathVariable Integer id){
		
		OwnerTO ownerTO = null;
		
		try {
			Owner owner = ownerService.findById(id);
			ownerTO = this.mapper.toOwnerTO(owner);
		}catch(OwnerNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(ownerTO);
	}
	
	@PutMapping(value = "/owners/{id}")
	ResponseEntity<OwnerTO> update(@RequestBody OwnerTO ownerTO, @PathVariable Integer id){
		
		OwnerTO updateOwnerTO = null;
		
		try {
			Owner updateOwner = ownerService.findById(id);
			
			updateOwner.setFirstName(ownerTO.getFirstName());
			updateOwner.setLastName(ownerTO.getLastName());
			updateOwner.setAddress(ownerTO.getAddress());
			updateOwner.setCity(ownerTO.getCity());
			updateOwner.setTelephone(ownerTO.getTelephone());
			
			ownerService.update(updateOwner);
			
			updateOwnerTO = this.mapper.toOwnerTO(updateOwner);
		}catch(OwnerNotFoundException e) {
			return ResponseEntity.notFound().build();
			}
		return ResponseEntity.ok(updateOwnerTO);
	}
	
	@DeleteMapping(value = "/owners/{id}")
	ResponseEntity<String> delete(@PathVariable Integer id){
		
		try {
			ownerService.delete(id);
			return ResponseEntity.ok("Delete ID: "+id);
		}catch(OwnerNotFoundException e){
			return ResponseEntity.notFound().build();
		}
	}
}
