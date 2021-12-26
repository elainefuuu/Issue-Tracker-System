package bug.life;

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
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;

public class BugLife {
    
    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String JSON_file = "data.json";
        JSONParser parser = new JSONParser();
        Scanner scan = new Scanner(System.in);
        //int select;
        
        //Creating a JSONParser object
        //JSONParser jsonParser = new JSONParser();
        
        try{
            FileReader reader = new FileReader(JSON_file);
            
            //Java object
            Object obj = parser.parse(reader);

            //JSON object
            JSONObject jsonObject = (JSONObject) obj;
            
            JSONArray array = (JSONArray) jsonObject.get("projects");
            
            //int choice = scan.nextInt();
            
            for (int i = 0; i < array.size(); i++) {
                JSONObject project = (JSONObject) array.get(i);
                JSONArray array1 = (JSONArray) jsonObject.get("issues");

                System.out.print("Enter issue ID to check the issue: ");
                //selected issue ID input
                int select = scan.nextInt();
                
                /*LOOP TO MAKE SURE THE USER DOESN'T ENTER THE WRONG INPUT*/
                while (select > array.size() || select == 0) {
                    System.out.println("Wrong input.");
                    System.out.print("Enter selected issue ID to check issue: ");
                    select = scan.nextInt();
                }
                
                //issuePage(select, array);

            
               // issuePage(issues);
                //Issue Page
                //issuePage(select,array1);
                //need select - 1?
                
                for (int j = 0; j < array.size(); j++) {
                    JSONObject issues = (JSONObject) array.get(i);
                    //issuePage();
                    
                    /*
                    JSONArray array2 = (JSONArray) jsonObject.get("tag");
                    
                    for (int k = 0; k < array.size(); k++) {
                        JSONObject tag = (JSONObject) array.get(i);
                        issuePage(issue);
                    }
                    JSONArray array3 = (JSONArray) jsonObject.get("comments");
                    
                    for (int l = 0; l < array.size(); l++) {
                        JSONObject comment = (JSONObject) array.get(i);
                        JSONArray array4 = (JSONArray) jsonObject.get("react");
                        
                        for (int m = 0; m < array.size(); m++){
                            JSONObject react = (JSONObject) array.get(i);
                            //issueComment(comment);
                        } 
                        //issueComment(comment);
                    } 
                    */
                }
            }
                
            //Issue Comment
            //issueComment(array3);

            //Issue Lifecycle
            

        } catch (Exception e) {
            e.printStackTrace();    //shows neither the line number where the error occurred nor the functions that were executed
        }
    }
    
    public static void issuePage(JSONObject object){
        String alignment1 = "%-9s %-5d %-7s %-15s %n";
        String alignment2 = "%-4s %-10s %-9s %-5d %-11s %-16s %n";
        String alignment3 = "%-30s %n";
        String alignment4 = "%-12s %-14s %-11s %-10s %n";
        long id = (long) object.get("id");
        String title = (String) object.get("title");
        String status = (String) object.get("status");
        int priority = (int) object.get("priority");
        String assignee = (String) object.get("assignee");
        String createdBy = (String) object.get("createdBy");
        String descriptionText = (String) object.get("descriptionText");
        long timestamp1 = (long) object.get("timestamp");
        // convert seconds to milliseconds
        Date dt = new Date(timestamp1 * 1000);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        //timezone reference 
        sdf1.setTimeZone(java.util.TimeZone.getTimeZone("GMT"));
        String time1 = sdf1.format(dt);

        JSONArray Tag = (JSONArray) object.get("tag");
        for (int i = 0; i < Tag.size(); i++) {

            String tag = (String) Tag.get(i);

            System.out.format(alignment1, "Issue ID:", id, "Status:", status);
            System.out.format(alignment2, "Tag:", tag, "Priority:", priority, "Created On:", time1);
            System.out.format(alignment3, title);
            System.out.format(alignment4, "Assigned to:", assignee, "Created By:", createdBy);            
            System.out.println("");
            System.out.println("Issue Description");
            System.out.println("-----------------");
            System.out.format(descriptionText);
            System.out.println("");
        }
        
        String alignment5 = "%1s%-6d %-11s %-19s %-3s %-10s %n";
        String alignment6 = "%-2s %-60s %n";
        
        JSONArray Comment = (JSONArray) object.get("comment");
        
        for (int j = 0; j < Comment.size(); j++) {
            long comment_id = (long) object.get("comment_id");
            String text = (String) object.get("text");
            String user = (String) object.get("user");
            long timestamp2 = (long) object.get("timestamp");
            // convert seconds to milliseconds
            Date dt2 = new Date(timestamp2 * 1000);
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            //timezone reference 
            sdf2.setTimeZone(java.util.TimeZone.getTimeZone("GMT"));
            String time2 = sdf2.format(dt);
        
            System.out.println("Comments");
            System.out.println("---------");
            System.out.format(alignment5, "#", comment_id, "Created on:", time2, "By:", user);
            System.out.println(text);
                
            JSONArray React = (JSONArray) object.get("react");
            for (int k = 0; k < React.size(); k++) {

                String reaction = (String) object.get("reaction");
                long count = (long) object.get("count");
                
                if(count > 0){
                    System.out.format(alignment6, "$$", count, "people react with", reaction);
                }else{
                    System.out.print("");
                }
            }
            System.out.println("");
        }
        System.out.println("Enter /n'r' to react /nor'c' to comment /nor 'help' for more commands");
    }
     
        
    public static void issueLifeCycle(int num, JSONArray array){
        Scanner scan = new Scanner(System.in);
        int select = scan.nextInt();

        
        JSONObject project = (JSONObject) array.get(num - 1);
        JSONArray issue = (JSONArray) project.get("issues");
        
        for (int i = 0; i < issue.size(); i++) {
            //user or asignee input
            int issueID = scan.nextInt(); 
            issuePage(project);
            
            System.out.println("Is the issue resolved? (yes/no)");
            String status1 = scan.nextLine();
            
            //Status change to Resolved
            if(status1.equalsIgnoreCase("yes")){
                JSONObject js = new JSONObject(); 
                js.remove("status"); 
                js.put("status", "Resolved");             
            }
            
            if(status1.equalsIgnoreCase("no")){
                System.out.println("Close the issue? (yes/no");
                String status2 = scan.nextLine();

                //Status change to Closed
                if(status2.equalsIgnoreCase("yes")){
                    JSONObject js = new JSONObject(); 
                    js.remove("status"); 
                    js.put("status", "Closed"); 
                }
            }
            /*
            //Status change to Open             //when issue created //ArrayLsit
            ArrayList<JSONObject> list = new ArrayList<>();
                
                JSONObject js = new JSONObject(); 
                js.remove("status"); 
                js.put("status", "Open"); 
            
            //Status change to In Progress
            
                JSONObject js = new JSONObject(); 
                js.remove("status"); 
                js.put("status", "In Progress"); 
        }
            
            //Status change to Open
            if(issueID - 1 == JSONObject.get("issues")){            //when issue created //ArrayList
                JSONObject issues = (JSONObject) issue.get(i - 1);
            
                JSONObject js = new JSONObject(); 
                js.remove("status"); 
                js.put("status", "Open"); 
            }
            
            
            //Status change to In Progress
            while(iterator.hasNext()){                 //comment array
                nameRead = iterator.next().toString();
                JSONObject js = new JSONObject(); 
                js.remove("status"); 
                js.put("status", "In Progress"); 
            }
            
            if (sort1 == 1) {
                ArrayList<JSONObject> list = new ArrayList<>();
                ArrayList<JSONObject> sortedJsonArray = new ArrayList<>();

                for (int i = 0; i < issue.size(); i++) {
                    list.add((JSONObject) issue.get(i));
                }

                Collections.sort(list, new Comparator<JSONObject>() {
                    //sort by priority
                    String KEY_NAME = "priority";

                    @Override
                    public int compare(JSONObject a, JSONObject b) {
                        String valA = new String();
                        String valB = new String();

                        try {
                            valA = String.valueOf(a.get(KEY_NAME));
                            valB = String.valueOf(b.get(KEY_NAME));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        return -valA.compareTo(valB);   // higher priority to lower priority
                    }
                });

                for (int i = 0; i < issue.size(); i++) {
                    sortedJsonArray.add(list.get(i));
                }

                for (JSONObject object : sortedJsonArray) {
                    issueDashboard(object);
                }
                System.out.format("+------+------------------------------------+---------------+-------------------+"
                        + "----------+-------------------+----------+-----------+%n");
            }
            */
    }
    }
    
