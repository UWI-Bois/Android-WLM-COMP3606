package me.comp3606_asg2;

public class Score {
    private int score, highScore, time, bestTime, count, cumulativeScore;

    public Score(){
        this.highScore = 0;
        this.score = 0;
        this.cumulativeScore = 0;
        this.count = 0;
    }

    public int getCumulativeScore() {
        return cumulativeScore;
    }

    public void setCumulativeScore(int cumulativeScore) {
        this.cumulativeScore = cumulativeScore;
    }

    public void incrementCumulativeScore(int score){
        this.cumulativeScore += score;
    }

    public int getCount() {
        return count;
    }

    public void incrementCount(){
        this.count++;
    }

    public void setBestTime(int bestTime) {
        this.bestTime = bestTime;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getBestTime() {
        return bestTime;
    }

    public int getHighScore() {
        return highScore;
    }

    public int getScore() {
        return score;
    }

    public int getTime() {
        return time;
    }

    public void init(){
        this.highScore = 0;
        this.score = 0;
        this.cumulativeScore = 0;
        this.count = 0;
    }

    public String toString(){
        String s = "Your Stats:\n"
                + "Score: " + score
                + "\nTime: " + time
                + "Best Stats:\n"
                + "Score: " + highScore
                + "\nTime: " + bestTime;
        return  s;
    }

}
