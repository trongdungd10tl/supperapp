package com.example.supperapp.service.impl;

import com.example.supperapp.dao.ChannelDao;
import com.example.supperapp.dto.ChannelDto;
import com.example.supperapp.service.ChannelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelServiceImpl implements ChannelService {

    private final ChannelDao channelDao;

    public ChannelServiceImpl(ChannelDao channelDao) {
        this.channelDao = channelDao;
    }

    @Override
    public List<ChannelDto> getAllChannels() {
        return channelDao.findAll();
    }

    @Override
    public List<ChannelDto> searchByKeyword(String keyword) {
        return channelDao.searchByKeyword(keyword);
    }
}
