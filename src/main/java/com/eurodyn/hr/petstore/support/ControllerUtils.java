package com.eurodyn.hr.petstore.support;

import com.eurodyn.hr.petstore.web.dto.Dto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 *  Utilities used by petstore' Controllers
 */
public class ControllerUtils {
	
	public static <T extends Dto> ResponseEntity<T> listResource(T resource) {
		ResponseEntity<T> response;
		
		if (resource != null) {
			response = ResponseEntity.status(HttpStatus.OK).body(resource);
		} else {
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return response;
	}

	public static <T extends Dto> ResponseEntity<T> listResource(List<T> resources) {
		ResponseEntity<T> response;

		if (resources != null  && !resources.isEmpty()) {
			response = ResponseEntity.status(HttpStatus.OK).body(resources.get(0));
		} else {
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return response;
	}

	public static <T extends Dto> ResponseEntity<Iterable> listResources(List<T> resources) {
		ResponseEntity<Iterable> response;

		if (resources != null && !resources.isEmpty()) {
			response = ResponseEntity.status(HttpStatus.OK).body(resources);
		} else {
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(resources);
		}

		return response;
	}
}
