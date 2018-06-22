package com.eurodyn.hr.petstore.dao.model;


import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Cacheable
@DynamicUpdate
@DynamicInsert
@Table(name = "PET")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "petstore.entity-cache")
public class Pet implements Serializable {

	// Surrogate primary key
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Exposed resource ID
	private UUID externalId;

	private String name;
	
	private String species‎;
	
	private String breed;

	private String description;
	
	private Long price;
	
	@OneToOne(mappedBy = "pet", cascade = CascadeType.ALL , fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "SALE_ID")
	private Sale sale;

	private ZonedDateTime publicationDate;
	

	public Pet() {
		this.externalId = UUID.randomUUID();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public UUID getExternalId() {
		return externalId;
	}
	
	public void setExternalId(UUID externalId) {
		this.externalId = externalId;
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
	
	public Sale getSale() {
		return sale;
	}
	
	public void setSale(Sale sale) {
		this.sale = sale;
	}
	
	public ZonedDateTime getPublicationDate() {
		return publicationDate;
	}
	
	public void setPublicationDate(ZonedDateTime publicationDate) {
		this.publicationDate = publicationDate;
	}
}
