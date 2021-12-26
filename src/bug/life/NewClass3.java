
package bug.life;

import static bug.life.BugLife.issuePage;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;

public class NewClass3 {
        //public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException {
        String JSON_file = "data1.json";
        //JSONParser parser = new JSONParser();
        //Scanner scan = new Scanner(System.in);
        FileReader reader = new FileReader(JSON_file);
        
        try {
            JSONObject rootJSON = (JSONObject) new JSONParser().parse(reader);
            JSONArray dataList = (JSONArray) rootJSON.get("projects");

            for(Object projectObj: dataList.toArray()){
                JSONObject projects = (JSONObject) projectObj;

                JSONObject project = (JSONObject)projectObj;
                JSONArray issueList = (JSONArray) project.get("issues");

                for(Object issueObj: issueList.toArray()){
                    JSONObject iss = (JSONObject) issueObj;

                    long idd = (long)iss.get("id");
                    String title = (String)iss.get("title");
                    long priority = (long)iss.get("priority");
                    String status = (String)iss.get("status");
                    
                    String alignment1 = "%-9s %-5d %-7s %-15s %n";
                    String alignment2 = "%-4s %-10s %-9s %-5d %-11s %-16s %n";
                    String alignment3 = "%-30s %n";
                    String alignment4 = "%-12s %-14s %-11s %-10s %n";
                    
                    /*System.out.println("Issues: ");
                    System.out.println("id: " + idd);
                    System.out.println("title: " + title);
                    System.out.println("priority: " + priority);
                    System.out.println("status: " + status);*/

                    JSONObject issues = (JSONObject)issueObj;
                    JSONArray tagList = (JSONArray) issues.get("tag");

                    /*for (Object tagObject : tagList.toArray()){
                        System.out.println("tag: " + tagObject);
                    }*/

                    String description = (String)iss.get("descriptionText");
                    String createdBy = (String)iss.get("createdBy");
                    String assignee = (String)iss.get("assignee");
                    long timestamp = (long)iss.get("timestamp");

                    /*System.out.println("description text: " + description);
                    System.out.println("created by: " + createdBy);
                    System.out.println("assignee: " + assignee);
                    System.out.println("timestamp: " + timestamp);*/
                    
                    System.out.printf(alignment1, "Issue ID:", idd, "Status:", status);
                    System.out.printf(alignment2, "Tag:", tagList, "Priority:", priority, "Created On:", timestamp);
                    System.out.printf(alignment3, title);
                    System.out.printf(alignment4, "Assigned to:", assignee, "Created By:", createdBy);            
                    System.out.println("");
                    System.out.println("Issue Description");
                    System.out.println("-----------------");
                    System.out.printf(description);
                    System.out.println("");
                    System.out.println("");

                    System.out.println("Comments");
                    System.out.println("---------");
                        
                    JSONObject comments = (JSONObject)issueObj;
                    JSONArray commentArray = (JSONArray) comments.get("comments");
                    for (Object commentObject : commentArray.toArray()){
                        JSONObject commentObj = (JSONObject)commentObject;

                        long commentId = (long)commentObj.get("comment_id");
                        String text = (String)commentObj.get("text");
                        long timestampp = (long)commentObj.get("timestamp");
                        String user = (String)commentObj.get("user");

                        String alignment5 = "%1s%-6d %-11s %-19s %-3s %-10s %n";
                        String alignment6 = "%-2s %-60s %n";

                        /*System.out.println("comments: ");
                        System.out.println("comment id: " + commentId);
                        System.out.println("text: " + text);*/

                        
                        System.out.printf(alignment5, "#", commentId, "Created on:", timestampp, "By:", user);
                        System.out.println(text);

                        JSONObject reactObject = (JSONObject)commentObject;
                        JSONArray reactArray = (JSONArray)reactObject.get("react");
                        for (Object reactObj : reactArray.toArray()){
                            JSONObject reactionObj = (JSONObject)reactObj;

                            String reaction = (String)reactionObj.get("reaction");
                            long count = (long)reactionObj.get("count");

                            System.out.printf(alignment6, "$$", count, "people react with", reaction);

                            /*System.out.println("react:");
                            System.out.println("reaction: " + reaction);
                            System.out.println("count: " + count);*/
                        }
                        System.out.println("");

                        /*long timestampp = (long)commentObj.get("timestamp");
                        String user = (String)commentObj.get("user");

                        System.out.println("timestamp: " + timestampp);
                        System.out.println("user: " + user);*/

                    }
                }
            }
            //Date dt = new Date (timestamp * 1000);
            //SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");

            /*JSONArray userList = (JSONArray) rootJSON.get("users");
            for(Object userObject: userList.toArray()) {
                JSONObject userPro = (JSONObject) userObject;

                long userid = (long) userPro.get("userid");
                String username = (String)userPro.get("username");
                String password = (String)userPro.get("password");

                System.out.println();
                System.out.println("user ID: " + userid);
                System.out.println("username: " + username);
                System.out.println("password: " + password);
            }*/

            
        } catch (ParseException e) {
            //do smth
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
      
}
}

