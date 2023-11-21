package com.example.QPGassignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/question-paper")
public class QuestionPaperController {
    @Autowired
    private QuestionPaperGeneratorService questionPaperGeneratorService;

    @GetMapping("/generate")
    public ResponseEntity<?> generateQuestionPaper(
            @RequestParam int totalMarks,
            @RequestParam int easyPercentage,
            @RequestParam int mediumPercentage,
            @RequestParam int hardPercentage) {
        try {
            if (easyPercentage + mediumPercentage + hardPercentage != 100) {
                throw new IllegalArgumentException("Percentages should add up to 100%");
            }

            List<Question> questionPaper = questionPaperGeneratorService.generateQuestionPaper(
                    totalMarks, easyPercentage, mediumPercentage, hardPercentage);

            return ResponseEntity.ok(questionPaper);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
}
