
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class RulesFrame extends JFrame {

    private final JTextArea area;
    private final JButton back;

    RulesFrame() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                StartFrame.s.setVisible(true);
            }
        });
        this.setTitle("Quiz by Anna Maria Gianni");
        setSize(700, 700);
        setResizable(false);
        this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
        //this.setUndecorated(true);
        //this.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
        this.setLayout(new BorderLayout());
        Font f = new Font("Mistral", Font.BOLD, 18);
        Font f2 = new Font("Castellar", Font.BOLD, 25);;
        String text = "<p align = \"center\" style=\"color:red; font-size: 17px; font-family:mistral;\">Κανόνες παιχνιδιού:</p>\n"
                + "<ul>\n"
                + "<li style=\"color:yellow; font-size: 17px; font-family:mistral;\" align=\"justify\">Για να μπορείς να παίξεις το παιχνίδι θα πρέπει να καταχωρηθείς πατώντας την επιλογή “REGISTER”.\n"
                + "<li style=\"color:yellow; font-size: 17px; font-family:mistral;\" align=\"justify\">Για να ξεκινήσεις το παιχνίδι θα πρέπει να πατήσεις «LOGIN», να βάλεις τα στοιχεία εισόδου, να επιλέξεις την κατηγορία που θες και μετά να πατήσεις «START».\n"
                + "<li style=\"color:yellow; font-size: 17px; font-family:mistral;\" align=\"justify\">Εάν ξεκινήσετε ένα quiz αυτό θα πρέπει να ολοκληρωθεί χωρίς παύση διότι ο χρόνος απάντησης συμμετέχει στην βαθμολογία του παιχνιδιού.\n"
                + "<li style=\"color:yellow; font-size: 17px; font-family:mistral;\" align=\"justify\">Εάν δεν γνωρίζεται μια ερώτηση μπορείτε να χρησιμοποιήσετε το κουμπί με τον λαμπτήρα στα δεξιά εάν είναι ενεργοποιημένο ώστε να απαντηθεί σωστά. \n"
                + "<li style=\"color:yellow; font-size: 17px; font-family:mistral;\" align=\"justify\">Εάν δεν είστε σίγουρος για την σωστή απάντηση μπορείτε να χρησιμοποιήσετε τα «κερασάκια» από τα αριστερά τα οποία μειώνουν στα μισά της επιλογές των απαντήσεων.\n"
                + "<li style=\"color:yellow; font-size: 17px; font-family:mistral;\" align=\"justify\">Κατά την διάρκεια του παιχνιδιού και όσο ανεβαίνει η βαθμολογία κερδίζεται ζωές (καρδούλες), έτοιμες απαντήσεις (λαμπτήρες) και βοήθειες (κερασάκια)\n"
                + "<li style=\"color:yellow; font-size: 17px; font-family:mistral;\" align=\"justify\">Κατά την διάρκεια του παιχνιδιού μπορείτε να βλέπετε το υπόλοιπο από το καλύτερο score για το συγκεκριμένο quiz.\n"
                + "<li style=\"color:yellow; font-size: 17px; font-family:mistral;\" align=\"justify\">Μετά από ένα αριθμό απαιτήσεων το επίπεδο δυσκολίας ανεβαίνει και εμφανίζεται με σχετικό γραφικό μήνυμα.\n"
                + "<li style=\"color:yellow; font-size: 17px; font-family:mistral;\" align=\"justify\">Για να κάθε σωστή απάντηση κερδίζεις 200 ποντους + τους κάποιους επιπλέον που εξαρτώνται απο την ταχύτητα της αντιδρασή σου. \n"
                + "<li style=\"color:yellow; font-size: 17px; font-family:mistral;\" align=\"justify\">Εάν δεν ολοκληρώσετε ένα παιχνίδι η προσπάθεια σας θα καταγραφεί ως μη ολοκληρωμένη.\n"
                + "<li style=\"color:yellow; font-size: 17px; font-family:mistral;\" align=\"justify\">Στο τέλος του παιχνιδιού ανοίγει αυτόματα ο περιηγητής ώστε να μπορείς να δεις την καταταξή σου στου παιχνίδι. \n"
                + "</ul> \n"
                + "<p align = \"center\" style=\"color:red; font-size: 17px; font-family:mistral;\">Χρήσιμες πληροφορίες:</p>\n"
                + "<ul>\n"
                + "<li style=\"color:green; font-size: 17px; font-family:mistral;\" align=\"justify\">Αρχικές ζωές 3\n"
                + "<li style=\"color:green; font-size: 17px; font-family:mistral;\" align=\"justify\">Έτοιμες απαντήσεις 33\n"
                + "<li style=\"color:green; font-size: 17px; font-family:mistral;\" align=\"justify\">Αρχικά κερασάκια \n"
                + "<li style=\"color:green; font-size: 17px; font-family:mistral;\" align=\"justify\">Ζωές στα 5000,10000,15000,20000\n"
                + "<li style=\"color:green; font-size: 17px; font-family:mistral;\" align=\"justify\">Λαμπτήρες στα 3000,6000,9000,12000\n"
                + "<li style=\"color:green; font-size: 17px; font-family:mistral;\" align=\"justify\">Κερασάκια στα 2000,4000,8000,10000\n"
                + "<li style=\"color:green; font-size: 17px; font-family:mistral;\" align=\"justify\">Πολλαπλασιαστής γρήγορης απάντησης στην βαθμολογία 2\n"
                + "<li style=\"color:green; font-size: 17px; font-family:mistral;\" align=\"justify\">Πολλαπλασιαστής Χρόνου 2\n"
                + "<li style=\"color:green; font-size: 17px; font-family:mistral;\" align=\"justify\">Κέρδος σωστής απάντησης 200\n"
                + "<li style=\"color:green; font-size: 17px; font-family:mistral;\" align=\"justify\">Χρόνος ερώτησης 130\n"
                + "<li style=\"color:green; font-size: 17px; font-family:mistral;\" align=\"justify\">Χρόνος καθυστέρησης για την επόμενη ερώτηση 60\n"
                + "<li style=\"color:green; font-size: 17px; font-family:mistral;\" align=\"justify\">Ανά 30 σωστές απαντήσεις ανεβαίνεις επίπεδο\n"
                + "</ul>\n"
                + "<p align = \"center\" style=\"color:red; font-size: 17px; font-family:mistral;\">Καλή διασκέδαση!</p>";
        area = new JTextArea(5, 30);
        JEditorPane ep = new JEditorPane();
        ep.setContentType("text/html");
        ep.setText(text);
        ep.setBackground(Color.BLACK);
        ep.setFont(f);
        JScrollPane scrollPane = new JScrollPane(ep);

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
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(back, BorderLayout.SOUTH);
    }

}
