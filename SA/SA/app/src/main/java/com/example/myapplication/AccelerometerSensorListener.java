package com.example.myapplication;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class AccelerometerSensorListener implements SensorEventListener {

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

        AccelerometerData accelerometerData = new AccelerometerData();
        accelerometerData.setValueX(event.values[0]);
        accelerometerData.setValueY(event.values[1]);
        accelerometerData.setValueZ(event.values[2]);
        accelerometerData.setAccuracy(event.accuracy);
        accelerometerData.setId(id);
        id++;
        dataInstance.incNumberData();
        dataInstance.addAccelerometerData(accelerometerData);

        System.out.println(String.format("[Accelerometer] - X=%f, Y=%f, Z=%f", accelerometerData.getValueX(),accelerometerData.getValueY(),accelerometerData.getValueZ() ));

    }

    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }
}
