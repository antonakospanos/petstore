package com.eurodyn.hr.petstore.dao.model;


import com.eurodyn.hr.petstore.dao.enums.DeliveryMethod;
import com.eurodyn.hr.petstore.dao.enums.PaymentMethod;
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
@Table(name = "SALE")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "petstore.entity-cache")
public class Sale implements Serializable {
	
	// Surrogate primary key
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// Exposed resource ID
	private UUID externalId;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User buyer;
	
	@OneToOne
	@JoinColumn(name = "PET_ID")
	private Pet pet;
	
	private String remarks;
	
	private DeliveryMethod delivery;
	
	private PaymentMethod payment;
	
	private ZonedDateTime date;
	
	
	public Sale() {
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
	
	public User getBuyer() {
		return buyer;
	}
	
	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}
	
	public Pet getPet() {
		return pet;
	}
	
	public void setPet(Pet pet) {
		this.pet = pet;
	}
	
	public String getRemarks() {
		return remarks;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public PaymentMethod getPayment() {
		return payment;
	}
	
	public void setPayment(PaymentMethod payment) {
		this.payment = payment;
	}
	
	public ZonedDateTime getDate() {
		return date;
	}
	
	public void setDate(ZonedDateTime date) {
		this.date = date;
	}
	
	public DeliveryMethod getDelivery() {
		return delivery;
	}
	
	public void setDelivery(DeliveryMethod delivery) {
		this.delivery = delivery;
	}
}
