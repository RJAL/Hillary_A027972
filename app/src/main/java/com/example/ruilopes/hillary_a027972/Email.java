package com.example.ruilopes.hillary_a027972;

/**
 * Created by Rui Lopes on 07/11/2016.
 */
public class Email {
    private int docID;
    private String docDate;
    private String toName;
    private String fromName;
    private String originalTo;
    private String subject;


    public int getDocID() {
        return docID;
    }

    public void setDocID(int docID) {
        this.docID = docID;
    }

    public String getDocDate() {
        return docDate;
    }

    public void setDocDate(String docDate) {
        this.docDate = docDate;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getOriginalTo() {
        return originalTo;
    }

    public void setOriginalTo(String originalTo) {
        this.originalTo = originalTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Date: " + getDocDate() + " " + "From:" + getFromName() + " " + "Subject:" + getSubject();}
}
