package com.deeconsulting.desafiostoom.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.deeconsulting.desafiostoom.data.vo.AddressVO;
import com.deeconsulting.desafiostoom.entity.Address;
import com.deeconsulting.desafiostoom.exceptions.ResourceNotFoundException;
import com.deeconsulting.desafiostoom.repositories.AddressRepository;

@Service
public class AddressService {

	private final AddressRepository repository;
	
	@Autowired
	public AddressService(AddressRepository repository) {
		this.repository = repository;
	}
	
	public AddressVO create(AddressVO addressVO) {
		AddressVO addressVORetorno = AddressVO.create(repository.save(Address.create(addressVO)));
		return addressVORetorno;
	}
	
	public Page<AddressVO> findAll(Pageable pageable){
		var page = repository.findAll(pageable);
		return page.map(this::convertToAddressVO);
	}
	
	public AddressVO findById(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return AddressVO.create(entity);
	}
	
	public AddressVO update(AddressVO addressVO) {
		final Optional<Address> oAddress = repository.findById(addressVO.getId());
		
		if(!oAddress.isPresent()) {
			new ResourceNotFoundException("No records found for this ID");
		}
		
		return AddressVO.create(repository.save(Address.create(addressVO)));
	}
	
	public void delete(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
	}
	
	public boolean existsById(Long id) {
		return repository.existsById(id);
	}
	
	private AddressVO convertToAddressVO(Address address) {
		return AddressVO.create(address);
	}
}
