package com.smart.prueba.entrypoint.formatter;

import com.smart.prueba.entrypoint.request.CompanyRequest;
import com.smart.prueba.entrypoint.response.CompanyResponse;
import com.smart.prueba.infra.entities.OrganizacionBean;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class CompanyFormatter {

    public CompanyResponse format(final List<OrganizacionBean> organizacionBean) {
        final CompanyResponse companyResponse = new CompanyResponse();
        if (organizacionBean != null && !organizacionBean.isEmpty()) {
            companyResponse.setCompanys(organizacionBean);
            companyResponse.setMessage("OK");
        } else {
            companyResponse.setCompanys(Collections.emptyList());
            companyResponse.setMessage("No existen registros");
        }

        return companyResponse;
    }

    public OrganizacionBean formatOrganizacion(CompanyRequest companyRequest) {
        final OrganizacionBean organizacionBean = new OrganizacionBean();
        organizacionBean.setIdExterno(companyRequest.getIdExternal());
        organizacionBean.setNombre(companyRequest.getName());
        organizacionBean.setDireccion(companyRequest.getAddress());
        organizacionBean.setTelefono(companyRequest.getTelephone());
        organizacionBean.setCodigoEncriptacion(companyRequest.getEncryptionCode());

        return organizacionBean;
    }

}

