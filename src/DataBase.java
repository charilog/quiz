
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataBase {

    public static Connection conn = null;
    String host = "jdbc:mysql://xxxxx:xxxx/quiz?useSSL=false&useUnicode=yes&characterEncoding=UTF-8", dbuser = "xxx", dbpass = "xxx";
    private String name;
    private String lastname;
    private String email;
    public static String user;
    private String password;
    private String admin;

    public String getUserName() {
        return name;
    }

    DataBase() {
        try {

            conn = DriverManager.getConnection(host, dbuser, dbpass);
            if (conn != null) {
                System.out.println("connection established.");
            } else {
                System.out.println("Error in connection in database.");
            }
        } catch (SQLException ex) {
            System.out.println("Connection was not established");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            System.out.println("\n\n***** START STACK ******\n");
            ex.printStackTrace();
            System.out.println("\n***** END STACK ******");

        }
    }

    public void select(String table) {
        try {
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery("select * from " + table);
            ResultSetMetaData rmeta = r.getMetaData();
            int n = rmeta.getColumnCount();
            for (int i = 1; i <= n; i++) {
                if (i < n) {
                    System.out.print(rmeta.getColumnName(i) + " ");
                } else {
                    System.out.println(rmeta.getColumnName(i));
                }
            }
            while (r.next()) {
                for (int i = 1; i <= n; i++) {
                    if (i < n) {
                        System.out.print(r.getString(i) + " ");
                    } else {
                        System.out.println(r.getString(i).trim());
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public boolean findUserIfExist(String tbl, String usr) {
        try {
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery("select * from " + tbl + " where user='" + usr + "'");

            if (r.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public void insertUser(String n, String l, String e, String u, String p) {
        try {
            Statement s = conn.createStatement();
            s.executeUpdate("INSERT INTO users (name, lastname, email, user, password)" + " VALUES ('" + n + "','" + l + "','" + e + "','" + u + "','" + p + "')");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public boolean checkUser(String tbl, String usr, String pwd) {
        try {
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery("select * from " + tbl + " where BINARY user='" + usr + "' and BINARY password='" + pwd + "'");

            if (r.next()) {

                this.name = r.getString("name");
                this.lastname = r.getString("lastname");
                this.email = r.getString("email");
                this.user = r.getString("user");
                this.password = r.getString("password");
                this.admin = r.getString("admin");
                StartFrame.s.start.setEnabled(true);
                if (this.admin.equals("yes")) {
                    StartFrame.s.adminPanel.setEnabled(true);
                }
                StartFrame.s.quizzes.setEnabled(true);
                StartFrame.s.invalidate();
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean checkUserIfAdmin(String usr) {
        try {
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery("select * from users where user='" + usr + "'");

            if (r.next()) {
                this.admin = r.getString("admin");
                if (this.admin == "yes") {
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }

    public void insertStats(String u, String qz, String t, String tp, int s, int q, int c, int h, int hh, int co) {
        try {
            Statement st = conn.createStatement();
            //st.executeUpdate("INSERT INTO statistics (user,time,duration,score,questions,correctanswers,help,half)" + " VALUES ('" + u + "'," + " NOW() " + ", NOW() ,'" + s + "','" + q + "','" + c + "','" + h + "','" + hh + "')");
            //st.executeUpdate("INSERT INTO statistics (user,quiz,time,duration,score,questions,correctanswers,help,half)" + " VALUES ('" + u + "','" + qz + "'," + " NOW() " + ",'" + tp + "' ,'" + s + "','" + q + "','" + c + "','" + h + "','" + hh + "')");
            st.executeUpdate("INSERT INTO statistics (user,quiz,time,duration,score,questions,correctanswers,help,half,completed)" + " VALUES ('" + u + "','" + qz + "'," + " NOW() " + ",'" + tp + "' ,'" + s + "','" + q + "','" + c + "','" + h + "','" + hh + "','" + co + "')");
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public static List<Object> lSettings(String qq) {
        String multiply = null;
        String mqt = null;
        String nhalf = null;
        String help = null;
        String nlives = null;
        String plus = null;
        String qt = null;
        String lives = null;
        String nhelp = null;
        String dly = null;
        String half = null;
        String clevel = null;
        try {
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery("select * from settings WHERE quiz = '" + qq + "'");

            while (r.next()) {
                multiply = r.getString("multiply");
                mqt = r.getString("mqt");
                nhalf = r.getString("nhalf");
                help = r.getString("help");
                nlives = r.getString("nlives");
                plus = r.getString("plus");
                qt = r.getString("qt");
                lives = r.getString("lives");
                nhelp = r.getString("nhelp");
                dly = r.getString("dly");
                half = r.getString("half");
                clevel = r.getString("clevel");
            }
        } catch (SQLException e) {

        }

        return Arrays.asList(multiply, mqt, nhalf, help, nlives, plus, qt, lives, nhelp, dly, half, clevel);
    }

    public void insertQuestion(String qName, String question, String answerA, String answerB, String answerC, String answerD, String correctAnswer, String image, String difficulty) {
        try {
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO " + qName + " (question, answerA, answerB, answerC, answerD, correctAnswer, image, difficulty)" + " VALUES ('" + question + "','" + answerA + "','" + answerB + "','" + answerC + "','" + answerD + "','" + correctAnswer + "','" + image + "','" + difficulty + "')");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void modifyQuestion(String qName, String question, String answerA, String answerB, String answerC, String answerD, String correctAnswer, String image, String difficulty, int location) {
        try {
            String query = "UPDATE " + qName + " SET question=?, answerA=?, answerB=?, answerC=?, answerD=?, correctAnswer=?, image=?, difficulty=? WHERE id=?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, question);
            preparedStmt.setString(2, answerA);
            preparedStmt.setString(3, answerB);
            preparedStmt.setString(4, answerC);
            preparedStmt.setString(5, answerD);
            preparedStmt.setString(6, correctAnswer);
            preparedStmt.setString(7, image);
            preparedStmt.setString(8, difficulty);
            preparedStmt.setInt(9, location);
            preparedStmt.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public ArrayList<Question> questionslist(String qName, int level) {

        ArrayList<Question> qList = new ArrayList<>();
        int id;
        String question = null;
        String answerA = null;
        String answerB = null;
        String answerC = null;
        String answerD = null;
        String correctAnswer = null;
        String image = null;
        String difficulty = null;
        try {
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery("select * from " + qName + " where difficulty = " + level + "");
            while (r.next()) {
                id = r.getInt("id");
                question = r.getString("question");
                answerA = r.getString("ansWerA");
                answerB = r.getString("ansWerB");
                answerC = r.getString("ansWerC");
                answerD = r.getString("ansWerD");
                correctAnswer = r.getString("correctAnswer");
                image = r.getString("image");
                difficulty = r.getString("difficulty");
                qList.add(new Question(id, question, answerA, answerB, answerC, answerD, correctAnswer, image, difficulty));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return qList;
    }

    public ArrayList<Question> questionslist(String qName) {

        ArrayList<Question> qList = new ArrayList<>();
        int id;
        String question = null;
        String answerA = null;
        String answerB = null;
        String answerC = null;
        String answerD = null;
        String correctAnswer = null;
        String image = null;
        String difficulty = null;
        try {
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery("select * from " + qName + "");
            while (r.next()) {
                id = r.getInt("id");
                question = r.getString("question");
                answerA = r.getString("ansWerA");
                answerB = r.getString("ansWerB");
                answerC = r.getString("ansWerC");
                answerD = r.getString("ansWerD");
                correctAnswer = r.getString("correctAnswer");
                image = r.getString("image");
                difficulty = r.getString("difficulty");
                qList.add(new Question(id, question, answerA, answerB, answerC, answerD, correctAnswer, image, difficulty));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return qList;
    }

    public void deleteQuestion(String qName, int location) {
        try {
            String query = "DELETE FROM " + qName + " WHERE id = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, location);
            preparedStmt.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void optimizeTable(String tableName) {
        try {
            String query = "OPTIMIZE " + tableName + " EMPLOYEE\\G";
            Statement s = conn.createStatement();
            s.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public ArrayList<String> getQuizzes() {
        ArrayList<String> quizzes = new ArrayList<>();
        String quiz = null;

        try {
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery("select quiz from settings");
            while (r.next()) {
                quiz = r.getString("quiz");
                quizzes.add(quiz);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return quizzes;
    }

    public void insertQuiz(String q) {
        int numberRow = 0;
        try {

            Statement s = conn.createStatement();
            String sql1 = "SELECT COUNT(*) FROM settings WHERE quiz='" + q + "'";
            ResultSet r = s.executeQuery(sql1);
            while (r.next()) {
                numberRow = r.getInt("count(*)");
                //numberRow++;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        //System.out.println("rows=" + numberRow);
        if (numberRow == 0) {

            try {
                Statement s = conn.createStatement();
                s.executeUpdate("INSERT INTO settings (quiz)" + " VALUES ('" + q + "')");
                String sql = "CREATE TABLE if not exists " + q + " (id int(30) NOT NULL AUTO_INCREMENT, question varchar(255) COLLATE utf8_unicode_ci NOT NULL,"
                        + "  answerA varchar(255) COLLATE utf8_unicode_ci NOT NULL,"
                        + "  answerB varchar(255) COLLATE utf8_unicode_ci NOT NULL,"
                        + "  answerC varchar(255) COLLATE utf8_unicode_ci NOT NULL,"
                        + "  answerD varchar(255) COLLATE utf8_unicode_ci NOT NULL,"
                        + "  correctAnswer varchar(255) COLLATE utf8_unicode_ci NOT NULL,"
                        + "  image varchar(255) COLLATE utf8_unicode_ci NOT NULL,\n"
                        + "  difficulty varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '1',"
                        + "PRIMARY KEY ( id )"
                        + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;";
                s.executeUpdate(sql);
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            try {
                insertQuestion(q, "Ερώτηση", "Απάντηση Α", "Απάντηση Β", "Απάντηση Γ", "Απάντηση Δ", "A", "quiz.png", "1");
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else {
            System.out.println("Ο πίνακας υπάρχει ήδη ή έχει ακατάλληλο όνομα!");
        }
    }

    public void deleteQuiz(String quiz) {
        try {
            String query = "DELETE FROM settings WHERE quiz = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, quiz);
            preparedStmt.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        try {
            String query = "DROP TABLE " + quiz;
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            //preparedStmt.setString(1, quiz);
            preparedStmt.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public int getBestScore(String qName) {
        int score = 0;
        try {
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery("SELECT MAX(score) AS s FROM statistics WHERE quiz='" + qName + "'");
            while (r.next()) {
                score = r.getInt("s");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return score;
    }

    public int getMaxLevel(String q) {
        int level = 1;
        try {
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery("SELECT MAX(difficulty) AS s FROM " + q + "");
            while (r.next()) {
                level = r.getInt("s");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return level;
    }
}
