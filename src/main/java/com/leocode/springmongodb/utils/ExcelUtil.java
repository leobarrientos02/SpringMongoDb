package com.leocode.springmongodb.utils;

import com.leocode.springmongodb.exceptions.NotFoundException;
import com.leocode.springmongodb.fighter.Biography;
import com.leocode.springmongodb.fighter.Fighter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class ExcelUtil {

    private static final Logger log = LoggerFactory.getLogger(ExcelUtil.class);
    public Fighter getFighterDataFromExcel(String name) throws IOException {
        String fileLocation = "python_webscraper/fighters.xlsx";
        FileInputStream file = new FileInputStream(fileLocation);
        Workbook workbook = new XSSFWorkbook(file);

        Sheet sheet = workbook.getSheetAt(0);
        String fighter_name = sheet.getRow(0).getCell(0).getStringCellValue();
        if(fighter_name.equals(name)){
            String nickname = sheet.getRow(0).getCell(1).getStringCellValue();
            String weightclass = sheet.getRow(0).getCell(2).getStringCellValue();
            String record = sheet.getRow(0).getCell(3).getStringCellValue();

            String status = sheet.getRow(0).getCell(4).getStringCellValue();
            String birthplace = sheet.getRow(0).getCell(5).getStringCellValue();
            String training = sheet.getRow(0).getCell(6).getStringCellValue();
            double age = sheet.getRow(0).getCell(7).getNumericCellValue();
            Double height = sheet.getRow(0).getCell(8).getNumericCellValue();
            Double weight = sheet.getRow(0).getCell(9).getNumericCellValue();
            Date dateCellValue = sheet.getRow(0).getCell(10).getDateCellValue();
            LocalDate debut = dateCellValue.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Double arm_reach = sheet.getRow(0).getCell(11).getNumericCellValue();
            Double leg_reach = sheet.getRow(0).getCell(12).getNumericCellValue();
            Biography biography = new Biography(status, birthplace, (int)age, training, debut, height, weight, arm_reach, leg_reach);
            String image = sheet.getRow(0).getCell(13).getStringCellValue();
            Fighter fighter = new Fighter(fighter_name, nickname, weightclass, record, biography, image);
            log.info("Data from Excel: " + fighter);
            file.close();
            return fighter;
        }else {
            file.close();
            throw new NotFoundException(name + " was not found. Ensure to run the python file first.");
        }
    }
}
