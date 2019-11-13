package me.comp3606_asg2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Quiz extends AppCompatActivity {

    // TODO: 13-Nov-19 implement a timer, save the time taken upon succesfully submiting the quiz, save that time in the file

    private TextView txtView_yourScore, txtView_highScore;
    
    private RadioGroup q1, q2, q3, q4, q5;
    private RadioButton a1, a2, a3, a4, a5;
    private int q1Selection,q2Selection,q3Selection,q4Selection,q5Selection;
    private Button btn_done;
    private Score score;
    private String FILENAME;
    private File file;
    private FileOutputStream fop;
    private FileInputStream fis;
    private boolean newFile, newHS;

    // timer stuff
    private long startTime, elapsedTime, seconds, secondsDisplay, minutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        newHS = false; // check for a new high score
        score = new Score();

        initTimer();

        try {
            initFile(); // check to see if the file exists, if it does not, create it, and initialize the score
        } catch (IOException e) {
            e.printStackTrace();
        }
        openFile();
        initLayout();
        initUI();
    }

    private void initTimer() {
        startTime = System.currentTimeMillis();
        elapsedTime = System.currentTimeMillis() - startTime;
        seconds = elapsedTime / 1000;
        secondsDisplay = seconds % 60;
        System.out.println(secondsDisplay);
    }

    private void initUI() {
        txtView_yourScore.setText(
                "Your stats: " + score.getScore()
        );
        txtView_highScore.setText(
                "Best stats: " + score.getHighScore() + " - time: " + score.getBestTime()
        );
    }

    private void initFile() throws IOException {
        FILENAME = "high_scores";
        file = new File(FILENAME); // does this create a new file every time? na i doh think so tbh
        if(!file.exists()){
//            file.createNewFile();
            newFile = true;
            this.score.init();
            writeFile();
        }
        else newFile = false;
        fop = openFileOutput(FILENAME, Context.MODE_PRIVATE);
        fis = openFileInput(FILENAME);
    }

    public void writeFile(){
        System.out.println("writeFile() says: ");
        try {
            ObjectOutputStream oos = new ObjectOutputStream(fop); // object stream to write an object to the file in bytes
            oos.writeObject(this.score);
            oos.close();
            System.out.println("Finished writing person objects to file " + FILENAME);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openFile() {
        // try and open the file and get the highscore
        // this is essentially openFile() from p4
        try{
            ObjectInputStream ois = new ObjectInputStream(fis); // for reading objects from the file
            this.score = (Score) ois.readObject();
            ois.close();
            this.score.setScore(0);
            System.out.println("getHighScore() says: " + score.toString());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initLayout() {
        /*
            initializes the xml components
        */

        txtView_yourScore = findViewById(R.id.txtView_yourScore);
        txtView_yourScore.setText("Your Stats: ");
        txtView_highScore = findViewById(R.id.txtView_highScore);
        txtView_highScore.setText("Best Stats: ");

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
            writeFile();
            updateUI();
        }
        else{
            System.out.println("please attempt all questions!");
            Toast.makeText(this, "Please attempt all questions!", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUI() {
        /*
        update text views with the scores obtained from score
         */
        txtView_yourScore.setText(
                "Your stats: " + score.getScore() + " - time: " + score.getTime()
        );
        txtView_highScore.setText(
                "Best stats: " + score.getHighScore() + " - time: " + score.getBestTime()
        );
        if(newHS){
            Toast.makeText(this, "NEW HIGH SCORE!", Toast.LENGTH_SHORT).show();
        }
    }

    private void calcScore() {
        System.out.println("calcSCore() says: ");
        int score = 0;
        if(q1Selection == a1.getId()) score++;
        if(q2Selection == a2.getId()) score++;
        if(q3Selection == a3.getId()) score++;
        if(q4Selection == a4.getId()) score++;
        if(q5Selection == a5.getId()) score++;
        this.score.setScore(score);
        this.score.incrementCumulativeScore(score);
        this.score.incrementCount();
        if(this.score.getScore() >= this.score.getHighScore()){
            this.score.setHighScore(score);
            newHS = true;
        }
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
