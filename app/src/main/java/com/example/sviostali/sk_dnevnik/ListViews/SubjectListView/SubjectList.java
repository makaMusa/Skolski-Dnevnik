package com.example.sviostali.sk_dnevnik.ListViews.SubjectListView;

public class SubjectList {
    public String subject;
    public boolean isMarked;

    SubjectList(String footballClub){
        this.subject = footballClub;
        this.isMarked = false;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean marked) {
        isMarked = marked;
    }
}
