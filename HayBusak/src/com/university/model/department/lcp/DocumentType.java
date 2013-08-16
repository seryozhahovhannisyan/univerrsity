package com.university.model.department.lcp;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 23.07.13
 * Time: 22:27
 * To change this template use File | Settings | File Templates.
 */
public enum DocumentType {

    DIPLOMA  (1, new DocumentTypeInfo[]{DocumentTypeInfo.DIPLOMA_AM,
                                        DocumentTypeInfo.DIPLOMA_EN,
                                        DocumentTypeInfo.DIPLOMA_RU,
                                        DocumentTypeInfo.DIPLOMA_FR}),

    AWARDS   (2, new DocumentTypeInfo[]{DocumentTypeInfo.AWARD_AM,
                                        DocumentTypeInfo.AWARD_EN,
                                        DocumentTypeInfo.AWARD_RU,
                                        DocumentTypeInfo.AWARD_FR}),

    ACCREDITATION    (3, new DocumentTypeInfo[]{DocumentTypeInfo.ACCREDITATION_AM,
                                                DocumentTypeInfo.ACCREDITATION_EN,
                                                DocumentTypeInfo.ACCREDITATION_RU,
                                                DocumentTypeInfo.ACCREDITATION_FR});

    DocumentType(int id, DocumentTypeInfo[] info) {
        this.id = id;
        this.info = info;
    }

    public static DocumentType valueOf(final int id){
        for(DocumentType documentType : DocumentType.values()){
            if(documentType.getId() == id ){
                return documentType;
            }
        }
        return null;
    }

    private int id;

    private DocumentTypeInfo[] info;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DocumentTypeInfo[] getInfo() {
        return info;
    }

    public void setInfo(DocumentTypeInfo[] info) {
        this.info = info;
    }
}
