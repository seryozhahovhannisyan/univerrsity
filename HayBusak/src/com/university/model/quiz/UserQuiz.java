package com.university.model.quiz;

import com.university.model.account.User;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 13.08.13
 * Time: 0:28
 * To change this template use File | Settings | File Templates.
 */
public class UserQuiz {
    /**
     * id                    int(7) unsigned  (NULL)     NO      PRI     (NULL)   auto_increment  select,insert,update,references
     * start_time            datetime         (NULL)     NO              (NULL)                   select,insert,update,references
     * duration              int(6) unsigned  (NULL)     NO              (NULL)                   select,insert,update,references
     * is_active             int(1) unsigned  (NULL)     NO              (NULL)                   select,insert,update,references
     * count_correct_answers int(1) unsigned  (NULL)     NO              (NULL)                   select,insert,update,references
     * is_passed             int(1) unsigned  (NULL)     NO              (NULL)                   select,insert,update,references
     * count_failed_times    int(2) unsigned  (NULL)     YES             (NULL)                   select,insert,update,references
     * question_id           int(7) unsigned  (NULL)     NO      MUL     (NULL)                   select,insert,update,references
     * user_id               int(7) unsigned  (NULL)     NO      MUL     (NULL)                   select,insert,update,references
     */

    private int id;
    private Date startTime;
    private int duration;
    private boolean isActive;
    // for check to pass
    private int countCorrectAnswers;
    private boolean isPassed;
    private int countFailedTimes;
    private Question question;
    private User user;
    //many to one
    private List<Answer> userQuizAnswers;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public int getCountCorrectAnswers() {
        return countCorrectAnswers;
    }

    public void setCountCorrectAnswers(int countCorrectAnswers) {
        this.countCorrectAnswers = countCorrectAnswers;
    }

    public boolean getIsPassed() {
        return isPassed;
    }

    public void setIsPassed(boolean passed) {
        isPassed = passed;
    }

    public int getCountFailedTimes() {
        return countFailedTimes;
    }

    public void setCountFailedTimes(int countFailedTimes) {
        this.countFailedTimes = countFailedTimes;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Answer> getUserQuizAnswers() {
        return userQuizAnswers;
    }

    public void setUserQuizAnswers(List<Answer> userQuizAnswers) {
        this.userQuizAnswers = userQuizAnswers;
    }
}
