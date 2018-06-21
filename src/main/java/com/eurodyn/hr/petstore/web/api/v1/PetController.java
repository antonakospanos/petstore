package com.eurodyn.hr.petstore.web.api.v1;

import com.eurodyn.hr.petstore.service.PetService;
import com.eurodyn.hr.petstore.support.ControllerUtils;
import com.eurodyn.hr.petstore.web.api.BasePetStoreController;
import com.eurodyn.hr.petstore.web.dto.pets.PetBaseDto;
import com.eurodyn.hr.petstore.web.dto.pets.PetDto;
import com.eurodyn.hr.petstore.web.dto.response.CreateResponse;
import com.eurodyn.hr.petstore.web.dto.response.ResponseBase;
import com.eurodyn.hr.petstore.web.enums.Result;
import com.eurodyn.hr.petstore.web.support.SecurityHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Pet API", tags = "Pets", position = 1, description = "Pet Management")
@RequestMapping(value = "/pets")
public class PetController extends BasePetStoreController {

    private final static Logger logger = LoggerFactory.getLogger(PetController.class);

    @Autowired
    PetService service;

    @RequestMapping(value = "", produces = {"application/json"}, consumes = {"application/json"}, method = RequestMethod.POST)
    @ApiOperation(value = "Creates a new pet", response = CreateResponse.class)
    @ApiImplicitParam(
            name = "Authorization",
            value = "Bearer <The user's access token obtained upon registration or authentication>",
            example = "Bearer 6b6f2985-ae5b-46bc-bad1-f9176ab90171",
            required = true,
            dataType = "string",
            paramType = "header"
    )
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The pet is created!", response = ResponseBase.class),
            @ApiResponse(code = 400, message = "The request is invalid!"),
            @ApiResponse(code = 500, message = "server error")})
    public ResponseEntity<ResponseBase> create(@RequestHeader(value = "Authorization") String authorization, @Valid @RequestBody PetBaseDto petBaseDto) {
        UUID accessToken = SecurityHelper.getAccessToken(authorization);
        
        service.create(accessToken, petBaseDto);
        
        ResponseBase responseBase = ResponseBase.Builder().build(Result.SUCCESS);
        ResponseEntity<ResponseBase> response = ResponseEntity.status(HttpStatus.CREATED).body(responseBase);

        return response;
    }

    @ApiOperation(value = "Lists all available pets", response = PetDto.class, responseContainer = "List")
    @RequestMapping(value = "", produces = {"application/json"}, method = RequestMethod.GET)
    public ResponseEntity<Iterable> listAvailablePets() {
        List<PetDto> pets = service.list();
        ResponseEntity<Iterable> response = ControllerUtils.listResources(pets);

        return response;
    }
    
    @ApiOperation(value = "Lists the pet's information", response = PetDto.class, responseContainer = "List")
    @RequestMapping(value = "/{id}", produces = {"application/json"}, method = RequestMethod.GET)
    public ResponseEntity<PetDto> list(@PathVariable UUID petId) {
        PetDto pet = service.find(petId);
        ResponseEntity<PetDto> response = ControllerUtils.listResource(pet);
        
        return response;
    }
}
