package dcit.uwi.simplelocationtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static int MY_PERMISSION_CODE = 234;
    TextView distanceTraveled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        double dist = getDistance();
        distanceTraveled = findViewById(R.id.distance_traveled);
        distanceTraveled.setText("Total distance travelled: " + dist);

        boolean hasPermission = ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED;

        if(!hasPermission){
            ActivityCompat.requestPermissions(
                    this,
                    new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSION_CODE
            );
        }
        else{
            // we have permission so we can setup the service
            if(!isServiceSet()) startBackgroundProcess();
        }
    }

    public double getDistance(){
        SharedPreferences pref = getSharedPreferences("DCIT", Context.MODE_PRIVATE);
        return pref.getFloat("total_dist", 0);
    }

    public boolean isServiceSet(){
        SharedPreferences pref = getSharedPreferences("DCIT", Context.MODE_PRIVATE);
        return pref.getBoolean("service_set", false);
    }

    public void startBackgroundProcess(){
        // we use a dedicated class to setup the process of the service
        PollReceiver.setUpService(this);
        // after setup we save the preference
        SharedPreferences.Editor editor = getSharedPreferences("DCIT", Context.MODE_PRIVATE).edit();
        editor.putBoolean("service_set", true).apply();
    }

}
