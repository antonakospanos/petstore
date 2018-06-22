package com.eurodyn.hr.petstore.service;

import com.eurodyn.hr.petstore.dao.model.Pet;
import com.eurodyn.hr.petstore.dao.repository.PetRepository;
import com.eurodyn.hr.petstore.web.dto.pets.PetBaseDto;
import com.eurodyn.hr.petstore.web.dto.pets.PetDto;
import com.eurodyn.hr.petstore.web.dto.response.CreateResponseData;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {
	
	private final static Logger logger = LoggerFactory.getLogger(PetService.class);
	
	@Autowired
	PetRepository petRepository;
	
	@Autowired
	UserService userService;
	
	@Transactional
	public CreateResponseData create(PetBaseDto petBaseDto) {
		PetDto petDto = new PetDto(petBaseDto);
		Pet pet = petRepository.findByName(petDto.getName());
		
		if (pet != null) {
			throw new IllegalArgumentException("Pet '" + pet.getName() + "' already exists!");
		} else {
			// Add new Pet in DB
			pet = petDto.toEntity();
			petRepository.save(pet);
			
			logger.info("New Pet added: " + pet);
		}
		
		return new CreateResponseData(pet.getExternalId().toString());
	}
	
	@Transactional
	public PetDto find(UUID petId) {
		Pet pet = petRepository.findByExternalId(petId);
		
		return new PetDto().fromEntity(pet);
	}
	
	/**
	 * Fetches all available pets
	 */
	@Transactional
	public List<PetDto> list() {
		return petRepository.findAll()
				.stream()
				.filter(pet -> pet.getSale() == null)
				.map(pet -> new PetDto().fromEntity(pet))
				.collect(Collectors.toList());
	}
}