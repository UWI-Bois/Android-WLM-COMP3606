package me.comp3606_asg2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class IntentsCallback_Calc extends AppCompatActivity {

    private String msg;
    private int num1, num2, total;
    private Intent intent;
    private EditText edtTxt_num1, edtTxt_num2, edtTxt_total;
    private Button btn_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intents_callback__calc);

        msg = "hi good day";

        edtTxt_num2 = findViewById(R.id.editText2);
        edtTxt_num1 = findViewById(R.id.editText);
        edtTxt_total = findViewById(R.id.edtTxt_total);


        btn_return = findViewById(R.id.btn_return);
        btn_return.setOnClickListener(new BtnListener());

        initNums(); // grab the data from the intent passed from screen 1
        calc(); // add the 2 numbers grabbed
    }

    private void initNums() {
        num1 = getIntent().getIntExtra("num1", 0);
        num2 = getIntent().getIntExtra("num2", 0);
        edtTxt_num1.setText(Integer.toString(num1));
        edtTxt_num2.setText(Integer.toString(num2));
    }

    class BtnListener implements Button.OnClickListener{

        @Override
        public void onClick(View v) {
            if(v == btn_return){
                intent = new Intent(getApplicationContext(), IntentsCallback.class);
                intent.putExtra("total", total);
                intent.putExtra("msg", msg);

                startActivity(intent);
            }
        }
    }

    private void calc() {
        total = num1 + num2;
        edtTxt_total.setText(Integer.toString(total));
    }

}