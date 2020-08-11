package com.smart.prueba.entrypoint.controller;

import com.smart.prueba.entrypoint.request.CompanyRequest;
import com.smart.prueba.entrypoint.request.EncryptionCodeRequest;
import com.smart.prueba.entrypoint.response.ErrorResponse;
import com.smart.prueba.entrypoint.service.CompanyService;
import com.smart.prueba.entrypoint.service.TokenValidateService;
import com.smart.prueba.entrypoint.validation.ValidationError;
import com.smart.prueba.infra.exception.CompanyServiceException;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;

@RestController
@RequestMapping("/api")
@Api(description = "Company Service")
@Slf4j
public class CompanySystem implements CompanyContract {

    @Autowired
    private TokenValidateService tokenValidateService;

    @Autowired
    private CompanyService service;

    @Override
    public ResponseEntity<Object> getCompanies(@RequestHeader(value = "token", required = true) String token,
                                               @RequestHeader(value = "session", required = true) String session) {
        log.info("headers {} {}", token, session);

        try {
            tokenValidateService.validateToken(token, session);
        } catch (CompanyServiceException e) {
            return new ResponseEntity<>(
                    new ErrorResponse("Bad Request", "400", "invalid session"),
                    HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.retriveCompanies(null));
    }

    @Override
    public ResponseEntity<Object> getCompanyById(@RequestHeader(value = "token", required = true) String token,
                                                 @RequestHeader(value = "session", required = true) String session,
                                                 @PathVariable(value = "companyId") String id) {
        log.info("CompanyById {} ", id);
        try {
            tokenValidateService.validateToken(token, session);
        } catch (CompanyServiceException e) {
            return new ResponseEntity<>(
                    new ErrorResponse("Bad Request", "400", "invalid session"),
                    HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(service.retriveCompanies(id));
    }

    @Override
    public ResponseEntity<Object> addCompany(@RequestHeader(value = "token", required = true) String token,
                                             @RequestHeader(value = "session", required = true) String session,
                                             @RequestBody @Valid CompanyRequest request, Errors errors) {

        log.info("Company {} ", request);

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationError.fromBindingErrors(errors));
        }


        try {
            tokenValidateService.validateToken(token, session);
        } catch (CompanyServiceException e) {
            return new ResponseEntity<>(
                    new ErrorResponse("Bad Request", "400", "invalid session"),
                    HttpStatus.BAD_REQUEST);
        }

        try {
            service.addCompany(request);
        } catch (SQLException e) {
            return new ResponseEntity<>(
                    new ErrorResponse("INTERNAL_SERVER_ERROR", "500", e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return ResponseEntity.ok("OK");


    }

    @Override
    public ResponseEntity<Object> validateId(@RequestHeader(value = "token", required = true) String token,
                                             @RequestHeader(value = "session", required = true) String session,
                                             @RequestBody @Valid EncryptionCodeRequest request, Errors errors) {


        log.info("ValidateId {} ", request);

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationError.fromBindingErrors(errors));
        }


        try {
            tokenValidateService.validateToken(token, session);
            service.verifyEncryptionCode(request);
        } catch (CompanyServiceException e) {
            return new ResponseEntity<>(
                    new ErrorResponse("Bad Request", "400", e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }


        return ResponseEntity.ok("El codigo es valido");

    }
}

