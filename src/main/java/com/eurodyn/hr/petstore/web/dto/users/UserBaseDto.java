package com.eurodyn.hr.petstore.web.dto.users;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

/**
 * UserBaseDto
 */
@JsonPropertyOrder({ "username", "password", "name", "email", "address", "city", "cellphone" })
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
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCellphone() {
		return cellphone;
	}
	
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
