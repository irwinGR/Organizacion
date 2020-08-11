package com.smart.prueba.entrypoint.controller;

import com.smart.prueba.entrypoint.request.CompanyRequest;
import com.smart.prueba.entrypoint.request.EncryptionCodeRequest;
import com.smart.prueba.entrypoint.response.CompanyResponse;
import com.smart.prueba.entrypoint.response.ErrorResponse;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface CompanyContract {

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "retrieve the complete list of companies",
                    response = CompanyResponse.class),
            @ApiResponse(code = 400, message = "Bad request",
                    response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal server error",
                    response = ErrorResponse.class)})
    @RequestMapping(value = "/v1/companies",
            method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<Object> getCompanies(@ApiParam(name = "token", required = true,
            value = "Represents the encrypted service") String token,
                                        @ApiParam(name = "session", required = true,
                                                value = "Represents the session") String session);


    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "retrieve the company information",
                    response = CompanyResponse.class),
            @ApiResponse(code = 400, message = "Bad request",
                    response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal server error",
                    response = ErrorResponse.class)})
    @RequestMapping(value = "/v1/company/{companyId}",
            method = RequestMethod.POST,
            produces = "application/json")
    ResponseEntity<Object> getCompanyById(@ApiParam(name = "token", required = true,
            value = "Represents the encrypted service") String token,
                                          @ApiParam(name = "session", required = true,
                                                  value = "Represents the session") String session,
                                          String id);

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful registration",
                    response = CompanyResponse.class),
            @ApiResponse(code = 400, message = "Bad request",
                    response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal server error",
                    response = ErrorResponse.class)})
    @RequestMapping(value = "/v1/companies/register",
            method = RequestMethod.POST,
            produces = "application/json", consumes = "application/json")
    ResponseEntity<Object> addCompany(@ApiParam(name = "token", required = true,
            value = "Represents the encrypted service") String token,
                                      @ApiParam(name = "session", required = true,
                                              value = "Represents the session") String session,
                                      CompanyRequest request, Errors errors);

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful validate",
                    response = CompanyResponse.class),
            @ApiResponse(code = 400, message = "Bad request",
                    response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal server error",
                    response = ErrorResponse.class)})
    @RequestMapping(value = "/v1/companies/validate-id",
            method = RequestMethod.POST,
            produces = "application/json", consumes = "application/json")
    ResponseEntity<Object> validateId(@ApiParam(name = "token", required = true,
            value = "Represents the encrypted service") String token,
                                      @ApiParam(name = "session", required = true,
                                              value = "Represents the session") String session,
                                      EncryptionCodeRequest request, Errors errors);

}
