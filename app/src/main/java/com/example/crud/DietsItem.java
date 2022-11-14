package com.example.crud;

public class DietsItem {

    String userID;
    String DietName;
    String breakfast;
    String lunch;
    String dinner;
    String water;
    String goals;

    public DietsItem() {
    }

    public DietsItem(String userID, String dietName, String breakfast, String lunch, String dinner, String water, String goals) {
        this.userID = userID;
        this.DietName = dietName;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
        this.water = water;
        this.goals = goals;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDietName() {
        return DietName;
    }

    public void setDietName(String dietName) {
        DietName = dietName;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }
}
