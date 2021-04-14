package com.deeconsulting.desafiostoom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deeconsulting.desafiostoom.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

	boolean existsById(Long id);
	
}
