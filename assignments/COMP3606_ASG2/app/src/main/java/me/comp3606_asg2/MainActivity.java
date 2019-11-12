package me.comp3606_asg2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.LineNumberReader;

public class MainActivity extends AppCompatActivity {

    private Button btn_intro, btn_gui, btn_quiz, btn_intentsCallback, btn_paper1, btn_paper2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initButtons();
    }

    private void initButtons() {
        // introduction to android button on main screen
        btn_intro = findViewById(R.id.btn_intro);
        btn_intro.setOnClickListener(new CustomBtnListener());
        // gui components button on main screen
        btn_gui = findViewById(R.id.btn_gui);
        btn_gui.setOnClickListener(new CustomBtnListener());
        // quiz button on main screen
        btn_quiz = findViewById(R.id.btn_quiz);
        btn_quiz.setOnClickListener(new CustomBtnListener());
        // intents callback button on main screen
        btn_intentsCallback = findViewById(R.id.btn_intentsCallback);
        btn_intentsCallback.setOnClickListener(new CustomBtnListener());
        // paper 1 button on main screen
        btn_paper1 = findViewById(R.id.btn_paper1);
        btn_paper1.setOnClickListener(new CustomBtnListener());
        // paper 2 button on main screen
        btn_paper2 = findViewById(R.id.btn_paper2);
        btn_paper2.setOnClickListener(new CustomBtnListener());
    }

    class CustomBtnListener implements Button.OnClickListener{
        Intent i;
        @Override
        public void onClick(View v) {
            if(v == btn_intro){
                i = new Intent(getApplicationContext(), AndroidIntro.class);
                startActivity(i);
            }
            if(v == btn_gui){
                i = new Intent(getApplicationContext(), GUI.class);
                startActivity(i);
            }
            if(v == btn_quiz){
                i = new Intent(getApplicationContext(), Quiz.class);
                startActivity(i);
            }
            if(v == btn_intentsCallback){
                i = new Intent(getApplicationContext(), IntentsCallback.class);
                startActivity(i);
            }
            if(v == btn_paper1){
                i = new Intent(getApplicationContext(), Paper1.class);
                startActivity(i);
            }
            if(v == btn_paper2){
                i = new Intent(getApplicationContext(), Paper2.class);
                startActivity(i);
            }
        }
    }

}
