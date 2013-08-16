package com.university.model.department.lcp;

public enum ReportType {

    NEW                 (1, "new"),
    ANNOUNCEMENT        (2, "announcement");

    ReportType(final int id, final String type) {
        this.id = id;
        this.type = type;
    }

    public static synchronized ReportType valueOf(final int id) {
        for (ReportType e : ReportType.values()) {
            if (e.id == id) {
                return e;
            }
        }
        return null;
    }

    private final int id;
    private final String type;

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
