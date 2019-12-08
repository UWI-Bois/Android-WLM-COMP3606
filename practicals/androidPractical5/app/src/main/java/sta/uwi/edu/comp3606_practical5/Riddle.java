package sta.uwi.edu.comp3606_practical5;

import androidx.annotation.NonNull;

public class Riddle
{
    private static int Objounter = 0;
    private int id = 0;
    private int solvedCount = 0;
    private String riddle, solution;
    public Riddle(String riddle, String solution)
    {
        id = Objounter++;
        this.riddle = riddle;
        this.solution = solution;
    }

    public int getId() {
        return id;
    }


    public int checkAnswer(String Ans)
    {
        if (solution.toLowerCase().equals(Ans.toLowerCase())) return 1;
        else if (solution.toLowerCase().contains(Ans.toLowerCase())) return 0;
        else return -1;
    }


    public String getRiddle() {
        return riddle;
    }

    public int getSolvedCount() {
        return solvedCount;
    }

    public void updateSolvedCount()
    {
       solvedCount++;
    }
}
