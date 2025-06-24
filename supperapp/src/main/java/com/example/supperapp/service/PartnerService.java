package com.example.supperapp.service;

import com.example.supperapp.dto.PartnerDto;

import java.util.List;

public interface PartnerService {
    List<PartnerDto> getAllPartners();
    List<PartnerDto> searchByKeyword(String keyword);
}
