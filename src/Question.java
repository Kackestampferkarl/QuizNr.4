import java.util.List;
import java.util.Random;
import java.util.Set;

public class Question implements Comparable<Question> {
    private Set<Integer> correctOptions;
    private List<String> options;//TODO may move into own class
    private String questionText;
    private int questionTimer;

    public Question(Set<Integer> correctOptions, List<String> options, String questionText, int questionTimer) {
        this.correctOptions = correctOptions;
        this.options = options;
        this.questionText = questionText;
        this.questionTimer = questionTimer;
    }

    public Set<Integer> getCorrectOptions() {
        return correctOptions;
    }

    public void setCorrectOptions(Set<Integer> correctOptions) {
        this.correctOptions = correctOptions;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public int getQuestionTimer() {
        return questionTimer;
    }

    public void setQuestionTimer(int questionTimer) {
        this.questionTimer = questionTimer;
    }

    public boolean checkAnswer() {
        return false; // TODO: 26.01.2024 have to implement
    }

    @Override
    public String toString() {
        return "Question{" +
                "correctOption=" + correctOptions +
                ", options=" + options +
                ", questionText='" + questionText + '\'' +
                ", questionTimer=" + questionTimer +
                '}';
    }

    @Override
    public int compareTo(Question o) {
        Random random = new Random();
        return Integer.compare(this.questionTimer + random.nextInt(), o.getQuestionTimer() + random.nextInt());
    }

}