package com.example.core.util;

import com.example.core.anno.ExcelResource;
import com.example.core.entity.dto.ImportExcelInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Required;

import java.io.*;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class ExcelUtil {

    /**
     * 导入Excel获取导入对象
     * @param importExcelInfo
     * @param <T>
     * @return
     */
    public static <T> List<T> importExcel(ImportExcelInfo<T> importExcelInfo) {
        try {
            POIFSFileSystem poifsFileSystem = new POIFSFileSystem(importExcelInfo.getStream());
            HSSFWorkbook workbook = new HSSFWorkbook(poifsFileSystem);
            HSSFSheet sheet;
            if(StringUtils.isNotBlank(importExcelInfo.getSheetName())) {
                sheet = workbook.getSheet(importExcelInfo.getSheetName());
            } else {
                sheet = workbook.getSheetAt(importExcelInfo.getSheetIndex());
            }
            if(sheet == null) {
                throw new RuntimeException("sheet不存在");
            }
            return readExcel(sheet, importExcelInfo.getReturnType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读取Excel
     * @param sheet
     * @param returnType
     * @param <T>
     * @return
     */
    public static <T> List<T>  readExcel(HSSFSheet sheet, Class<T> returnType) {
        List<T> list = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            HSSFRow row = sheet.getRow(i);
            // 第0行为表头
            if (i == 0) {
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    map.put(row.getCell(j).getStringCellValue(), j);
                }
                continue;
            }
            try {
                T returnObj = returnType.newInstance();
                Field[] declaredFields = returnType.getDeclaredFields();
                Arrays.asList(declaredFields).stream().forEach(field -> {
                    if (field.isAnnotationPresent(ExcelResource.class)) {
                        ExcelResource annotation = field.getAnnotation(ExcelResource.class);
                        String fieldName = StringUtils.isNotBlank(annotation.name()) ? annotation.name() : annotation.value();
                        field.setAccessible(true);
                        try {
                            field.set(returnObj, row.getCell(map.get(fieldName)).getStringCellValue());
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                });
                list.add(returnObj);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * 导出excel
     * @param dataList
     * @param sheetName
     * @param <T>
     */
    public static <T> void exportExcel(List<T> dataList, String sheetName, Class<T> clazz) {
        // 1.创建工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 2.新建一个sheet
        HSSFSheet sheet = workbook.createSheet(sheetName);
        // 3.添加表头
        AtomicInteger rowIndex = new AtomicInteger();
        HSSFRow header = sheet.createRow(rowIndex.get());
        // 4.创建单元格,并设置表头,设置表头居中
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        // 获取表头类容
        String[] templateHeaders = getTemplateHeaders(clazz);
        // 创建表头
        for (int i = 0; i < templateHeaders.length; i++) {
            HSSFCell cell = header.createCell(i);
            System.out.println(templateHeaders[i]);
            cell.setCellValue(templateHeaders[i]);
            cell.setCellStyle(cellStyle);
        }
        // 创建类容
        List<Map<String, Object>> templateData = getTemplateData(dataList);
        IntStream.range(0, templateData.size()).forEach(index -> {
            HSSFRow row = sheet.createRow(index + 1);
            Map<String, Object> data = templateData.get(index);
            for (int i = 0; i < data.size(); i++) {
                row.createCell(i).setCellValue((String)data.get(header.getCell(i).getStringCellValue()));
            }
        });

        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("C:\\Users\\sundear\\Desktop\\" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).toString() + ".xlsx");
            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取表头数组
     * @param dataClass
     * @param <?>
     * @return
     */
    private static String[] getTemplateHeaders(Class<?> dataClass) {
        StringBuffer stringBuffer = new StringBuffer();
        Field[] declaredFields = dataClass.getDeclaredFields();
        Arrays.asList(declaredFields).forEach(item -> {
            if(item.isAnnotationPresent(ExcelResource.class)) {
                // 获取注解
                ExcelResource annotation = item.getAnnotation(ExcelResource.class);
                String fieldName = StringUtils.isNotBlank(annotation.name()) ? annotation.name() : annotation.value();
                stringBuffer.append(fieldName + ",");
            }
        });
        return stringBuffer.toString().split(",");
    }

    /**
     * 获取Excel模板数据
     * @param dataList
     * @param <T>
     * @return
     */
    private static <T> List<Map<String, Object>> getTemplateData(List<T> dataList) {
        List<Map<String, Object>> list = new ArrayList<>();
        dataList.stream().forEach(excelTemplate -> {
            Map<String, Object> map = new HashMap<>();
            Field[] declaredFields = excelTemplate.getClass().getDeclaredFields();
            Arrays.asList(declaredFields).stream().forEach(item -> {
                if(item.isAnnotationPresent(ExcelResource.class)) {
                    // 获取注解
                    ExcelResource annotation = item.getAnnotation(ExcelResource.class);
                    try {
                        // 打开私有属性访问权限
                        item.setAccessible(true);
                        String fieldName = StringUtils.isNotBlank(annotation.name()) ? annotation.name() : annotation.value();
                        map.put(fieldName, item.get(excelTemplate));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            });
            list.add(map);
        });
        return list;
    }
}
