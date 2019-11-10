package dcit.uwi.edu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SensorList extends AppCompatActivity {

    private SensorManager sensorManager;
    private List<String> sensors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_list);

        this.sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        this.sensors = new ArrayList<>();

        final List<Sensor> deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor s :
                deviceSensors) {
            sensors.add(s.getName() + ": " + getSensorTypeFromID(s.getType()));
        }
        //retrieve UI ListView and Create Adapter
        ListView lv = findViewById(R.id.sensor_list);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                sensors
        );
        if(lv != null){
            lv.setAdapter(arrayAdapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Sensor s = deviceSensors.get(position);
                    //listen to seonsor
                    SensorListener sensorListener = new SensorListener((getApplicationContext()));
                    sensorManager.registerListener(sensorListener, s, SensorManager.SENSOR_DELAY_NORMAL);
                }
            });

        }
    }

    @SuppressWarnings("depreciation")
    private String getSensorTypeFromID(int sensorID){
        switch(sensorID){
            case Sensor.TYPE_ACCELEROMETER:
                return "TYPE_ACCELEROMETER";
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                return "TYPE_AMBIENT_TEMPERATURE";
            case Sensor.TYPE_GRAVITY:
                return "TYPE_GRAVITY";
            case Sensor.TYPE_GYROSCOPE:
                return "TYPE_GYROSCOPE";
            case Sensor.TYPE_LIGHT:
                return "TYPE_LIGHT";
            case Sensor.TYPE_LINEAR_ACCELERATION:
                return "TYPE_LINEAR_ACCELERATION";
            case Sensor.TYPE_MAGNETIC_FIELD:
                return "TYPE_MAGNETIC_FIELD";
            case Sensor.TYPE_PRESSURE:
                return "TYPE_PRESSURE";
            case Sensor.TYPE_PROXIMITY:
                return "TYPE_PROXIMITY";
            case Sensor.TYPE_RELATIVE_HUMIDITY:
                return "TYPE_RELATIVE_HUMIDITY";
            case Sensor.TYPE_ROTATION_VECTOR:
                return "TYPE_ROTATION_VECTOR";
            case Sensor.TYPE_HEART_RATE:
                return "TYPE_HEART_RATE";
            case Sensor.TYPE_STEP_COUNTER:
                return "TYPE_STEP_COUNTER";
            case Sensor.TYPE_ORIENTATION:
                return "TYPE_ORIENTATION";
            default: return "Unknown";
        }

    }
}
