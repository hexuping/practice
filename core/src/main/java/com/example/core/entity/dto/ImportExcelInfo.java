package com.example.core.entity.dto;

import lombok.Data;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * 导入Excel参数
 * @param <T>
 */
@Data
public class ImportExcelInfo<T> {

    // 输入流
    InputStream stream;

    // 返回参数类型
    Class<T> returnType;

    // 读取Excel指定名称的sheet,优先
    String sheetName;

    // 读取Excel指定sheet
    Integer sheetIndex = 0;

}
