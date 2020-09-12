package com.eurodyn.hr.petstore.web.dto.sales;

import com.eurodyn.hr.petstore.dao.enums.DeliveryMethod;
import com.eurodyn.hr.petstore.dao.enums.PaymentMethod;
import com.eurodyn.hr.petstore.dao.model.Sale;
import com.eurodyn.hr.petstore.web.dto.Dto;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import java.time.ZonedDateTime;
import java.util.UUID;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.stereotype.Component;

@Getter
@Setter
@JsonPropertyOrder({ "pet", "payment", "delivery", "remarks" })
public class SaleDto implements Dto<Sale> {

	@NotNull
	@ApiModelProperty(example = "6b6f2985-ae5b-46bc-bad1-f9176ab90171", required = true)
	private UUID pet;

	@NotNull
	@ApiModelProperty(example = "CASH", required = true)
	private PaymentMethod payment;

	@NotNull
	@ApiModelProperty(example = "SHIPPING", required = true)
	private DeliveryMethod delivery;

	@ApiModelProperty(example = "Please call me on my cellphone")
	private String remarks;

	public SaleDto() {
	}

	@Override
	public SaleDto fromEntity(Sale sale) {
		setPet(sale.getPet().getExternalId());
		setDelivery(sale.getDelivery());
		setPayment(sale.getPayment());
		setRemarks(sale.getRemarks());

		return this;
	}

	@Override
	public Sale toEntity() {
		return toEntity(new Sale());
	}

	@Override
	public Sale toEntity(Sale sale) {
		sale.setDelivery(this.getDelivery());
		sale.setPayment(this.getPayment());
		sale.setRemarks(this.getRemarks());
		sale.setDate(ZonedDateTime.now());
		// Pet && Buyer are added in the service layer

		return sale;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
