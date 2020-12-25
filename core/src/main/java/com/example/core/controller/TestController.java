package com.example.core.controller;

import com.example.core.anno.SetUserInfo;
import com.example.core.entity.dto.UserExportDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
public class TestController {

    @SetUserInfo
    @PostMapping("/first")
    public UserExportDTO test(UserExportDTO userExportDTO) {
        return userExportDTO;
    }
}
