package com.example.supperapp.controller.channel;

import com.example.supperapp.dto.ChannelDto;
import com.example.supperapp.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/user")
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    @GetMapping("/create/ajax-channel-search")
    public List<Map<String, Object>> searchChannel(@RequestParam(value = "input_", required = false) String keyword) {
        List<ChannelDto> channels = (keyword == null || keyword.isBlank())
                ? channelService.getAllChannels()
                : channelService.searchByKeyword(keyword);

        return channels.stream().map(c -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", c.getId());
            map.put("text", c.getNameChannel());
            return map;
        }).collect(Collectors.toList());
    }
}
