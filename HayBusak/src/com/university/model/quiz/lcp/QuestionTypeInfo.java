package com.university.model.quiz.lcp;

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
public enum QuestionTypeInfo {

    TRAINING_AM     (1,Language.ARMENIAN, "դիպլոմ"),
    TRAINING_EN     (1,Language.ENGLISH, "training"),
    TRAINING_RU     (1,Language.RUSSIAN, "диплом"),
    TRAINING_FR     (1,Language.FRANCE, "diplôme"),

    EXAM_AM         (2,Language.ARMENIAN, "վճիռ"),
    EXAM_EN         (2,Language.ENGLISH, "exam"),
    EXAM_RU         (2,Language.RUSSIAN, "награда"),
    EXAM_FR         (2,Language.FRANCE, "Award");

    QuestionTypeInfo(int key, final Language language, final String info) {

        this.key = key;
        this.language = language;
        this.info = info;
    }

    public static QuestionTypeInfo valueOfLanguage(Language language) {

        for (QuestionTypeInfo info : QuestionTypeInfo.values()) {
            if (info.getLanguage().getValue() == language.getValue()) {
                return info;
            }
        }

        return null;
    }

    public static List<QuestionTypeInfo> listValueOfLanguage(Language language) {

        List<QuestionTypeInfo> infos = new ArrayList<QuestionTypeInfo>();

        QuestionTypeInfo[] values = QuestionTypeInfo.values();
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