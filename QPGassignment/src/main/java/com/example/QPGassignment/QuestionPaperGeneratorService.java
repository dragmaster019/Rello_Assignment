package com.example.QPGassignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class QuestionPaperGeneratorService {
    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> generateQuestionPaper(int totalMarks, int easyPercentage, int mediumPercentage, int hardPercentage) {
        List<Question> questionPaper = new ArrayList<>();

        int easyMarks = (int) (totalMarks * easyPercentage / 100);
        int mediumMarks = (int) (totalMarks * mediumPercentage / 100);
        int hardMarks = (int) (totalMarks * hardPercentage / 100);

        questionPaper.addAll(selectQuestionsByDifficulty("Easy", easyMarks));
        questionPaper.addAll(selectQuestionsByDifficulty("Medium", mediumMarks));
        questionPaper.addAll(selectQuestionsByDifficulty("Hard", hardMarks));

        return questionPaper;
    }

    private List<Question> selectQuestionsByDifficulty(String difficulty, int marks) {
        List<Question> questions = questionRepository.findByDifficulty(difficulty);
        Collections.shuffle(questions);
        return questions.subList(0, Math.min(marks, questions.size()));
    }
}

