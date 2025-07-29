package opic.config;

import opic.domain.OpicQuestion;
import opic.repository.OpicQuestionRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.List;

@Configuration
public class ExcelQuestionLoader {

    @Bean
    public CommandLineRunner loadExcelData(OpicQuestionRepository repository) {
        return args -> {
            List<String> files = List.of(
                    "questions/part1_questions.xlsx",
                    "questions/part2_questions.xlsx",
                    "questions/part3_questions.xlsx",
                    "questions/part4_questions.xlsx"
            );

            for (String file : files) {
                String part = file.split("/")[1].split("_")[0]; // "part1" 추출

                try (InputStream is = getClass().getClassLoader().getResourceAsStream(file);
                     Workbook workbook = new XSSFWorkbook(is)) {

                    Sheet sheet = workbook.getSheetAt(0);
                    for (Row row : sheet) {
                        if (row.getRowNum() == 0) continue; // 헤더 스킵

                        String condition = row.getCell(1).getStringCellValue();
                        int level = (int) row.getCell(2).getNumericCellValue();
                        String kr = row.getCell(3).getStringCellValue();
                        String en = row.getCell(4).getStringCellValue();

                        OpicQuestion q = new OpicQuestion();
                        q.setPart(part);
                        q.setQuestionId(row.getRowNum()); // 간단히 row 번호 사용
                        q.setConditionText(condition);
                        q.setLevel(level);
                        q.setQuestionKr(kr);
                        q.setQuestionEn(en);

                        repository.save(q);
                    }
                }
            }
        };
    }
}
