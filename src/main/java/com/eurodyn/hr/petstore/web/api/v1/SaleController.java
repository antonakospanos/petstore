package com.eurodyn.hr.petstore.web.api.v1;

import com.eurodyn.hr.petstore.service.SaleService;
import com.eurodyn.hr.petstore.web.api.BasePetStoreController;
import com.eurodyn.hr.petstore.web.dto.sales.SaleDto;
import com.eurodyn.hr.petstore.web.dto.response.CreateResponse;
import com.eurodyn.hr.petstore.web.dto.response.CreateResponseData;
import com.eurodyn.hr.petstore.web.dto.users.UserDto;
import com.eurodyn.hr.petstore.web.enums.Result;
import com.eurodyn.hr.petstore.web.support.SecurityHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.UUID;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@Api(value = "Sale API", tags = "Sales", position = 3, description = "Sales Management")
@RequestMapping(value = "/sales")
public class SaleController extends BasePetStoreController {
	
	private final static Logger logger = LoggerFactory.getLogger(SaleController.class);
	
	@Autowired
	SaleService service;
	
	@RequestMapping(value = "/{id}", produces = {"application/json"}, consumes = {
			"application/json"}, method = RequestMethod.POST)
	@ApiOperation(value = "Creates a new sale", response = CreateResponse.class)
	@ApiImplicitParam(
			name = "Authorization",
			value = "Bearer <The user's access token obtained upon registration or authentication>",
			example = "Bearer 6b6f2985-ae5b-46bc-bad1-f9176ab90171",
			required = true,
			dataType = "string",
			paramType = "header"
	)
	@ResponseStatus(HttpStatus.OK)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "The sale request is accepted!", response = CreateResponse.class),
			@ApiResponse(code = 400, message = "The request is invalid!"),
			@ApiResponse(code = 500, message = "server error")})
	public ResponseEntity<CreateResponse> create(UriComponentsBuilder uriBuilder,
			@RequestHeader(value = "Authorization") String authorization,
			@Valid SaleDto saleDto) {
		UUID accessToken = SecurityHelper.getAccessToken(authorization);
		
		CreateResponseData data = service.create(accessToken, saleDto);
		
		UriComponents uriComponents = uriBuilder.path("/sales/{id}").buildAndExpand(data.getId());
		CreateResponse responseBase = CreateResponse.Builder().build(Result.SUCCESS).data(data);
		ResponseEntity<CreateResponse> response = ResponseEntity.created(uriComponents.toUri())
				.body(responseBase);
		
		return response;
	}
	
	@ApiOperation(value = "Lists the sale's information", response = UserDto.class)
	@RequestMapping(value = "", produces = {"application/json"}, method = RequestMethod.GET)
	@ApiImplicitParam(
			name = "Authorization",
			value = "Bearer <The user's access token obtained upon registration or authentication>",
			example = "Bearer 6b6f2985-ae5b-46bc-bad1-f9176ab90171",
			required = true,
			dataType = "string",
			paramType = "header"
	)
	public ResponseEntity<SaleDto> list(@RequestParam UUID id) {
		SaleDto saleDto = service.list(id);
		ResponseEntity response = ResponseEntity.status(HttpStatus.OK).body(saleDto);
		
		return response;
	}
}
