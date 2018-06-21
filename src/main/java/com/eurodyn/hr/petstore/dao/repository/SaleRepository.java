package com.eurodyn.hr.petstore.dao.repository;

import com.eurodyn.hr.petstore.dao.model.Sale;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	
	Sale findByExternalId(UUID externalId);
}