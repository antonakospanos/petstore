package com.eurodyn.hr.petstore.web.dto.pets;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@JsonPropertyOrder({ "name", "species", "breed", "description", "price" })
public class PetBaseDto {

    @NotEmpty
    @ApiModelProperty(example = "Lassie", required = true)
    private String name;
    
    @NotEmpty
    @ApiModelProperty(example = "Dog", required = true)
    private String species‎;
    
    @NotEmpty
    @ApiModelProperty(example = "Collie", required = true)
    private String breed;
    
    @NotEmpty
    @ApiModelProperty(example = "Lassie is a marvelous dog!", required = true)
    private String description;
    
    @NotNull
    @ApiModelProperty(example = "300", required = true)
    private Long price;

    public PetBaseDto() {
    }
    
    public PetBaseDto(@NotEmpty String name, @NotEmpty String species‎, @NotEmpty String breed, @NotEmpty String description, @NotNull Long price) {
        this.name = name;
        this.species‎ = species‎;
        this.breed = breed;
        this.description = description;
        this.price = price;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getSpecies‎() {
        return species‎;
    }
    
    public void setSpecies‎(String species‎) {
        this.species‎ = species‎;
    }
    
    public String getBreed() {
        return breed;
    }
    
    public void setBreed(String breed) {
        this.breed = breed;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Long getPrice() {
        return price;
    }
    
    public void setPrice(Long price) {
        this.price = price;
    }
}

