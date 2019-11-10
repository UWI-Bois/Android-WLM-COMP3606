package dcit.uwi.edu;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.Toast;

public class SensorListener implements SensorEventListener {
    Context context;

    public SensorListener(Context context){
        this.context = context;
    }

    @Override
    public void onSensorChanged(SensorEvent event){
        Toast.makeText(
                context,
                "Received Values of " +
                        event.values[0] +
                        "from the sensor",
                Toast.LENGTH_SHORT
        ).show();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }
}
