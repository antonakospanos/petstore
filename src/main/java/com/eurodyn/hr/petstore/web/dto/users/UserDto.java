package com.eurodyn.hr.petstore.web.dto.users;

import com.eurodyn.hr.petstore.dao.model.User;
import com.eurodyn.hr.petstore.web.dto.Dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

/**
 * AccountDto
 */
@JsonPropertyOrder({ "id", "username", "password", "name", "email" })
@Component
public class UserDto extends UserBaseDto implements Dto<User> {

	public static List<String> fields = Arrays.asList(UserBaseDto.class.getDeclaredFields())
			.stream()
			.map(field -> field.getName())
			.collect(Collectors.toList());

	@NotNull
	@ApiModelProperty(example = "eba3e4be-0d60-41ee-92e4-0c96b1af5b6e", required = true)
	private UUID id;


	public UserDto() {
	}

	public UserDto(UUID id, String username, String password, String name, String email, String address, String city, String cellphone) {
		this.id = id;
		setUsername(username);
		setPassword(password);
		setName(name);
		setEmail(email);
		setAddress(address);
		setCity(city);
		setCellphone(cellphone);
	}

	public UserDto(UUID id, UserBaseDto userBaseDto) {
		this(userBaseDto);
		this.id = id;
	}

	public UserDto(UserBaseDto userBaseDto) {
		super(userBaseDto.getUsername(), userBaseDto.getPassword(), userBaseDto.getName(), userBaseDto.getEmail(),
				userBaseDto.getAddress(), userBaseDto.getCity(), userBaseDto.getCellphone());
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	@JsonIgnore
	@ApiModelProperty(hidden = true)
	@Override
	public String getPassword() {
		return super.getPassword();
	}

	@JsonIgnore
	@ApiModelProperty(hidden = true)
	@Override
	public void setPassword(String password) {
		super.setPassword(password);
	}

	@Override
	public UserDto fromEntity(User user) {
		this.id = user.getExternalId();

		setUsername(user.getUsername());
		setPassword(user.getPassword());
		setEmail(user.getEmail());
		setName(user.getName());
		setAddress(user.getAddress());
		setCity(user.getCity());
		setCellphone(user.getCellphone());

		return this;
	}

	@Override
	public User toEntity() {
		User user = new User();

		return toEntity(user);
	}

	@Override
	public User toEntity(User user) {
		// Do not pass this.getId(), USER_EXTERNAL_ID is auto-generated!
		user.setUsername(this.getUsername());
		user.setName(this.getName());
		user.setEmail(this.getEmail());
		user.setAddress(this.getAddress());
		user.setCity(this.getCity());
		user.setCellphone(this.getCellphone());
		// DAO: Add hash password

		return user;
	}
}
