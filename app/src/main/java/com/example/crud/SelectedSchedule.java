package com.example.crud;



import java.util.ArrayList;

public class SelectedSchedule {


    String weight;
    String height;
    String typeOfBody;
    ArrayList<Schedule> schedules;

    public SelectedSchedule() {
    }

    public SelectedSchedule(String weight, String height, String typeOfBody, ArrayList<Schedule> schedules) {
        this.weight = weight;
        this.height = height;
        this.typeOfBody = typeOfBody;
        this.schedules = schedules;
    }

    public String getWeight() {
        return weight;
    }

    public String getHeight() {
        return height;
    }

    public String getTypeOfBody() {
        return typeOfBody;
    }

    public ArrayList<Schedule> getSchedules() {
        return schedules;
    }
}

