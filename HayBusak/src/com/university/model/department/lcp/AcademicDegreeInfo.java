package com.university.model.department.lcp;

import com.university.model.general.lcp.Language;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 23.07.13
 * Time: 22:10
 * To change this template use File | Settings | File Templates.
 */
public enum AcademicDegreeInfo {

    COLLEGE_AM(1, Language.ARMENIAN, "Քոլեջ"),
    COLLEGE_EN(1, Language.ENGLISH, "College"),
    COLLEGE_RU(1, Language.RUSSIAN, "колледж"),
    COLLEGE_FR(1, Language.FRANCE, "Collège"),


    BACHELOR_AM(2, Language.ARMENIAN, "Բակալավրիատ"),
    BACHELOR_EN(2, Language.ENGLISH, "Bachelor"),
    BACHELOR_RU(2, Language.RUSSIAN, "бакалавр"),
    BACHELOR_FR(2, Language.FRANCE, "baccalauréat"),


    MAGISTRACY_AM(3, Language.ARMENIAN, "Մագիստրատուրա"),
    MAGISTRACY_EN(3, Language.ENGLISH, "magistracy"),
    MAGISTRACY_RU(3, Language.RUSSIAN, "магистратура"),
    MAGISTRACY_FR(3, Language.FRANCE, "magistrature"),

    CERTIFICATE_AM(4, Language.ARMENIAN, "վկայական"),
    CERTIFICATE_EN(4, Language.ENGLISH, "Certificate"),
    CERTIFICATE_RU(4, Language.RUSSIAN, "сертификат"),
    CERTIFICATE_FR(4, Language.FRANCE, "certificat");

    AcademicDegreeInfo(int key, final Language language, final String info) {
        this.key = key;
        this.language = language;
        this.info = info;
    }


    public static AcademicDegreeInfo valueOfLanguage(Language language) {

        for (AcademicDegreeInfo info : AcademicDegreeInfo.values()) {
            if (info.getLanguage().getValue() == language.getValue()) {
                return info;
            }
        }

        return null;
    }

    public static List<AcademicDegreeInfo> listValueOfLanguage(Language language) {

        List<AcademicDegreeInfo> infos = new ArrayList<AcademicDegreeInfo>();

        AcademicDegreeInfo[] values = AcademicDegreeInfo.values();
        for (int i = 0; i < values.length; i++) {
            if (values[i].getLanguage().getValue() == language.getValue()) {
                infos.add(values[i]);
            }
        }

        return infos;
    }

    private int key;

    private Language language;

    private String info;

    public int getKey() {
        return key;
    }

    public Language getLanguage() {
        return language;
    }

    public String getInfo() {
        return info;
    }
}