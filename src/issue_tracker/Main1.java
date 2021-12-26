package issue_tracker;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main1 {
    private static int IssueID;
    private static int IssuePriority;

    public static void main(String[] args) throws IOException, ParseException {

    }
}
       /* //Main m=new Main();
        //m.CreateNewIssue();

        //  Comment c=new Comment("a",1,"b","l");
        try {
            FileReader filereader = new FileReader(".\\json file\\data.json");

            JSONObject rootJSON = (JSONObject) new JSONParser().parse(filereader);
            JSONArray dataList = (JSONArray) rootJSON.get("projects");

            for (Object projectObj : dataList.toArray()) {
                // JSONObject projects = (JSONObject) projectObj;

                JSONObject project = (JSONObject) projectObj;
                JSONArray issueList = (JSONArray) project.get("issues");

                for (Object issueObj : issueList.toArray()) {
                    JSONObject iss = (JSONObject) issueObj;

                    long idd = (long) iss.get("id");
                    String title = (String) iss.get("title");
                    long priority = (long) iss.get("priority");
                    String status = (String) iss.get("status");

                    String alignment1 = "%-9s %-5d %-7s %-15s %n";
                    String alignment2 = "%-4s %-10s %-9s %-5d %-11s %-16s %n";
                    String alignment3 = "%-30s %n";
                    String alignment4 = "%-12s %-14s %-11s %-10s %n";

                    //Convert datatype to fit in Iss ArrayList
                    IssueID = (int) idd;
                    IssuePriority = (int) priority;


                    System.out.println("Issues: ");
                    System.out.println("id: " + idd);
                    System.out.println("title: " + title);
                    System.out.println("priority: " + priority);
                    System.out.println("status: " + status);

                    JSONObject issues = (JSONObject) issueObj;
                }
            }
        } catch (ParseException e) {
            //do smth
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/