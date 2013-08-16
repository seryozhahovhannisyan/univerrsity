package com.university.model.department.lcp;

import com.university.model.general.lcp.Language;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 23.07.13
 * Time: 22:27
 * To change this template use File | Settings | File Templates.
 */
public enum DocumentTypeInfo {

    DIPLOMA_AM(1,Language.ARMENIAN, "դիպլոմ"),
    DIPLOMA_EN(1,Language.ENGLISH, "diploma"),
    DIPLOMA_RU(1,Language.RUSSIAN, "диплом"),
    DIPLOMA_FR(1,Language.FRANCE, "diplôme"),

    AWARD_AM(2,Language.ARMENIAN, "վճիռ"),
    AWARD_EN(2,Language.ENGLISH, "award"),
    AWARD_RU(2,Language.RUSSIAN, "награда"),
    AWARD_FR(2,Language.FRANCE, "Award"),

    ACCREDITATION_AM(3,Language.ARMENIAN, "Հավատարմագրման"),
    ACCREDITATION_EN(3,Language.ENGLISH, "accreditation"),
    ACCREDITATION_RU(3,Language.RUSSIAN, "аккредитация"),
    ACCREDITATION_FR(3, Language.FRANCE, "accréditation"), ;

    DocumentTypeInfo(int key, final Language language, final String info) {

        this.key = key;
        this.language = language;
        this.info = info;
    }

    public static DocumentTypeInfo valueOfLanguage(Language language) {

        for (DocumentTypeInfo info : DocumentTypeInfo.values()) {
            if (info.getLanguage().getValue() == language.getValue()) {
                return info;
            }
        }

        return null;
    }

    public static List<DocumentTypeInfo> listValueOfLanguage(Language language) {

        List<DocumentTypeInfo> infos = new ArrayList<DocumentTypeInfo>();

        DocumentTypeInfo[] values = DocumentTypeInfo.values();
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