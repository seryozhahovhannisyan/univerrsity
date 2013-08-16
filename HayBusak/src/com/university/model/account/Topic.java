package com.university.model.account;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 07.08.13
 * Time: 1:30
 * To change this template use File | Settings | File Templates.
 */
public class Topic {

    private int id;
    private String title;
    private String dataPath;
    private Date creationDate;
    // Database column is group_subject_id will get the subject from CourseGroup
    private GroupSubject groupSubject;
    //the db field is not, will get from cross table user_topic
    //private List<User> users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDataPath() {
        return dataPath;
    }

    public void setDataPath(String dataPath) {
        this.dataPath = dataPath;
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

    /*public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }*/

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", dataPath='" + dataPath + '\'' +
                ", creationDate=" + creationDate +
                ", groupSubject=" + groupSubject +
                //", users=" + users +
                '}';
    }
}