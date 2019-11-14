package me.comp3606_asg2;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class IntentsCallback extends AppCompatActivity {

    private Button btn_screen2;
    private EditText edtTxt_num1, edtText_num2;
    private Intent intent;
    private int num1, num2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intents_callback);

        btn_screen2 = findViewById(R.id.btn_screen2);
        btn_screen2.setOnClickListener(new BtnListener());

        edtTxt_num1 = findViewById(R.id.editText);
        edtText_num2 = findViewById(R.id.editText2);
    }

    class BtnListener implements Button.OnClickListener{

        @Override
        public void onClick(View v) {
            if(v == btn_screen2){
                String s;
                s = edtTxt_num1.getText().toString();
                if(s.equals("")){
                    Toast.makeText(IntentsCallback.this, "Please enter num1!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                num1 = Integer.parseInt(s);
                s = edtText_num2.getText().toString();
                if(s.equals("")){
                    Toast.makeText(IntentsCallback.this, "Please enter num2!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                num2 = Integer.parseInt(s);

                intent = new Intent(getApplicationContext(), IntentsCallback_Calc.class);
                intent.putExtra("num1", num1);
                intent.putExtra("num2", num2);

                startActivity(intent);
            }
        }
    }
}

