package com.eurodyn.hr.petstore.dao.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@Cacheable
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
@Table(name = "users")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "petstore.entity-cache")
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // Surrogate primary key
	private String username;
	private String password; // hashed
	private UUID externalId;
	private String name;
	private String email;
	private String address;
	private String city;
	private String cellphone;

	@OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL , fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<Sale> sales;

	public User() {
		this.externalId = UUID.randomUUID();
	}
}
