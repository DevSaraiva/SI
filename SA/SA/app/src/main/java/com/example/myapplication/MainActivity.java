package com.example.myapplication;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.AggregateQuery;
import com.google.firebase.firestore.AggregateQuerySnapshot;
import com.google.firebase.firestore.AggregateSource;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class MainActivity extends AppCompatActivity {

    private String label;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private boolean started = false;
    private  int testeID;
    private DataInstance dataInstance;
    private  SensorManager sensorManager;
    private  AccelerometerSensorListener accelerometerSensorListener;
    private GyroscopicSensorListener gyroscopicSensorListener;
    private MagnetometerSensorListener magnetometerSensorListener;
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.escadas:
                if (checked)
                    label = "escadas";
                    break;
            case R.id.queda:
                if (checked)
                    label = "queda";
                    break;
            case R.id.correr:
                if (checked)
                    label = "correr";
                    break;
            case R.id.andar:
                if (checked)
                    label = "andar";
                    break;
            case R.id.sentar:
                if (checked)
                    label = "sentar";
                    break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Query query = db.collection("cities");
        AggregateQuery countQuery = query.count();
        countQuery.get(AggregateSource.SERVER).addOnCompleteListener(new OnCompleteListener<AggregateQuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<AggregateQuerySnapshot> task) {
                if (task.isSuccessful()) {
                    // Count fetched successfully
                    AggregateQuerySnapshot snapshot = task.getResult();
                    Log.d(TAG, "Count: " + snapshot.getCount());
                    testeID = (int) snapshot.getCount();
                } else {
                    Log.d(TAG, "Count failed: ", task.getException());
                }
            }
        });

        // Get SensorManager
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        //Get The accelometer sensor
        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Sensor magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        Sensor gyroscopic = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);


        if (accelerometer != null && magnetometer != null && gyroscopic != null){
            accelerometerSensorListener = new AccelerometerSensorListener();
            accelerometerSensorListener.setSensorManager(sensorManager);

            magnetometerSensorListener = new MagnetometerSensorListener();
            magnetometerSensorListener.setSensorManager(sensorManager);

            gyroscopicSensorListener = new GyroscopicSensorListener();
            gyroscopicSensorListener.setSensorManager(sensorManager);

        }

        //Set buttons functions

        Button start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                    if(started == false){
                        dataInstance = new DataInstance(label, testeID);

                        accelerometerSensorListener.setDataInstance(dataInstance);
                        sensorManager.registerListener(accelerometerSensorListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

                        magnetometerSensorListener.setDataInstance(dataInstance);
                        sensorManager.registerListener(magnetometerSensorListener, magnetometer, SensorManager.SENSOR_DELAY_NORMAL);

                        gyroscopicSensorListener.setDataInstance(dataInstance);
                        sensorManager.registerListener(gyroscopicSensorListener, gyroscopic, SensorManager.SENSOR_DELAY_NORMAL);

                        started = true;
                    }

                }

        });

        Button stop = (Button) findViewById(R.id.stop);
        stop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(started == true){
                    db.collection("testes").document(Integer.toString(testeID)).set(dataInstance);
                    testeID++;
                    sensorManager.unregisterListener(accelerometerSensorListener);
                    sensorManager.unregisterListener(gyroscopicSensorListener);
                    sensorManager.unregisterListener(magnetometerSensorListener);

                    started = false;
                }
            }
        });




    }

}