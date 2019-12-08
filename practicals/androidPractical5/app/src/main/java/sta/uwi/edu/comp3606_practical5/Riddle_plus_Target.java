package sta.uwi.edu.comp3606_practical5;

public class Riddle_plus_Target
{
    private int riddleid;
    private String phoneNo;
    private String riddle;
    private String solution;

    public  Riddle_plus_Target(String phoneNo, String riddle, String solution, int riddleid)
    {
        this.phoneNo = phoneNo;
        this.riddle = riddle;
        this.solution = solution;
        this.riddleid = riddleid;
    }

    public int checkAnswer(String Ans)
    {
        if (solution.toLowerCase().equals(Ans.toLowerCase())) return 1;
        else if (solution.toLowerCase().contains(Ans.toLowerCase())) return 0;
        else return -1;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getRiddle() {
        return riddle;
    }

    public int getRiddleid()
    {
        return riddleid;
    }
}
