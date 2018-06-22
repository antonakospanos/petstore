package com.eurodyn.hr.petstore.service;

import com.eurodyn.hr.petstore.dao.model.Pet;
import com.eurodyn.hr.petstore.dao.model.Sale;
import com.eurodyn.hr.petstore.dao.model.User;
import com.eurodyn.hr.petstore.dao.repository.PetRepository;
import com.eurodyn.hr.petstore.dao.repository.SaleRepository;
import com.eurodyn.hr.petstore.dao.repository.UserRepository;
import com.eurodyn.hr.petstore.web.dto.response.CreateResponseData;
import com.eurodyn.hr.petstore.web.dto.sales.SaleDto;
import java.util.UUID;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleService {
	
	private final static Logger logger = LoggerFactory.getLogger(SaleService.class);
	
	@Autowired
	PetRepository petRepository;
	
	@Autowired
	SaleRepository saleRepository;
	
	@Autowired
	UserRepository userRepository;
	
	
	@Transactional
	public CreateResponseData create(UUID userId, SaleDto saleDto) {
		User user = userRepository.findByExternalId(userId);
		Pet pet = petRepository.findByExternalId(saleDto.getPet());
		Sale sale;
		
		if (user == null) {
			throw new IllegalArgumentException("User with ID '" + userId + "' does not exist!");
		} else if (pet == null) {
			throw new IllegalArgumentException("Pet with ID '" + saleDto.getPet() + "' does not exist!");
		} else if (pet.getSale() != null) {
			throw new IllegalArgumentException("Pet with name '" + pet.getName() + "' is already bought!");
		} else {
			// Add new Sale in DB
			sale = saleDto.toEntity();
			sale.setBuyer(user);
			sale.setPet(pet);
			saleRepository.save(sale);
			
			logger.info("New Sale transaction added: " + sale);
		}
		
		return new CreateResponseData(sale.getExternalId().toString());
	}
	
	@Transactional
	public SaleDto list(UUID saleId) {
		Sale sale = saleRepository.findByExternalId(saleId);
		
		if (sale == null) {
			throw new IllegalArgumentException("Sale transaction with ID '" + saleId + "' does not exist!");
		}
		
		return new SaleDto().fromEntity(sale);
	}
}
