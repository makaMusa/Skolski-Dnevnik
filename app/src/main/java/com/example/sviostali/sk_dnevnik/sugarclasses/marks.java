package com.example.sviostali.sk_dnevnik.sugarclasses;


import com.orm.SugarRecord;

public class marks extends SugarRecord{

    public studentsubject studsub;
    public int mark;
    public String date;

    public marks() {
    }

    public marks(studentsubject stud_sub, int mark, String date, int finalmark) {
        this.studsub = stud_sub;
        this.mark = mark;
        this.date = date;
    }


    public studentsubject getStudsub() {
        return studsub;
    }

    public void setStud_sub(studentsubject stud_sub) {
        this.studsub = stud_sub;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
