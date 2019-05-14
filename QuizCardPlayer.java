package QuizCardPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
public class QuizCardPlayer {

    private JTextArea display;
    private  JTextArea answer;
    private ArrayList<QuizCard> cardlist;
    private QuizCard currentcard;
    private  int currentcardindex;
    private JFrame frame;
    private JButton nextbutton;
    private  boolean isshowanswer;

    public static void main(String[]args){
        QuizCardPlayer reader=new QuizCardPlayer();
        reader.go();
    }
    public void go(){
        frame=new JFrame("QuizCardPlayer");
        JPanel mainPanel=new JPanel();
        Font big=new Font("sanserif",Font.BOLD,24);
        display=new JTextArea(10,20);
        answer=new JTextArea(10,20);
        display.setFont(big);
        display.setLineWrap(true);
        display.setEditable(false);
        JScrollPane js=new JScrollPane(display);
        js.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        js.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        nextbutton=new JButton("show question");
        mainPanel.add(js);
        mainPanel.add(nextbutton);
        nextbutton.addActionListener(new NextCardListener());
        JMenuBar menuBar=new JMenuBar();
        JMenu fileMenu=new JMenu("file");
        JMenuItem loadMenuItem=new JMenuItem("load card set");
        loadMenuItem.addActionListener(new OpenMenuListener());
        fileMenu.add(loadMenuItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(BorderLayout.CENTER,mainPanel);
        frame.setSize(640,500);
        frame.setVisible(true);

    }

    public class NextCardListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(isshowanswer){
                display.setText(currentcard.getAnswer());
                nextbutton.setText("Next Card");
                isshowanswer=false;
            }
            else{
                if(currentcardindex<cardlist.size()){
                    showNextcard();
                }
                else{
                    display.setText("NO card");
                    nextbutton.setEnabled(false);
                }
            }
        }
    }

    public class OpenMenuListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileopen=new JFileChooser();
            fileopen.showOpenDialog(frame);
            loadFile(fileopen.getSelectedFile());
        }
    }


    private void loadFile(File file){
        cardlist=new ArrayList<QuizCard>();
        try{
            BufferedReader reader=new BufferedReader(new FileReader(file));
            String line=null;
            while((line=reader.readLine())!=null){
                makeCard(line);
            }
            reader.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void makeCard(String linetoparse){
        String[] result=linetoparse.split("/");
        QuizCard card=new QuizCard(result[0],result[1]);
        cardlist.add(card);
        System.out.println("made a card");
    }
    private void showNextcard(){
        currentcard=cardlist.get(currentcardindex);
        currentcardindex++;
        display.setText(currentcard.getQuestion());
        nextbutton.setText("show Answer");
        isshowanswer=true;
    }
}

