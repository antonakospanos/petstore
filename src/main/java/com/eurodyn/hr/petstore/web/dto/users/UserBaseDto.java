package com.eurodyn.hr.petstore.web.dto.users;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * UserBaseDto
 */
@JsonPropertyOrder({ "username", "password", "name", "email", "address", "city", "cellphone" })
@Getter
@Setter
public class UserBaseDto {

	@NotEmpty
	@ApiModelProperty(example = "panto", required = true)
	private String username;

	@NotEmpty
	@ApiModelProperty(example = "password", required = true)
	private String password;

	@NotEmpty
	@ApiModelProperty(example = "panos@antonakos.com", required = true)
	private String email;

	@NotEmpty
	@ApiModelProperty(example = "Panos Antonakos", required = true)
	private String name;

	@NotEmpty
	@ApiModelProperty(example = "209 Kifissias Avenue, Maroussi", required = true)
	private String address;

	@NotEmpty
	@ApiModelProperty(example = "Athens", required = true)
	private String city;

	@ApiModelProperty(example = "6941234567")
	private String cellphone;

	public UserBaseDto() {
	}

	public UserBaseDto(String username, String password, String name, String email, String address, String city, String cellphone) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.address = address;
		this.city = city;
		this.cellphone = cellphone;
	}
}
