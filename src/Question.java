
import java.io.Serializable;
import javax.swing.JOptionPane;

public class Question implements Serializable {

    private static final long serialVersionUID = -1988516715521350128L;
    private String question, answerA, answerB, answerC, answerD, correctAnswer, image, difficulty;
    private int id;

    public Question() {
        id = Integer.parseInt(JOptionPane.showInputDialog("Enter question: "));
        question = JOptionPane.showInputDialog("Enter question: ");
        answerA = JOptionPane.showInputDialog("Enter answerA: ");
        answerB = JOptionPane.showInputDialog("Enter answerB: ");
        answerC = JOptionPane.showInputDialog("Enter answerC: ");
        answerD = JOptionPane.showInputDialog("Enter answerD: ");
        correctAnswer = JOptionPane.showInputDialog("Enter correct answer: ");
        image = JOptionPane.showInputDialog("Enter image name: ");
        difficulty = JOptionPane.showInputDialog("Enter difficulty: ");
    }

    public int getId() {
        return this.id;
    }

    public String getQuestion() {
        return this.question;
    }

    public String getAnswerA() {
        return this.answerA;
    }

    public String getAnswerB() {
        return this.answerB;
    }

    public String getAnswerC() {
        return this.answerC;
    }

    public String getAnswerD() {
        return this.answerD;
    }

    public String getCorrectAnswer() {
        return this.correctAnswer;
    }

    public String getImage() {
        return this.image;
    }

    public String getDifficulty() {
        return this.difficulty;
    }

    public Question(int id, String q, String a, String b, String c, String d, String n, String i, String diff) {
        this.id = id;
        this.question = q;
        this.answerA = a;
        this.answerB = b;
        this.answerC = c;
        this.answerD = d;
        this.correctAnswer = n;
        this.image = i;
        this.difficulty = diff;
    }

    @Override
    public String toString() {
        return id + question + "," + answerA + "," + answerB + "," + answerC + "," + answerD + "," + correctAnswer + "," + image + "," + difficulty;
    }
}
