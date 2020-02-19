package com.smart.prueba.entrypoint.service;

import com.smart.prueba.entrypoint.dao.CompanyDao;
import com.smart.prueba.entrypoint.formatter.CompanyFormatter;
import com.smart.prueba.entrypoint.request.CompanyRequest;
import com.smart.prueba.entrypoint.request.EncryptionCodeRequest;
import com.smart.prueba.entrypoint.response.CompanyResponse;
import com.smart.prueba.entrypoint.response.ErrorResponse;
import com.smart.prueba.infra.entities.OrganizacionBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyDao repository;

    @Autowired
    private CompanyFormatter companyFormatter;

    public CompanyResponse retriveCompanies(String id) {
        List<OrganizacionBean> organizacioness = new ArrayList<OrganizacionBean>();
        OrganizacionBean item = new OrganizacionBean();
        item.setNombre("nombre1");
        item.setDireccion("dir1");
        item.setCodigoEncriptacion("codigo1");
        item.setIdExterno("externo1");
        organizacioness.add(item);
        OrganizacionBean item2 = new OrganizacionBean();
        item2.setNombre("nombre2");
        item2.setDireccion("dir2");
        item2.setCodigoEncriptacion("codigo2");
        item2.setIdExterno("externo2");
        organizacioness.add(item2);
        return companyFormatter.format(organizacioness);


        //return companyFormatter.format(repository.query(id));

    }

    public void addCompany(final CompanyRequest request) throws SQLException {
        String nombre = request.getName().replaceAll("\\s", "");
        int sizeTel = request.getTelephone().length();
        StringBuilder idExterno = new StringBuilder();
        idExterno.append(nombre.trim().substring(0, 4));
        idExterno.append(request.getTelephone().substring(sizeTel - 4));
        idExterno.append("NE");
        idExterno.append(repository.retrieveConsecutiveId());

        request.setIdExternal(idExterno.toString());

        repository.insert(companyFormatter.formatOrganizacion(request));
    }

    public void verifyEncryptionCode(EncryptionCodeRequest request) {
        CompanyResponse response = companyFormatter.format(repository.query(request.getIdExternal()));
        List<OrganizacionBean> validarte = new ArrayList<OrganizacionBean>();
        validarte = response.getCompanys();

        if (validarte.isEmpty()) {
            new ErrorResponse("Bad Request", "400", "no existe el registro");
        }
        OrganizacionBean code = validarte.get(0);
        if (StringUtils.compareIgnoreCase(code.getCodigoEncriptacion(), request.getEncryptionCode()) != 0) {
            new ErrorResponse("Bad Request", "400", "el codigo no es igual");
        }

    }


}

