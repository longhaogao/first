package QuizCardPlayer;

public class QuizCard {
    private String question;
    private String answer;
    private String test;

    QuizCard(String q,String a){
        q=question;
        a=answer;
    }
    public void setTest(String test){
        this.test=test;
    }
    public String getQuestion(){
        return question;
    }
    public String getAnswer(){
        return answer;
    }
}
