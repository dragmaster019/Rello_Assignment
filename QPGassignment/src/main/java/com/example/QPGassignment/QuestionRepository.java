package com.example.QPGassignment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByDifficulty(String difficulty);
}
