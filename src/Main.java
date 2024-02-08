import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Question> questions = new ArrayList<>(9999);
        questions.addAll(List.of(
                new Question(Set.of(1), List.of("A", "B", "C", "D"), "What is the capital of France?", 10),
                new Question(Set.of(3), List.of("Red", "Blue", "Green", "Yellow"), "Which color is a primary color?", 15),
                new Question(Set.of(2), List.of("Python", "Java", "C++", "Ruby"), "Which programming language is statically typed?", 20),
                new Question(Set.of(4), List.of("Mercury", "Venus", "Earth", "Mars"), "Which planet is known as the Red Planet?", 12),
                new Question(Set.of(1), List.of("Lion", "Elephant", "Giraffe", "Zebra"), "Which animal is known as the king of the jungle?", 18),
                new Question(Set.of(3), List.of("Football", "Basketball", "Tennis", "Golf"), "Which sport is played with a shuttlecock?", 14),
                new Question(Set.of(2), List.of("USA", "Canada", "Australia", "Brazil"), "Which country is known as the Land of the Rising Sun?", 16),
                new Question(Set.of(4), List.of("Oxygen", "Nitrogen", "Carbon dioxide", "Helium"), "Which gas do plants absorb during photosynthesis?", 12),
                new Question(Set.of(1), List.of("Rome", "Athens", "Cairo", "Berlin"), "Which city is the capital of Italy?", 15),
                new Question(Set.of(2, 4, 3), List.of("Mount Everest", "K2", "Kangchenjunga", "Makalu"), "Which is the third highest mountain in the world?", 20)));
        Collections.sort(questions);
        Quiz quiz = new Quiz(questions);
        for (Question currentQuestion : quiz.getQuestions()) {
            System.out.println(currentQuestion.getQuestionText());
            List<String> options = currentQuestion.getOptions();
            for (String option : options) {
                System.out.println(option);
            }
            System.out.print("Bitte Antwort eingeben: ");
            String userInput = timedUserInput(currentQuestion.getQuestionTimer(), TimeUnit.SECONDS).get();
            if (currentQuestion.getCorrectOptions().contains(Integer.parseInt(userInput))) {
                System.out.println("gut gemacht ");
                quiz.setScore(quiz.getScore() + (int) (currentQuestion.getQuestionTimer() * (Math.E)));
            } else {
                System.out.println("Du bist scheisse");
            }
            System.out.println("you score " + quiz.getScore());
        }
    }

    public static CompletableFuture<String> timedUserInput(int timeout, TimeUnit unit) {
        CompletableFuture<String> userInputFuture = new CompletableFuture<>();

        // Start a new thread for user input
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            userInputFuture.complete(input);
        }).start();

        // Schedule a timeout for the CompletableFuture
        CompletableFuture.delayedExecutor(timeout, unit)
                .execute(() -> {
                    if (!userInputFuture.isDone()) {
                        System.out.println("Time's up! You didn't enter anything.");
                        userInputFuture.complete("");
                    }
                });
        return userInputFuture;
    }

}
