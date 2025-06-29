package com.example.supperapp.service;

import com.example.supperapp.dto.ChannelDto;
import java.util.List;

public interface ChannelService {
    List<ChannelDto> getAllChannels();
    List<ChannelDto> searchByKeyword(String keyword);
}
