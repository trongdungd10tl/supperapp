package com.example.supperapp.dao.impl;

import com.example.supperapp.dao.PartnerDao;
import com.example.supperapp.dto.PartnerDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PartnerDaoImpl implements PartnerDao {


    private final JdbcTemplate jdbcTemplate;

    public PartnerDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<PartnerDto> findAll() {
        String sql = "SELECT id, name FROM tbl_partner_v2";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            PartnerDto partner = new PartnerDto();
            partner.setId(rs.getLong("id"));
            partner.setPartnerId(rs.getString("name"));
            return partner;
        });
    }

    @Override
    public List<PartnerDto> searchByKeyword(String keyword) {
        String sql = "SELECT id, name FROM tbl_partner_v2 WHERE name LIKE ?";
        return jdbcTemplate.query(sql, new Object[]{"%" + keyword + "%"}, (rs, rowNum) -> {
            PartnerDto partner = new PartnerDto();
            partner.setId(rs.getLong("id"));
            partner.setPartnerId(rs.getString("name"));
            return partner;
        });
    }


}
