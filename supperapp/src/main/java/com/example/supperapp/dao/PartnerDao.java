package com.example.supperapp.dao;

import com.example.supperapp.dto.PartnerDto;

import java.util.List;

public interface PartnerDao {
    List<PartnerDto> findAll();
    List<PartnerDto> searchByKeyword(String keyword);
}
