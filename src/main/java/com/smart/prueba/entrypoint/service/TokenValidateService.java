package com.smart.prueba.entrypoint.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.smart.prueba.infra.exception.CompanyServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TokenValidateService {

    @Value(value = "${companies.key-decript}")
    private String userKeyDecript;

    public void validateToken(String token, String session) {

        String tokenJWTId;
        DecodedJWT decodedJWT = null;

        try {
            decodedJWT = JWT.require(Algorithm.HMAC512(userKeyDecript.getBytes()))
                    .build().verify(token);
        } catch (Exception e) {
            throw new CompanyServiceException("SessionValidateService",
                    "invalid session argument", "invalid-request");
        }

        tokenJWTId = decodedJWT.getId().trim();
        if (session.equals(tokenJWTId)) {
            throw new CompanyServiceException("SessionValidateService",
                    "invalid session argument", "invalid session parameter");
        }

    }

}

