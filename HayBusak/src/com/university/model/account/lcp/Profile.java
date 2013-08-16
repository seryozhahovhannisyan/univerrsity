package com.university.model.account.lcp;

public enum Profile {

    ADMIN           (1, "admin"),
    MODERATOR       (2, "moderator"),
    STUDENT         (3, "student"),
    USER            (4, "user");

    private Profile(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Profile valueOf(int id){
        for(Profile userType : Profile.values()){
            if(userType.getId() == id){
                return userType;
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
