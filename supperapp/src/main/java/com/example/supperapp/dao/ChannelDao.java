package com.example.supperapp.dao;

import com.example.supperapp.dto.ChannelDto;
import java.util.List;

public interface ChannelDao {
    List<ChannelDto> findAll();
    List<ChannelDto> searchByKeyword(String keyword);
}
