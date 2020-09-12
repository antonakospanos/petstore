package com.eurodyn.hr.petstore.dao.model;

import com.eurodyn.hr.petstore.dao.enums.DeliveryMethod;
import com.eurodyn.hr.petstore.dao.enums.PaymentMethod;
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
@Table(name = "sales")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "petstore.entity-cache")
public class Sale implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id; // Surrogate primary key
	UUID externalId; // Exposed resource ID
	ZonedDateTime date;
	String remarks;

	@ManyToOne @JoinColumn(name = "USER_ID")
	User buyer;
	@OneToOne @JoinColumn(name = "PET_ID")
	Pet pet;

	@Enumerated(EnumType.STRING)
	DeliveryMethod delivery;
	@Enumerated(EnumType.STRING)
	PaymentMethod payment;

	public Sale() {
		this.externalId = UUID.randomUUID();
	}
}
