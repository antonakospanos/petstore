package com.eurodyn.hr.petstore.dao.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Cacheable
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
@Table(name = "pets")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "petstore.entity-cache")
public class Pet implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // Surrogate primary key
	private UUID externalId; // Exposed resource ID
	private String name;
	private String species;
	private String breed;
	private String description;
	private Long price;
	private ZonedDateTime publicationDate;

	@OneToOne(mappedBy = "pet", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "SALE_ID")
	private Sale sale;

	public Pet() {
		this.externalId = UUID.randomUUID();
	}
}
