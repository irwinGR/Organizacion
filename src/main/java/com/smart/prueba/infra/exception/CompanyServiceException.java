package com.smart.prueba.infra.exception;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class CompanyServiceException extends RuntimeException implements Serializable {


    private final String uuid;


    private final String date;


    private final String type;


    private final HttpStatus code;


    private final String moreInfo;


    private final static String DATE_FORMAT = "yyyy-MM-dd";


    public CompanyServiceException(String message) {

        super(message);
        this.uuid = UUID.randomUUID().toString();
        this.date =
                LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT))
                        + LocalTime.now().toString();
        this.type = "invalid-request";
        this.code = HttpStatus.BAD_REQUEST;
        this.moreInfo = "invalid-request";
    }


    public CompanyServiceException(HttpStatus code, String type, String message,
                                   String moreInfo) {

        super(message);
        this.uuid = UUID.randomUUID().toString();
        this.date =
                LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT))
                        + LocalTime.now().toString();
        this.type = type;
        this.code = code;
        this.moreInfo = moreInfo;
    }

    /**
     * Instantiates a new resource not found exception.
     *
     * @param message the message
     */
    public CompanyServiceException(String type, String message,
                                   String moreInfo) {

        super(message);
        this.uuid = UUID.randomUUID().toString();
        this.date =
                LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT))
                        + LocalTime.now().toString();
        this.type = type;
        this.code = HttpStatus.BAD_REQUEST;
        this.moreInfo = moreInfo;
    }


    public String getUuid() {
        return uuid;
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public HttpStatus getCode() {
        return code;
    }

    public String getMoreInfo() {
        return moreInfo;
    }
}

