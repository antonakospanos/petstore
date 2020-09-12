package com.eurodyn.hr.petstore.web.dto.patch;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
public class PatchRequest {

	@ApiModelProperty(required = true)
	@Valid
	private List<PatchDto> patches;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
