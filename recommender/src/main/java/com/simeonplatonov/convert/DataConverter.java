/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simeonplatonov.convert;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Simeon
 */
public class DataConverter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
       JSONParser parser = new JSONParser();
       FileReader reader = new FileReader("data/beer.json");
       JSONArray beers = (JSONArray) parser.parse(reader);
       ArrayList<String> categories = new ArrayList<String>();
       beers.forEach( beer->{
           JSONObject beerItem = (JSONObject) beer;
           String[] flavors = beerItem.get("flavors").toString().split(",");
           for(String flavor:flavors){
               String charToDel = "[] ,-";
               String pat = "[" + Pattern.quote(charToDel) + "]";
               flavor = flavor.replaceAll(pat, "");
   
               if(!categories.contains(flavor.toLowerCase())){
                   categories.add(flavor.toLowerCase());
                   System.out.println(flavor.toLowerCase());
               }

           }
       });
       
       BufferedWriter writer = new BufferedWriter(new FileWriter("data/beers.csv"));
       beers.forEach( beer->{
           JSONObject beerItem = (JSONObject) beer;
           String id = beerItem.get("id").toString();
           String[] flavors = beerItem.get("flavors").toString().split(",");
           int[] category = new int[categories.size()];
           for(String flavor:flavors){
               String charToDel = "[] ,-";
               String pat = "[" + Pattern.quote(charToDel) + "]";
               flavor = flavor.replaceAll(pat, "");
               
               int index  = categories.indexOf(flavor.toLowerCase());
               category[index] = 1;
           }
           String dataToWrite = id;
           
           for(int index:category){
               dataToWrite+=","+index;
           }
           
           dataToWrite+="\n";
           try {
               writer.write(dataToWrite);
           } catch (IOException ex) {
               Logger.getLogger(DataConverter.class.getName()).log(Level.SEVERE, null, ex);
           }
       });       
       writer.close();
       reader.close();
       
    }
    
}
