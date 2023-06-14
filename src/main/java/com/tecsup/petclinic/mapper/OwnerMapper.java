package com.tecsup.petclinic.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.domain.OwnerTO;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface OwnerMapper {
	
	OwnerMapper INSTANCE = Mappers.getMapper(OwnerMapper.class);
	
	Owner toOwner(OwnerTO ownerTO);
	
	OwnerTO toOwnerTO(Owner owner);
	
	List<OwnerTO> toOwnerTOList(List<Owner> ownerList);
	
	List<Owner> toOwnerList(List<OwnerTO> ownerTOList);

}
