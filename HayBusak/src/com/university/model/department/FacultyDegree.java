package com.university.model.department;

import com.university.model.department.lcp.AcademicDegree;


/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 23.07.13
 * Time: 22:36
 * To change this template use File | Settings | File Templates.
 */
public class FacultyDegree {

    private int id;
    private Faculty faculty;
    private AcademicDegree degree;
    private Document document;
    //private int fee;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public AcademicDegree getDegree() {
        return degree;
    }

    public void setDegree(AcademicDegree degree) {
        this.degree = degree;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    /*public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }*/

    @Override
    public String toString() {
        return "FacultyDegree{" +
                "id=" + id +
                ", faculty=" + faculty +
                ", degree=" + degree +
                ", document=" + document +
                //", fee=" + fee +
                '}';
    }
}
