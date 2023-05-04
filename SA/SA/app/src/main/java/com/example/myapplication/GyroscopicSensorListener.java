package com.example.myapplication;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class GyroscopicSensorListener implements SensorEventListener {

    private SensorManager sensorManager;
    private DataInstance dataInstance;
    private  int id;


    public void setSensorManager(SensorManager sensorManager) {
        this.sensorManager = sensorManager;
    }


    public void setDataInstance(DataInstance dataInstance) {
        this.dataInstance = dataInstance;
    }

    @Override
    public void onSensorChanged(SensorEvent event){
        GyroscopicData gyroscopicData = new GyroscopicData();
        gyroscopicData.setValueY(event.values[1]);
        gyroscopicData.setValueX(event.values[0]);
        gyroscopicData.setValueZ(event.values[2]);
        gyroscopicData.setId(id);
        id++;
        dataInstance.incNumberData();
        dataInstance.addGyroscopicData(gyroscopicData);

        System.out.println(String.format("[Gyroscopic] - X=%f, Y=%f, Z=%f", gyroscopicData.getValueX(),gyroscopicData.getValueY(),gyroscopicData.getValueZ() ));

    }

    public void onAccuracyChanged(Sensor sensor, int Accuracy){

    }
}
