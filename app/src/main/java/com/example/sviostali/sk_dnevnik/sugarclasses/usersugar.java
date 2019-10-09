package com.example.sviostali.sk_dnevnik.sugarclasses;

import com.orm.SugarRecord;
import java.util.List;

public class usersugar extends SugarRecord {
    String login;
    String password;
    String avatar;
    String firstname;
    String lastname;
    String birthdate;
    int professor;

    public usersugar(){

    }

    public usersugar(String login, String password, String avatar, String firstname, String lastname, String birthdate, int professor) {
        this.login = login;
        this.password = password;
        this.avatar = avatar;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.professor = professor;
    }

    public List<subjects> getSubjects(){
        return subjects.find(subjects.class, "user = ?", String.valueOf(this.getId()));
    }

    public List<studentsubject> getStudSub(){
        return studentsubject.find(studentsubject.class, "user = ?", String.valueOf(this.getId()));
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public int getProfessor() {
        return professor;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setProfessor(int professor) {
        this.professor = professor;
    }
}
