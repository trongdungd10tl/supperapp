package com.example.supperapp.controller.partner;

import com.example.supperapp.dto.PartnerDto;
import com.example.supperapp.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/user")
public class PartnerController {

    @Autowired
    private PartnerService partnerService;

    @GetMapping("/create/ajax-partner-search")
    public List<Map<String, Object>> searchPartner(@RequestParam(value = "input_", required = false) String keyword) {
        List<PartnerDto> partners = (keyword == null || keyword.isBlank())
                ? partnerService.getAllPartners()
                : partnerService.searchByKeyword(keyword);

        return partners.stream().map(p -> {
            Map<String, Object> m = new HashMap<>();
            m.put("id", p.getId());
            m.put("text", p.getPartnerId()); // Select2 yêu cầu key là 'text'
            return m;
        }).collect(Collectors.toList());
    }
}
