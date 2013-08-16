package com.university.model.quiz;

import com.university.model.account.GroupSubject;
import com.university.model.quiz.lcp.Type;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 12.08.13
 * Time: 1:34
 * To change this template use File | Settings | File Templates.
 */
public class Question {

    /**
     * id             int(7) unsigned  (NULL)     NO      PRI     (NULL)   auto_increment  select,insert,update,references
     * value          text             utf8_bin   NO              (NULL)                   select,insert,update,references
     * description    text             utf8_bin   NO              (NULL)                   select,insert,update,references
     * creation_date  date             (NULL)     NO              (NULL)                   select,insert,update,references
     * topic_id       int(7) unsigned  (NULL)     YES     MUL     (NULL)                   select,insert,update,references
     * type_id        int(1) unsigned  (NULL)     NO      MUL     (NULL)                   select,insert,update,references
     */
    private int id;
    private String value;
    private String description;
    private Date creationDate;
    private GroupSubject groupSubject;
    private Type type;
    // one to many
    private List<Answer> answers;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public GroupSubject getGroupSubject() {
        return groupSubject;
    }

    public void setGroupSubject(GroupSubject groupSubject) {
        this.groupSubject = groupSubject;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", description='" + description + '\'' +
                ", creationDate=" + creationDate +
                ", groupSubject=" + groupSubject +
                ", type=" + type +
                ", answers=" + answers +
                '}';
    }
}