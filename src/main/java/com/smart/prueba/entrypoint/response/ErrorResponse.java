package com.smart.prueba.entrypoint.response;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@Setter
public class ErrorResponse {

    private final String type;

    private final String code;

    private final String details;


    public ErrorResponse(String type, String code, String details) {

        super();
        this.type = type;
        this.code = code;
        this.details = details;
    }


    @Override
    public String toString() {

        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}