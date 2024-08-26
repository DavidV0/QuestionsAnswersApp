package com.david_velickovic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * This class provides functionality for adding questions and their answers.
 * It uses a HashMap to store questions as keys and a list of answers as values.
 */
public class QuestionsAnswersApp {

    public static final HashMap<String, List<String>> db = new HashMap<>();
    
    private static final String INVALID_FORMAT_MESSAGE = "Invalid format. The question must end with a '?' and be followed by at least one answer. Please try again.";
    private static final String EXCEEDS_LENGTH_MESSAGE = "Question or answer length exceeds 255 characters. Please try again.";
    private static final String NO_ANSWER_MESSAGE = "At least one answer is required. Please try again.";
    private static final String EXIT_MESSAGE = "Exiting the program. Goodbye!";
    private static final String DEFAULT_ANSWER = "the answer to life, universe and everything is 42";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean isRunning = true;
            while (isRunning) {
                printTerminalInterface();
                String choice = scanner.nextLine();
                isRunning = handleUserChoice(choice, scanner);
            }
        }
    }

    /**
     * Prints the main menu interface to the terminal.
     */
    private static void printTerminalInterface() {
        System.out.println("Please choose an option:");
        System.out.println("1. Ask a question");
        System.out.println("2. Add a question and answers");
        System.out.println("3. Exit");
    }

    /**
     * Handles the user's choice from the terminal menu.
     *
     * @param choice The user's menu choice.
     * @param scanner The Scanner object to read user input.
     * @return false if the user chooses to exit, true otherwise to continue the loop.
     */
    private static boolean handleUserChoice(String choice, Scanner scanner) {
        switch (choice) {
            case "1":
                askQuestion(scanner);
                break;
            case "2":
                addQuestionAndAnswers(scanner);
                break;
            case "3":
                System.out.println(EXIT_MESSAGE);
                return false;  
            default:
                System.out.println("Invalid option. Please try again.");
        }
        return true;
    }

    /**
     * Prompts the user to enter a question and checks if the question exists in the database.
     * If the question exists, prints all associated answers; otherwise, prints a default message.
     *
     * @param scanner The Scanner object to read user input.
     */
    private static void askQuestion(Scanner scanner) {
        System.out.println("Enter your question (must end with a '?'):");
        String question = scanner.nextLine().trim();

       
        if (db.containsKey(question)) {
            List<String> answers = db.get(question);
            answers.forEach(System.out::println);
        } else {
            System.out.println(DEFAULT_ANSWER);
        }
    }

    /**
     * Prompts the user to enter a question followed by its answers in a specific format.
     * Validates the input, extracts the question and answers, checks their lengths,
     * and saves them to the database if all conditions are met.
     *
     * @param scanner The Scanner object to read user input.
     */
    private static void addQuestionAndAnswers(Scanner scanner) {
        System.out.println("Enter your question followed by answers in the format: <question>? \"<answer1>\" \"<answer2>\" ... (at least one answer required)");
        String input = scanner.nextLine();

        if (validateInputFormat(input)) {
            String question = extractQuestion(input);
            List<String> answers = extractAnswers(input);

            if (!isLengthInvalid(question) && !isAnswerListEmpty(answers)) {
                saveQuestionAndAnswers(question, answers);
            }
        }
    }

    /**
     * Validates the input format for a question and its answers.
     * The input must contain a question mark '?' to be considered valid.
     *
     * @param input The input string to validate.
     * @return true if the input format is valid, false otherwise.
     */
    public static boolean validateInputFormat(String input) {
        int separatorIndex = input.indexOf('?');
        if (separatorIndex == -1 || !input.contains("?")) {
            System.out.println(INVALID_FORMAT_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Extracts the question from the input string.
     * The question is assumed to end with a question mark '?'.
     *
     * @param input The input string containing the question and answers.
     * @return The extracted question.
     */
    public static String extractQuestion(String input) {
        int separatorIndex = input.indexOf('?');
        return input.substring(0, separatorIndex + 1).trim();
    }

    /**
     * Extracts and cleans answers from the input string.
     * Each answer is enclosed in double quotes and separated by whitespace.
     *
     * @param input The input string containing the question and answers.
     * @return A list of cleaned answers extracted from the input.
     */
    public static List<String> extractAnswers(String input) {
        int separatorIndex = input.indexOf('?');
        String answersPart = input.substring(separatorIndex + 1).trim();
        String[] answersArray = answersPart.split("\"\\s*\"");
        List<String> answers = new ArrayList<>();

        for (String answer : answersArray) {
            String cleanAnswer = cleanAnswer(answer);
            if (!cleanAnswer.isEmpty() && !isLengthInvalid(cleanAnswer)) {
                answers.add(cleanAnswer);
            }
        }
        return answers;
    }

    /**
     * Cleans an answer string by removing any surrounding double quotes.
     *
     * @param answer The answer string to clean.
     * @return The cleaned answer without surrounding double quotes.
     */
    public static String cleanAnswer(String answer) {
        return answer.trim().replaceAll("^\"|\"$", "");
    }

    /**
     * Checks if a list of answers is empty.
     *
     * @param answers The list of answers to check.
     * @return true if the list is empty, false otherwise.
     */
    public static boolean isAnswerListEmpty(List<String> answers) {
        if (answers.isEmpty()) {
            System.out.println(NO_ANSWER_MESSAGE);
            return true;
        }
        return false;
    }

    /**
     * Saves the question and its associated answers to the database.
     *
     * @param question The question to save.
     * @param answers The list of answers to save.
     */
    public static void saveQuestionAndAnswers(String question, List<String> answers) {
        db.put(question, answers);
        System.out.println("Question and answers added successfully.");
    }

    
    /**
     * Checks if a string exceeds the maximum allowed length of 255 characters.
     *
     * @param string The string to check.
     * @return true if the string length exceeds 255 characters, false otherwise.
     */
    public static boolean isLengthInvalid(String string) {
        if (string.length() > 255) {
            System.out.println(EXCEEDS_LENGTH_MESSAGE);
            return true;
        }
        return false;
    }
}