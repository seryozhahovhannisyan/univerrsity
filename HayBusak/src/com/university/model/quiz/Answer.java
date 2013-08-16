package com.university.model.quiz;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 13.08.13
 * Time: 0:24
 * To change this template use File | Settings | File Templates.
 */
public class Answer {

    /**
     * id           int(7) unsigned  (NULL)     NO      PRI     (NULL)   auto_increment  select,insert,update,references
     * value        text             utf8_bin   NO              (NULL)                   select,insert,update,references
     * is_correct   int(1) unsigned  (NULL)     NO              (NULL)                   select,insert,update,references
     * question_id  int(7) unsigned  (NULL)     NO      MUL     (NULL)                   select,insert,update,references
     */
    private int id;
    private String value;
    private boolean isCorrect;
    private Question question;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(boolean correct) {
        isCorrect = correct;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", isCorrect=" + isCorrect +
                ", question=" + question +
                '}';
    }
}
