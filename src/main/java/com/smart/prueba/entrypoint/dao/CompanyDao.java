package com.smart.prueba.entrypoint.dao;

import com.smart.prueba.infra.entities.OrganizacionBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

@Repository
public class CompanyDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List query(String id) {

        String sqlQuery =
                "SELECT IDEXTERNO,NOMBRE,DIRECCION,CODIGO from SMART.ORGANIZACION WHERE IDEXTERNO = nvl(? , IDEXTERNO);";
        return jdbcTemplate.query(sqlQuery, new Object[]{id},
                new RowMapper<OrganizacionBean>() {

                    @Override
                    public OrganizacionBean mapRow(ResultSet rs, int i) throws SQLException {

                        OrganizacionBean item = new OrganizacionBean();
                        item.setIdExterno(rs.getString("IDEXTERNO"));
                        item.setNombre(rs.getString("NOMBRE"));
                        item.setDireccion(rs.getString("DIRECCION"));
                        item.setCodigoEncriptacion(rs.getString("CODIGO"));
                        return item;
                    }
                });
    }

    public void insert(OrganizacionBean paramInput) throws SQLException {
        String sqlQuery =
                "insert into SMART.ORGANIZACION(IDEXTERNO,NOMBRE,DIRECCION,CODIGO) values(?,?,?,?);";
        jdbcTemplate.update(sqlQuery, new Object[]{paramInput.getIdExterno(), paramInput.getNombre(),
                paramInput.getDireccion(), paramInput.getCodigoEncriptacion()});
    }

    public String retrieveConsecutiveId() {
        String sqlQuery =
                "select SUBSTR(IDEXTERNO, LENGTH(IDEXTERNO) - 3 ) as ID from (select IDEXTERNO from SMART.ORGANIZACION \n" +
                        "order by IDEXTERNO desc )\n" +
                        "where rownum = 1";

        String id = jdbcTemplate.queryForList(sqlQuery).get(0).toString();
        Integer consecutivo = Integer.parseInt(id) + 1;

        return consecutivo.toString();

    }
}

