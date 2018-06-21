package com.eurodyn.hr.petstore.dao.repository;

import com.eurodyn.hr.petstore.dao.model.Pet;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {

	Pet findByName(String name);
	Pet findByExternalId(UUID externalId);
}