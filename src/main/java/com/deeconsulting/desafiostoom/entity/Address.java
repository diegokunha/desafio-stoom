package com.deeconsulting.desafiostoom.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.modelmapper.ModelMapper;

import com.deeconsulting.desafiostoom.data.vo.AddressVO;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Address implements Serializable{

	private static final long serialVersionUID = 4752402073579576011L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String streetName;
	
	@Column(nullable = false)
	private String number;
	
	private String complement;
	
	@Column(nullable = false)
	private String neighbourhood;
	
	@Column(nullable = false)
	private String city;
	
	@Column(nullable = false)
	private String state;
	
	@Column(nullable = false)
	private String country;
	
	@Column(nullable = false)
	private String zipcode;
	
	private String latitude;
	private String longitude;
	
	public static Address create(AddressVO addressVO) {
		return new ModelMapper().map(addressVO, Address.class);
	}
	
}
