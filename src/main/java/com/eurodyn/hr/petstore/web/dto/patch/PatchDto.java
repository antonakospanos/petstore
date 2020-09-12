package com.eurodyn.hr.petstore.web.dto.patch;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotNull;

/**
 * https://tools.ietf.org/html/draft-ietf-appsawg-json-patch-10#section-4.1
 */
@Data
public class PatchDto {

	@NotNull
	@JsonProperty("operation")
	@ApiModelProperty(example = "replace", allowableValues = "add, replace, remove", required = true)
	private PatchOperation operation;

	@NotNull
	@ApiModelProperty(example = "name", required = true)
	private String field;

	@ApiModelProperty(example = "Panos Antonakos")
	private String value;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
