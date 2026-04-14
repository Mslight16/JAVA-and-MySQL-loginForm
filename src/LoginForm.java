import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LoginForm extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginForm().initialize());
    }

    private JTextField tfEmail;
    private JPasswordField pfPassword;

    public void initialize() {

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Title
        JLabel title = new JLabel("Login System", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setBorder(new EmptyBorder(25, 10, 25, 10));

        // Form Panel (using BoxLayout for better control)
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(new EmptyBorder(20, 50, 20, 50));

        // Email Field
        tfEmail = new JTextField();
        tfEmail.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        tfEmail.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        tfEmail.setBorder(BorderFactory.createTitledBorder("Email"));

        // Password Field
        pfPassword = new JPasswordField();
        pfPassword.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        pfPassword.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        pfPassword.setBorder(BorderFactory.createTitledBorder("Password"));

        formPanel.add(tfEmail);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(pfPassword);

        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 20, 10));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(new EmptyBorder(20, 50, 30, 50));

        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnLogin.setBackground(new Color(0, 120, 215));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnCancel.setBackground(new Color(200, 50, 50));
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setFocusPainted(false);

        buttonPanel.add(btnLogin);
        buttonPanel.add(btnCancel);

        // Button Actions
    btnLogin.addActionListener(e -> {
        String email = tfEmail.getText().trim().toLowerCase();
        String password = new String(pfPassword.getPassword()).trim();

        User user = getAuthenticatedUser(email, password);

        if (user != null) {
            System.out.println("Login success, opening dashboard...");
            JOptionPane.showMessageDialog(this, "Login Successful!");
            dispose();
            new MainFrame().initialize(user);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Invalid Email or Password",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    });
    
    

        btnCancel.addActionListener(e -> dispose());

        // Add everything
        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // Frame Settings
        setTitle("Login Form");
        setSize(450, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
 
private User getAuthenticatedUser(String email, String password) {
    User user = null;

    final String DB_URL = "jdbc:mysql://localhost:3306/loginusers";
    final String USERNAME = "root";
    final String PASSWORD = "your_mysql_password";

    try (Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {

        email = email.trim().toLowerCase();
        password = password.trim();

        System.out.println("Entered Email: [" + email + "]");
        System.out.println("Entered Password: [" + password + "]");

        String sql = "SELECT * FROM users WHERE LOWER(email) = ? AND password = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.name = rs.getString("name");
                    user.email = rs.getString("email");
                    user.phone = rs.getString("phone");
                    user.address = rs.getString("address");
                    user.password = rs.getString("password");
                }
            }
        }

    } catch (SQLException e) {
        System.err.println("Database connection failed: " + e.getMessage());
    } catch (Exception e) {
        System.err.println("An unexpected error occurred: " + e.getMessage());
    }

    return user;
}

}
