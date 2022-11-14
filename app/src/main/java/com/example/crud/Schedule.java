package com.example.crud;


import android.os.Parcel;
import android.os.Parcelable;


public class Schedule implements Parcelable {

    int day;
    int pushUps;
    int squats;
    int bridge;
    int press;
    int bodyType;

    public Schedule() {
    }

    public Schedule(int day, int pushUps, int squats, int bridge, int press,int bodyType) {
        this.day = day;
        this.pushUps = pushUps;
        this.squats = squats;
        this.bridge = bridge;
        this.press = press;
        this.bodyType = bodyType;
    }

    protected Schedule(Parcel in) {
        day = in.readInt();
        pushUps = in.readInt();
        squats = in.readInt();
        bridge = in.readInt();
        press = in.readInt();
        bodyType = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(day);
        dest.writeInt(pushUps);
        dest.writeInt(squats);
        dest.writeInt(bridge);
        dest.writeInt(press);
        dest.writeInt(bodyType);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Schedule> CREATOR = new Creator<Schedule>() {
        @Override
        public Schedule createFromParcel(Parcel in) {
            return new Schedule(in);
        }

        @Override
        public Schedule[] newArray(int size) {
            return new Schedule[size];
        }
    };

    public int getDay() {
        return day;
    }

    public int getPushUps() {
        return pushUps;
    }

    public int getSquats() {
        return squats;
    }

    public int getBridge() {
        return bridge;
    }

    public int getPress() {
        return press;
    }

    public int getBodyType() {
        return bodyType;
    }
}
