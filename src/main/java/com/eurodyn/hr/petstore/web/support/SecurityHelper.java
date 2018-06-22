package com.eurodyn.hr.petstore.web.support;

import com.eurodyn.hr.petstore.web.configuration.SecurityConfiguration;
import com.eurodyn.hr.petstore.web.security.exception.PetStoreAuthenticationException;
import com.eurodyn.hr.petstore.web.configuration.SecurityConfiguration;
import com.eurodyn.hr.petstore.web.security.exception.PetStoreAuthenticationException;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public class SecurityHelper {

    /**
     * Checks if is url protected.
     *
     * @param url the url
     * @return true, if is url protected
     */
    public static boolean isPublicApiUrl(String url) {
        boolean result = false;

        for (String pattern : SecurityConfiguration.getPublicApis()) {
            if (url.matches(pattern)) {
                result = true;
                break;
            }
        }

        return result;
    }

    /**
     * Gets the request url.
     *
     * @param request the request
     * @return the request url
     */
    public static String getRequestUrl(HttpServletRequest request) {
        String result = request.getServletPath();

        if (StringUtils.isBlank(result)) {
            result = request.getPathInfo();
        }

        return result;
    }

    /**
     * Parses the HTTP Authorization header and returns, if found, the user's access token
     *
     * @param authorizationHeader
     * @return The UUID access-token
     */
    public static UUID getAccessToken(String authorizationHeader) {
        String token = StringUtils.substringAfter(authorizationHeader, "Bearer ");
        return convertAccessToken(token);
    }

    /**
     * Converts the parsed access-token, from HTTP Authorization header, to UUID
     *
     * @param token
     * @return The UUID access-token
     */
    public static UUID convertAccessToken(String token) {
        UUID accessToken;
        try {
            accessToken = UUID.fromString(token);
        } catch (Exception e) {
            throw new PetStoreAuthenticationException("Invalid HTTP Authorization header Bearer: " + token);
        }
        return accessToken;
    }
}
