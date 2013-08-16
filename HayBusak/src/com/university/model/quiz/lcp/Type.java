package com.university.model.quiz.lcp;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 12.08.13
 * Time: 21:54
 * To change this template use File | Settings | File Templates.
 */
public enum Type {

    TRAINING    (1, "training"),
    EXAM        (2, "exam");

    Type(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Type valueOf(final int id){
        for(Type type : Type.values()){
            if(type.getId() == id ){
                return type;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private int id;
    private String name;
}
