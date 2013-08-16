package com.university.model.quiz.lcp;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 12.08.13
 * Time: 1:35
 * To change this template use File | Settings | File Templates.
 */
public enum QuestionType {

    TRAINING  (1, new QuestionTypeInfo[]{QuestionTypeInfo.TRAINING_AM,
                                        QuestionTypeInfo.TRAINING_EN,
                                        QuestionTypeInfo.TRAINING_RU,
                                        QuestionTypeInfo.TRAINING_FR}),

    EXAM   (2, new QuestionTypeInfo[]{QuestionTypeInfo.EXAM_AM,
            QuestionTypeInfo.EXAM_EN,
            QuestionTypeInfo.EXAM_RU,
            QuestionTypeInfo.EXAM_FR});

    QuestionType(int id, QuestionTypeInfo[] info) {
        this.id = id;
        this.info = info;
    }

    public static QuestionType valueOf(final int id){
        for(QuestionType type : QuestionType.values()){
            if(type.getId() == id ){
                return type;
            }
        }
        return null;
    }

    private int id;

    private QuestionTypeInfo[] info;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public QuestionTypeInfo[] getInfo() {
        return info;
    }

    public void setInfo(QuestionTypeInfo[] info) {
        this.info = info;
    }
}
