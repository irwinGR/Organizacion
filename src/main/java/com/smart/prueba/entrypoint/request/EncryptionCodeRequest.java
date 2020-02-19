package com.smart.prueba.entrypoint.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotNull;


@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class EncryptionCodeRequest {
    @JsonProperty(value = "idExternal")
    @NotNull(message = "idExternal no puede ser nulo")
    @ApiModelProperty(example = "Dwer123NE123", notes = "Representa id externo")
    private String idExternal;

    @JsonProperty(value = "name", required = true)
    @NotNull(message = "name no puede ser nulo")
    @ApiModelProperty(example = "Dwer AC", notes = "Representa nombre de la organización")
    private String name;

    @JsonProperty(value = "encryptionCode", required = true)
    @NotNull(message = "encryptionCode no puede ser nulo")
    @ApiModelProperty(example = "qasd", notes = "Representa llave de encripción")
    private String encryptionCode;

    @Override
    public String toString() {

        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}

