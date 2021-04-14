package com.deeconsulting.desafiostoom.data.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.deeconsulting.desafiostoom.entity.Address;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonPropertyOrder({ "id", "streetName", "number", "complement", "neighbourhood", "city", "state", "country", "zipcode",
		"latitude", "longitude" })
public class AddressVO extends RepresentationModel<AddressVO> implements Serializable {

	private static final long serialVersionUID = 8584074851679064689L;

	@JsonProperty("id")
	@Positive(message = "{id.not.blank}")
	private Long id;
	
	@JsonProperty("streetName")
	@NotBlank(message = "{streetName.not.blank}")
	private String streetName;
	
	@JsonProperty("number")
	@NotBlank(message = "{number.not.blank}")
	private String number;
	
	@JsonProperty("complement")
	private String complement;
	
	@JsonProperty("neighbourhood")
	@NotBlank(message = "{neighbourhood.not.blank}")
	private String neighbourhood;
	
	@JsonProperty("city")
	@NotBlank(message = "{city.not.blank}")
	private String city;
	
	@JsonProperty("state")
	@NotBlank(message = "{state.not.blank}")
	private String state;
	
	@JsonProperty("country")
	@NotBlank(message = "{country.not.blank}")
	private String country;
	
	@JsonProperty("zipcode")
	@NotBlank(message = "{zipcode.not.blank}")
	private String zipcode;
	
	@JsonProperty("latitude")
	private String latitude;
	
	@JsonProperty("longitude")
	private String longitude;

	public static AddressVO create(Address address) {
		return new ModelMapper().map(address, AddressVO.class);
	}
	
}
