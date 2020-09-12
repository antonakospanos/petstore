package com.eurodyn.hr.petstore.web.dto.pets;

import com.eurodyn.hr.petstore.dao.model.Pet;
import com.eurodyn.hr.petstore.web.dto.Dto;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;

@JsonPropertyOrder({"id", "name", "species", "breed", "description", "price", "publicationDate"})
@Getter
@Setter
public class PetDto extends PetBaseDto implements Dto<Pet> {

	@ApiModelProperty(example = "6b6f2985-ae5b-46bc-bad1-f9176ab90171")
	private UUID id;
	private ZonedDateTime publicationDate;

	public PetDto() {
	}

	public PetDto(PetBaseDto petBaseDto) {
		super(petBaseDto.getName(), petBaseDto.getSpecies(), petBaseDto.getBreed(),
				petBaseDto.getDescription(), petBaseDto.getPrice());
	}

	@Override
	public PetDto fromEntity(Pet pet) {
		setId(pet.getExternalId());
		setName(pet.getName());
		setSpecies(pet.getSpecies());
		setBreed(pet.getBreed());
		setPrice(pet.getPrice());
		setDescription(pet.getDescription());
		setPublicationDate(pet.getPublicationDate());

		return this;
	}

	@Override
	public Pet toEntity() {
		Pet pet = new Pet();

		return toEntity(pet);
	}

	@Override
	public Pet toEntity(Pet pet) {
		pet.setName(this.getName());
		pet.setSpecies(this.getSpecies());
		pet.setBreed(this.getBreed());
		pet.setPrice(this.getPrice());
		pet.setDescription(this.getDescription());
		pet.setPublicationDate(ZonedDateTime.now());

		return pet;
	}
}
