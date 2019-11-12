package me.comp3606_asg2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GUI extends AppCompatActivity {

    private Button btn_toast, btn_datePicker, btn_imgView, btn_lv, btn_spinner, btn_edtTxt, btn_btn, btn_numberPicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gui);
        initButtons();
    }

    private void initButtons() {

        btn_toast = findViewById(R.id.btn_toast);
        btn_toast.setOnClickListener(new CustomBtnListener());

        btn_datePicker = findViewById(R.id.btn_datePicker);
        btn_datePicker.setOnClickListener(new CustomBtnListener());

        btn_imgView = findViewById(R.id.btn_imgView);
        btn_imgView.setOnClickListener(new CustomBtnListener());

        btn_lv = findViewById(R.id.btn_lv);
        btn_lv.setOnClickListener(new CustomBtnListener());

        btn_spinner = findViewById(R.id.btn_spinner);
        btn_spinner.setOnClickListener(new CustomBtnListener());

        btn_edtTxt = findViewById(R.id.btn_edtTxt);
        btn_edtTxt.setOnClickListener(new CustomBtnListener());

        btn_btn = findViewById(R.id.btn_btn);
        btn_btn.setOnClickListener(new CustomBtnListener());

        btn_numberPicker = findViewById(R.id.btn_numberPicker);
        btn_numberPicker.setOnClickListener(new CustomBtnListener());

    }

    class CustomBtnListener implements Button.OnClickListener{
        Intent i;
        @Override
        public void onClick(View v) {
            if(v == btn_toast){
                i = new Intent(getApplicationContext(), GUI_Toast.class);
                startActivity(i);
            }
            if(v == btn_datePicker){
                i = new Intent(getApplicationContext(), GUI_DatePicker.class);
                startActivity(i);
            }
            if(v == btn_imgView){
                i = new Intent(getApplicationContext(), GUI_ImageView.class);
                startActivity(i);
            }
            if(v == btn_lv){
                i = new Intent(getApplicationContext(), GUI_ListView.class);
                startActivity(i);
            }
            if(v == btn_spinner){
                i = new Intent(getApplicationContext(), GUI_Spinner.class);
                startActivity(i);
            }
            if(v == btn_edtTxt){
                i = new Intent(getApplicationContext(), GUI_EditText.class);
                startActivity(i);
            }
            if(v == btn_btn){
                i = new Intent(getApplicationContext(), GUI_Button.class);
                startActivity(i);
            }
            if(v == btn_numberPicker){
                i = new Intent(getApplicationContext(), GUI_NumberPicker.class);
                startActivity(i);
            }
        }
    }
}
