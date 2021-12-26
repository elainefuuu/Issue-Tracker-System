/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

public class NewClass1 {
    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String JSON_file = "data.json";
        JSONParser parser = new JSONParser();
        Scanner scan = new Scanner(System.in);
        
        try{
            FileReader reader = new FileReader(JSON_file);
            
            //Java object
            Object obj = parser.parse(reader);

            //JSON object
            JSONObject jsonObject = (JSONObject) obj;
            
            JSONArray array = (JSONArray) jsonObject.get("projects");
            
            for (int i = 0; i < array.size(); i++) {
                int choice = scan.nextInt();
                
                JSONObject project = (JSONObject) array.get(i - 1);
                JSONArray array1 = (JSONArray) jsonObject.get("issues");

                //selected issue ID input
                int select = scan.nextInt();
                
                /*LOOP TO MAKE SURE THE USER DOESN'T ENTER THE WRONG INPUT*/
                while (select > array.size() || select == 0) {
                    System.out.println("Wrong input.");
                    System.out.print("Enter selected issue ID to check issue: ");
                    select = scan.nextInt();
                }
            
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
}
