package com.example.quizzicalapp.entity;


import java.io.Serializable;

public class Player implements Serializable {
    static final long serialVersionUID = 104L;


    private String name;
    private int place;
    private String currentAnswer ="";
    private int totalPoints;

    public Player(){};
    public Player(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place){
        this.place = place;
    }

    public String getCurrentAnswer() {
        return currentAnswer;
    }

    public void setCurrentAnswer(String currenAnswer) {
        this.currentAnswer = currenAnswer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }
}
