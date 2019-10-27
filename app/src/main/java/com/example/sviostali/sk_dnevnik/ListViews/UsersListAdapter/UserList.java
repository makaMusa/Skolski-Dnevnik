package com.example.sviostali.sk_dnevnik.ListViews.UsersListAdapter;

public class UserList {
    public String user;
    public boolean isMarked;

    UserList(String userr){
        this.user = userr;
        this.isMarked = false;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean marked) {
        isMarked = marked;
    }
}
