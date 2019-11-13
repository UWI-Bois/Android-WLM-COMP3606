package me.comp3606_asg2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Quiz extends AppCompatActivity {

    // TODO: 13-Nov-19 implement a timer, save the time taken upon succesfully submiting the quiz, save that time in the file
    
    private RadioGroup q1, q2, q3, q4, q5;
    private RadioButton a1, a2, a3, a4, a5;
    private int q1Selection,q2Selection,q3Selection,q4Selection,q5Selection;
    private Button btn_done;
    private int score, highScore;
    private String FILENAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        FILENAME = "high_scores";

        initLayout();
    }

    private void initLayout() {
        /*
            initializes the xml components
        */
        // question radio groups
        q1 = findViewById(R.id.q1Group);
        q2 = findViewById(R.id.q2Group);
        q3 = findViewById(R.id.q3Group);
        q4 = findViewById(R.id.q4Group);
        q5 = findViewById(R.id.q5Group);
        // correct answer radio buttons
        a1 = findViewById(R.id.ans_q1);
        a2 = findViewById(R.id.ans_q2);
        a3 = findViewById(R.id.ans_q3);
        a4 = findViewById(R.id.ans_q4);
        a5 = findViewById(R.id.ans_q5);
        // submission button
        btn_done = findViewById(R.id.btn_done);
        btn_done.setOnClickListener(new CustomBtnListener());
    }

    class CustomBtnListener implements Button.OnClickListener{

        @Override
        public void onClick(View v) {
            if(v == btn_done){
                processQuiz();
            }
        }
    }

    public void processQuiz(){
        /*
            main method that is called when the button is clicked,
            initializes and determines the states of the radio groups
            then performs the calculations necessary
         */
        boolean attemptedAll = false;
        attemptedAll = checkRadioButtons(); // check to make sure they attempted all questions
        if(attemptedAll) {
            calcScore();
//            writeFile();
        }
        else{
            System.out.println("please attemps all questions!");
            Toast.makeText(this, "Please attempt all questions!", Toast.LENGTH_SHORT).show();
        }
    }

    private void calcScore() {
        this.score = 0;
        if(q1Selection == a1.getId()) score++;
        if(q2Selection == a2.getId()) score++;
        if(q3Selection == a3.getId()) score++;
        if(q4Selection == a4.getId()) score++;
        if(q5Selection == a5.getId()) score++;
        System.out.println("Score: " + score);
    }

    private boolean checkRadioButtons() {
        boolean val = true;

        // init the selections from the quiz
        q1Selection = q1.getCheckedRadioButtonId();
        q2Selection = q2.getCheckedRadioButtonId();
        q3Selection = q3.getCheckedRadioButtonId();
        q4Selection = q4.getCheckedRadioButtonId();
        q5Selection = q5.getCheckedRadioButtonId();

        // if the id is -1, it means it wasnt used, which means nothing was selected.
        if(
                q1Selection == -1 ||
                q2Selection == -1 ||
                q3Selection == -1 ||
                q4Selection == -1 ||
                q5Selection == -1
        ){
            val = false;
        }

        return val;
    }

}
