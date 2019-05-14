package QuizCardPlayer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.swing.*;
import java.util.*;


public class QuizCardBuilder {

    private JTextArea question;
    private JTextArea answer;
    private ArrayList<QuizCard> cardlist;
    private JFrame frame;

    public static void main(String[]args) {
        QuizCardBuilder builder=new QuizCardBuilder();
        builder.go();
        System.
    }

    public void go() {
        frame=new JFrame("QuizCardBuilder");
        JPanel mainPanel=new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        Font bigFont=new Font("sanserif",Font.BOLD,24);
        question=new JTextArea(6,20);
        question.setLineWrap(true);
        question.setWrapStyleWord(true);
        question.setFont(bigFont);

        JScrollPane js=new JScrollPane(question);
        js.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        js.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        answer=new JTextArea(6,20);
        answer.setLineWrap(true);
        answer.setWrapStyleWord(true);
        answer.setFont(bigFont);

        JScrollPane js2=new JScrollPane(answer);
        js2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        js2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JButton nextbutton=new JButton("Next Card");
        cardlist=new ArrayList<QuizCard>();

        JLabel qLebel=new JLabel("Question");
        qLebel.setFont(bigFont);
        JLabel aLebel=new JLabel("Answer");
        aLebel.setFont(bigFont);

        mainPanel.add(qLebel);
        mainPanel.add(js);
        mainPanel.add(aLebel);
        mainPanel.add(js2);
        mainPanel.add(nextbutton);
        nextbutton.addActionListener(new NextCardListener());

        JMenuBar menuBar=new JMenuBar();
        JMenu filemenu=new JMenu("File");
        JMenuItem newMenuItem=new JMenuItem("New");
        JMenuItem saveMenuItem=new JMenuItem("Save");
        newMenuItem.addActionListener(new NewMenuListener());
        saveMenuItem.addActionListener(new SaveMenuListener());

        filemenu.add(newMenuItem);
        filemenu.add(saveMenuItem);
        menuBar.add(filemenu);

        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        frame.setSize(500, 600);
        frame.setVisible(true);



    }

    private class NextCardListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent arg0) {
            QuizCard card=new QuizCard(question.getText(),answer.getText());
            cardlist.add(card);
            clearCard();

        }


    }


    private class NewMenuListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            cardlist.clear();
            clearCard();
        }

    }
    private class SaveMenuListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            QuizCard card=new QuizCard(question.getText(),answer.getText());
            cardlist.add(card);

            JFileChooser fileSave=new JFileChooser();
            fileSave.showSaveDialog(frame);
            saveFile(fileSave.getSelectedFile());

        }

    }
    private void clearCard() {
        question.setText("");
        answer.setText("");
        question.requestFocus();
    }

    private void saveFile(File file) {
        try {
            BufferedWriter writer=new BufferedWriter(new FileWriter(file));

            for(QuizCard card:cardlist) {
                writer.write(card.getQuestion()+"/");
                writer.write(card.getAnswer()+"\n");


            }
            writer.close();
        }catch(Exception x) {
            x.printStackTrace();
        }
    }
}
