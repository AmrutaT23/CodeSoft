// Quiz Questions and Options: Store quiz questions along with multiple-choice options and correct
// answers.
// Timer: Implement a timer for each question to limit the time to answer.
// Question Display: Present one question at a time with multiple-choice options.
// Answer Submission: Allow users to select an option and submit their answer within the given
// time.
// Score Calculation: Keep track of the user's score based on correct 


import java.util.*;

public class QuizApp {
    static class Question {
        String question;
        String[] options;
        char correctAnswer;

        Question(String question, String[] options, char correctAnswer) {
            this.question = question;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        // Define quiz questions
        List<Question> questions = Arrays.asList(
            new Question("What is the capital of France?", new String[]{"A. Paris", "B. London", "C. Rome", "D. Madrid"}, 'A'),
            new Question("Which planet is known as the Red Planet?", new String[]{"A. Earth", "B. Mars", "C. Jupiter", "D. Venus"}, 'B'),
            new Question("Who wrote 'Hamlet'?", new String[]{"A. Charles Dickens", "B. Mark Twain", "C. William Shakespeare", "D. Jane Austen"}, 'C')
        );

        int score = 0;
        Map<Question, Boolean> summary = new HashMap<>();

        for (Question q : questions) {
            boolean answeredCorrectly = askQuestion(q, scanner);
            summary.put(q, answeredCorrectly);
            if (answeredCorrectly) {
                score++;
            }
        }

        // Display result
        System.out.println("\nFinal Score: " + score + " out of " + questions.size());
        System.out.println("Summary:");
        for (Map.Entry<Question, Boolean> entry : summary.entrySet()) {
            System.out.println(entry.getKey().question);
            System.out.println("Your answer: " + (entry.getValue() ? "Correct" : "Incorrect"));
        }

        scanner.close();
    }

    private static boolean askQuestion(Question question, Scanner scanner) throws InterruptedException {
        System.out.println("\n" + question.question);
        for (String option : question.options) {
            System.out.println(option);
        }

        long startTime = System.currentTimeMillis();
        long endTime = startTime + 15 * 1000; // 15 seconds timer

        while (System.currentTimeMillis() < endTime) {
            if (scanner.hasNext()) {
                char answer = scanner.next().toUpperCase().charAt(0);
                if (answer == question.correctAnswer) {
                    System.out.println("Correct!");
                    return true;
                } else {
                    System.out.println("Incorrect. The correct answer was " + question.correctAnswer + ".");
                    return false;
                }
            }
            Thread.sleep(100);
        }

        System.out.println("Time's up! The correct answer was " + question.correctAnswer + ".");
        return false;
    }
}
