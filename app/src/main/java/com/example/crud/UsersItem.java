package com.example.crud;


public class UsersItem {

    String userID;
    String userName;
    String userPlan;
    String userComment;

    public UsersItem() {
    }

    public UsersItem(String userID, String userName, String userPlan, String userComment) {
        this.userID = userID;
        this.userName = userName;
        this.userPlan = userPlan;
        this.userComment= userComment;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPlan() {
        return userPlan;
    }

    public void setUserPlan(String userPlan) {
        this.userPlan = userPlan;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }
}

