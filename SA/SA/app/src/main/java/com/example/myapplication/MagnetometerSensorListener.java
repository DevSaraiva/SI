package com.example.myapplication;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class MagnetometerSensorListener implements SensorEventListener {

    private SensorManager sensorManager;
    private DataInstance dataInstance;
    private int id = 0;



    public void setSensorManager(SensorManager sensorManager) {
        this.sensorManager = sensorManager;
    }

    public void setDataInstance(DataInstance dataInstance) {
        this.dataInstance = dataInstance;
    }

    @Override
    public void onSensorChanged(SensorEvent event){

        MagnetometerData magnetometerData = new MagnetometerData();
        magnetometerData.setValueX(event.values[0]);
        magnetometerData.setValueY(event.values[1]);
        magnetometerData.setValueZ(event.values[2]);
        magnetometerData.setId(id);
        id++;
        dataInstance.incNumberData();
        dataInstance.addMagnetometer(magnetometerData);
        System.out.println(String.format("[Magnetometer] - X=%f, Y=%f, Z=%f", magnetometerData.getValueX(),magnetometerData.getValueY(),magnetometerData.getValueZ() ));

    }

    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }
}
