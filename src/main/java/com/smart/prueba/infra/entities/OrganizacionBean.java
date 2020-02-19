package com.smart.prueba.infra.entities;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class OrganizacionBean implements Serializable {
    private String idExterno;
    private String nombre;
    private String direccion;
    private String telefono;
    private String codigoEncriptacion;

}

