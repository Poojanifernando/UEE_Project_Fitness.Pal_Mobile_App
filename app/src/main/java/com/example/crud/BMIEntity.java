package com.example.crud;



public class BMIEntity {
    private String id;
    private String Weight;
    private String Age;
    private String Feet;
    private String Inch;
    private String Index;

    public BMIEntity(){

    }

    public BMIEntity(String id, String weight, String age, String feet, String inch, String index) {
        this.id = id;
        Weight= weight;
        Age=age;
        Feet=feet;
        Inch=inch;
        Index=index;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getFeet() {
        return Feet;
    }

    public void setFeet(String feet) {
        Feet = feet;
    }

    public String getInch() {
        return Inch;
    }

    public void setInch(String inch) {
        Inch = inch;
    }

    public String getIndex() {
        return Index;
    }

    public void setIndex(String index) {
        Index = index;
    }
}
