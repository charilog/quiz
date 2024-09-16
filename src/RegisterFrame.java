
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterFrame extends JFrame {

    private JTextField name;
    private JTextField lastname;
    private JTextField email;
    private JTextField user;
    private JTextField password;
    private JLabel n, l, e, u, p;
    private JButton reg;
    private JButton back;

    RegisterFrame() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                StartFrame.s.setVisible(true);
            }
        });

        this.setTitle("Quiz by Anna Maria Gianni");
        setSize(300, 480);
        setResizable(false);
        this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
        this.setUndecorated(true);
        this.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
        this.setLayout(new GridLayout(12, 1, 10, 10));
        Font f = new Font("Castellar", Font.BOLD, 42);
        Font f2 = new Font("Castellar", Font.BOLD, 25);

        n = new JLabel("           Name");
        n.setFont(f2);
        //n.setFocusable(true);
        n.setForeground(Color.BLACK);
        name = new JTextField();
        name.setFont(f2);
        name.setFocusable(true);
        name.setForeground(Color.BLACK);
        l = new JLabel("        Lastname");
        l.setFont(f2);
        //l.setFocusable(true);
        l.setForeground(Color.BLACK);
        lastname = new JTextField();
        lastname.setFont(f2);
        lastname.setFocusable(true);
        lastname.setForeground(Color.BLACK);
        e = new JLabel("            Email");
        e.setFont(f2);
        //e.setFocusable(true);
        e.setForeground(Color.BLACK);
        email = new JTextField();
        email.setFont(f2);
        email.setFocusable(true);
        email.setForeground(Color.BLACK);
        u = new JLabel("             User");
        u.setFont(f2);
        //u.setFocusable(true);
        u.setForeground(Color.BLACK);
        user = new JTextField();
        user.setFont(f2);
        user.setFocusable(true);
        user.setForeground(Color.BLACK);
        p = new JLabel("        Password");
        p.setFont(f2);
        //p.setFocusable(true);
        p.setForeground(Color.BLACK);
        password = new JPasswordField();
        password.setFont(f2);
        password.setFocusable(true);
        password.setForeground(Color.BLACK);
        reg = new JButton("SUBMIT");
        reg.setFont(f2);
        reg.setFocusable(true);
        reg.setForeground(Color.BLACK);
        reg.addActionListener((ActionEvent ae) -> {
            if (checkFields()) {

                if (StartFrame.db.findUserIfExist("users", user.getText())) {
                    JOptionPane.showMessageDialog(this, user.getText() + " EXIST! PLEASE CHOOSE ANOTHER NAME USE");
                } else {
                    System.out.println("Inserting..");
                    StartFrame.db.insertUser(name.getText(), lastname.getText(), email.getText(), user.getText(), password.getText());
                    this.dispose();
                    StartFrame.s.setVisible(true);
                }
            }
        });
        back = new JButton("BACK");
        back.setFont(f2);
        back.setOpaque(false);
        //back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setFocusable(true);
        back.setForeground(Color.ORANGE);
        back.addActionListener((ActionEvent ae) -> {
            this.dispose();
            StartFrame.s.setVisible(true);
        });
        this.add(n);
        this.add(name);
        this.add(l);
        this.add(lastname);
        this.add(e);
        this.add(email);
        this.add(u);
        this.add(user);
        this.add(p);
        this.add(password);
        this.add(reg);
        this.add(back);
    }

    public boolean checkSpecialCras(String str) {
        Pattern regex = Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^*()%!-]");
        if (regex.matcher(str).find()) {
            return false;
        }
        return true;
    }

    public boolean validEmail(String str) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        return pat.matcher(str).matches();
    }

    public boolean validPassword(String str) {
        if (password.getText().length() <= 5) {
            return false;
        }
        return true;
    }

    public boolean checkFields() {
        if (name.getText().isEmpty() || name.getText().isEmpty() || lastname.getText().isEmpty() || email.getText().isEmpty() || user.getText().isEmpty() || password.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "NULL FIELDS");
            return false;
        }
        String msg = "";
        if (!checkSpecialCras(name.getText())) {
            msg = "SPECIAL CHARS FOUND IN NAME\n";
        }
        if (!checkSpecialCras(lastname.getText())) {
            msg += "SPECIAL CHARS FOUND IN LASTNAME\n";
        }
        if (!validEmail(email.getText())) {
            msg += "EMAIL IS NOT VALID\n";
        }
        if (!checkSpecialCras(user.getText())) {
            msg += "SPECIAL CHARS FOUND IN LASTNAME\n";
        }
        if (!validPassword(password.getText())) {
            msg += "PASSWORD TOO SORT\n";
        }
        if (!msg.isEmpty()) {
            JOptionPane.showMessageDialog(this, msg);
            return false;
        }
        return true;
    }
}
