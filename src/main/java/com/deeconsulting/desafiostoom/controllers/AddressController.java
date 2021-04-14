package com.deeconsulting.desafiostoom.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deeconsulting.desafiostoom.data.vo.AddressVO;
import com.deeconsulting.desafiostoom.data.vo.Message;
import com.deeconsulting.desafiostoom.services.AddressService;

@RestController
@RequestMapping("/desafio-stoom/address")
public class AddressController {

	private final AddressService service;
	private final PagedResourcesAssembler<AddressVO> assembler;
	
	@Autowired
	public AddressController(AddressService service, PagedResourcesAssembler<AddressVO> assembler) {
		super();
		this.service = service;
		this.assembler = assembler;
	}
	
	@GetMapping(value = "/{id}", produces = {"application/json", "application/xml"})
	public AddressVO findByID(@PathVariable("id") Long id) {
		AddressVO addressVO = service.findById(id);
		addressVO.add(linkTo(methodOn(AddressController.class).findByID(id)).withSelfRel());
		return addressVO;
	}
	
	@GetMapping(produces = {"application/json", "application/xml"})
	public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "streetName"));
		
		Page<AddressVO> addresses = service.findAll(pageable);
		addresses.stream()
				 .forEach(a -> a.add(linkTo(methodOn(AddressController.class).findByID(a.getId())).withSelfRel()));
		
		PagedModel<EntityModel<AddressVO>> pagedModel = assembler.toModel(addresses);
		return new ResponseEntity<>(pagedModel, HttpStatus.OK);
	}
	
	@PostMapping(produces = {"application/json", "application/xml"},
			     consumes = {"application/json", "application/xml"})
	public AddressVO create(@Valid @RequestBody AddressVO addressVO) {
		AddressVO addressVORetorno = service.create(addressVO);
		addressVORetorno.add(linkTo(methodOn(AddressController.class).findByID(addressVORetorno.getId())).withSelfRel());
		
		return addressVORetorno;
	}
	
	@PutMapping(produces = {"application/json", "application/xml"},
		     consumes = {"application/json", "application/xml"})
	public ResponseEntity<?> update(@Valid @RequestBody AddressVO addressVO) {
		if(!service.existsById(addressVO.getId())) {
			return new ResponseEntity(new Message("No records found for this ID!"), HttpStatus.BAD_REQUEST);
		}
		
		AddressVO addressVORetorno = service.update(addressVO);
		addressVORetorno.add(linkTo(methodOn(AddressController.class).findByID(addressVORetorno.getId())).withSelfRel());
		
		return new ResponseEntity(addressVORetorno, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		if(!service.existsById(id))
            return new ResponseEntity(new Message("No records found for this ID!"), HttpStatus.NOT_FOUND);
		
		service.delete(id);
		return new ResponseEntity(new Message("Address deleted successfully!"), HttpStatus.OK);
	}
}