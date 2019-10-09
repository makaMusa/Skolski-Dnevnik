package com.example.sviostali.sk_dnevnik.sugarclasses;

import com.orm.SugarRecord;

import java.util.List;


public class studentsubject extends SugarRecord {
    public usersugar user;
    public subjects subject;
    public int finalmark;
    public int absence;

    public studentsubject() {
    }

    public studentsubject(usersugar user, subjects subject, int finalmark, int absence) {
        this.user = user;
        this.subject = subject;
        this.finalmark = finalmark;
        this.absence = absence;
    }

    public int getFinalmark() {
        return finalmark;
    }

    public void setFinalmark(int finalmark) {
        this.finalmark = finalmark;
    }

    public List<marks> getMarks(){
        return marks.find(marks.class, "studsub = ?", String.valueOf(this.getId()));
    }


    public int getAbsence() {
        return absence;
    }

    public void setAbsence(int absence) {
        this.absence = absence;
    }

    public usersugar getUser() {
        return user;
    }

    public void setUser(usersugar user) {
        this.user = user;
    }

    public subjects getSubject() {
        return subject;
    }

    public void setSubject(subjects subject) {
        this.subject = subject;
    }
}
