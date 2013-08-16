package com.university.model.account;


/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 08.08.13
 * Time: 1:26
 * To change this template use File | Settings | File Templates.
 */
public class GroupSubject {

    //db fields
    private int id;
    private int groupId;
    private int subjectId;
    // view  object
    private Subject subject;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "GroupSubject{" +
                "id=" + id +
                ", groupId=" + groupId +
                ", subjectId=" + subjectId +
                ", subject=" + subject +
                '}';
    }
}