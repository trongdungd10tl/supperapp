package com.example.supperapp.dao.impl;

import com.example.supperapp.dao.ChannelDao;
import com.example.supperapp.dto.ChannelDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChannelDaoImpl implements ChannelDao {

    private final JdbcTemplate jdbcTemplate;

    public ChannelDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ChannelDto> findAll() {
        String sql = "SELECT id, name_channel FROM tbl_channel";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new ChannelDto(rs.getLong("id"), rs.getString("name_channel"))
        );
    }

    @Override
    public List<ChannelDto> searchByKeyword(String keyword) {
        String sql = "SELECT id, name_channel FROM tbl_channel WHERE name_channel LIKE ?";
        return jdbcTemplate.query(sql, new Object[]{"%" + keyword + "%"}, (rs, rowNum) ->
                new ChannelDto(rs.getLong("id"), rs.getString("name_channel"))
        );
    }
}
