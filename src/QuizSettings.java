
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class QuizSettings extends JFrame {

    private JComboBox<String> quizzes;
    private ArrayList<Question> qList = new ArrayList<>();
    private final JButton addButton;
    private final JButton modifyButton;
    private final JButton deleteButton;
    private final JButton picture;

    private final JButton addQuiz;
    private final JButton deleteQuiz;
    private final JTextField insertQuiz;
    private final JLabel q, dif;
    private final JTextField que;
    private final JTextField u_a;
    private final JTextField u_b;
    private final JTextField u_c;
    private final JTextField u_d;
    private final JTextField diff;
    private final JRadioButton ckA;
    private final JRadioButton ckB;
    private final JRadioButton ckC;
    private final JRadioButton ckD;
    private final ButtonGroup bg;
    private String cor;
    private final JPanel p1;
    private final JPanel p2;
    private final JPanel p3;
    private final JPanel pb;
    private final JPanel ps;
    private final JPanel pss;
    Icon img;
    static String quizFileName;
    ArrayList<String> qs;
    JTable table;
    DefaultTableModel model;
    JScrollPane scroll;
    int mod = -1, qid;
    FileOutputStream fos;
    ObjectOutputStream oos;

    void addQuestion() {
        //qList.add(new Question());
        if (que.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ερώτηση;;; ");
            return;
        } else if (u_a.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Απάντηση A;;; ");
            return;
        } else if (u_b.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Απάντηση B;;; ");
            return;
        } else if (u_c.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Απάντηση Γ;;; ");
            return;
        } else if (u_d.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Απάντηση Δ;;; ");
            return;
        } else if (!(ckA.isSelected() || ckB.isSelected() || ckC.isSelected() || ckD.isSelected())) {
            JOptionPane.showMessageDialog(null, "Σωστή απάντηση;;; ");
            return;
        }
        if (ckA.isSelected()) {
            cor = "A";
        } else if (ckB.isSelected()) {
            cor = "B";
        } else if (ckC.isSelected()) {
            cor = "C";
        } else if (ckD.isSelected()) {
            cor = "D";
        }
        if (picture.getText().equals("LOAD PICTURE")) {
            JOptionPane.showMessageDialog(null, "Εικόνα;;; ");
            return;
        }
        if (diff.getText().isEmpty() || diff.getText().isBlank() || diff.getText().equals(" ")) {
            diff.setText("1");
        }
        StartFrame.db.optimizeTable(quizFileName);
        qid = qList.get(qList.size() - 1).getId() + 1;
        model.setRowCount(model.getRowCount());
        qList.add(new Question(qid, que.getText(), u_a.getText(), u_b.getText(), u_c.getText(), u_d.getText(), cor, picture.getText(), diff.getText()));
        model.addRow(new Object[]{qid, que.getText(), u_a.getText(), u_b.getText(), u_c.getText(), u_d.getText(), cor, picture.getText(), diff.getText()});
        model.fireTableDataChanged();
        quizFileName = quizzes.getSelectedItem().toString();
        StartFrame.db.insertQuestion(quizFileName, que.getText(), u_a.getText(), u_b.getText(), u_c.getText(), u_d.getText(), cor, picture.getText(), diff.getText());

    }

    public void modifyQuestion() {

        if (ckA.isSelected()) {
            cor = "A";
        } else if (ckB.isSelected()) {
            cor = "B";
        } else if (ckC.isSelected()) {
            cor = "C";
        } else if (ckD.isSelected()) {
            cor = "D";
        }
        if (diff.getText().isEmpty() || diff.getText().isBlank() || diff.getText().equals(" ")) {
            diff.setText("1");
        }

        if (mod > -1) {
            model.setRowCount(model.getRowCount());
            qList.set(mod, new Question(qid, que.getText(), u_a.getText(), u_b.getText(), u_c.getText(), u_d.getText(), cor, picture.getText(), diff.getText()));
            model.insertRow(mod, new Object[]{qid, que.getText(), u_a.getText(), u_b.getText(), u_c.getText(), u_d.getText(), cor, picture.getText(), diff.getText()});
            model.removeRow(mod + 1);
            model.fireTableDataChanged();
            quizFileName = quizzes.getSelectedItem().toString();
            StartFrame.db.modifyQuestion(quizFileName, que.getText(), u_a.getText(), u_b.getText(), u_c.getText(), u_d.getText(), cor, picture.getText(), diff.getText(), qid);
        }

    }

    public void deleteQuestion() {

        if (mod > -1) {
            qList.set(mod, new Question(qid, que.getText(), u_a.getText(), u_b.getText(), u_c.getText(), u_d.getText(), cor, picture.getText(), diff.getText()));
            qList.remove(mod);
            model.removeRow(mod);
            model.fireTableDataChanged();
            quizFileName = quizzes.getSelectedItem().toString();
            StartFrame.db.deleteQuestion(quizFileName, qid);
        }
    }

    void loadQuiz() {
        if (quizFileName == null) {
            quizFileName = "db";
        }
        qList = new ArrayList<>();
        qList.addAll(StartFrame.db.questionslist(quizFileName));
        model.setRowCount(0);
        for (int i = 0; i < qList.size(); i++) {
            model.addRow(new Object[]{qList.get(i).getId(), qList.get(i).getQuestion(), qList.get(i).getAnswerA(), qList.get(i).getAnswerB(), qList.get(i).getAnswerC(), qList.get(i).getAnswerD(), qList.get(i).getCorrectAnswer(), qList.get(i).getImage(), qList.get(i).getDifficulty()});
        }
    }

    void loadPicture() {
        JFileChooser chooser;
        chooser = new JFileChooser();
        File a = new File("./PICTURES/");
        chooser.setCurrentDirectory(a);
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp"));
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String filename = chooser.getSelectedFile().getName();
            String filepath = chooser.getSelectedFile().getAbsolutePath();
            BufferedImage imge;
            imge = null;
            try {
                imge = ImageIO.read(new File(filepath));
            } catch (IOException e) {
            }
            ImageIcon icon = new ImageIcon(imge);
            picture.setIcon(icon);
            picture.setText(filename);
            try {
                File outputfile = new File("PICTURES/" + filename);
                ImageIO.write(imge, "bmp", outputfile);
            } catch (IOException e) {
            }
        }
    }

    public void checkField() {
        boolean flag = false;
        if (ckA.isSelected() || ckB.isSelected() || ckC.isSelected() || ckD.isSelected()) {
            flag = true;
        }
        if (que.getText().isEmpty() || u_a.getText().isEmpty() || u_b.getText().isEmpty() || u_c.getText().isEmpty() || u_d.getText().isEmpty() || picture.getText().equals("LOAD PICTURE") || diff.getText().isEmpty() || !flag || !(quizzes.getSelectedIndex() >= 0)) {
            //addButton.setEnabled(false);
            modifyButton.setEnabled(false);
        } else if (mod > -1) {
            addButton.setEnabled(true);
            modifyButton.setEnabled(true);
        } else {
            addButton.setEnabled(true);
            modifyButton.setEnabled(false);
        }
    }

    public void resetComponents() {
        que.setText(null);
        u_a.setText(null);
        u_b.setText(null);
        u_c.setText(null);
        u_d.setText(null);
        bg.clearSelection();
        picture.setText("LOAD PICTURE");
        picture.setIcon(null);
        //addButton.setEnabled(false);
        deleteButton.setEnabled(false);
        modifyButton.setEnabled(false);
        diff.setText(null);
        mod = -1;
    }

    public void addQuiz() {
        if (!insertQuiz.getText().isBlank() || !insertQuiz.getText().isEmpty()) {
            StartFrame.db.insertQuiz(insertQuiz.getText().toLowerCase());
            String tmp = insertQuiz.getText();
            quizzes.addItem(tmp);
            quizzes.setSelectedItem(tmp);
            StartFrame.quizzes.addItem(tmp);
            StartFrame.quizzes.setSelectedItem(tmp);
        } else {
            JOptionPane.showMessageDialog(null, "Όνομα Quiz;;;");
        }
        //loadQuiz();
    }

    public QuizSettings() {
        this.setTitle("Quiz by Anna Maria Gianni");
        setSize(800, 800);
        setResizable(false);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                StartFrame.s.setVisible(true);
            }
        });
        this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
        setLayout(new GridLayout(3, 1));
        insertQuiz = new JTextField();
        insertQuiz.setText("Quiz name");
        p1 = new JPanel();
        p1.setLayout(new BorderLayout());
        p2 = new JPanel();
        p2.setLayout(new GridLayout(13, 1, 0, 0));
        p3 = new JPanel();
        p3.setLayout(new FlowLayout());
        p3.setPreferredSize(new Dimension(200, 200));
        pb = new JPanel();
        pb.setLayout(new GridLayout(1, 3, 0, 0));
        ps = new JPanel(new GridLayout(1, 4, 0, 0));
        quizzes = new JComboBox(StartFrame.qs.toArray());
        if (quizFileName == null) {
            quizFileName = "db";
        }
        quizzes.setSelectedItem(quizFileName);
        StartFrame.quizzes.setSelectedItem(quizFileName);
        quizzes.addItemListener((ItemEvent ie) -> {
            if (ie.getStateChange() == ItemEvent.SELECTED) {
                String selected = quizzes.getSelectedItem().toString();
                quizFileName = selected;
                StartFrame.quizzes.setSelectedItem(selected);
                loadQuiz();
                resetComponents();
            }
        });
        addQuiz = new JButton("ADD QUIZ");
        deleteQuiz = new JButton("DELETE QUIZ");
        deleteQuiz.setForeground(Color.red);
        pss = new JPanel();
        pss.setBackground(Color.GREEN);
        pss.setLayout(new GridLayout(1, 2, 0, 0));
        pss.add(insertQuiz);
        pss.add(addQuiz);
        ps.add(quizzes);
        ps.add(pss);
        ps.add(deleteQuiz);
        addQuiz.addActionListener((ActionEvent ae) -> {
            addQuiz();
            //QuizSettings.this.loadQuiz();
            StartFrame.loadQuizzes();
        });
        deleteQuiz.addActionListener((ActionEvent ae) -> {
            String str = quizzes.getSelectedItem().toString();
            StartFrame.db.deleteQuiz(str);
            quizzes.removeItem(str);
            StartFrame.quizzes.removeItem(str);
            StartFrame.loadQuizzes();
        });

        picture = new JButton("LOAD PICTURE");
        p1.add(ps, BorderLayout.NORTH);
        p1.add(picture, BorderLayout.CENTER);
        picture.addActionListener((ActionEvent ae) -> {
            loadPicture();
            checkField();
        });
        add(p1);
        add(p2);
        add(p3);
        q = new JLabel("  QUESTION");
        dif = new JLabel("  DIFFICULTY (1:Eazy, 2:Normal, 3:Difficult)");
        que = new JTextField();
        que.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                checkField();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkField();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                checkField();
            }
        });
        u_a = new JTextField();
        u_a.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                checkField();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkField();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                checkField();
            }
        });
        u_b = new JTextField();
        u_b.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                checkField();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkField();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                checkField();
            }
        });
        u_c = new JTextField();
        u_c.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                checkField();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkField();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                checkField();
            }
        });
        u_d = new JTextField();
        u_d.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                checkField();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkField();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                checkField();
            }
        });
        diff = new JTextField();
        diff.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                checkField();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkField();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                checkField();
            }
        });
        ckA = new JRadioButton("ANSWER A");
        ckA.addActionListener((ActionEvent ae) -> {
            checkField();
        });
        ckB = new JRadioButton("ANSWER B");
        ckB.addActionListener((ActionEvent ae) -> {
            checkField();
        });
        ckC = new JRadioButton("ANSWER C");
        ckC.addActionListener((ActionEvent ae) -> {
            checkField();
        });
        ckD = new JRadioButton("ANSWER D");
        ckD.addActionListener((ActionEvent ae) -> {
            checkField();
        });
        bg = new ButtonGroup();
        bg.add(ckA);
        bg.add(ckB);
        bg.add(ckC);
        bg.add(ckD);
        p2.add(q);
        p2.add(que);
        p2.add(ckA);
        p2.add(u_a);
        p2.add(ckB);
        p2.add(u_b);
        p2.add(ckC);
        p2.add(u_c);
        p2.add(ckD);
        p2.add(u_d);
        p2.add(dif);
        p2.add(diff);

        //pb.add(quizzes);
        addButton = new JButton("ADD QUESTION");
        addButton.addActionListener((ActionEvent ae) -> {
            addQuestion();
            resetComponents();
        });
        pb.add(addButton);
        modifyButton = new JButton("MODIFY QUESTION");
        modifyButton.addActionListener((ActionEvent ae) -> {
            modifyQuestion();
            resetComponents();
        });
        pb.add(modifyButton);
        deleteButton = new JButton("DELETE QUESTION");
        deleteButton.addActionListener((ActionEvent ae) -> {
            deleteQuestion();
            resetComponents();
        });
        pb.add(deleteButton);

        p2.add(pb);
        table = new JTable();
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"id", "question", "answerA", "answerB", "answerC", "answerD", "correctAnswer", "image", "difficulty"});
        table.setModel(model);
        table.setDefaultEditor(Object.class, null);
        scroll = new JScrollPane(table);

        table.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
            mod = -1;
            if (table.getSelectedRow() > -1) {
                qid = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
                que.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
                u_a.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
                u_b.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
                u_c.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
                u_d.setText(table.getValueAt(table.getSelectedRow(), 5).toString());
                String correct = (String) (table.getValueAt(table.getSelectedRow(), 6).toString());
                switch (correct) {
                    case "A":
                        ckA.setSelected(true);
                        cor = "A";
                        break;
                    case "B":
                        ckB.setSelected(true);
                        cor = "B";
                        break;
                    case "C":
                        ckC.setSelected(true);
                        cor = "C";
                        break;
                    case "D":
                        ckD.setSelected(true);
                        cor = "D";
                        break;
                }
                picture.setText(table.getValueAt(table.getSelectedRow(), 7).toString());
                try {
                    BufferedImage im;
                    im = ImageIO.read(new File("PICTURES/" + table.getValueAt(table.getSelectedRow(), 7).toString()));
                    ImageIcon icon = new ImageIcon(im);
                    QuizSettings.this.picture.setIcon(icon);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                diff.setText(table.getValueAt(table.getSelectedRow(), 8).toString());
            }
            mod = table.getSelectedRow();
            deleteButton.setEnabled(true);
            modifyButton.setEnabled(true);
        });
        Dimension dim = new Dimension(790, 248);
        scroll.setPreferredSize(dim);
        p3.add(scroll);
        //addButton.setEnabled(false);
        deleteButton.setEnabled(false);
        modifyButton.setEnabled(false);
        loadQuiz();
    }
}
