package com.example.myapplication;

public class GyroscopicData {
    public float valueX;
    public float valueY;
    public float valueZ;
    public int id;



    public GyroscopicData(){
        valueY = 0.0f;
        valueZ = 0.0f;
        valueX = 0.0f;

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
