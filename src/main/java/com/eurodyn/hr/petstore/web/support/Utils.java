package com.eurodyn.hr.petstore.web.support;

import java.util.UUID;

public class Utils {
	
	/**
	 * Converts the parsed String to UUID or throws an IllegalArgumentException
	 *
	 * @param uuid
	 * @return The transformed UUID
	 */
	public static UUID toUUID(String uuid) {
		UUID accessToken;
		try {
			accessToken = UUID.fromString(uuid);
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid UUID parameter: " + uuid);
		}
		return accessToken;
	}
	
}
