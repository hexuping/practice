package com.example.core.entity.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ExcelTemplate {

    // 表头
    private String[] header;

    // 表数据列表
    private List<Map<String, Object>> list;

}
