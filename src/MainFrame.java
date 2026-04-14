import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {

    public void initialize(User user) {

        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Title
        JLabel title = new JLabel("Dashboard", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setBorder(new EmptyBorder(20, 10, 20, 10));

        // Info Panel
        JPanel infoPanel = new JPanel(new GridLayout(4, 2, 15, 15));
        infoPanel.setBorder(new EmptyBorder(30, 100, 30, 100));
        infoPanel.setBackground(Color.WHITE);

        Font labelFont = new Font("Segoe UI", Font.BOLD, 16);
        Font valueFont = new Font("Segoe UI", Font.PLAIN, 16);

        infoPanel.add(createLabel("Name:", labelFont));
        infoPanel.add(createLabel(user.name, valueFont));

        infoPanel.add(createLabel("Email:", labelFont));
        infoPanel.add(createLabel(user.email, valueFont));

        infoPanel.add(createLabel("Phone:", labelFont));
        infoPanel.add(createLabel(user.phone, valueFont));

        infoPanel.add(createLabel("Address:", labelFont));
        infoPanel.add(createLabel(user.address, valueFont));

        // Add to frame
        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(infoPanel, BorderLayout.CENTER);

        add(mainPanel);

        // Frame settings
        setTitle("Dashboard");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JLabel createLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        return label;
    }
}