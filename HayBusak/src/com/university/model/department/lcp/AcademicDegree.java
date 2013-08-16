package com.university.model.department.lcp;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 22.07.13
 * Time: 20:30
 * To change this template use File | Settings | File Templates.
 */
public enum AcademicDegree {

    COLLEGE     (1, new AcademicDegreeInfo[]{   AcademicDegreeInfo.COLLEGE_AM,
            AcademicDegreeInfo.COLLEGE_EN,
            AcademicDegreeInfo.COLLEGE_RU,
            AcademicDegreeInfo.COLLEGE_FR}),

    BACHELOR    (2, new AcademicDegreeInfo[]{   AcademicDegreeInfo.BACHELOR_AM,
            AcademicDegreeInfo.BACHELOR_EN,
            AcademicDegreeInfo.BACHELOR_RU,
            AcademicDegreeInfo.BACHELOR_FR}),

    MAGISTRACY      (3, new AcademicDegreeInfo[]{   AcademicDegreeInfo.MAGISTRACY_AM,
            AcademicDegreeInfo.MAGISTRACY_EN,
            AcademicDegreeInfo.MAGISTRACY_RU,
            AcademicDegreeInfo.MAGISTRACY_FR}),

    CERTIFICATE      (4, new AcademicDegreeInfo[]{   AcademicDegreeInfo.CERTIFICATE_AM,
            AcademicDegreeInfo.COLLEGE_EN,
            AcademicDegreeInfo.COLLEGE_RU,
            AcademicDegreeInfo.COLLEGE_FR})

    ;

    AcademicDegree(int id, AcademicDegreeInfo[] info) {
        this.id = id;
        this.info = info;
    }

    public static AcademicDegree valueOf(final int id){
        for(AcademicDegree degree : AcademicDegree.values()){
            if(degree.getId() == id ){
                return degree;
            }
        }
        return null;
    }

    private int id;

    private AcademicDegreeInfo[] info;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AcademicDegreeInfo[] getInfo() {
        return info;
    }

    public void setInfo(AcademicDegreeInfo[] info) {
        this.info = info;
    }
}
