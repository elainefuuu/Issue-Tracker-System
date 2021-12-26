package issue_tracker;

import java.util.ArrayList;

public class Reaction {

    private int happy;
    private int thumbsup;
    private int angry;

    private ArrayList defaultreactionrange;


    public Reaction() {
        this.happy = happy;
        this.thumbsup = thumbsup;
        this.angry = angry;
    }



    public ArrayList getDefaultreactionrange() {
        return defaultreactionrange;
    }

    public void setDefaultreactionrange(ArrayList defaultreactionrange) {
        this.defaultreactionrange = defaultreactionrange;
    }

    public int getHappy() {
        return happy;
    }

    public void setHappy(int happy) {
        this.happy = happy;
    }

    public int getThumbsup() {
        return thumbsup;
    }

    public void setThumbsup(int thumbsup) {
        this.thumbsup = thumbsup;
    }

    public int getAngry() {
        return angry;
    }

    public void setAngry(int angry) {
        this.angry = angry;
    }
}