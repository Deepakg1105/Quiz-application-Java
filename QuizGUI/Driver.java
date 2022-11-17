import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class Driver {
    public static void main(String[] args) throws InterruptedException {
        Database.manuallyAdd();
        Start();

    }

    static int i = 0;
    static int score = 0;

    public static void Start() throws InterruptedException {
        JFrame frameobj = new JFrame(); // creating frame
        frameobj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameobj.setSize(500, 500); // declaring frame size
        frameobj.setTitle("Quiz Management");// declaring frame title

        GridLayout grid = new GridLayout(2, 2);
        // CardLayout cardLayout=new CardLayout(); // layout of the frame
        frameobj.setLayout(grid);
        JPanel p1 = new JPanel(); // creating panels
        JPanel p2 = new JPanel();
        // JPanel p3=new JPanel();

        p1.setBackground(Color.GREEN);
        p2.setBackground(Color.GREEN);

        //label for welcome message
        JLabel l1 = new JLabel("<html><center>WELCOME TO <br><h2>QUIZ MANAGEMENT SYSTEM</h2></center></html>");
        Border emptyBorder = new EmptyBorder(150, 150, 150, 150);
        Border emptyBorder2 = new EmptyBorder(20, 20, 20, 20);
        l1.setBorder(emptyBorder);
        l1.setHorizontalAlignment(JLabel.CENTER);

        JButton b1 = new JButton("Attempt Quiz");   //button for playing the quiz
        JButton b2 = new JButton("Manage Quiz");    //button for editing the quiz questions(add/delete)
        b1.setBorder(emptyBorder2);
        b2.setBorder(emptyBorder2);
        b1.setAlignmentY(Component.CENTER_ALIGNMENT);
        b2.setAlignmentX(Component.CENTER_ALIGNMENT);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openQuizWindow();
            }

        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openManageWindow();
            }
        });
        p1.add(l1);
        p2.add(b1); // add buttons to panel
        p2.add(b2);

        frameobj.add(p1); // add panels to frames
        frameobj.add(p2);

        frameobj.setVisible(true);
    }

    private static void openQuizWindow() {

        JFrame playQuiz = new JFrame();
        playQuiz.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        playQuiz.setSize(500, 500); // declaring frame size
        playQuiz.setTitle("Quiz Page");
        playQuiz.setVisible(true);
        playQuiz.setBounds(0, 0, 500, 500);

        Container c = playQuiz.getContentPane();
        c.setLayout(null);

        JRadioButton b1 = new JRadioButton(Database.get(i).getOption1());
        JRadioButton b2 = new JRadioButton(Database.get(i).getOption2());
        JRadioButton b3 = new JRadioButton(Database.get(i).getOption3());
        JRadioButton b4 = new JRadioButton(Database.get(i).getOption4());
        b1.setSelected(true);
        b1.setBounds(200, 150, 200, 25);
        b2.setBounds(200, 175, 200, 25);
        b3.setBounds(200, 200, 200, 25);
        b4.setBounds(200, 225, 200, 25);
        c.add(b1);
        c.add(b2);
        c.add(b3);
        c.add(b4);

        ButtonGroup optionsButtonGroup = new ButtonGroup();
        optionsButtonGroup.add(b1);
        optionsButtonGroup.add(b2);
        optionsButtonGroup.add(b3);
        optionsButtonGroup.add(b4);

        JLabel qLabel = new JLabel(
                "Questions goes here!!");
        c.add(qLabel);
        qLabel.setBounds(175, 90, 250, 100);

        JButton next = new JButton("Next");
        // next.setBorder(emptyBorder2);
        next.setAlignmentY(Component.CENTER_ALIGNMENT);
        c.add(next);
        next.setBounds(222, 275, 100, 40);

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean result = false;
                if (b1.isSelected()) {
                    result = Database.CheckAnswer(1, i);
                } else if (b2.isSelected()) {
                    result = Database.CheckAnswer(2, i);
                } else if (b3.isSelected()) {
                    result = Database.CheckAnswer(3, i);
                } else {
                    result = Database.CheckAnswer(4, i);
                }
                if (result == true) {
                    score++;
                    System.out.println("score" + String.valueOf(score));
                }
                i++;
                if (i < Database.Size()) {
                    qLabel.setText("<html><center><h3>" + Database.get(i).getQuestionStatement()
                            + "</h3><br></center></html>");
                    b1.setText(Database.get(i).getOption1());
                    b2.setText(Database.get(i).getOption2());
                    b3.setText(Database.get(i).getOption3());
                    b4.setText(Database.get(i).getOption4());

                    System.out.println(i);
                } 
                else 
                {
                    b1.setVisible(false);
                    b2.setVisible(false);
                    b3.setVisible(false);
                    b4.setVisible(false);
                    qLabel.setText("<html><center><h1>Score: </h1><br><h2>" + score + "/" + Database.Size()
                            + "</h2></center></html>");
                    qLabel.setBounds(180, 120, 250, 100);
                    next.setVisible(false);
                    i = 0;
                    score = 0;
                }
            }
        });

    }

    protected static void openManageWindow() {
        JFrame settingsFrame = new JFrame(); // creating frame
        settingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        settingsFrame.setSize(500, 500); // declaring frame size
        settingsFrame.setTitle("Quiz edits");// declaring frame title

        Container cc = settingsFrame.getContentPane();
        cc.setLayout(null);

        JLabel ll1 = new JLabel("<html><center><h1>Manage</h1></center></html>");//label for heading
        JLabel ll2 = new JLabel("<html>Enter Question:</html>");//label for entering question
        JLabel ll3 = new JLabel("<html>Option 1:</html>");//lables for options
        JLabel ll4 = new JLabel("<html>Option 2:</html>");
        JLabel ll5 = new JLabel("<html>Option 3:</html>");
        JLabel ll6 = new JLabel("<html>Option 4:</html>");
        JLabel ll7 = new JLabel("<html>Correct Option number:</html>");//label for answer of the question
        JLabel ll8 = new JLabel("<html>Choose question number to delete from:</html>");//label for heading of deleting a question
        JLabel ll10 = new JLabel("<html>Question number:</html>");//label for deleting question number
        JLabel ll9 = new JLabel("all question ");//display all the question here

        JButton bb1 = new JButton("<html><center>Add<br>Question</center></html>");//button to add-question option
        JButton bb2 = new JButton("<html><center>Delete<br>Question</center></html>");//button to delete-question option
        JButton bSubmitQues = new JButton("<html><center>Submit<br>Question</center></html>");//button to insert a new question;
        JButton deleteQues = new JButton("<html><center>Delete<br>Question</center></html>");//button to delete a existing question

        JTextField tf1 = new JTextField();  //for question input
        JTextField tf2 = new JTextField();  //for option1 input
        JTextField tf3 = new JTextField();  //for option2 input
        JTextField tf4 = new JTextField();  //for option3 input
        JTextField tf5 = new JTextField();  //for option4 input
        JTextField tf6 = new JTextField();  //for correct option input
        JTextField quesNum = new JTextField(); //for input for deleting a question

        ll1.setBounds(200, 150, 100, 50);
        ll2.setBounds(100, 100, 100, 40);
        ll3.setBounds(100, 180, 100, 40);
        ll4.setBounds(100, 210, 100, 40);
        ll5.setBounds(100, 240, 100, 40);
        ll6.setBounds(100, 270, 100, 40);
        ll7.setBounds(100, 310, 100, 40);
        ll8.setBounds(150, 50, 200, 60);
        ll9.setBounds(100, 60, 250, 300);
        ll10.setBounds(200, 305, 60, 40);

        tf1.setBounds(200, 105, 180, 40);
        tf2.setBounds(200, 185, 180, 27);
        tf3.setBounds(200, 215, 180, 27);
        tf4.setBounds(200, 245, 180, 27);
        tf5.setBounds(200, 275, 180, 27);
        tf6.setBounds(200, 315, 180, 27);
        quesNum.setBounds(260, 310, 40, 30);

        bb1.setBounds(140, 220, 100, 50);
        bb2.setBounds(280, 220, 100, 50);
        bSubmitQues.setBounds(200, 355, 100, 50);
        deleteQues.setBounds(200, 350, 100, 50);

        cc.add(ll1);
        cc.add(ll2);
        cc.add(ll3);
        cc.add(ll4);
        cc.add(ll5);
        cc.add(ll6);
        cc.add(ll7);
        cc.add(ll8);
        cc.add(ll9);
        cc.add(ll10);

        cc.add(tf1);
        cc.add(tf2);
        cc.add(tf3);
        cc.add(tf4);
        cc.add(tf5);
        cc.add(tf6);
        cc.add(quesNum);

        cc.add(bb1);
        cc.add(bb2);
        cc.add(bSubmitQues);
        cc.add(deleteQues);

        ll2.setVisible(false);
        ll3.setVisible(false);
        ll4.setVisible(false);
        ll5.setVisible(false);
        ll6.setVisible(false);
        ll7.setVisible(false);
        ll8.setVisible(false);
        ll9.setVisible(false);
        ll10.setVisible(false);

        tf1.setVisible(false);
        tf2.setVisible(false);
        tf3.setVisible(false);
        tf4.setVisible(false);
        tf5.setVisible(false);
        tf6.setVisible(false);
        quesNum.setVisible(false);

        bSubmitQues.setVisible(false);
        deleteQues.setVisible(false);

        bb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bb1.setVisible(false);
                bb2.setVisible(false);
                ll1.setVisible(false);
                ll2.setVisible(true);
                ll3.setVisible(true);
                ll4.setVisible(true);
                ll5.setVisible(true);
                ll6.setVisible(true);
                ll7.setVisible(true);

                tf1.setVisible(true);
                tf2.setVisible(true);
                tf3.setVisible(true);
                tf4.setVisible(true);
                tf5.setVisible(true);
                tf6.setVisible(true);

                bSubmitQues.setVisible(true);
            }

        });
        bb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bb2.setVisible(false);
                bb1.setVisible(false);
                ll1.setVisible(false);

                ll8.setVisible(true);
                ll9.setVisible(true);
                ll10.setVisible(true);
                quesNum.setVisible(true);
                deleteQues.setVisible(true);
                int qsize = Database.Size();
                String[] allQuesString = new String[qsize];
                for (int x = 0; x < qsize; x++) {
                    allQuesString[x] = Database.get(x).getQuestionStatement();
                }
                String finalStr = "<html>";
                for (int x = 0; x < qsize; x++) {
                    finalStr = finalStr + String.valueOf(x + 1) + " " + allQuesString[x] + "<br>";
                }
                finalStr += "</html>";
                ll9.setText(finalStr);

            }
        });

        deleteQues.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int qn = Integer.parseInt(quesNum.getText());
                if (qn <= 0 || qn > Database.Size()) {
                    quesNum.setBackground(Color.red);
                } else {
                    Database.remove(qn - 1);
                    ll1.setVisible(true);
                    bb1.setVisible(true);
                    bb2.setVisible(true);
                    ll8.setVisible(false);
                    ll9.setVisible(false);
                    ll10.setVisible(false);
                    quesNum.setVisible(false);
                    quesNum.setBackground(Color.white);
                    deleteQues.setVisible(false);

                }

            }

        });

        bSubmitQues.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String ques = tf1.getText();
                String op1 = tf2.getText();
                String op2 = tf3.getText();
                String op3 = tf4.getText();
                String op4 = tf5.getText();

                int co = Integer.parseInt(tf6.getText());
                String ans = "";
                if (co == 1)
                    ans = op1;
                else if (co == 2)
                    ans = op2;
                else if (co == 3)
                    ans = op3;
                else
                    ans = op4;

                if (co <= 0 || co > 4 || ques.isEmpty() || op1.isEmpty()||op2.isEmpty()||op3.isEmpty()||op4.isEmpty()) {
                    quesNum.setBackground(Color.red);
                    tf1.setBackground(Color.RED);
                    tf2.setBackground(Color.red);
                    tf3.setBackground(Color.red);
                    tf4.setBackground(Color.red);
                } else {
                    Database.Add(new Question(ques, ans, op1, op2, op3, op4));
                    System.out.println(Database.Size());

                    tf1.setText("");
                    tf2.setText("");
                    tf3.setText("");
                    tf4.setText("");
                    tf5.setText("");

                    ll2.setVisible(false);
                    ll3.setVisible(false);
                    ll4.setVisible(false);
                    ll5.setVisible(false);
                    ll6.setVisible(false);
                    ll7.setVisible(false);

                    tf1.setVisible(false);
                    tf2.setVisible(false);
                    tf3.setVisible(false);
                    tf4.setVisible(false);
                    tf5.setVisible(false);
                    tf6.setVisible(false);

                    quesNum.setBackground(Color.white);
                    tf1.setBackground(Color.white);
                    tf2.setBackground(Color.white);
                    tf3.setBackground(Color.white);
                    tf4.setBackground(Color.white);

                    bSubmitQues.setVisible(false);

                    ll1.setVisible(true);
                    bb1.setVisible(true);
                    bb2.setVisible(true);
                }

            }

        });

        settingsFrame.setVisible(true);
    }
}