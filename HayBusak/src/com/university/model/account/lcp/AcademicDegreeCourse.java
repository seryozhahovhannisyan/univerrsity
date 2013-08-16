package com.university.model.account.lcp;

import com.university.model.department.lcp.AcademicDegree;

import java.util.ArrayList;
import java.util.List;

public enum AcademicDegreeCourse {

    COLLEGE_X           (1, "X",    AcademicDegree.COLLEGE),
    COLLEGE_XI          (2, "XI",   AcademicDegree.COLLEGE),
    COLLEGE_XII         (3, "XII",  AcademicDegree.COLLEGE),
    BACHELOR_I          (4, "I",    AcademicDegree.BACHELOR),
    BACHELOR_II         (5, "II",   AcademicDegree.BACHELOR),
    BACHELOR_III        (6, "III",  AcademicDegree.BACHELOR),
    BACHELOR_IV         (7, "IV",   AcademicDegree.BACHELOR),
    MASTER_I            (8, "I",    AcademicDegree.MAGISTRACY),
    MASTER_II           (9, "II",   AcademicDegree.MAGISTRACY);

    AcademicDegreeCourse(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    private AcademicDegreeCourse(int id, String name, AcademicDegree academicDegree) {
        this.id = id;
        this.name = name;
        this.academicDegree = academicDegree;
    }

    public static synchronized AcademicDegreeCourse valueOf(final int id) {
        for (AcademicDegreeCourse e : AcademicDegreeCourse.values()) {
            if (e.id == id) {
                return e;
            }
        }
        return null;
    }

    public static synchronized List<AcademicDegreeCourse> academicDegreeCourses(final AcademicDegree academicDegree) {

        List<AcademicDegreeCourse>  academicDegreeCourses = new ArrayList<AcademicDegreeCourse>();

        for (AcademicDegreeCourse e : AcademicDegreeCourse.values()) {
            if(e.getAcademicDegree().getId()== academicDegree.getId()){
                academicDegreeCourses.add(e);
            }
        }
        return academicDegreeCourses;
    }

    private final int id;
    private final String name;
    private AcademicDegree academicDegree;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public AcademicDegree getAcademicDegree() {
        return academicDegree;
    }
}
