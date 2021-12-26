package issue_tracker;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Comment {


    private String comment_text;
    private int comment_id;
    private int project_ID;
    private int issue_ID;
    private String comment_user;
    private String timestamp;
    private String reaction;
    // private Object[] hashmapofreaction;
    // private ArrayList<String> outputHashMap=new ArrayList<>();
    public HashMap<String,Integer> hashmap1=new HashMap<>();

    private Scanner s = new Scanner(System.in);



    //JSON and All
    public Comment(int project_ID, int issue_ID, String comment_text, int comment_id, String comment_user, String timestamp, HashMap hashmap1) {
        this.project_ID=project_ID;
        this.issue_ID= issue_ID;
        this.comment_text = comment_text;
        this.comment_id = comment_id;
        this.comment_user = comment_user;
        this.timestamp = timestamp;
        this.hashmap1=hashmap1;
        // converthashmaptoString(hashmap1);

    }

    public Comment( int project_ID,int issue_ID, int comment_id, HashMap hashmap1) {
        this.comment_id = comment_id;
        this.project_ID = project_ID;
        this.issue_ID = issue_ID;
        this.hashmap1 = hashmap1;
    }

    public Comment(int project_ID, int issue_ID, String comment_text, int comment_id, String comment_user) {
        this.comment_text = comment_text;
        this.comment_id = comment_id;
        this.project_ID = project_ID;
        this.issue_ID = issue_ID;
        this.comment_user = comment_user;
        createHashMap();
        create_comment_timestamp();

    }

    private void createHashMap(){
        HashMap<String, Integer> hashMap=new HashMap<>();
        hashMap.put("happy", 0);
        hashMap.put("angry", 0);
        hashMap.put("thumbs up", 0);

    }

    public void create_comment_timestamp(){
        LocalDateTime now= LocalDateTime.now();
        DateTimeFormatter dtf= DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        timestamp= dtf.format(now);
    }

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public String getComment_user() {
        return comment_user;
    }

    public void setComment_user(String comment_user) {
        this.comment_user = comment_user;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getReaction() {
        return reaction;
    }

    public void setReaction(String reaction) {
        this.reaction = reaction;
    }

    public HashMap<String, Integer> getHashmap1() {
        return hashmap1;
    }

    public void setHashmap1(HashMap<String, Integer> hashmap1) {
        this.hashmap1 = hashmap1;
    }

    public int getProject_ID() {
        return project_ID;
    }

    public void setProject_ID(int project_ID) {
        this.project_ID = project_ID;
    }

    public int getIssue_ID() {
        return issue_ID;
    }

    public void setIssue_ID(int issue_ID) {
        this.issue_ID = issue_ID;
    }


    @Override
    public String toString() {

        return "Comment{" +
                "comment_text='" + comment_text + '\'' +
                ", comment_id=" + comment_id +
                ", comment_user='" + comment_user + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", reaction='" +  '\'' +
                hashmap1+
                '}';
    }

}