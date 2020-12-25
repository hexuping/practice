package com.example.core.entity.dto;

import com.example.core.anno.ExcelResource;
import lombok.Data;

@Data
public class UserExportDTO {

    @ExcelResource("姓名")
    private String name;

    @ExcelResource("手机号")
    private String phone;
}
