import javax.swing.*;
import java.awt.event.*;
import java.util.List;
import java.io.FileReader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LoginUI extends JFrame {
    private JTextField userText;
    private JPasswordField passwordText;
    private JLabel resultLabel;
    private List<User> users;

    public LoginUI() {
        setTitle("Quiz Management System - Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        // Load users.json
        loadUsers();

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 50, 80, 25);
        add(userLabel);

        userText = new JTextField();
        userText.setBounds(150, 50, 165, 25);
        add(userText);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 90, 80, 25);
        add(passLabel);

        passwordText = new JPasswordField();
        passwordText.setBounds(150, 90, 165, 25);
        add(passwordText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 130, 100, 30);
        add(loginButton);

        resultLabel = new JLabel("");
        resultLabel.setBounds(50, 170, 300, 25);
        add(resultLabel);

        loginButton.addActionListener(e -> login());

        setVisible(true);
    }

    private void loadUsers() {
        try {
            Gson gson = new Gson();
            FileReader reader = new FileReader("users.json");
            users = gson.fromJson(reader, new TypeToken<List<User>>() {}.getType());
            reader.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "users.json load failed!");
        }
    }

    private void login() {
        String username = userText.getText();
        String password = new String(passwordText.getPassword());

        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                JOptionPane.showMessageDialog(this, "Welcome " + u.getRole() + "!");
                dispose(); // close login window
                if (u.getRole().equalsIgnoreCase("admin")) new AdminUI();
                else new QuizUI();
                return;
            }
        }
        resultLabel.setText("Invalid login!");
    }
}
