import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class AdminUI extends JFrame {
    private JTextField questionField, option1Field, option2Field, option3Field, option4Field, answerKeyField;
    private JButton addButton, exitButton;
    private List<Question> questions;

    public AdminUI() {
        setTitle("Admin Panel - Add Questions");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(8,2,5,5));

        loadQuestions();

        add(new JLabel("Question:"));
        questionField = new JTextField(); add(questionField);

        add(new JLabel("Option 1:"));
        option1Field = new JTextField(); add(option1Field);

        add(new JLabel("Option 2:"));
        option2Field = new JTextField(); add(option2Field);

        add(new JLabel("Option 3:"));
        option3Field = new JTextField(); add(option3Field);

        add(new JLabel("Option 4:"));
        option4Field = new JTextField(); add(option4Field);

        add(new JLabel("Answer Key (1-4):"));
        answerKeyField = new JTextField(); add(answerKeyField);

        addButton = new JButton("Add Question");
        add(addButton);
        exitButton = new JButton("Exit");
        add(exitButton);

        addButton.addActionListener(e -> addQuestion());
        exitButton.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private void loadQuestions() {
        try {
            Gson gson = new Gson();
            FileReader reader = new FileReader("quiz.json");
            questions = gson.fromJson(reader, new TypeToken<List<Question>>() {}.getType());
            if (questions == null) questions = new ArrayList<>();
            reader.close();
        } catch (Exception e) {
            questions = new ArrayList<>();
            JOptionPane.showMessageDialog(this, "quiz.json not found, a new one will be created!");
        }
    }

    private void addQuestion() {
        try {
            String q = questionField.getText().trim();
            String o1 = option1Field.getText().trim();
            String o2 = option2Field.getText().trim();
            String o3 = option3Field.getText().trim();
            String o4 = option4Field.getText().trim();
            int key = Integer.parseInt(answerKeyField.getText().trim());

            if (q.isEmpty() || o1.isEmpty() || o2.isEmpty() || o3.isEmpty() || o4.isEmpty() || key<1 || key>4) {
                JOptionPane.showMessageDialog(this, "Please fill all fields correctly!");
                return;
            }

            questions.add(new Question(q,o1,o2,o3,o4,key));

            Gson gson = new Gson();
            FileWriter writer = new FileWriter("quiz.json");
            gson.toJson(questions, writer);
            writer.flush();
            writer.close();

            JOptionPane.showMessageDialog(this, "Question added successfully!");

            questionField.setText(""); option1Field.setText(""); option2Field.setText("");
            option3Field.setText(""); option4Field.setText(""); answerKeyField.setText("");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
}
