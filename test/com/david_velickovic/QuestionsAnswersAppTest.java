package com.david_velickovic;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class QuestionsAnswersAppTest {

    @BeforeEach
    void setUp() {
        QuestionsAnswersApp.db.clear();
    }

    @Test
    @DisplayName("Test: Valid input format with question mark")
    void testValidInputFormat() {
        String input = "What is your name? \"David\"";
        assertTrue(QuestionsAnswersApp.validateInputFormat(input), "The input format should be valid.");
    }

    @Test
    @DisplayName("Test: Invalid input format without question mark")
    void testInvalidInputFormat() {
        String input = "What is your name \"David\"";
        assertFalse(QuestionsAnswersApp.validateInputFormat(input), "The input format should be invalid because it lacks a question mark.");
    }

    @Test
    @DisplayName("Test: Extract question from input")
    void testExtractQuestion() {
        String input = "What is your favorite color? \"Blue\"";
        String expectedQuestion = "What is your favorite color?";
        assertEquals(expectedQuestion, QuestionsAnswersApp.extractQuestion(input), "The extracted question should match the expected format.");
    }

    @Test
    @DisplayName("Test: Extract answers from input")
    void testExtractAnswers() {
        String input = "What is your favorite color? \"Blue\" \"Green\"";
        List<String> expectedAnswers = List.of("Blue", "Green");
        assertEquals(expectedAnswers, QuestionsAnswersApp.extractAnswers(input), "The extracted answers should match the expected list.");
    }

    @Test
    @DisplayName("Test: Add question and answers to the database")
    void testAddQuestionAndAnswersToDB() {
        String question = "What is your favorite color?";
        List<String> answers = List.of("Blue", "Green");
        QuestionsAnswersApp.saveQuestionAndAnswers(question, answers);
        assertTrue(QuestionsAnswersApp.db.containsKey(question), "The database should contain the question.");
        assertEquals(answers, QuestionsAnswersApp.db.get(question), "The database should store the correct answers for the question.");
    }

    @Test
    @DisplayName("Test: Question length exceeds 255 characters")
    void testQuestionLengthExceeded() {
        String longQuestion = "Q".repeat(256) + "?";
        assertTrue(QuestionsAnswersApp.isLengthInvalid(longQuestion), "The question should be marked as exceeding the maximum length.");
    }

    @Test
    @DisplayName("Test: Answer length exceeds 255 characters")
    void testAnswerLengthExceeded() {
        String longAnswer = "A".repeat(256);
        assertTrue(QuestionsAnswersApp.isLengthInvalid(longAnswer), "The answer should be marked as exceeding the maximum length.");
    }

    @Test
    @DisplayName("Test: Empty answer list")
    void testEmptyAnswerList() {
        List<String> emptyAnswers = List.of();
        assertTrue(QuestionsAnswersApp.isAnswerListEmpty(emptyAnswers), "The answer list should be marked as empty.");
    }

    @Test
    @DisplayName("Test: Non-empty answer list")
    void testNonEmptyAnswerList() {
        List<String> answers = List.of("Blue");
        assertFalse(QuestionsAnswersApp.isAnswerListEmpty(answers), "The answer list should not be marked as empty.");
    }

    @Test
    @DisplayName("Test: Valid input with special characters")
    void testValidInputWithSpecialCharacters() {
        String input = "What's your favorite animal? \"dog\"";
        assertTrue(QuestionsAnswersApp.validateInputFormat(input), "The input format should be valid even with special characters.");
        List<String> expectedAnswers = List.of("dog");
        assertEquals(expectedAnswers, QuestionsAnswersApp.extractAnswers(input), "The extracted answer should match the expected list with special characters.");
    }
}
        