
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import static java.lang.System.exit;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class StartFrame extends JFrame implements ActionListener {

    public final JButton login;
    public final JButton register;
    public final JButton start;
    public final JButton adminPanel;
    private final JButton rules;
    private final JButton exit;
    private final JLabel name;

    static StartFrame s = new StartFrame();
    public static DataBase db;
    public static JComboBox<String> quizzes;
    public static ArrayList<String> qs;

    public static void loadQuizzes() {
        qs = StartFrame.db.getQuizzes();
    }

    StartFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 400);
        this.setResizable(false);
        this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
        this.setUndecorated(true);
        this.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
        this.setLayout(new GridLayout(8, 1, 10, 10));
        Font f = new Font("Castellar", Font.BOLD, 42);
        Font f2 = new Font("Mistral", Font.BOLD, 30);
        Font f3 = new Font("Arial", Font.BOLD, 25);
        name = new JLabel();
        name.setFont(f3);
        name.setText("            QUIZ");
        name.setForeground(Color.GRAY);
        db = new DataBase();

        loadQuizzes();
        quizzes = new JComboBox(qs.toArray());
        quizzes.setEnabled(false);
        quizzes.setFont(f3);
        quizzes.setFocusable(true);
        quizzes.setForeground(Color.BLACK);

        quizzes.addItemListener((ItemEvent ie) -> {
            if (ie.getStateChange() == ItemEvent.SELECTED) {
                
                String selected = quizzes.getSelectedItem().toString();               
                QuizSettings.quizFileName = selected;

                //loadQuiz();
                //resetComponents();
            }
        });

        start = new JButton("START");
        start.setEnabled(false);
        start.setFont(f3);
        start.setFocusable(true);
        //start.setContentAreaFilled(false);
        start.setBorderPainted(false);
        start.setForeground(Color.BLACK);
        start.addActionListener(this);

        adminPanel = new JButton("QUESTION MANAGEMENT");
        adminPanel.setEnabled(false);
        adminPanel.setFont(f3);
        adminPanel.setFocusable(true);
        //adminPanel.setContentAreaFilled(false);
        adminPanel.setBorderPainted(false);
        adminPanel.setForeground(Color.BLUE);
        adminPanel.addActionListener(this);

        login = new JButton("LOGIN");
        login.setFont(f3);
        login.setFocusable(true);
        //login.setContentAreaFilled(false);
        login.setBorderPainted(false);
        login.setForeground(Color.RED);
        login.addActionListener(this);

        register = new JButton("REGISTER");
        register.setFont(f3);
        register.setFocusable(true);
        //register.setEnabled(true);//////////////////////////////
        //register.setContentAreaFilled(false);
        register.setBorderPainted(false);
        register.setForeground(Color.MAGENTA);
        register.addActionListener(this);

        rules = new JButton("RULES");
        rules.setFont(f3);
        rules.setFocusable(true);
        //rules.setEnabled(true);//////////////////////////////
        //rules.setContentAreaFilled(false);
        rules.setBorderPainted(false);
        rules.setForeground(Color.darkGray);
        rules.addActionListener(this);

        exit = new JButton("EXIT");
        exit.setFont(f3);
        exit.setOpaque(false);
        //exit.setContentAreaFilled(false);
        exit.setBorderPainted(false);
        exit.setForeground(Color.ORANGE);
        exit.addActionListener(this);

        add(name);
        add(quizzes);
        add(start);
        add(adminPanel);
        add(login);
        add(register);
        add(rules);
        add(exit);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String n = e.getActionCommand();
        switch (n) {

            case "START": {
                GameFrame gf = new GameFrame();
                gf.setVisible(true);
                this.setVisible(false);
                break;
            }
            case "QUESTION MANAGEMENT": {
                QuizSettings qs = new QuizSettings();
                qs.setVisible(true);
                this.setVisible(false);
                break;
            }
            case "LOGIN": {
                LoginFrame u = new LoginFrame();
                u.setVisible(true);
                this.setVisible(false);
                break;
            }
            case "REGISTER": {
                RegisterFrame r = new RegisterFrame();
                r.setVisible(true);
                this.setVisible(false);
                break;
            }
            case "RULES": {
                RulesFrame r = new RulesFrame();
                r.setVisible(true);
                this.setVisible(false);
                break;
            }
            case "EXIT": {
                StartFrame.db.closeConnection();
                exit(0);
                break;
            }
            default: {
                System.out.println("error");
            }
        }
    }

    public static void main(String[] args) {
        s.setVisible(true);
    }

}