/*
    public static void issuePage(int num, JSONArray array){
        JSONObject project = (JSONObject) array.get(num - 1);
        JSONArray issue = (JSONArray) project.get("issues");
        
        for (int i = 0; i < issue.size(); i++) {
            JSONObject issues = (JSONObject) issue.get(i);
            issueDashboard(issues); 
        }
        
        String search = sc.nextLine();

        //search the issue by description text
        if (search.equalsIgnoreCase("s")) {
            System.out.println("");
            System.out.println("SEARCHING");
            System.out.print("Search the issue by issue description: ");
            String descriptionText = sc.nextLine();

            issueBoard();

            for (int j = 0; j < issue.size(); j++) {
                JSONObject issues = (JSONObject) issue.get(j);
                String description = (String) issues.get("descriptionText");
                if (description.toLowerCase().contains(descriptionText.toLowerCase())) {
                    issuePage(issues);
                }
            }
            System.out.format("+------+------------------------------------+---------------+-------------------+"
                    + "----------+-------------------+----------+-----------+%n");
        }
        
        //search the issue by comment
        if (search.equalsIgnoreCase("s")) {
            System.out.println("");
            System.out.println("SEARCHING");
            System.out.print("Search the issue by comment: ");
            String issueComment = sc.nextLine();

            issueBoard();

            for (int k = 0; k < issue.size(); k++) {
                JSONObject issues = (JSONObject) issue.get(k);
                String comment = (String) issues.get("comments");
                if (comment.toLowerCase().contains(issueComment.toLowerCase())) {
                    issueDashboard(issues);
                }
            }
            System.out.format("+------+------------------------------------+---------------+-------------------+"
                    + "----------+-------------------+----------+-----------+%n");
        }
    }*/
    
//    public static void projectDashboard(JSONObject object) {
//        //String allignment = "| %4d | %-20s | %8s |%n";
//        long project_ID = (long) object.get("id");
//        //String project_name = (String) object.get("name");
//        //JSONArray array1 = (JSONArray) object.get("issues");
//
//        for (int j = 0; j < array1.size(); j++) {
//            JSONObject issues = (JSONObject) array1.get(j);
//        }
//
//        //System.out.format(allignment, project_ID, project_name, array1.size());
//    }
}

