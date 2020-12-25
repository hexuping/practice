package com.example.core.controller;

import com.example.core.entity.dto.ImportExcelInfo;
import com.example.core.entity.dto.UserExportDTO;
import com.example.core.util.ExcelUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/export")
    public void testExport(@RequestBody List<UserExportDTO> list) {
        ExcelUtil.exportExcel(list, "testExport", UserExportDTO.class);
    }

    @RequestMapping("/import")
    public List<UserExportDTO> testImport(@RequestParam("file") MultipartFile file) throws IOException {
        ImportExcelInfo<UserExportDTO> importExcelInfo = new ImportExcelInfo<>();
        importExcelInfo.setStream(file.getInputStream());
        importExcelInfo.setReturnType(UserExportDTO.class);
        importExcelInfo.setSheetName("testImport");
        return ExcelUtil.importExcel(importExcelInfo);
    }
}
