package dcit.uwi.edu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void viewSensorsActivity(View view){
        System.out.println("View se CLICKED!");
        Intent i = new Intent(getApplicationContext(), SensorList.class);
        startActivity(i);
        return;
    }
}
