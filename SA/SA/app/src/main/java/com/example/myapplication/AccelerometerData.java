package com.example.myapplication;

public class AccelerometerData {
    public float valueX;
    public float valueY;
    public float valueZ;
    public int accuracy;

    public int id;


    public AccelerometerData(){
        valueY = 0.0f;
        valueZ = 0.0f;
        valueX = 0.0f;
        accuracy = 0;
        id = 0;
    }

    public float getValueX() {
        return valueX;
    }

    public float getValueY() {
        return valueY;
    }

    public float getValueZ() {
        return valueZ;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public void setValueX(float valueX) {
        this.valueX = valueX;
    }

    public void setValueY(float valueY) {
        this.valueY = valueY;
    }

    public void setValueZ(float valueZ) {
        this.valueZ = valueZ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
