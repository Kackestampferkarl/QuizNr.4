import java.util.List;

public class Quiz {
    private int score;
    private List<Question> questions;
    private int currentQuestion;

    public Quiz(List<Question> questions) {
        this.score = 1;
        this.questions = questions;
        this.currentQuestion = 0;
    }

    public void increaseScore() {
        score++;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}