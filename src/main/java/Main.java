import javax.swing.*;
import java.util.List;
import java.io.FileReader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new LoginUI();
        });
    }
}
