package com.example.udprogramacionporcomponentesparcial2.model;

import com.squareup.moshi.JsonClass;

import java.io.Serializable;
@JsonClass(generateAdapter = true)
public class Nutritions implements Serializable {
    private int calories;
    private double fat;
    private double sugar;
    private double carbohydrates;
    private double protein;

    public Nutritions(int calories, double fat, double sugar, double carbohydrates, double protein) {
        this.calories = calories;
        this.fat = fat;
        this.sugar = sugar;
        this.carbohydrates = carbohydrates;
        this.protein = protein;
    }
    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public void setSugar(double sugar) {
        this.sugar = sugar;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public int getCalories() {
        return calories;
    }

    public double getFat() {
        return fat;
    }

    public double getSugar() {
        return sugar;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public double getProtein() {
        return protein;
    }

}

