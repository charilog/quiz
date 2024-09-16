
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame {

    private JLabel u;
    private JLabel p;
    private JTextField user;
    private JPasswordField pass;
    private JButton submit;
    private JButton back;

    LoginFrame() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                StartFrame.s.setVisible(true);
            }
        });

        this.setTitle("Quiz by Anna Maria Gianni");
        setSize(300, 280);
        setResizable(false);
        this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
        this.setUndecorated(true);
        this.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
        this.setLayout(new GridLayout(6, 1, 10, 10));
        Font f = new Font("Arial", Font.PLAIN, 25);
        Font f2 = new Font("Arial", Font.BOLD, 25);//Castellar

        u = new JLabel("                User");
        u.setFont(f2);
        //u.setFocusable(true);
        u.setForeground(Color.BLACK);
        p = new JLabel("            Password");
        p.setFont(f2);
        //p.setFocusable(true);
        p.setForeground(Color.BLACK);
        user = new JTextField();
        user.setFont(f);
        user.setFocusable(true);
        user.setForeground(Color.BLACK);
        pass = new JPasswordField();
        pass.setFont(f);
        pass.setFocusable(true);
        pass.setForeground(Color.BLACK);
        submit = new JButton("LOGIN");
        submit.setFont(f2);
        submit.setFocusable(true);
        submit.setForeground(Color.BLACK);

        submit.addActionListener((ActionEvent ae) -> {
            if (StartFrame.db.checkUser("users", user.getText().toString(), pass.getText())) {
                //StartFrame.db.insertStats(StartFrame.db.user,"","", "", 4, 5, 6, 7);
                this.dispose();
                StartFrame.s.setVisible(true);
                StartFrame.s.login.setEnabled(false);
                StartFrame.s.register.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(this, " USER OR PASSWORD IS IT'S WRONG!");
            }
        });
        back = new JButton("BACK");
        back.setFont(f2);
        back.setOpaque(false);
        //back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setForeground(Color.ORANGE);
        back.addActionListener((ActionEvent ae) -> {
            this.dispose();
            StartFrame.s.setVisible(true);
        });
        this.add(u);
        this.add(user);
        this.add(p);
        this.add(pass);
        this.add(submit);
        this.add(back);
    }

}
