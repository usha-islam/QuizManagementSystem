import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.io.FileReader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class QuizUI extends JFrame {
    private JLabel questionLabel;
    private JRadioButton option1, option2, option3, option4;
    private ButtonGroup optionsGroup;
    private JButton nextButton;

    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;

    public QuizUI() {
        setTitle("Quiz");
        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        questionLabel = new JLabel("Question will appear here");
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(questionLabel, BorderLayout.NORTH);

        option1 = new JRadioButton("Option 1");
        option2 = new JRadioButton("Option 2");
        option3 = new JRadioButton("Option 3");
        option4 = new JRadioButton("Option 4");
        optionsGroup = new ButtonGroup();
        optionsGroup.add(option1); optionsGroup.add(option2);
        optionsGroup.add(option3); optionsGroup.add(option4);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4,1));
        optionsPanel.add(option1); optionsPanel.add(option2);
        optionsPanel.add(option3); optionsPanel.add(option4);
        panel.add(optionsPanel, BorderLayout.CENTER);

        nextButton = new JButton("Next");
        panel.add(nextButton, BorderLayout.SOUTH);

        add(panel);

        loadQuestions();

        if (!questions.isEmpty()) showQuestion(currentQuestionIndex);
        else JOptionPane.showMessageDialog(this, "No questions available!");

        nextButton.addActionListener(e -> {
            checkAnswer();
            currentQuestionIndex++;
            if (currentQuestionIndex<questions.size() && currentQuestionIndex<10) showQuestion(currentQuestionIndex);
            else showFinalScore();
        });

        setVisible(true);
    }

    private void loadQuestions() {
        try {
            Gson gson = new Gson();
            FileReader reader = new FileReader("quiz.json");
            questions = gson.fromJson(reader, new TypeToken<List<Question>>() {}.getType());
            if (questions==null) questions = new ArrayList<>();
            reader.close();
        } catch(Exception e) {
            questions = new ArrayList<>();
            JOptionPane.showMessageDialog(this,"Could not load quiz.json");
        }
    }

    private void showQuestion(int index) {
        Question q = questions.get(index);
        questionLabel.setText("Q"+(index+1)+": "+q.getQuestion());
        option1.setText(q.getOption1());
        option2.setText(q.getOption2());
        option3.setText(q.getOption3());
        option4.setText(q.getOption4());
        optionsGroup.clearSelection();
    }

    private void checkAnswer() {
        Question q = questions.get(currentQuestionIndex);
        int selected = -1;
        if (option1.isSelected()) selected = 1;
        else if (option2.isSelected()) selected = 2;
        else if (option3.isSelected()) selected = 3;
        else if (option4.isSelected()) selected = 4;
        if (selected==q.getAnswerKey()) score++;
    }

    private void showFinalScore() {
        String message;
        if (score>=8) message="Excellent! You got "+score+" out of 10";
        else if (score>=5) message="Good. You got "+score+" out of 10";
        else if (score>=3) message="Very poor! You got "+score+" out of 10";
        else message="Very sorry, you failed. You got "+score+" out of 10";
        JOptionPane.showMessageDialog(this,message);
        System.exit(0);
    }
}
