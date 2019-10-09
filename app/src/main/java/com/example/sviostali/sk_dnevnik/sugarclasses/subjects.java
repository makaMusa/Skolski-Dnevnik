package com.example.sviostali.sk_dnevnik.sugarclasses;

import com.orm.SugarRecord;
import java.util.List;

public class subjects extends SugarRecord {
    String name;
    usersugar user;

    public subjects(){
    }

    public subjects(String name, usersugar user) {
        this.name = name;
        this.user = user;
    }


    public List<studentsubject> getStudentSubjects(){
        return studentsubject.find(studentsubject.class, "subject = ?", String.valueOf(this.getId()));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public usersugar getUser() {
        return user;
    }

    public void setUser(usersugar user) {
        this.user = user;
    }
}
