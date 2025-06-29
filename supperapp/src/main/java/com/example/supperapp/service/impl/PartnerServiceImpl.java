package com.example.supperapp.service.impl;

import com.example.supperapp.dao.PartnerDao;
import com.example.supperapp.dto.PartnerDto;
import com.example.supperapp.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PartnerServiceImpl implements PartnerService {
    @Autowired
    private PartnerDao partnerDao;

    @Override
    public List<PartnerDto> getAllPartners() {
        return partnerDao.findAll();
    }

    public List<PartnerDto> searchByKeyword(String keyword){
        return partnerDao.searchByKeyword(keyword);
    }
}
