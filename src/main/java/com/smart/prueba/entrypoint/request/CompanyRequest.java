package com.smart.prueba.entrypoint.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyRequest {
    @JsonProperty(value = "idExternal")
    @ApiModelProperty(example = "Dwer123NE123", notes = "Representa id externo")
    private String idExternal;


    @JsonProperty(value = "name", required = true)
    @NotNull(message = "name no puede ser nulo")
    @ApiModelProperty(example = "Dwer AC", notes = "Representa nombre de la organización")
    private String name;

    @JsonProperty(value = "address", required = true)
    @NotNull(message = "address no puede ser nulo")
    @ApiModelProperty(example = "xxxxxx #3", notes = "Representa la dirección de la orgaización")
    private String address;

    @JsonProperty(value = "telephone", required = true)
    @ApiModelProperty(example = "5544332211", notes = "Representa el número telefonico")
    @NotNull(message = "telephone no puede ser nulo")
    private String telephone;

    @JsonProperty(value = "encryptionCode", required = true)
    @NotNull(message = "encryptionCode no puede ser nulo")
    @ApiModelProperty(example = "qasd", notes = "Representa llave de encripción")
    private String encryptionCode;


    @Override
    public String toString() {

        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
